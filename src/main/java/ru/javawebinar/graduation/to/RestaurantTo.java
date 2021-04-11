package ru.javawebinar.graduation.to;

public class RestaurantTo {
    private final Integer id;
    private final String name;
    private final int countVotes;

    public RestaurantTo(Integer id, String name, int votes) {
        this.id = id;
        this.name = name;
        this.countVotes = votes;
    }
}
