package seedu.address.model.person;

import java.util.Objects;

/**
 * Unique identifier for a Student.
 * studentId is an integer but prints out with prefix 'S' and is padded to a total of 5 digits.
 * Current design can accommodate up to 100,000 different students.
 */
public class StudentId {

    private static int nextStudentId = 1;

    public final int studentId;

    /**
     * Creates a new StudentId, automatically generated. No parameters needed.
     */
    public StudentId() {
        studentId = nextStudentId;
        nextStudentId++;
    }

    /**
     * Creates a StudentId with a specific integer value.
     * Useful when loading from storage.
     */
    public StudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "S" + String.format("%05d", studentId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StudentId)) {
            return false;
        }

        StudentId otherId = (StudentId) other;
        return studentId == otherId.studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}
