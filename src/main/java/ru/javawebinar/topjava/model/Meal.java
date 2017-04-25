package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "delete from  Meal m where m.id=:id and m.user.id=:userId"),
        @NamedQuery(name = Meal.GET, query = "select m from Meal m where  m.id=:id and  m.user.id=:userId " +
                "order by m.dateTime desc"),
        @NamedQuery(name = Meal.GET_ALL, query = "select m from Meal m where m.user.id=:userId " +
                "order by m.dateTime desc"),
        @NamedQuery(name = Meal.GET_BETWEEN, query = "select m from Meal m " +
                "where m.user.id=:userId and m.dateTime BETWEEN :startDate and :endDate " +
                "order by m.dateTime desc")})

@Entity
@Table(name = "meals")

public class Meal extends BaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String GET = "Meal.get";
    public static final String GET_ALL = "Meal.getAll";
    public static final String GET_BETWEEN = "Meal.getBetween";

    @NotEmpty
    @Column(name = "dateTime", nullable = false, columnDefinition = "timestamp default now()")
    private LocalDateTime dateTime;

    @NotBlank
    @Column(name = "description", nullable = false)
    private String description;

    @NotEmpty
    @Column(name = "calories", nullable = false, columnDefinition = "int default 2000")
    private int calories;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
