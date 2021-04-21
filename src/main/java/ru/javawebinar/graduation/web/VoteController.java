package ru.javawebinar.graduation.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.repository.UserRepository;
import ru.javawebinar.graduation.repository.VoteRepository;

import javax.validation.Valid;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    static final String REST_URL = "/rest/vote";

    private VoteRepository voteRepository;
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Vote> createOrUpdate(@Valid Vote vote, Restaurant restaurant) {
        return null;
    }



}
