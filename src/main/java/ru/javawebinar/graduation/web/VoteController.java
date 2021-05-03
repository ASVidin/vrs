package ru.javawebinar.graduation.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.graduation.AuthorizedUser;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.repository.dataJpaRepository.DataJpaVoteRepository;
import ru.javawebinar.graduation.repository.dataJpaRepository.RestaurantRepository;
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
    private final RestaurantRepository restaurantRepository;

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
    public ResponseEntity<Vote> createOrUpdate(@RequestBody Restaurant restaurant) {
        // change user
        int userId = AuthorizedUser.getTestId();
//        int userId = AuthorizedUser.authUserId();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = TimeUtil.convertToLocalDate(currentDateTime);

        Vote vote = voteRepository.getByDateForUser(currentDate, userId)
                .map(v -> {
                    String operationType = "Return";
                    if (TimeUtil.isBeforeDeadLine(currentDateTime)) {
                        v.setRestaurant(restaurant);
                        operationType = "Change";
                    }
                    log.info("{} current vote for {} from user {} for {}", operationType, v.getRestaurant(), userId, currentDate);
                    return v;
                })
                .orElseGet(() -> {
                    log.info("Create vote from user {} for {}", userId, currentDate);
                    Vote createdVote = new Vote(currentDate, userRepository.getOne(userId), restaurant);
                    ValidationUtil.checkNew(createdVote);
                    return createdVote;
                });
        voteRepository.save(vote, restaurant, userId);

        return new ResponseEntity<>(vote, HttpStatus.OK);
    }
}
