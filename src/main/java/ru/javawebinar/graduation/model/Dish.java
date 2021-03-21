package ru.javawebinar.graduation.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dish")
@ToString(callSuper = true)
public class Dish extends AbstractEntity {
    private int price;

}
