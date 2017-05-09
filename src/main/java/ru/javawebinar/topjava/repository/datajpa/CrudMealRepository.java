package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Meal m WHERE m.id=:id and m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    Meal findByIdAndUserId(Integer id, Integer userId);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Transactional
    List<Meal> findAllByUserIdOrderByDateTime(Integer userId);
    @Transactional
    List<Meal> findAllByUserId(Integer userId,Sort sort);

    @Transactional
    List<Meal> findAllByUserIdAndDateTimeBetween(Integer userId, LocalDateTime startTime, LocalDateTime endTime, Sort sort);

}
