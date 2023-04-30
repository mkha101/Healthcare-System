package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Patient implements Comparable<Patient>, Serializable {
    private String firstName;
    private String lastName;
    private String medications;
    private String condition;
    private String notes;
    private String caretaker;
    private String patientId;
    private LocalDate dateOfBirth;
    private int roomNumber;
    private static int patientIdCounter = 0;
    public Patient(String firstName, String lastName, LocalDate dateOfBirth, String medications, int roomNumber, String condition, String notes,
                   String caretaker) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.medications = medications;
        this.condition = condition;
        this.notes = notes;
        this.caretaker = caretaker;
        this.patientId = String.valueOf(patientIdCounter++);
        this.roomNumber = roomNumber;
    }

    public static int getPatientIdCounter() {
        return patientIdCounter;
    }
    public static void setPatientIdCounter(int idCounter) {
        Patient.patientIdCounter = idCounter;
    }
    public String getPatientId() {
        return patientId;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
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
        return patientId;
    }

    @Override
    public String toString() {
        return "Patient [\nfirstName=" + firstName + "\nlastName=" + lastName + "\ndateOfBirth=" + dateOfBirth
                +"\nmedications=" + medications + "\ncondition=" + condition + "\nnotes=" + notes + "\ncaretaker=" + caretaker + "\npatientID="
                + patientId + "\nroomNumber=" +roomNumber+"\n]";
    }

    @Override
    public int compareTo(Patient o) {
        return this.firstName.compareTo(o.firstName);
    }
    }

