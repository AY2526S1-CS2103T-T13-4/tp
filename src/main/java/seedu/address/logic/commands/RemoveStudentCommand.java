package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.course.CourseId;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;

/**
 *  Adds a student to a course.
 */
public class RemoveStudentCommand extends Command {

    public static final String COMMAND_WORD = "remove_student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes a student to a course... "
            + "Parameters: "
            + "STUDENT_ID "
            + "COURSE_ID\n"
            + "Example: " + COMMAND_WORD + " S00001 C0001";

    public static final String MESSAGE_SUCCESS =
            "Student '%1$s' (%2$s) removed from Course '%3$s' (%4$s) successfully.";
    public static final String MESSAGE_COURSE_NOT_FOUND = "Course with course ID %1$s not found.";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "Student with student ID %1$s not in addressbook.";
    public static final String MESSAGE_STUDENT_NOT_IN_COURSE = "Student with student ID %1$s not in course.";

    public final StudentId studentId;
    public final CourseId courseId;
    /**
     * Creates a RemoveStudentCommand to add the specified student to the specified course
     */
    public RemoveStudentCommand(StudentId studentId, CourseId courseId) {
        requireAllNonNull(studentId, courseId);
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);

        Course course = model.getCourseById(courseId);
        if (course == null) {
            throw new CommandException(String.format(MESSAGE_COURSE_NOT_FOUND, courseId));
        }

        Person student = model.getAddressBook().getPersonList().stream()
                .filter(p -> p.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
        if (student == null) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_FOUND, studentId));
        }

        if (!course.containsStudent(student)) {
            throw new CommandException(String.format(MESSAGE_STUDENT_NOT_IN_COURSE, studentId));
        }

        course.removeStudent(student);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, student.getName(), studentId, course.getName(), courseId)
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RemoveStudentCommand otherRemoveStudentCommand)) {
            return false;
        }

        return studentId.equals(otherRemoveStudentCommand.studentId)
                && courseId.equals(otherRemoveStudentCommand.courseId);
    }

}
