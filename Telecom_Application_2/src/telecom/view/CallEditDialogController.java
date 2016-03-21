package telecom.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import telecom.model.Call;
import telecom.util.DateUtil;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class CallEditDialogController {

    @FXML
    private TextField billingNumberField;
    @FXML
    private TextField accountNameField;
    @FXML
    private TextField spokeWithField;
    @FXML
    private ComboBox<String> verifiedBox;
    @FXML
    private TextField canBeReachedField;
    @FXML
    private RadioButton issue1Radio;
    @FXML
    private RadioButton issue2Radio;
    @FXML
    private Label crisIDLabel;
    @FXML
    private TextField crisIDField;
    @FXML
    private Label crisNameLabel;
    @FXML
    private TextField crisNameField;


    private Stage dialogStage;
    private Call call;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setCall(Call call) {
        this.call = call;

        billingNumberField.setText(call.getBillingNumber());
        accountNameField.setText(call.getAcctName());
        spokeWithField.setText(call.getSpokeWith());
        canBeReachedField.setText(call.getCanBeReached());
        verifiedBox.setValue(call.getVerified());
        crisIDField.setText(call.getCrisID());
        crisNameField.setText(call.getCrisName());
        
        if( verifiedBox.getValue().equals("Bypass")){
        	crisIDLabel.setOpacity(1);
        	crisIDField.setOpacity(1);
        	crisNameLabel.setOpacity(1);
        	crisNameField.setOpacity(1);
        } else{
        	crisIDLabel.setOpacity(0);
        	crisIDField.setOpacity(0);
        	crisNameLabel.setOpacity(0);
        	crisNameField.setOpacity(0);
        }            
        //Sets Call Start when dialog is loaded instead of when OK is pressed. 
        //TODO Test this better. Looks like it's working at the moment. 
        if (call.getCallStart() == null ){
        	call.setCallStart(new SimpleDateFormat("dd-MM-YYYY HH:mm").format(new Date()));
        }
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            call.setBillingNumber(billingNumberField.getText());
            call.setAcctName(accountNameField.getText());
            call.setSpokeWith(spokeWithField.getText());
            call.setCanBeReached(canBeReachedField.getText());
            call.setVerified(verifiedBox.getValue());
            if( verifiedBox.getValue().equals("Bypass")){
            	call.setCrisID(crisIDField.getText());
            	call.setCrisName(crisNameField.getText());
            }

            

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    /**
     * Called when the user chooses a validation.
     */
    @FXML
    private void handleValidation() {
    	
        if( verifiedBox.getValue().equals("Bypass")){
        	crisIDLabel.setOpacity(1);
        	crisIDField.setOpacity(1);
        	crisNameLabel.setOpacity(1);
        	crisNameField.setOpacity(1);
        } else{
        	crisIDLabel.setOpacity(0);
        	crisIDField.setOpacity(0);
        	crisNameLabel.setOpacity(0);
        	crisNameField.setOpacity(0);
        }
    }
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (billingNumberField.getText() == null || billingNumberField.getText().length() == 0) {
            errorMessage += "No valid billing number!\n"; 
        }
        if (accountNameField.getText() == null || accountNameField.getText().length() == 0) {
            errorMessage += "No valid account number!\n"; 
        }
        if (spokeWithField.getText() == null || spokeWithField.getText().length() == 0) {
            errorMessage += "No valid spoke with!\n"; 
        }

        if (canBeReachedField.getText() == null || canBeReachedField.getText().length() == 0) {
            errorMessage += "No valid can be reached number!\n"; 
        } 

        if (verifiedBox.getValue() == null || verifiedBox.getValue().length() == 0) {
            errorMessage += "No valid can be verification choice!\n"; 
        } 

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}