package ru.javawebinar.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "dish")
@ToString(callSuper = true)
public class Dish extends AbstractEntity {

    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 5000)
    private int price;

    @Column(name = "input_date", nullable = false)
    @NotNull
    private LocalDate inputDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Restaurant restaurant;

}
