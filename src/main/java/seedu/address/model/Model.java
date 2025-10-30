package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Course> PREDICATE_SHOW_ALL_COURSES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the user prefs' course book file path.
     */
    Path getCourseBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setCourseBookFilePath(Path courseBookFilePath);

    /**
     * Replaces course book data with the data in {@code courseBook}.
     */
    void setCourseBook(ReadOnlyCourseBook courseBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Returns the CourseBook */
    ReadOnlyCourseBook getCourseBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered course list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if a course with the same identity as {@code course} exists in the address book.
     */
    boolean hasCourse(Course course);

    /**
     * Deletes the given course.
     * The course must exist in the address book.
     */
    void deleteCourse(Course target);

    /**
     * Adds the given course.
     * {@code course} must not already exist in the address book.
     */
    void addCourse(Course course);

    /**
     * Replaces the given course {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The course identity of {@code editedPerson} must not be the same as another existing course in the address book.
     */
    void setCourse(Course target, Course editedPerson);

    /**
     * Returns true if the given student is in one of the course.
     */
    boolean checkStudentInAllCourse(Person student);

    /** Returns an unmodifiable view of the filtered course list */
    ObservableList<Course> getFilteredCourseList();

    /**
     * Updates the filter of the filtered course list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCourseList(Predicate<Course> predicate);

    /**
     * Returns the course with the given CourseId, or null if not found.
     */
    Course getCourseById(CourseId courseId);

    void updateFilteredStudentListForCourse(Course course);

    void updateFilteredCourseListForCourse(Course course);

}
