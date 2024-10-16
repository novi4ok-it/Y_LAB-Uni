package by.yakovlevpavel.habittracker.test.model;

import by.yakovlevpavel.habittracker.model.HabitRecord;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class HabitRecordTest {

    @Test
    void testHabitRecordConstructor() {
        int habitId = 1;
        boolean completed = true;
        Date date = new Date();

        HabitRecord habitRecord = new HabitRecord(habitId, completed, date);

        assertEquals(habitId, habitRecord.getHabitId());
        assertTrue(habitRecord.isCompleted());
        assertEquals(date, habitRecord.getDate());
    }

    @Test
    void testSettersAndGetters() {
        HabitRecord habitRecord = new HabitRecord(2, false, new Date());

        habitRecord.setId(1);
        assertEquals(1, habitRecord.getId());

        habitRecord.setHabitId(3);
        assertEquals(3, habitRecord.getHabitId());

        habitRecord.setCompleted(true);
        assertTrue(habitRecord.isCompleted());

        Date newDate = new Date(new Date().getTime() + 1000L * 60 * 60 * 24); // дата на 1 день позже
        habitRecord.setDate(newDate);
        assertEquals(newDate, habitRecord.getDate());
    }
}
