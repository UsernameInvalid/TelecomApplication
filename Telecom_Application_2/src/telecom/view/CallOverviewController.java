package telecom.view;

import java.time.LocalDateTime;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import telecom.MainApp;
import telecom.model.Call;
import telecom.util.DateUtil;

public class CallOverviewController {
    @FXML
    private TableView<Call> callTable;
    @FXML
    private TableColumn<Call, String> billingNumberColumn;
    @FXML
    private TableColumn<Call, String> callStartColumn;

    @FXML
    private Label acctNameLabel;
    @FXML
    private Label spokeWithLabel;
    @FXML
    private Label verifiedLabel;
    @FXML
    private Label canBeReachedLabel;
    @FXML
    private Label issueLabel;
    @FXML
    private TextField filterField;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public CallOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	billingNumberColumn.setCellValueFactory(cellData -> cellData.getValue().billingNumberProperty());
    	callStartColumn.setCellValueFactory(cellData -> cellData.getValue().callStartProperty());

        // Clear person details.
        showCallDetails(null);

        // Listen for selection changes and show the person details when changed.
        callTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCallDetails(newValue));
        


       

    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        callTable.setItems(mainApp.getCallData());
        FilteredList<Call> filteredData = new FilteredList<>(mainApp.getCallData(), p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getBillingNumber().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getCallStart().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Call> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(callTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        callTable.setItems(sortedData);
    }
    
    private void showCallDetails(Call call) {
        if (call != null) {
            // Fill the labels with info from the person object.
        	acctNameLabel.setText(call.getAcctName());
        	spokeWithLabel.setText(call.getSpokeWith());
        	verifiedLabel.setText(call.getVerified());
        	canBeReachedLabel.setText(call.getCanBeReached());
        	issueLabel.setText(call.getIssue());

            // TODO: We need a way to convert the birthday into a String! 
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
        	acctNameLabel.setText("");
        	spokeWithLabel.setText("");
        	verifiedLabel.setText("");
        	verifiedLabel.setText("");
        	canBeReachedLabel.setText("");
        	issueLabel.setText("");
        }
    }
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteCall() {
        Call selectedItem = callTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            mainApp.getCallData().remove(selectedItem);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Call Selected");
            alert.setContentText("Please select a call in the table.");

            alert.showAndWait();
        }
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Call tempCall = new Call();
        boolean okClicked = mainApp.showCallEditDialog(tempCall);
        if (okClicked) {
            mainApp.getCallData().add(tempCall);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Call selectedPerson = callTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showCallEditDialog(selectedPerson);
            if (okClicked) {
                showCallDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Call Selected");
            alert.setContentText("Please select a call in the table.");

            alert.showAndWait();
        }
    }
}