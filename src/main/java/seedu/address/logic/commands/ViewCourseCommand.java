package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSES;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.course.Course;
import seedu.address.model.person.StudentId;

/**
 * Lists all courses in eduBase to the user.
 * Can optionally filter courses by student ID to show only courses the student is enrolled in.
 */
public class ViewCourseCommand extends Command {

    public static final String COMMAND_WORD = "view_courses";

    public static final String MESSAGE_SUCCESS = "Listed all courses";
    public static final String MESSAGE_SUCCESS_FILTERED = "Listed all courses for student: %1$s";
    public static final String MESSAGE_NO_COURSES = "No courses have been created yet.";
    public static final String MESSAGE_NO_COURSES_FOR_STUDENT = "Student %1$s is not enrolled in any courses.";

    private final StudentId studentId;

    /**
     * Creates a ViewCourseCommand to list all courses.
     */
    public ViewCourseCommand() {
        this.studentId = null;
    }

    /**
     * Creates a ViewCourseCommand to list courses for a specific student.
     */
    public ViewCourseCommand(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (studentId == null) {
            model.updateFilteredCourseList(PREDICATE_SHOW_ALL_COURSES);
            if (model.getFilteredCourseList().isEmpty()) {
                return new CommandResult(MESSAGE_NO_COURSES);
            }
            return new CommandResult(MESSAGE_SUCCESS);
        } else {
            Predicate<Course> predicate = course -> course.getStudentIds().contains(studentId.getValue());
            model.updateFilteredCourseList(predicate);
            if (model.getFilteredCourseList().isEmpty()) {
                return new CommandResult(String.format(MESSAGE_NO_COURSES_FOR_STUDENT, studentId));
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS_FILTERED, studentId));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewCourseCommand otherCommand)) {
            return false;
        }

        return studentId == null ? otherCommand.studentId == null : studentId.equals(otherCommand.studentId);
    }
}
