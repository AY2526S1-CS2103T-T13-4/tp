package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.AppUtil;

/**
 * Represents a Student's gender in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGender(String)}
 */
public class Gender {

    public static final String MESSAGE_CONSTRAINTS =
            "Gender should only be 'Male', 'Female', or 'Other' (case-insensitive).";

    private static final String VALIDATION_REGEX = "(?i)male|female|other";

    public final String value;

    /**
     * Constructs a {@code Gender}.
     * @param gender A valid gender string.
     */
    public Gender(String gender) {
        requireNonNull(gender);
        AppUtil.checkArgument(isValidGender(gender.trim()), MESSAGE_CONSTRAINTS);
        this.value = capitalizeFirstLetter(gender.trim().toLowerCase());
    }

    /**
     * Returns true if a given string is a valid gender.
     */
    public static boolean isValidGender(String test) {
        return test != null && test.trim().matches(VALIDATION_REGEX);
    }

    /**
     * Capitalises only the first letter.
     */
    private static String capitalizeFirstLetter(String str) {
        if (str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Gender
                && value.equalsIgnoreCase(((Gender) other).value));
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }
}
