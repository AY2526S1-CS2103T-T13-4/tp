package seedu.address.testutil;

import seedu.address.model.CourseBook;
import seedu.address.model.course.Course;

/**
 * A utility class to help with building CourseBook objects.
 * Example usage: <br>
 *     {@code CourseBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class CourseBookBuilder {

    private CourseBook courseBook;

    public CourseBookBuilder() {
        courseBook = new CourseBook();
    }

    public CourseBookBuilder(CourseBook courseBook) {
        this.courseBook = courseBook;
    }

    /**
     * Adds a new {@code Person} to the {@code CourseBook} that we are building.
     */
    public CourseBookBuilder withPerson(Course course) {
        courseBook.addCourse(course);
        return this;
    }

    public CourseBook build() {
        return courseBook;
    }
}
