package ru.javawebinar.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"input_date", "restaurant_id"}, name = "menu_unique_date_restaurant_idx")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = "restaurant")
public class DailyMenu extends AbstractEntity {
    @Column(name = "input_date", nullable = false)
    @NotNull
    private LocalDate inputDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull
    private Restaurant restaurant;

    public DailyMenu(Integer id, @NotNull LocalDate inputDate) {
        super(id);
        this.inputDate = inputDate;
    }

    public DailyMenu(@NotNull LocalDate inputDate) {
        this.inputDate = inputDate;
    }
}