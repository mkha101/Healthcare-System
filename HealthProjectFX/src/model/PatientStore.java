package model;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class PatientStore {
    private static TreeMap<Integer, Patient> thePatientMap;
    private static PatientStore patientStore;

    public PatientStore() {
        thePatientMap = new TreeMap<Integer, Patient>();
        setThePatientMap();
    }

    public static PatientStore getPatientStore() {
        if (patientStore == null) {
            return patientStore = new PatientStore();
        } else {
            return patientStore;
        }
    }

    public static void setThePatientMap() {
        importPatientFile("UserInfo/patients.txt");
    }

    public static void importPatientFile(String string) {
        try {
            File patientFile = new File("UserInfo/patientStore.dat");
            if (patientFile.exists() && patientFile != null) {
                FileInputStream fis = new FileInputStream(patientFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                thePatientMap.putAll((Map<? extends Integer, ? extends Patient>) ois.readObject());
                ois.close();
                fis.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insert(int patientId, Patient patient) {
        thePatientMap.put(patientId, patient);
    }

    public boolean searchPatient(String string) {
        if (thePatientMap.containsKey(string)) {
            return true;
        } else {
            System.out.println("User not found");
            return false;
        }
    }

    public void display() {
        Iterator mapIt = thePatientMap.entrySet().iterator();
        while (mapIt.hasNext()) {
            System.out.println(mapIt.next());
        }
    }

    public void delete() {

    }

    public static void savePatient() {
        try {
            File file = new File("UserInfo/patientStore.dat");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(thePatientMap);
            oos.close();
        } catch (Exception e) {
        }
    }
}
