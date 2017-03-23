package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        test();
    }

    private static void test() {
        List<UserMeal> mealList1 = new ArrayList<>();
        List<UserMeal> mealList2 = new ArrayList<>();

        System.out.println("100K List filling");
        for (int i = 0; i < 100_000; i++) {
            mealList1.add(new UserMeal(LocalDateTime.ofEpochSecond(i * 60 * 60, 0, ZoneOffset.UTC), "Завтрак", 500));
        }

        System.out.println("10M List filling");
        for (int i = 0; i < 10_000_000; i++) {
            mealList2.add(new UserMeal(LocalDateTime.ofEpochSecond(1000000000 + i * 60 * 60, 0, ZoneOffset.UTC), "Завтрак", 500));
        }
        System.out.println("Preheating");
        durationOfTest(mealList1);//preheating

        long duration = 0;
        for (int i = 0; i < 10; i++) {
            duration += durationOfTest(mealList1);
            System.out.println("1." + (i + 1));
        }
        System.out.println(duration / 10);

        duration = 0;
        for (int i = 0; i < 10; i++) {
            duration += durationOfTest(mealList2);
            System.out.println("2." + (i + 1));
        }
        System.out.println(duration / 10);
    }

    private static long durationOfTest(List<UserMeal> listMeal) {
        long sTime, eTime, duration = 0;
        sTime = System.nanoTime();
        getFilteredWithExceeded(listMeal, LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        eTime = System.nanoTime();
        duration = (eTime - sTime) / 1_000_000;
        return duration;
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        new ArrayList<>();
        Map<LocalDate, Integer> dayCalories =
                mealList.parallelStream()
                        .collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));

        List<UserMealWithExceed> listUserME =
                mealList.parallelStream()
                        .unordered()
                        .filter(userMeal -> TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                        .map(m -> new UserMealWithExceed(m.getDateTime()
                                , m.getDescription()
                                , m.getCalories()
                                , dayCalories.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                        .collect(Collectors.toList());

        return listUserME;
    }
}
