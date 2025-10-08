package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 * Deregisters (deletes) a student identified by their Student ID from the address book.
 */
public class DeregisterCommand extends Command {

    public static final String COMMAND_WORD = "deregister";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by their Student ID.\n"
            + "Parameters: STUDENT_ID (e.g., S00001)\n"
            + "Example: " + COMMAND_WORD + " S00001";

    public static final String MESSAGE_SUCCESS = "Student '%1$s' (%2$s) deleted successfully.";
    public static final String MESSAGE_INVALID_ID_FORMAT = "Error: Invalid student ID.";
    public static final String MESSAGE_NOT_FOUND = "Error: Student with student ID %1$s does not exist.";

    private final StudentId targetStudentId;

    public DeregisterCommand(StudentId targetStudentId) {
        requireNonNull(targetStudentId);
        this.targetStudentId = targetStudentId;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // find the student
        Person studentToDelete = model.getFilteredPersonList().stream()
                .filter(p -> targetStudentId.equals(p.getStudentId()))
                .findFirst()
                .orElse(null);

        if (studentToDelete == null) {
            return new CommandResult(String.format(MESSAGE_NOT_FOUND, targetStudentId));
        }

        model.deletePerson(studentToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                studentToDelete.getName(), targetStudentId));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeregisterCommand
                && targetStudentId.equals(((DeregisterCommand) other).targetStudentId));
    }
}
