package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.course.Course;

/**
 * Unmodifiable view of a course book
 */
public interface ReadOnlyCourseBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Course> getCourseList();

    /**
     * Returns true if there is no course in the course book.
     */
    boolean isEmpty();

}
