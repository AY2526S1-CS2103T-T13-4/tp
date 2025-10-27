package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label genderLabel;
    @FXML
    private Label genderValue;
    @FXML
    private Label studentId;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText("Phone: " + person.getPhone().value);
        studentId.setText("Student ID: " + person.getStudentId().getValue());
        genderLabel.setText("Gender:");
        genderValue.setText(person.getGender().value);

        String genderValueLower = person.getGender().value.toLowerCase();

        switch (genderValueLower) {
        case "female":
            genderValue.setStyle("-fx-text-fill: #ff76a3;");
            break;
        case "male":
            genderValue.setStyle("-fx-text-fill: #4da3ff;");
            break;
        case "other":
            genderValue.setStyle("-fx-text-fill: #c56bff;");
            break;
        default:
            genderValue.setStyle("-fx-text-fill: #ffffff;");
        }

    }
}
