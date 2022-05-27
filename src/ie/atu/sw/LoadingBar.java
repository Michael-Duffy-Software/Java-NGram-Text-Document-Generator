package ie.atu.sw;

// LOADING BAR : This class is responsible for printing a loading bar
public class LoadingBar {
	
	// LOAD : This method prints a loading bar
	public static void load() {
		
		// These symbols are used to display the loading bar progress
		String loaded = "==========";
		char loading = ' ';
		
		// Record Row Number
		int rowNumber = 0;
		
		// Print Loading Bar Header
        System.out.println("***************************************** LOADING N-GRAM BUILDER *****************************************");
        System.out.println();
        
        // Print 10 Rows
		for (int i = 0 ; i < 10; i++) {
			
			rowNumber++;
			
			// Print Bar Opening
			System.out.print("[");
			
			// Print Loaded Symbol For Number of Rows
			for (int j = 0; j < rowNumber; j++) {
				System.out.print(loaded);
			}
			
			// Print Loading Symbol For Number of Rows Remaining
			for (int k = 0; k < (100 - (rowNumber*10)); k++) {
				System.out.print(loading);
			}
			
			//Print Bar Closing
			System.out.print("]");
			// Print Perctange Loaded
			System.out.println(" " + ((i*10)+10) + "%");
			
			System.out.println();
		}
		System.out.println();
    }
	
	
	
	
	

}
