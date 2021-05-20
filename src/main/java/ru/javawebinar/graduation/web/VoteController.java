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
import ru.javawebinar.graduation.service.VoteServiceImpl;
import ru.javawebinar.graduation.repository.UserRepository;
import ru.javawebinar.graduation.util.IllegalRequestDataException;
import ru.javawebinar.graduation.util.TimeUtil;
import ru.javawebinar.graduation.util.ValidationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static ru.javawebinar.graduation.util.ValidationUtil.checkNotFoundWithId;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {
    static final String REST_URL = "/rest/votes";

    private final VoteServiceImpl voteService;
    private final UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Vote> get(@PathVariable int id) {
        int userId = AuthorizedUser.authUserId();
        log.info("vote {} from user {}", id, userId);
        Vote vote =  checkNotFoundWithId(voteService.get(id, userId), id);
        return new ResponseEntity<>(vote, HttpStatus.OK);
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createOrUpdate(@PathVariable("id") Restaurant restaurant) {
        int userId = AuthorizedUser.authUserId();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate currentDate = TimeUtil.convertToLocalDate(currentDateTime);

        Vote vote = voteService.getByDateForUser(currentDate, userId)
                .map(v -> {
                    if (TimeUtil.isBeforeDeadLine(currentDateTime)) {
                        ValidationUtil.assureIdConsistent(v, v.id());
                        v.setRestaurant(restaurant);
                    } else {
                        throw new IllegalRequestDataException(currentDate + " must be one per day");
                    }
                    log.info("Change current vote for {} from user {} for {}", v.getRestaurant(), userId, currentDate);
                    return v;
                })
                .orElseGet(() -> {
                    log.info("Create vote from user {} for {}", userId, currentDate);
                    Vote createdVote = new Vote(currentDate, userRepository.getOne(userId), restaurant);
                    ValidationUtil.checkNew(createdVote);
                    return createdVote;
                });
        voteService.save(vote, restaurant, userId);

        return new ResponseEntity<>(vote, HttpStatus.OK);
    }
}
