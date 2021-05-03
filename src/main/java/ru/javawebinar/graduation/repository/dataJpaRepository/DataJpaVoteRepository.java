package ru.javawebinar.graduation.repository.dataJpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Vote;
import ru.javawebinar.graduation.repository.VoteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaVoteRepository implements VoteRepository {
    RestaurantRepository restaurantRepository;
    UserRepository userRepository;
    CrudVoteRepository voteRepository;

    public DataJpaVoteRepository(RestaurantRepository restaurantRepository, UserRepository userRepository, CrudVoteRepository voteRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public Vote get(int id, int userId) {
        return voteRepository.findById(id)
                .filter(v -> v.getFromUser().id() == userId)
                .orElse(null);
    }

    @Transactional
    @Override
    public Vote save(Vote vote, int menuId, int userId) {
        if (!vote.isNew() && voteRepository.getWithUser(vote.id(), userId) == null) {
            return null;
        }
        vote.setRestaurant(restaurantRepository.getOne(menuId));
        vote.setFromUser(userRepository.getOne(userId));
        return voteRepository.save(vote);
    }

    @Override
    public int delete(int id) {
        return voteRepository.delete(id);
    }

    @Override
    public List<Vote> getAllByDate(LocalDate voteDate) {
        return voteRepository.getAllByDate(voteDate);
    }

    @Override
    public Optional<Vote> getByDateForUser(LocalDate voteDate, int userId) {
        return voteRepository.getByDateForUser(voteDate, userId);
    }

    @Override
    public List<Vote> getAllByRestaurant(int restaurantId) {
        return voteRepository.getAllByRestaurant(restaurantId);
    }

    @Override
    public Vote getWithUser(int id, int userId) {
        return voteRepository.getWithUser(id, userId);
    }
}
