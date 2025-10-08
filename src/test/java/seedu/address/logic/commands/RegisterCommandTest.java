package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalPersons.ALICE;

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

public class RegisterCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
    }

    @Test
    public void execute_validPerson_success() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("99887766");
        Gender gender = new Gender("Male");
        RegisterCommand command = new RegisterCommand(name, phone, gender);

        CommandResult result = command.execute(model);

        assertEquals(1, model.getFilteredPersonList().size());
        Person added = model.getFilteredPersonList().get(0);
        assertEquals(name, added.getName());
        assertEquals(phone, added.getPhone());
        assertEquals(gender, added.getGender());

        String expectedMessageStart = "Success! " + name + " with ";
        assertTrue(result.getFeedbackToUser().startsWith(expectedMessageStart));
    }

    @Test
    public void equals() {
        Name nameAlice = new Name("Alice");
        Phone phoneAlice = new Phone("12345678");
        Gender genderAlice = new Gender("Female");

        Name nameBob = new Name("Bob");
        Phone phoneBob = new Phone("87654321");
        Gender genderBob = new Gender("Male");

        RegisterCommand registerAliceCommand = new RegisterCommand(nameAlice, phoneAlice, genderAlice);
        RegisterCommand registerAliceCommandCopy = new RegisterCommand(nameAlice, phoneAlice, genderAlice);
        RegisterCommand registerBobCommand = new RegisterCommand(nameBob, phoneBob, genderBob);

        // same object -> true
        assertTrue(registerAliceCommand.equals(registerAliceCommand));

        // same values -> true
        assertTrue(registerAliceCommand.equals(registerAliceCommandCopy));

        // different types -> false
        assertFalse(registerAliceCommand.equals(1));

        // null -> false
        assertFalse(registerAliceCommand.equals(null));

        // different person -> false
        assertFalse(registerAliceCommand.equals(registerBobCommand));
    }

    @Test
    public void toStringMethod() {
        Name name = new Name("Charlie");
        Phone phone = new Phone("55555555");
        Gender gender = new Gender("Male");
        RegisterCommand command = new RegisterCommand(name, phone, gender);
        String expected = RegisterCommand.class.getCanonicalName()
                + "{name=" + name + ", phone=" + phone + ", gender=" + gender + "}";
        assertEquals(expected, command.toString());
    }
}
