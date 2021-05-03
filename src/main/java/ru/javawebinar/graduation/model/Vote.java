package ru.javawebinar.graduation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"voting_date", "user_id"}, name = "votes_unique_date_user_idx")})
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(name = "voting_date", nullable = false)
    @NotNull
    private LocalDate voteDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User fromUser;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Vote(Integer id, @NotNull LocalDate voteDate) {
        this.id = id;
        this.voteDate = voteDate;
    }

    public Vote(@NotNull LocalDate voteDate, @NotNull User fromUser, @NotNull Restaurant restaurant) {
        this.voteDate = voteDate;
        this.fromUser = fromUser;
        this.restaurant = restaurant;
    }

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

    public int id() {
        Assert.notNull(id, "Entity must have id");
        return id;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", voteDate=" + voteDate +
                ", fromUser=" + fromUser.getId() +
                ", toRestaurant=" + restaurant.getId() +
                '}';
    }
}
