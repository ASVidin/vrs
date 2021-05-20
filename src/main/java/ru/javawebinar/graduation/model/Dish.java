package ru.javawebinar.graduation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dish", uniqueConstraints = {@UniqueConstraint(columnNames = {"menu_id", "name"}, name = "dishes_unique_menu_name_idx")})
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true, exclude = "dailyMenu")
public class Dish extends AbstractNamedEntity {
    @Column(name = "price", nullable = false)
    @Range(min = 1, max = 500000)
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull
    private DailyMenu dailyMenu;

    public Dish(Dish dish) {
        this(dish.getName(), dish.getPrice());
    }

    public Dish(Integer id, @Size(max = 128) String name, @Range(min = 1, max = 500000) int price) {
        super(id, name);
        this.price = price;
    }

    public Dish(@NotBlank @Size(min = 2, max = 100) String name, @Range(min = 1, max = 500000) int price) {
        super(name);
        this.price = price;
    }
}
