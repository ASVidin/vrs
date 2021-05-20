package ru.javawebinar.graduation.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Restaurant;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.repository.VoteRepository;
import ru.javawebinar.graduation.repository.RestaurantRepository;
import ru.javawebinar.graduation.repository.UserRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {
    RestaurantRepository restaurantRepository;
    UserRepository userRepository;
    VoteRepository voteRepository;

    public VoteServiceImpl(RestaurantRepository restaurantRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public Vote get(int id, int userId) {
        return voteRepository.findById(id)
                .filter(v -> v.getUser().id() == userId)
                .orElse(null);
    }

    @Transactional
    @Override
    public Vote save(Vote vote, Restaurant restaurant, int userId) {
        if (!vote.isNew() && voteRepository.getWithUser(vote.id(), userId) == null) {
            return null;
        }
        vote.setRestaurant(restaurant);
        vote.setUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    @Override
    public int delete(int id) {
        return voteRepository.delete(id);
    }

    @Override
    public Optional<Vote> getByDateForUser(LocalDate voteDate, int userId) {
        return voteRepository.getByDateForUser(voteDate, userId);
    }
}
