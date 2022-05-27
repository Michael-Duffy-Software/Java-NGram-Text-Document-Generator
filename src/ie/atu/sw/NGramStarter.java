package ie.atu.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// NGRAM : This class is responsible for constructing NGrams
public class NGramStarter {

	// Flag Any Problems Encountered While Running NGram Builder
	boolean failedToBuild = false;
	boolean failedToOpenInput = false;
	boolean failedDueToIO = false;

	// NGRAM STARTER : This method control the NGram Build process, it calls a method to build NGrams and prints information describing the process
	public NGramStarter() {

		// Before Beginning Process of building NGrams check if user has updated settings from the default values
		if (Settings.checkIfSettingsAreDefault()) {

			// Print Starting NGRAM Builder Header Information
			System.out.println();
			System.out.println("***************************************** STARTING N-GRAM BUILDER *****************************************");
			System.out.println();
			System.out.println("SELECTED SETTINGS");
			System.out.println("Input File Location : " + Settings.getInputFileDirectory());
			System.out.println("Output File Location : " + Settings.getOutputFileDirectory());
			System.out.println("Input File Name : " + Settings.getInputFileName());
			System.out.println("Output File Name : " + Settings.getOutputFileName());
			System.out.println("N-Gram Size : " + Settings.getNGramSize());
			System.out.println();

			// Call NGRAM BUILDER METHOD
			try {
				// Attempt To Call NGram Builder, This Method Will Return "SUCESS" or "FAILED"
				String attemptToBuild = buildNGram();

				// If NGram Builder Has Failed For Any Reason
				if (attemptToBuild.equals("FAILED")) {
					// Flag That A Problem Has Been Encountered
					failedToBuild = true;
					failedDueToIO = true;
					failedToOpenInput = true;
				}

				// If NGram Builder Has Failed Due To IO Exception
			} catch (IOException e) {
				failedToBuild = true;
				failedDueToIO = true;
			}

			// Print NGRAM Builder Footer Information

			// If There Has Been No Problems Print Success Message
			if (!failedToBuild) {
				System.out.println();
				System.out.println("******************************** SUCCESSFULLY EXPORTED N-GRAMS TO CSV FILE ********************************");
				System.out.println();
			}

			// If A Problem Has Occurred Print Failed Message
			if (failedToBuild) {

				System.out.println("********************************** FAILED TO EXPORT N-GRAMS TO CSV FILE **********************************");
				System.out.println();

				if (failedToOpenInput) {
					System.out.println("- FAILED TO OPEN INPUT FILE");
				}
				if (failedDueToIO) {
					System.out.println("- FAILED DUE TO IO EXCEPTION");
				}

				System.out.println();

			}

		}

		else {
			// Prompt user to update settings before continuing
			Settings.settingsAreDefault();
		}

	}

	// BUILD NGRAM : This Method Controls The NGram Building Process, It Calls Methods To Open Files, Read Files, Search For NGrams and Export a CSV File.
	private static String buildNGram() throws IOException {

		// Convert the Input File To A String - This will either return the input file as a string or "FAILED" if a problem occurred.
		String inputString = convertInputFileToString();

		// If Input File Did Not Convert To String Return Failed
		if (inputString.equals("FAILED")) {
			return "FAILED";
		}
		
		// Print Loading Bar
		LoadingBar.load();
		// Convert The Input File String To A String of Only Lower Case Characters (Remove Any Characters That Are Not Letters)
		String onlyCharacters = convertStringToOnlyLowerCaseCharacters(inputString);
		// Create an Array of NGrams From The Only Characters String (This array contains total NGrams rather than unique NGrams)
		ArrayList<String> nGramsArray = convertStringToNGramsArray(onlyCharacters);
		// Create an Array of Only Unique NGrams From The Previous NGram Array
		ArrayList<String> uniqueNGramsArray = findUniqueNGrams(nGramsArray);
		// Create an Array To Store The Frequency Count of Each Unique NGram Which Occurs In The Total NGram Array
		ArrayList<NGramCount> nGramCount = countUniqueNGrams(nGramsArray, uniqueNGramsArray);
		// Export The Frequency Count Array In A CSV Format
		exportToCSV(nGramCount);
		// Print Information About The Most Frequently Occurring N-Grams
		printMostFrequentNGram(nGramCount);

		// NGram Builder Was Successful
		return "SUCESS";
	}

	// CONVERT INPUT FILE TO STRING : This Method Opens The File Specified By The User And Returns A String Containing The Contents of The File
	private static String convertInputFileToString() {

		// Attempt To Open The Input File
		try {
			// Determine The Location and Name of Input File From Settings Given By User
			String fileToOpen = Settings.getInputFileDirectory() + "\\" + Settings.getInputFileName();
			// Open Input File
			File inputFile = new File(fileToOpen);
			// Prepare To Read From Input File
			FileInputStream inputStream = new FileInputStream(inputFile);
			// Create A String To Store Contents of Input File
			String inputString = "";

			// Read Input File
			int reading = 0;

			while ((reading = inputStream.read()) != -1) {
				// Write Each Character of Input File To Input String
				inputString = inputString + ((char) reading);
			}

			// Close The Input File Stream
			inputStream.close();

			// Return The Input File String
			return inputString;

		}

		// Input File Could Not Be Opened
		catch (Exception e) {
			return "FAILED";
		}

	}

	// CONVERT STRING TO ONLY LOWER CASE CHARTERS : This removes any characters which are not letters and converts all letters to lowercase
	private static String convertStringToOnlyLowerCaseCharacters(String inputString) {

		// Store Number of Letters In String
		int numberOfLetters = 0;

		// Create a String To Store The Lower Case Character String
		String onlyCharacters = "";

		// Loop Through Entire Input File String
		for (int letter = 0; letter < inputString.length(); letter++) {

			// Store Current Character
			char letterToAdd = inputString.charAt(letter);

			// If Current Character Is A Letter Add To String
			if (Character.isLetter(letterToAdd)) {
				onlyCharacters = onlyCharacters + letterToAdd;
				numberOfLetters++;
			}

		}
		// Print NGRAM Information Header
		System.out.println("********************************************* N-GRAM REPORT *********************************************");
		System.out.println();
		// Print Number of Letters Contained In Input File
		System.out.println("Number of Letters Contained In Input File : " + numberOfLetters);
		// Convert String To Lower Case And Return
		return onlyCharacters.toLowerCase();
	}

	// CONVERT STRING TO N-GRAMS ARRAY : This method loops through a string and adds NGrams from the string to an array
	private static ArrayList<String> convertStringToNGramsArray(String inputString) {

		// Create an array of strings to store the ngrams
		ArrayList<String> nGramsArray = new ArrayList<String>();
		// Get the size of ngrams to create from user settings
		int nGramSize = Settings.getNGramSize();

		// Loop through the input string
		for (int i = 0; i < inputString.length(); i++) {

			// Create a string to store ngram
			String nGramToAdd = "";

			// If remainder of string that has not been read is greater or equal to ngram size (this prevents out of bounds error)
			if (i <= inputString.length() - nGramSize) {

				// Nested Loop : Add characters to ngram string based on required size of ngram
				for (int j = 0; j < nGramSize; j++) {

					nGramToAdd = nGramToAdd + inputString.charAt(i + j);

				}

				// Add ngram string to ngram array
				nGramsArray.add(nGramToAdd);
			}

		}

		return nGramsArray;
	}

	// FIND UNIQUE NGRAMS : This method creates an array of unique ngrams by searching the ngrams array
	private static ArrayList<String> findUniqueNGrams(ArrayList<String> nGramsArray) {

		// Store number of unique ngrams
		int numberOfUniqueNGrams = 0;

		// Create an array of strings to store unique ngrams
		ArrayList<String> uniqueNGrams = new ArrayList<String>();

		// Loop through ngrams array
		for (int i = 0; i < nGramsArray.size(); i++) {

			// Store the value of current ngram
			String nGramToAdd = nGramsArray.get(i);
			// Assume current ngram is unique initially
			boolean isUnique = true;

			// Loop through unique ngram array
			for (int j = 0; j < uniqueNGrams.size(); j++) {

				// Compare current ngram to current unique ngram - If they are the same then current ngram is not unique
				if (nGramToAdd.equals(uniqueNGrams.get(j))) {

					isUnique = false;
					break;
				}
			}

			// If current was unique add it to unique ngrams array
			if (isUnique) {
				uniqueNGrams.add(nGramToAdd);
				numberOfUniqueNGrams++;
			}
		}
		// Print Number of Unique N-Grams
		System.out.println("Number of Unique N-Grams Contained In Input File : " + numberOfUniqueNGrams);
		return uniqueNGrams;
	}

	// COUNT UNIQUE N-GRAMS : This method creates an array of each ngram and the frequency
	private static ArrayList<NGramCount> countUniqueNGrams(ArrayList<String> nGramsArray,
			ArrayList<String> uniqueNGrams) {

		// Create an array of "ngramcounts" - An object containing an ngram string and an integer of its frequency
		ArrayList<NGramCount> nGramCount = new ArrayList<NGramCount>();

		// Loop through the unique ngrams array
		for (int i = 0; i < uniqueNGrams.size(); i++) {

			// Store the current ngram
			String toAdd = uniqueNGrams.get(i);
			// Add a new "ngramcount" to the ngram count array - Initially this ngram has a frequency of 0
			nGramCount.add(new NGramCount(toAdd, 0));

		}

		// Loop through the ngram count array
		for (int i = 0; i < nGramCount.size(); i++) {

			// Record how many times current ngram appears in ngrams array
			int counted = 0;
			// Loop through ngram array
			for (int j = 0; j < nGramsArray.size(); j++) {
				// Compare current ngramcount to current ngram in ngram array
				boolean isSame = (nGramCount.get(i)).getNGram().equals(nGramsArray.get(j));
				// If they are the same ngram increase the value of counted
				if (isSame) {
					counted++;
				}
			}
			// Update the frequency count of the current ngramcount to the counted value
			nGramCount.get(i).setCount(counted);
		}

		// Return the array of ngram frequencies
		return nGramCount;
	}

	// EXPORT TO CSV : This method exports the ngram frequency array to a CSV file
	private static void exportToCSV(ArrayList<NGramCount> nGramCount) throws IOException {

		// Determine the output file location from the settings given by User
		String fileToOutput = Settings.getOutputFileDirectory() + "\\" + Settings.getOutputFileName();
		// Create an output file and prepare to write to output file
		File file = new File(fileToOutput);
		file.setWritable(true);
		file.setReadable(true);
		FileWriter csvWriter = new FileWriter(file);

		// Loop through ngram frequency array
		for (int i = 0; i < nGramCount.size(); i++) {
			// Write the ngram string to CSV file
			csvWriter.append((nGramCount.get(i)).getNGram());
			// Write a comma to CSV file
			csvWriter.append(",");
			// Write the frequency count integer to CSV file
			csvWriter.append(Integer.toString((nGramCount.get(i)).getCount()));
			// Write a new line to the CSV file
			csvWriter.append("\n");
		}

		// CLose the output file
		csvWriter.flush();
		csvWriter.close();

	}

	// PRINT MOST FREQUENT N-GRAMS : This method determines the most frequently occurring ngrams and prints the information
	private static void printMostFrequentNGram(ArrayList<NGramCount> nGramCount) {

		// Store the value of the most frequently occurred ngram
		int highestCount = 0;

		// Loop through ngram count array
		for (int i = 0; i < nGramCount.size(); i++) {
			// Store the count of the current ngram
			int compareCount = (nGramCount.get(i)).getCount();

			// Compare the current ngram count to the highest count so far and update if current count is highest
			if (compareCount > highestCount) {
				highestCount = compareCount;
			}
		}

		// Print the most frequently occurring ngram
		System.out.print("Most Frequently Occuring N-Gram(s) : ");

		// Loop through the ngram count array
		for (int i = 0; i < nGramCount.size(); i++) {
			
			// If the frequency count of the current ngram equals the highest count print the ngram
			if ((nGramCount.get(i)).getCount() == highestCount) {
				System.out.print((nGramCount.get(i)).getNGram() + " ");
			}
		}
		// Print the number of occurrences of the most frequent ngram
		System.out.println();
		System.out.println("Number of Occurences : " + highestCount);

	}

}
