package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.DeregisterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.StudentId;

/**
 * Parses input arguments and creates a new DeregisterCommand object.
 */
public class DeregisterCommandParser implements Parser<DeregisterCommand> {

    private static final Pattern STUDENT_ID_FORMAT = Pattern.compile("^S\\d{5}$");

    @Override
    public DeregisterCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeregisterCommand.MESSAGE_USAGE));
        }

        Matcher matcher = STUDENT_ID_FORMAT.matcher(trimmedArgs);
        if (!matcher.matches()) {
            throw new ParseException(DeregisterCommand.MESSAGE_INVALID_ID_FORMAT);
        }

        // Extract numeric part of ID
        int idNumber = Integer.parseInt(trimmedArgs.substring(1));
        StudentId studentId = new StudentId(idNumber);

        return new DeregisterCommand(studentId);
    }
}
