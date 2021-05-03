package ru.javawebinar.graduation.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.graduation.AuthorizedUser;
import ru.javawebinar.graduation.model.DailyMenu;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.repository.DataJpaVoteRepository;
import ru.javawebinar.graduation.repository.UserRepository;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VoteController {
    static final String REST_URL = "/rest/vote";

    private final DataJpaVoteRepository voteRepository;
    private final UserRepository userRepository;

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@RequestBody DailyMenu dailyMenu) {
        //// change user
        int userId = AuthorizedUser.getTestId();
        Vote vote = new Vote(dailyMenu.getInputDate(), userRepository.getOne(userId), dailyMenu.getRestaurant());
        voteRepository.save(vote, dailyMenu.getId(), userId);

        return new ResponseEntity<>(vote, HttpStatus.OK);
    }



}
