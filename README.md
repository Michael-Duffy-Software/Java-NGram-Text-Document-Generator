## N-Gram Frequency Builder Application



### **About The Project**

This application allows the user to generate a CSV file containing the frequency of occurrences of ngrams in a chosen text file. The app utilises a menu navigation allowing the user to change settings to specifie the input and output file details and to select the ngram size.


### **Project Details**
- Application allows user to navigate through console menu.
- Application takes user input to specify settings.
- Application opens text file and parses ngrams
- Application outputs a CSV file containing frequency of occurence of ngrams

### **Menu Navigation**

App allows the user to navigate through options to run the ngram builder, change settings and quit the program.

### **Change Settings**

Menu navigation allows the user to either update all settings or specific settings. As settings are updated app verifies that settings are acceptable or prompts the user to comply with setting criteria.

### **Settings Criteria**

Example of Acceptable Settings :
- Input File Location : C:\Users\User\Desktop
- Output File Location : C:\Users\User\Desktop
- Input File Name : document.txt
- Output File Name : document.csv
- N-Gram Size : 3

### **Settings Verification**

If the user attempts to run the build ngrams option before updating settings they are prompted to update the required settings before continuing.

### **Display N-Gram Report**

In addition to exporting a CSV file the app prints a report after completion of running the ngram builder. The report details the number of characters in the input file, the number of unique ngramsfound, the most frequency occurring ngram and the number of occurrences.

### **Running Details**
- Main method is contained in Runner.java
- To Run Jar File In Command Prompt: java -cp ./ngrammer.jar ie.atu.sw.Runner

### **Built With**
- Java
- Eclipse

### **Text Document Input**
![Table](/assets/images/ScreenshotofAllAlgorithmResults.png)

### **Console Output**
![Table](/assets/images/ScreenshotofAllAlgorithmResults.png)

### **CSV Output**
![Graph](/assets/images/GraphComparingAllAlgorithms.png)
