package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;

public class DeregisterCommandTest {

    private Model model;
    private Person student;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        Name name = new Name("John Doe");
        Phone phone = new Phone("99887766");
        Gender gender = new Gender("Male");
        student = new Person(name, phone, gender, new StudentId());
        model.addPerson(student);
    }

    @Test
    public void execute_validStudentId_success() {
        StudentId idToDelete = student.getStudentId();
        DeregisterCommand command = new DeregisterCommand(idToDelete);

        String expectedMessage = String.format(DeregisterCommand.MESSAGE_SUCCESS, student.getName(), idToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(student);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidStudentId_failure() {
        StudentId invalidId = new StudentId(99999); // ID that doesn't exist
        DeregisterCommand command = new DeregisterCommand(invalidId);

        assertCommandFailure(command, model,
                String.format(DeregisterCommand.MESSAGE_NOT_FOUND, invalidId));
    }

    @Test
    public void equals() {
        DeregisterCommand command1 = new DeregisterCommand(student.getStudentId());
        DeregisterCommand command2 = new DeregisterCommand(new StudentId(99999));

        // same object -> true
        assertTrue(command1.equals(command1));

        // same values -> true
        DeregisterCommand command1Copy = new DeregisterCommand(student.getStudentId());
        assertTrue(command1.equals(command1Copy));

        // different types -> false
        assertFalse(command1.equals(1));

        // null -> false
        assertFalse(command1.equals(null));

        // different StudentId -> false
        assertFalse(command1.equals(command2));
    }

    @Test
    public void toStringMethod() {
        DeregisterCommand command = new DeregisterCommand(student.getStudentId());
        String expected = DeregisterCommand.class.getCanonicalName()
                + "{studentId=" + student.getStudentId() + "}";
        assertEquals(expected, command.toString());
    }
}
