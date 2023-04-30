package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.*;
public class Utilities {
    private static TextArea displayArea;
    private static Label displayLabel;
    private static Button yesBtn;
    private static Button noBtn;
    public static Stage createChoiceStage(){
        Stage newStage1 = new Stage();
        newStage1.setTitle("Confirmation Alert");
        return newStage1;
    }
    public static VBox createMasterBox(){
        VBox masterBox = new VBox(25);
        masterBox.setAlignment(Pos.CENTER);
        masterBox.setPadding(new Insets(20));
        return masterBox;
    }
    public static HBox createButtonBox(){
        HBox buttonBox = new HBox(5);
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(20));
        return buttonBox;
    }
    public static Button creatYesBtn(){
        yesBtn = new Button("Confirm Update");
        yesBtn.setPrefSize(200, 40);
        yesBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        yesBtn.setTextFill(Color.BLACK);
        return yesBtn;
    }
    public static Button createNoBtn(){
        noBtn = new Button("Go Back");
        noBtn.setPrefSize(200, 40);
        noBtn.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        noBtn.setTextFill(Color.BLACK);
        return noBtn;
    }
    public static TextArea createDisplayTextArea(){
        displayArea = new TextArea();
        displayArea.setPrefSize(50, 50);
        displayArea.setEditable(false);
        displayArea.setFocusTraversable(false);
        return displayArea;
    }
    public static Label createDisplayLabel(){
        displayLabel = new Label("Are you sure you want to update this patient?");
        displayLabel.setPrefSize(250, 250);
        displayLabel.setAlignment(Pos.CENTER);
        displayLabel.setFocusTraversable(false);
        return displayLabel;
    }
    public static void savePatientIdCount() {
        try {
            File idFile = new File("HealthProjectFX/UserInfo/PatientId.dat");
            idFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(idFile, false);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(Patient.getPatientIdCounter());
            fos.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static int readPatientIdCount() {
        boolean endOfFile = false;
        int count = 0;
        try {
            FileInputStream fis = new FileInputStream("HealthProjectFX/UserInfo/PatientId.dat");
            DataInputStream dis = new DataInputStream(fis);
            while (!endOfFile) {
                try {
                    count = dis.readInt();
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
    public static void saveUserIdCount() {
        try {
            File idFile = new File("HealthProjectFX/UserInfo/UserId.dat");
            idFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(idFile, false);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(User.getUserIdCounter());
            fos.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static int readUserIdCount() {
        boolean endOfFile = false;
        int count = 0;
        try {
            FileInputStream fis = new FileInputStream("HealthProjectFX/UserInfo/UserId.dat");
            DataInputStream dis = new DataInputStream(fis);

            while (!endOfFile) {
                try {
                    count = dis.readInt();
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }
            dis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}

