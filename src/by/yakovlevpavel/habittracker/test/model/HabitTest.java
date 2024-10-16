package by.yakovlevpavel.habittracker.test.model;

import by.yakovlevpavel.habittracker.model.Habit;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class HabitTest {

    @Test
    void testHabitConstructor() {
        String name = "Чтение книги";
        String description = "Прочитать по 1 главе в день";
        String frequency = "Ежедневно";
        Date date = new Date();
        int userId = 1;

        Habit habit = new Habit(name, description, frequency, date, userId);

        assertEquals(name, habit.getName());
        assertEquals(description, habit.getDescription());
        assertEquals(frequency, habit.getFrequency());
        assertEquals(date, habit.getDate());
        assertEquals(userId, habit.getUserId());
    }

    @Test
    void testSettersAndGetters() {
        Habit habit = new Habit("Прогулка", "Прогулка 30 минут", "Ежедневно", new Date(), 2);

        habit.setId(1);
        assertEquals(1, habit.getId());

        habit.setName("Бег");
        assertEquals("Бег", habit.getName());

        habit.setDescription("Бегать по утрам 1 час");
        assertEquals("Бегать по утрам 1 час", habit.getDescription());

        habit.setFrequency("Ежемесячно");
        assertEquals("Ежемесячно", habit.getFrequency());

        Date newDate = new Date(new Date().getTime() + 1000L * 60 * 60 * 24); // дата на 1 день позже
        habit.setDate(newDate);
        assertEquals(newDate, habit.getDate());

        habit.setUserId(3);
        assertEquals(3, habit.getUserId());
    }
}