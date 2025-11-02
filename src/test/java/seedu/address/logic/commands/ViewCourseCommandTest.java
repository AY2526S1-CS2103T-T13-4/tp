package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.CourseBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.StudentId;
import seedu.address.testutil.TypicalCourses;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ViewCourseCommand.
 */
public class ViewCourseCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        // Set up model with both address book and course book populated
        model = new ModelManager(
                TypicalPersons.getTypicalAddressBook(),
                TypicalCourses.getTypicalCourseBook(),
                new UserPrefs()
        );
        expectedModel = new ModelManager(
                model.getAddressBook(),
                model.getCourseBook(),
                new UserPrefs()
        );
    }

    @Test
    public void execute_viewCourse_showsAllCourses() {
        assertCommandSuccess(new ViewCourseCommand(), model,
                ViewCourseCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_emptyCourseBook_showsNoCourses() {
        Model emptyModel = new ModelManager(new AddressBook(), new CourseBook(), new UserPrefs());
        Model emptyExpectedModel = new ModelManager(new AddressBook(), new CourseBook(), new UserPrefs());
        assertCommandSuccess(new ViewCourseCommand(), emptyModel,
                ViewCourseCommand.MESSAGE_NO_COURSES, emptyExpectedModel);
    }

    @Test
    public void execute_viewCourseWithStudentId_noCoursesForStudent() {
        StudentId studentId = ALICE.getStudentId();
        String expectedMessage = String.format(ViewCourseCommand.MESSAGE_NO_COURSES_FOR_STUDENT, studentId);
        expectedModel.updateFilteredCourseList(course -> course.getStudentIds().contains(studentId.getValue()));
        assertCommandSuccess(new ViewCourseCommand(studentId), model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        StudentId studentId1 = new StudentId("S00001");
        StudentId studentId2 = new StudentId("S00002");
        ViewCourseCommand viewAllCommand = new ViewCourseCommand();
        ViewCourseCommand viewStudent1Command = new ViewCourseCommand(studentId1);
        ViewCourseCommand viewStudent2Command = new ViewCourseCommand(studentId2);

        // same object -> returns true
        assertTrue(viewAllCommand.equals(viewAllCommand));
        assertTrue(viewStudent1Command.equals(viewStudent1Command));

        // same values -> returns true
        ViewCourseCommand viewAllCommandCopy = new ViewCourseCommand();
        assertTrue(viewAllCommand.equals(viewAllCommandCopy));
        ViewCourseCommand viewStudent1CommandCopy = new ViewCourseCommand(studentId1);
        assertTrue(viewStudent1Command.equals(viewStudent1CommandCopy));

        // different types -> returns false
        assertFalse(viewAllCommand.equals(1));

        // null -> returns false
        assertFalse(viewAllCommand.equals(null));

        // different student id -> returns false
        assertFalse(viewStudent1Command.equals(viewStudent2Command));

        // view all vs view by student -> returns false
        assertFalse(viewAllCommand.equals(viewStudent1Command));
    }
}
