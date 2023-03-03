package model;

import java.io.Serializable;

public class Patient implements Comparable<Patient>, Serializable {
    private String firstName;
    private String lastName;
    private String prescritions;
    private String condition;
    private String notes;
    private String caretaker;

    private static int patientID = 0;

    public Patient(String firstName, String lastName, String prescritions, String condition, String notes,
                   String caretaker) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.prescritions = prescritions;
        this.condition = condition;
        this.notes = notes;
        this.caretaker = caretaker;
        patientID = patientID + 1 ;
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

    public String getPrescritions() {
        return prescritions;
    }

    public void setPrescritions(String prescritions) {
        this.prescritions = prescritions;
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

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    @Override
    public String toString() {
        return "Patient [firstName=" + firstName + ", lastName=" + lastName + ", prescritions=" + prescritions
                + ", condition=" + condition + ", notes=" + notes + ", caretaker=" + caretaker + ", patientID="
                + patientID + "]";
    }

    @Override
    public int compareTo(Patient o) {
        return this.firstName.compareTo(o.firstName);
    }
    }

