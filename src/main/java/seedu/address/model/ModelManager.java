package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final CourseBook courseBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Course> filteredCourse;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyCourseBook courseBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, courseBook, userPrefs);

        logger.fine("Initializing with address book: "
                + addressBook + "\n course book: " + courseBook + "\n user prefs: " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.addressBook = new AddressBook(addressBook, this.userPrefs);
        this.courseBook = new CourseBook(courseBook);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredCourse = new FilteredList<>(this.courseBook.getCourseList());
    }

    public ModelManager() {
        this(new AddressBook(), new CourseBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public Path getCourseBookFilePath() {
        return userPrefs.getCourseBookFilePath();
    }

    @Override
    public void setCourseBookFilePath(Path courseBookFilePath) {
        requireNonNull(courseBookFilePath);
        userPrefs.setCourseBookFilePath(courseBookFilePath);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== CourseBook ================================================================================

    @Override
    public void setCourseBook(ReadOnlyCourseBook courseBook) {
        this.courseBook.resetData(courseBook);
    }

    @Override
    public ReadOnlyCourseBook getCourseBook() {
        return courseBook;
    }

    @Override
    public boolean hasCourse(Course course) {
        requireNonNull(course);
        return courseBook.hasCourse(course);
    }

    @Override
    public void deleteCourse(Course target) {
        courseBook.removeCourse(target);
    }

    @Override
    public void addCourse(Course course) {
        courseBook.addCourse(course);
        updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
    }

    @Override
    public void setCourse(Course target, Course editedPerson) {
        requireAllNonNull(target, editedPerson);

        courseBook.setCourse(target, editedPerson);
    }

    //=========== Filtered Course List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Course} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Course> getFilteredCourseList() {
        return filteredCourse;
    }

    @Override
    public void updateFilteredCourseList(Predicate<Course> predicate) {
        requireNonNull(predicate);
        filteredCourse.setPredicate(predicate);
    }

    @Override
    public Course getCourseById(CourseId courseId) {
        return courseBook.getCourseById(courseId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return courseBook.equals(otherModelManager.courseBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && addressBook.equals(otherModelManager.addressBook)
                && filteredCourse.equals(otherModelManager.filteredCourse);
    }

}
