package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.util.MealsUtil.*;

/**
 * Created by butkoav on 18.05.2017.
 */
public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_URL + '/';

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL1));
    }

    @Test
    public void testDelete() throws Exception {
        for (int i = 0; i < 5; i++) {
            mockMvc.perform(delete(REST_URL + (MEAL1_ID + i)))
                    .andDo(print())
                    .andExpect(status().isOk());
        }
        MATCHER.assertCollectionEquals(Collections.singletonList(MEAL6), mealService.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();

        mockMvc.perform(put(REST_URL + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, mealService.get(updated.getId(), USER_ID));
    }

    @Test
    public void testCreate() throws Exception {
        Meal expected = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Meal returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(
                Arrays.asList(expected, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1)
                , mealService.getAll(USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EX.contentListMatcher(getWithExceeded(MEALS, DEFAULT_CALORIES_PER_DAY))));
    }

    @Test
    public void testFilter() throws Exception {
        TestUtil.print(mockMvc.perform(
                post(REST_URL + "filter")
                        .param("startDate", "2015-05-31")
                        .param("startTime", "20:00"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER_WITH_EX.contentListMatcher(
                        getFilteredWithExceeded(Arrays.asList(MEAL4, MEAL5, MEAL6),
                                LocalTime.parse("20:00"), LocalTime.MAX,
                                DEFAULT_CALORIES_PER_DAY))));
    }

}
