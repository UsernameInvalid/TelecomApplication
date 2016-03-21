package telecom.model;

import java.time.LocalDateTime;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Call {

	private final StringProperty acctName;
    //private final ObjectProperty<LocalDateTime> callStart;
    private final StringProperty callStart;
    private final StringProperty billingNumber;
    private final StringProperty spokeWith;
    private final StringProperty verified;
    private final StringProperty canBeReached;
    private final StringProperty issue;
    private final StringProperty crisID;
    private final StringProperty crisName;

    /**
     * Default constructor.
     */
    public Call() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public Call(String billingNumber, String callStart) {
        this.billingNumber = new SimpleStringProperty(billingNumber);
        this.callStart = new SimpleStringProperty(callStart);

        // Some initial dummy data, just for convenient testing.
        this.acctName = new SimpleStringProperty("");
        this.spokeWith = new SimpleStringProperty("");
        this.verified = new SimpleStringProperty("");
        this.canBeReached = new SimpleStringProperty("");
        this.issue = new SimpleStringProperty("");
        this.crisID = new SimpleStringProperty("");
        this.crisName = new SimpleStringProperty("");
    }

    //BillingNumber
    public String getBillingNumber() {
        return billingNumber.get();
    }

    public void setBillingNumber(String billingNumber) {
        this.billingNumber.set(billingNumber);
    }

    public StringProperty billingNumberProperty() {
        return billingNumber;
    }

    //CallStart
    public String getCallStart() {
        return callStart.get();
    }

    public void setCallStart(String callStart) {
        this.callStart.set(callStart);
    }

    public StringProperty callStartProperty() {
        return callStart;
    }

    //AcctName
    public String getAcctName() {
        return acctName.get();
    }

    public void setAcctName(String acctName) {
        this.acctName.set(acctName);
    }

    public StringProperty acctNameProperty() {
        return acctName;
    }

    //SpokeWith
    public String getSpokeWith() {
        return spokeWith.get();
    }

    public void setSpokeWith(String spokeWith) {
        this.spokeWith.set(spokeWith);
    }

    public StringProperty spokeWithProperty() {
        return spokeWith;
    }

    //Verified
    public String getVerified() {
        return verified.get();
    }

    public void setVerified(String verified) {
        this.verified.set(verified);
    }

    public StringProperty verifiedProperty() {
        return verified;
    }

    //CanBeReached
    public String getCanBeReached() {
        return canBeReached.get();
    }

    public void setCanBeReached(String canBeReached) {
        this.canBeReached.set(canBeReached);
    }

    public StringProperty canBeReachedProperty() {
        return canBeReached;
    }

    //Issue
    public String getIssue() {
        return issue.get();
    }

    public void setIssue(String issue) {
        this.canBeReached.set(issue);
    }

    public StringProperty issueProperty() {
        return issue;
    }

    //CrisID
    public String getCrisID() {
        return crisID.get();
    }

    public void setCrisID(String crisID) {
        this.crisID.set(crisID);
    }

    public StringProperty crisIDProperty() {
        return crisID;
    }

    //CrisName
    public String getCrisName() {
        return crisName.get();
    }

    public void setCrisName(String crisName) {
        this.crisName.set(crisName);
    }

    public StringProperty crisNameProperty() {
        return crisName;
    }
}