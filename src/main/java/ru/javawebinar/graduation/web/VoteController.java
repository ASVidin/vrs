package ru.javawebinar.graduation.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.graduation.AuthorizedUser;
import ru.javawebinar.graduation.model.DailyMenu;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.repository.dataJpaRepository.DataJpaVoteRepository;
import ru.javawebinar.graduation.repository.dataJpaRepository.UserRepository;
import ru.javawebinar.graduation.util.TimeUtil;
import ru.javawebinar.graduation.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {
    static final String REST_URL = "/rest/vote";

    private final DataJpaVoteRepository voteRepository;
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Vote> get(@PathVariable int id) {
        // change user
        int userId = AuthorizedUser.getTestId();
//        int userId = AuthorizedUser.authUserId();
        log.info("vote {} from user {}", id, userId);
        Vote vote =  checkNotFoundWithId(voteRepository.get(id, userId), id);
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getAll() {
        // change current date
        LocalDate currentDate = LocalDate.of(2021, Month.JANUARY, 27);
//        LocalDate currentDate = TimeUtil.convertToLocalDate(LocalDateTime.now());
        log.info("votes for {}", currentDate);
        List<Vote> votes = voteRepository.getAllByDate(currentDate);
        return !votes.isEmpty() ? new ResponseEntity<>(votes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createOrUpdate(@RequestBody DailyMenu dailyMenu) {
        // change user
        int userId = AuthorizedUser.getTestId();
//        int userId = AuthorizedUser.authUserId();
        LocalDateTime currentDateTime = LocalDateTime.now();

        Vote vote = voteRepository.getByDateForUser(TimeUtil.convertToLocalDate(currentDateTime), userId)
                .map(v -> {
                    String operationType = "Return";
                    if (TimeUtil.isBeforeDeadLine(currentDateTime)) {
                        v.setRestaurant(dailyMenu.getRestaurant());
                        operationType = "Change";
                    }
                    log.info("{} current vote for {} from user {} for {}", operationType, v.getRestaurant(), userId, dailyMenu.getInputDate());
                    return v;
                })
                .orElseGet(() -> {
                    log.info("Create vote for {} from user {} for {}", dailyMenu.getRestaurant(), userId, dailyMenu.getInputDate());
                    Vote createdVote = new Vote(dailyMenu.getInputDate(), userRepository.getOne(userId), dailyMenu.getRestaurant());
                    ValidationUtil.checkNew(createdVote);
                    return createdVote;
                });
        voteRepository.save(vote, dailyMenu.id(), userId);

        return new ResponseEntity<>(vote, HttpStatus.OK);
    }
}
