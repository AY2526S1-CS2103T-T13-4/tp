package seedu.address.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CourseIdTest {

    @Test
    public void constructor_autoGeneratesUniqueCourseIds() {
        CourseId id1 = new CourseId();
        CourseId id2 = new CourseId();
        CourseId id3 = new CourseId();

        // Each new CourseId should be unique
        assertNotEquals(id1, id2);
        assertNotEquals(id2, id3);
        assertNotEquals(id1, id3);
    }

    @Test
    public void toString_correctFormat() {
        CourseId id = new CourseId();
        String idString = id.toString();

        // Should start with 'C'
        assertTrue(idString.startsWith("C"));

        // Should be 5 characters total (C + 4 digits)
        assertEquals(5, idString.length());

        // Should follow the pattern C####
        assertTrue(idString.matches("C\\d{4}"));
    }

    @Test
    public void toString_paddedWithZeros() {
        // Create multiple course IDs to test padding
        // Assuming we start from a clean state, but this test is general
        CourseId id = new CourseId();
        String idString = id.toString();

        // Should have exactly 4 digits after 'C'
        assertEquals(4, idString.substring(1).length());

        // All characters after 'C' should be digits
        assertTrue(idString.substring(1).matches("\\d{4}"));
    }

    @Test
    public void equals() {
        CourseId id1 = new CourseId();
        CourseId id2 = new CourseId();

        // same object -> returns true
        assertTrue(id1.equals(id1));

        // null -> returns false
        assertFalse(id1.equals(null));

        // different types -> returns false
        assertFalse(id1.equals(5));

        // different course IDs -> returns false
        assertFalse(id1.equals(id2));
    }

    @Test
    public void hashCode_sameId_sameHashCode() {
        CourseId id = new CourseId();
        assertEquals(id.hashCode(), id.hashCode());
    }

    @Test
    public void hashCode_differentIds_differentHashCodes() {
        CourseId id1 = new CourseId();
        CourseId id2 = new CourseId();
        assertNotEquals(id1.hashCode(), id2.hashCode());
    }
}
