package model;

public class InitializeData {

	public static void initializeUserTree() {
		UserStore.getUserStore();
	}

	public static void initializePatientTree() {
		PatientStore.getPatientStore();
	}
	public static void initialize() {
		initializeUserTree();
		initializePatientTree();
	}
}