package ru.javawebinar.graduation.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "restaurant")
@ToString(callSuper = true)
public class Restaurant extends AbstractEntity {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @JsonManagedReference
    private List<Dish> menu;

}
