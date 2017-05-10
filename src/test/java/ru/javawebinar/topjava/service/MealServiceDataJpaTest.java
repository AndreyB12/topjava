package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by butkoav on 10.05.2017.
 */
@ActiveProfiles({Profiles.ACTIVE_DB,Profiles.DATA_JPA})
public class MealServiceDataJpaTest extends MealServiceTest {

}
