package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCourseCommand;
import seedu.address.model.person.StudentId;

public class ViewCourseCommandParserTest {

    private ViewCourseCommandParser parser = new ViewCourseCommandParser();

    @Test
    public void parse_emptyArg_returnsViewCourseCommand() {
        assertParseSuccess(parser, "", new ViewCourseCommand());
        assertParseSuccess(parser, "   ", new ViewCourseCommand());
    }

    @Test
    public void parse_validStudentId_returnsViewCourseCommand() {
        StudentId studentId = new StudentId("S00001");
        assertParseSuccess(parser, "S00001", new ViewCourseCommand(studentId));
        assertParseSuccess(parser, "  S00001  ", new ViewCourseCommand(studentId));
    }
}

