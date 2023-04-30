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

public class UserStore { 
	private static TreeMap<String, User> theMap;
	private static UserStore userStore;

	public UserStore() {
		theMap = new TreeMap<String, User>();
		setTheUserMap();
	}

	public static UserStore getUserStore() {
		if (userStore == null) {
			return userStore = new UserStore();
		} else {
			return userStore;
		}
	}

	public void insert(String username, User user) {
		theMap.put(username, user);
	}

	public boolean searchUsername(String string) {
		if (theMap.containsKey(string)) {
			return true;
		} else {
			System.out.println("User not found");
			return false;
		}
	}

	public boolean searchPassword(String username, String password) {
		if (theMap.get(username).getPassword().equals(password)) {
			return true;
		} else {
			System.out.println("Incorrect Password");
			return false;
		}
	}

	public static boolean passwordTest(String password) {
		if (password.length() < 6) {
			return false;
		}
		char ch;
		boolean oneCapital = false;
		boolean oneDigit = false;
		boolean oneLowerCase = false;
		for (int i = 0; i < password.length(); i++) {
			ch = password.charAt(i);
			if (Character.isDigit(ch)) {
				oneDigit = true;
			} else if (Character.isUpperCase(ch)) {
				oneCapital = true;
			} else if (Character.isLowerCase(ch)) {
				oneLowerCase = true;
			}
			if (oneDigit && oneCapital && oneLowerCase)
				return true;
		}
		return false;
	}

	public void display() {
		Iterator mapIt = theMap.entrySet().iterator();
		while (mapIt.hasNext()) {
			System.out.println(mapIt.next());
		}
	}
	public void deleteUser(String id) {
		 theMap.remove(id);
	}

	public static void setTheUserMap() {
		importUserFile("HealthProjectFX/UserInfo/userStore.dat");
	}

	public static void importUserFile(String fileName) {
		try {
			File userFile = new File(fileName);
			if (userFile.exists() && userFile != null) {
				FileInputStream fis = new FileInputStream(userFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				theMap.putAll((Map<? extends String, ? extends User>) ois.readObject());
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

	public static void saveUser() {
		try {
			File file = new File("HealthProjectFX/UserInfo/userStore.dat");
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(theMap);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}