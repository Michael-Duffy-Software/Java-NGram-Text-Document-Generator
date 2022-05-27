package ie.atu.sw;

import java.util.Scanner;

// MENU : This class is responsible for navigating the options menu
public class Menu {

	// Create Scanner To Take Input From User
	Scanner userInput = new Scanner(System.in);

	// DISPLAY MENU : This method prints the main menu
	public void displayMenu() {

		// Print Menu Header
		printMenuHeader();
		// Print Options Menu
		printOptionsMenu();
	}

	// PRINT MENU HEADER : This method prints the main menu header
	private void printMenuHeader() {

		// Print Menu Header
		System.out.println();
		System.out.println("************************************************************");
		System.out.println("*                                                          *");
		System.out.println("*                  N-GRAM FREQUENCY BUILDER                *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println();
	}

	// PRINT OPTIONS MENU : This method prints the main menu options
	private void printOptionsMenu() {

		// Print Menu Options
		System.out.println("************************ MAIN MENU *************************");
		System.out.println();
		System.out.println("1 - Build N-Grams ");
		System.out.println("2 - Settings");
		System.out.println("3 - Quit");
		System.out.println();
		System.out.print("Select Option :");
		System.out.println();

		// Wait for user to select from menu options
		listenForUserOption();
	}

	// LISTEN FOR USER OPTIONS : This method brings the user to the main menu option selected
	private void listenForUserOption() {

		// Take input from the user until a valid option is selected
		boolean waitForInput = true;

		while (waitForInput) {

			String userMenuOption = userInput.nextLine();

			// The user has selected option 1
			if (userMenuOption.equals("1")) {
				// Stop waiting for input from the user as a valid option was selected
				waitForInput = false;
				// Bring the user to the nGam builder option
				nGramOptionSelected();
			}

			// The user has selected option 2
			if (userMenuOption.equals("2")) {
				// Stop waiting for input from the user as a valid option was selected
				waitForInput = false;
				// Bring the user to the settings option
				settingsOptionSelected();

			}

			// The user has selected option 3
			if (userMenuOption.equals("3")) {
				// Stop waiting for input from the user as a valid option was selected
				waitForInput = false;
				// Bring the user to the quit option
				quitOptionSelected();
			}

		}

	}

	// NGRAM OPTION SELECTED : This method calls the NGRAM builder Method and then returns the user to the main menu option
	private void nGramOptionSelected() {
		// Create NGram object
		new NGramStarter();
		// Return user to main menu options
		printOptionsMenu();
	}

	// QUIT OPTION SELECTED : This method exits the program
	private void quitOptionSelected() {

		// Confirm that the user wants to quit
		System.out.println("Are you sure you want to quit? (Y / N)");

		// Take input from user until a valid input is given
		boolean waitForInput = true;

		while (waitForInput) {

			String userQuitOption = userInput.nextLine();

			// User wants to quit
			if (userQuitOption.equals("Y")) {
				// Announce that program is quitting
				System.out.println();
				userInput.close();
				System.out.println("Quitting Program");
				// Exit program
				System.exit(0);
			}

			// User does not want to quit
			if (userQuitOption.equals("N")) {
				System.out.println();
				// Return user to main menu options
				printOptionsMenu();
			}
		}
	}

	// SETTINGS OPTION SELECTED : This method displays current setting and gives the user the option to change settings
	private void settingsOptionSelected() {

		// Print Current Settings
		System.out.println();
		System.out.println("********************* CURRENT SETTINGS *********************");
		System.out.println();
		System.out.println("Input File Location : " + Settings.getInputFileDirectory());
		System.out.println("Output File Location : " + Settings.getOutputFileDirectory());
		System.out.println("Input File Name : " + Settings.getInputFileName());
		System.out.println("Output File Name : " + Settings.getOutputFileName());

		// If N-Gram size not been updated display "none"
		if (Settings.getNGramSize() == 0) {
			System.out.println("N-Gram Size : None");
		}

		// if N-Gram size has been updated display integer value
		else {
			System.out.println("N-Gram Size : " + Settings.getNGramSize());
		}

		System.out.println();
		// Print Option To Change Settings
		System.out.println("********************* CHANGE SETTINGS **********************");
		System.out.println();
		System.out.println("1 - Change All Settings");
		System.out.println("2 - Change Input File Location");
		System.out.println("3 - Change Output File Locatin");
		System.out.println("4 - Change Input File Name");
		System.out.println("5 - Change Output File Name");
		System.out.println("6 - Change N-Gram Size");
		System.out.println("7 - Return To Main Menu");
		System.out.println();
		System.out.println("Select Option (1 - 7) :");

		// Take input from the user until a valid option is selected
		boolean waitForInput = true;

		while (waitForInput) {

			String userQuitOption = userInput.nextLine();

			// User has selected option 1
			if (userQuitOption.equals("1")) {
				// Stop waiting for option from user as an option has been selected
				waitForInput = false;

				// Ask user to input a new file directory
				System.out.println("New Input File Location (e.g. C:\\Users\\Desktop) :");
				// Store the input given by the user
				String newInputFileDirectory = userInput.nextLine();
				// Set the file directory to the new directory given by user
				Settings.setInputFileDirectory(newInputFileDirectory);

				// Ask user to input a new file directory
				System.out.println("New Output File Location (e.g. C:\\Users\\Desktop) :");
				// Store the input given by the user
				String newOutputFileDirectory = userInput.nextLine();
				// Set the file directory to the new directory given by user
				Settings.setOutputFileDirectory(newOutputFileDirectory);

				// Ask user to input a new file name
				System.out.println("New Input File Name (e.g. filename.txt) :");
				// Store the input given by the user
				String newInputFileName = userInput.nextLine();
				// Set the input file name to the new name given by user
				Settings.setInputFileName(newInputFileName);

				// Ask user to input a new file name
				System.out.println("New Output File Name (e.g. filename.csv) :");
				// Store the output given by the user
				String newOutputFileName = userInput.nextLine();
				// Set the output file name to the new name given by user
				Settings.setOutputFileName(newOutputFileName);

				// Ask user to input a new nGram size
				System.out.println("New N-Gram Size (1 - 5) :");

				// Take input from the user until a valid integer is selected
				boolean waitForIntegerInput = true;

				while (waitForIntegerInput) {

					String userIntegerInput = userInput.nextLine();

					// Try to use the input given by user (will only work if input is an integer)
					try {

						// Convert the input given by user to an integer (This throws exception if input can not be converted)
						int newNGramSize = Integer.parseInt(userIntegerInput);

						// Check is integer chosen is within valid range (1 - 5)
						if (newNGramSize == 1 || newNGramSize == 2 || newNGramSize == 3 || newNGramSize == 4
								|| newNGramSize == 5) {
							// Set nGram size to new size chosen by user
							Settings.setNGramSize(newNGramSize);
							// Confirm that settings were changed
							System.out.println("N-Gram Size Changed");
							// Stop waiting for input as integer has already been given
							waitForIntegerInput = false;
							// Return user to settings options menu
							settingsOptionSelected();
						}

						// If integer given is outside of accepted range (1 - 5)
						else {
							// Display error message
							System.out.println("N-Gram Size Must Be An Integer Between 1 - 5");
							System.out.println();
							// Ask the user to give another input
							System.out.println("New N-Gram Size :");
						}

						// Could not use input given by the user as it was not an integer
					} catch (NumberFormatException e) {
						// Display error message
						System.out.println("N-Gram Size Must Be An Integer Between 1 - 5");
						System.out.println();
						// Ask the user to give another input
						System.out.println("New N-Gram Size :");
					}

				}

				// Announce that setting have been changed
				System.out.println("All Settings Changed");
				// Return the user to the settings option menu
				settingsOptionSelected();
			}

			// User has selected option 2
			if (userQuitOption.equals("2")) {
				// Stop waiting for option from user as an option has been selected
				waitForInput = false;
				// Ask user to input a new file directory
				System.out.println("New Input File Location (eg C:\\Users\\Desktop) :");
				// Store the input given by the user
				String newInputFileDirectory = userInput.nextLine();
				// Set the file directory to the new directory given by user
				Settings.setInputFileDirectory(newInputFileDirectory);
				// Announce that settings were changed
				System.out.println("Input File Location Changed");
				System.out.println();
				// Return the user to the settings option menu
				settingsOptionSelected();
			}

			// User has selected option 3
			if (userQuitOption.equals("3")) {
				// Stop waiting for option from user as an option has been selected
				waitForInput = false;
				// Ask user to input a new file directory
				System.out.println("New Output File Location (eg C:\\Users\\Desktop) :");
				// Store the input given by the user
				String newOutputFileDirectory = userInput.nextLine();
				// Set the file directory to the new directory given by user
				Settings.setOutputFileDirectory(newOutputFileDirectory);
				// Announce that settings were changed
				System.out.println("Output File Location Changed");
				System.out.println();
				// Return the user to the settings option menu
				settingsOptionSelected();
			}

			// User has selected option 4
			if (userQuitOption.equals("4")) {
				// Stop waiting for option from user as an option has been selected
				waitForInput = false;
				// Ask user to input a new file name
				System.out.println("New Input File Name (e.g. filename.txt) :");
				// Store the input given by the user
				String newInputFileName = userInput.nextLine();
				// Set the input file name to the new name given by user
				Settings.setInputFileName(newInputFileName);
				// Announce that settings were changed
				System.out.println("Input File Name Changed");
				System.out.println();
				// Return the user to the settings option menu
				settingsOptionSelected();
			}

			// User has selected option 5
			if (userQuitOption.equals("5")) {
				// Stop waiting for option from user as an option has been selected
				waitForInput = false;
				// Ask user to input a new file name
				System.out.println("New Output File Name (e.g. filename.csv) :");
				// Store the output given by the user
				String newOutputFileName = userInput.nextLine();
				// Set the output file name to the new name given by user
				Settings.setOutputFileName(newOutputFileName);
				// Announce that settings were changed
				System.out.println("Output File Name Changed");
				System.out.println();
				// Return the user to the settings option menu
				settingsOptionSelected();
			}

			// User has selected option 6
			if (userQuitOption.equals("6")) {
				// Stop waiting for option from user as an option has been selected
				waitForInput = false;
				// Ask user to input a new nGram size
				System.out.println("New N-Gram Size (1 - 5) :");

				// Take input from the user until a valid integer is selected
				boolean waitForIntegerInput = true;

				while (waitForIntegerInput) {

					String userIntegerInput = userInput.nextLine();

					// Try to use the input given by user (will only work if input is an integer)
					try {

						// Convert the input given by user to an integer (This throws exception if input can not be converted)
						int newNGramSize = Integer.parseInt(userIntegerInput);

						// Check is integer chosen is within valid range (1 - 5)
						if (newNGramSize == 1 || newNGramSize == 2 || newNGramSize == 3 || newNGramSize == 4
								|| newNGramSize == 5) {
							// Set nGram size to new size chosen by user
							Settings.setNGramSize(newNGramSize);
							// Confirm that settings were changed
							System.out.println("N-Gram Size Changed");
							// Stop waiting for input as integer has already been given
							waitForIntegerInput = false;
							// Return user to settings options menu
							settingsOptionSelected();
						}

						// If integer given is outside of accepted range (1 - 5)
						else {
							// Display error message
							System.out.println("N-Gram Size Must Be An Integer Between 1 - 5");
							System.out.println();
							// Ask the user to give another input
							System.out.println("New N-Gram Size :");
						}

						// Could not use input given by the user as it was not an integer
					} catch (NumberFormatException e) {
						// Display error message
						System.out.println("N-Gram Size Must Be An Integer Between 1 - 5");
						System.out.println();
						// Ask the user to give another input
						System.out.println("New N-Gram Size :");
					}

				}

			}

			// User has selected option 7
			if (userQuitOption.equals("7")) {
				// Stop waiting for option from user as an option has been selected
				waitForInput = false;
				System.out.println();
				// Return user to the main menu
				printOptionsMenu();
			}
		}

	}
}
