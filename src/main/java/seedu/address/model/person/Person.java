package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final StudentId studentId;
    private final Gender gender;

    /**
     * Every field must be present and not null.
     * Use this constructor for new Student registration.
     */
    public Person(Name name, Phone phone, Gender gender, StudentId studentId) {
        requireAllNonNull(name, phone, gender, studentId);
        this.name = name;
        this.phone = phone;
        this.email = null;
        this.address = null;
        this.gender = gender;
        this.studentId = studentId;
    }

    /**
     * Original constructor kept for backward compatibility (used by existing AB3 commands/tests).
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.gender = null;
        this.studentId = null;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Gender getGender() {
        return gender;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        // if studentId exists, use it as unique identifier
        if (studentId != null && otherPerson.studentId != null) {
            return studentId.equals(otherPerson.studentId);
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return Objects.equals(name, otherPerson.name)
                && Objects.equals(phone, otherPerson.phone)
                && Objects.equals(email, otherPerson.email)
                && Objects.equals(address, otherPerson.address)
                && Objects.equals(tags, otherPerson.tags)
                && Objects.equals(gender, otherPerson.gender)
                && Objects.equals(studentId, otherPerson.studentId);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, gender, studentId);
    }

    @Override
    public String toString() {
        return Person.class.getCanonicalName()
                + "{name=" + name
                + ", phone=" + phone
                + ", gender=" + gender
                + ", studentId=" + studentId + "}";
    }
}
