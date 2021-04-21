package ru.javawebinar.graduation.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Vote;

@Repository
public class DataJpaVoteRepository {
    RestaurantRepository restaurantRepository;
    UserRepository userRepository;
    VoteRepository voteRepository;

    public DataJpaVoteRepository(RestaurantRepository restaurantRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    @Transactional
    public Vote save(Vote vote, int menuId, int userId) {
        if (!vote.isNew() && voteRepository.getWithUser(vote.getId(), userId) == null) {
            return null;
        }
        vote.setRestaurant(restaurantRepository.getOne(menuId));
        vote.setFromUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }
}
