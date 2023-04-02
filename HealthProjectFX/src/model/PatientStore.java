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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PatientStore {
    private static TreeMap<String, Patient> thePatientMap;
    private static PatientStore patientStore;

    public PatientStore() {
        thePatientMap = new TreeMap<String, Patient>();
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
        importPatientFile("HealthProjectFX/UserInfo/patientStore.dat");
    }

    public static void importPatientFile(String fileName) {
        try {
            File patientFile = new File(fileName);
            if (patientFile.exists() && patientFile != null) {
                FileInputStream fis = new FileInputStream(patientFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                thePatientMap.putAll((Map<? extends String, ? extends Patient>) ois.readObject());
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

    public void insert(String patientId, Patient patient) {
        thePatientMap.put(patientId, patient);
    }

    public boolean searchPatientId(String patientId) {
        if (thePatientMap.containsKey(patientId)) {
            return true;
        } else {
            System.out.println("patientId not found");
            return false;
        }
    }
    public List<Patient> searchPatientRoom(int roomId) {
        return thePatientMap.values()
                .stream()
                .filter(c -> c.getRoomNumber() == (roomId))
                .collect(Collectors.toList());
    }
    public Patient display(String patientId) {
            return thePatientMap.get(patientId);
        }

    public void displayAll() {
        Iterator mapIt = thePatientMap.entrySet().iterator();
        while (mapIt.hasNext()) {
            System.out.println(mapIt.next());
        }
    }

    public void delete() {

    }

    public static void savePatient() {
        try {
            File file = new File("HealthProjectFX/UserInfo/patientStore.dat");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(thePatientMap);
            oos.close();
        } catch (Exception e) {
        }
    }
}
