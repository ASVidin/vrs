package ru.javawebinar.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT v FROM Vote v WHERE v.voteDate=:date ORDER BY v.toRestaurant.id")
    List<Vote> getAllByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate voteDate);

    @Query("SELECT v FROM Vote v WHERE v.fromUser.id=:userId and v.voteDate=:date ORDER BY v.voteDate DESC")
    List<Vote> getByDateForUser(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate voteDate, @Param("userId") int userId);

    @Query("SELECT v FROM Vote v WHERE v.toRestaurant.id=:restaurantId ORDER BY v.voteDate DESC")
    List<Vote> getAllByRestaurant(@Param("restaurantId") int restaurantId);
}
