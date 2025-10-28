package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UserPrefs userPrefs;

    /**
     * Creates an AddressBook with default values.
     */
    public AddressBook() {
        this.userPrefs = new UserPrefs();
        persons = new UniquePersonList();
    }

    /**
     * Creates an AddressBook using the given UserPrefs.
     */
    public AddressBook(UserPrefs userPrefs) {
        this.userPrefs = userPrefs;
        persons = new UniquePersonList();
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied} and given UserPrefs.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied, UserPrefs userPrefs) {
        this(userPrefs);
        resetData(toBeCopied);
    }

    /**
     * Generates a unique student ID in the format SXXXXX where XXXXX is a 5-digit number.
     * The ID sequence uses the higher of max existing ID + 1.
     *
     * @return a new unique StudentId object
     */
    public StudentId getLatestStudentId() {
        int maxExistingId = getPersonList().stream()
                .map(Person::getStudentId)
                .mapToInt(sid -> Integer.parseInt(sid.getValue().substring(1)))
                .max()
                .orElse(0);

        int nextId = maxExistingId + 1;

        String idString = String.format("S%05d", nextId);
        return new StudentId(idString);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
