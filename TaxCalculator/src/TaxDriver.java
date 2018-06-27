import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.ArrayList;
import javax.swing.JFrame; 


public class TaxDriver {

	public static void main(String[] args) throws FileNotFoundException {
		
		//doInput();
		
		JFrame frame = new TaxFrame1(); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tax Calculator");
		frame.setVisible(true);
		
		// This is just a tester for now
		
		
	} // end main
	
	
	public static void doInput() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("taxes.txt"));
		String token = "";
		String countryToFind = "United States";
		String country = ""; 
		Double percentage1 = 0.0;
		Double percentage2 = 0.0; 
		Double percentage3 = 0.0;
		Double percentage4 = 0.0; 
		Double percentage5 = 0.0;
		Double percentage6 = 0.0;
		Double percentage7 = 0.0;
		Double bracket1 = 0.0; 
		Double bracket2 = 0.0;
		Double bracket3 = 0.0;
		Double bracket4 = 0.0;
		Double bracket5 = 0.0;
		Double bracket6 = 0.0;
		Double bracket7 = 0.0;
		
		
		while(scanner.hasNextLine()) { // start while1
			String line = scanner.nextLine();
			Scanner lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");
			
			// this if statement checks for comment lines
			if (!line.startsWith("#")) {
				// start going through the tokens in the line
				// and only continuing if this is the country we want
				country = lineScanner.next();
				if (line.startsWith(countryToFind)) {
					// we are expecting exactly 15 tokens from the text file
					// otherwise we will get a null pointer.
					
					bracket1 = lineScanner.nextDouble();
					bracket2 = lineScanner.nextDouble();
					bracket3 = lineScanner.nextDouble();
					bracket4 = lineScanner.nextDouble();
					bracket5 = lineScanner.nextDouble();
					bracket6 = lineScanner.nextDouble();
					bracket7 = lineScanner.nextDouble();
					percentage1 = lineScanner.nextDouble();
					percentage2 = lineScanner.nextDouble();
					percentage3 = lineScanner.nextDouble();
					percentage4 = lineScanner.nextDouble();
					percentage5 = lineScanner.nextDouble();
					percentage6 = lineScanner.nextDouble();
					percentage7 = lineScanner.nextDouble();			
				}	
			}
			lineScanner.close();
		}
		scanner.close();
		
		
		ArrayList<Double> taxTotals = new ArrayList<Double>();
		
		//CountryTax taxTest = new CountryTax("USA", 10000.0, 30000.0, 70000.0, 90000.0, 0.0, 0.0, 0.0, 0.20, 0.25, 0.30, 0.35, 0.40, 0.45, 0.50, 0.0);
		CountryTax taxTest = new CountryTax(countryToFind,bracket1,bracket2,bracket3,bracket4,bracket5,bracket6,bracket7,
								percentage1,percentage2,percentage3,percentage4,percentage5,percentage6,percentage7,125314.0);
		//taxTest.setIncome(89000.0);
		taxTotals = taxTest.calculateTotal();
		
		// A for loop to parse and print the tax
        // deductions
		System.out.println(taxTest.getFormattedTotal());
		
		//System.out.println(taxTest.fullToString());
		//System.out.println(taxTest);
	}

} // end class
