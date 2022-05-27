package ie.atu.sw;

// SETIINGS : This class is responsible for managing the NGram builder settings
public class Settings {

	// Initialise all settings to default empty values
	private static String inputFileDirectory = "None";
	private static String outputFileDirectory = "None";
	private static String inputFileName = "None";
	private static String outputFileName = "None";
	private static int nGramSize = 0;

	// GETTERS : These methods get settings information

	// GET INPUT FILE DIRECTORY
	public static String getInputFileDirectory() {
		return inputFileDirectory;
	}

	// GET OUTPUT FILE DIRECTORY
	public static String getOutputFileDirectory() {
		return outputFileDirectory;
	}

	// GET INPUT FILE NAME
	public static String getInputFileName() {
		return inputFileName;
	}

	// GET OUTPUT FILE NAME
	public static String getOutputFileName() {
		return outputFileName;
	}

	// GET NGRAM SIZE
	public static int getNGramSize() {
		return nGramSize;
	}

	// SETTERS : these methods set settings information

	// SET INPUT FILE DIRECTORY
	public static void setInputFileDirectory(String newDirectory) {
		inputFileDirectory = newDirectory;
	}

	// SET OUTPUT FILE DIRECTORY
	public static void setOutputFileDirectory(String newDirectory) {
		outputFileDirectory = newDirectory;
	}

	// SET INPUT FILE Name
	public static void setInputFileName(String newName) {
		inputFileName = newName;
	}

	// SET OUTPUT FILE Name
	public static void setOutputFileName(String newName) {
		outputFileName = newName;
	}

	// SET NGRAM SIZE
	public static void setNGramSize(int newSize) {
		nGramSize = newSize;
	}

	// CHECK IF SETTINGS ARE DEFUALT : This method checks if settings have been updated from default values
	public static boolean checkIfSettingsAreDefault() {
		if (inputFileDirectory.equals("None") || outputFileDirectory.equals("None") || inputFileName.equals("None") || outputFileName.equals("None") || nGramSize == 0) {
			return false;
		}

		else {
			return true;
		}
	}

	// SETTINGS ARE DEFAULT : The method displays a warning message prompting user to update settings
	public static void settingsAreDefault() {

		// Print Warning Message Header
		System.out.println();
		System.out.println("******************** SETTINGS INCORRECT ********************");
		System.out.println();
		System.out.println("The Following Settings Have Not Been Specified :");

		// Print Warning Message Specific Details Based On Which Settings Have Not Been Updated
		if (inputFileDirectory.equals("None")) {
			System.out.println("- Input File Location : " + inputFileDirectory);
		}

		if (outputFileDirectory.equals("None")) {
			System.out.println("- Output File Location : " + outputFileDirectory);
		}

		if (inputFileName.equals("None")) {
			System.out.println("- Input File Name : " + inputFileName);
		}

		if (outputFileName.equals("None")) {
			System.out.println("- Output File Name : " + outputFileName);
		}

		if (nGramSize == 0) {
			System.out.println("- N-Gram Size : None");
		}

		System.out.println();
	}

}
