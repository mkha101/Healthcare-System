package model;

import java.io.Serializable;

public class Patient implements Comparable<Patient>, Serializable {
    private String firstName;
    private String lastName;
    private String prescriptions;
    private String condition;
    private String notes;
    private String caretaker;
    private String patientID;

    private static int idCounter= 0;


    public Patient(String firstName, String lastName, String prescriptions, String condition, String notes,
                   String caretaker) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.prescriptions = prescriptions;
        this.condition = condition;
        this.notes = notes;
        this.caretaker = caretaker;
        this.patientID = String.valueOf(idCounter++) ;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(String caretaker) {
        this.caretaker = caretaker;
    }

    public String getPatientID() {
        return patientID;
    }

    @Override
    public String toString() {
        return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", prescritions=" + prescriptions
                + ", condition=" + condition + ", notes=" + notes + ", caretaker=" + caretaker + ", patientID="
                + patientID + "]";
    }

    @Override
    public int compareTo(Patient o) {
        return this.firstName.compareTo(o.firstName);
    }
    }

