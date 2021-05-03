package ru.javawebinar.graduation.repository;

import ru.javawebinar.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VoteRepository {

    Vote get(int id, int userId);

    Vote save(Vote vote, int menuId, int userId);

    int delete(int id);

    List<Vote> getAllByDate(LocalDate voteDate);

    Optional<Vote> getByDateForUser(LocalDate voteDate, int userId);

    List<Vote> getAllByRestaurant(int restaurantId);

    default Vote getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
