package ru.javawebinar.graduation.to;

public class RestaurantTo {
    private final Integer id;
    private final String name;
    private final int vote;

    public RestaurantTo(Integer id, String name, int vote) {
        this.id = id;
        this.name = name;
        this.vote = vote;
    }
}
