package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;

public class RegisterCommand extends Command {

    public static final String COMMAND_WORD = "register";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Registers a new student.\n"
            + "Parameters: n/ NAME p/PHONE g/ GENDER\n"
            + "Example: " + COMMAND_WORD + " n/ John Doe p/99887766 g/ Male";

    public static final String MESSAGE_SUCCESS = "Success! %1$s with %2$s";

    private final Name name;
    private final Phone phone;
    private final Gender gender;

    public RegisterCommand(Name name, Phone phone, Gender gender) {
        requireNonNull(name);
        requireNonNull(phone);
        requireNonNull(gender);
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        StudentId newId = new StudentId();
        Person newStudent = new Person(name, phone, gender, newId);

        model.addPerson(newStudent);
        return new CommandResult(String.format(MESSAGE_SUCCESS, name, newId));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RegisterCommand
                && name.equals(((RegisterCommand) other).name)
                && phone.equals(((RegisterCommand) other).phone)
                && gender.equals(((RegisterCommand) other).gender));
    }

    @Override
    public String toString() {
        return RegisterCommand.class.getCanonicalName()
                + "{name=" + name
                + ", phone=" + phone
                + ", gender=" + gender + "}";
    }
}
