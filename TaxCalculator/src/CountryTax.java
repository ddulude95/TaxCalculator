import java.util.ArrayList;

public class CountryTax {
	
	private String country = ""; 
	private Double bracket1 = 0.0; 
	private Double bracket2 = 0.0;
	private Double bracket3 = 0.0;
	private Double bracket4 = 0.0;
	private Double bracket5 = 0.0;
	private Double bracket6 = 0.0;
	private Double bracket7 = 0.0;
	private Double percentage1 = 0.0;
	private Double percentage2 = 0.0; 
	private Double percentage3 = 0.0;
	private Double percentage4 = 0.0; 
	private Double percentage5 = 0.0;
	private Double percentage6 = 0.0;
	private Double percentage7 = 0.0;
	private Double income = 0.0; 
	private Double total = 0.0; 
	private Double deduction1 = 0.0;
	private Double deduction2 = 0.0;
	private Double deduction3 = 0.0; 
	private Double deduction4 = 0.0;
	private Double deduction5 = 0.0; 
	private ArrayList<Double> taxedAmounts;
	private ArrayList<Double> brackets;
	private ArrayList<Double> percentages;
	
	
	
	public CountryTax()
	{
		country = "";
		bracket1 = bracket2 = bracket3 = bracket4 = bracket5 = bracket6 = bracket7 = 0.0;
		percentage1 = percentage2 = percentage3 = percentage4 = percentage5 = percentage6 = percentage7 = 0.0; 
		income = 0.0; 
	}

	public CountryTax(String country, Double bracket1, Double bracket2, Double bracket3, Double bracket4, Double bracket5, 
			          Double bracket6, Double bracket7, Double percentage1, Double percentage2, Double percentage3, 
			          Double percentage4, Double percentage5, Double percentage6, Double percentage7, Double income)
	{
		brackets = new ArrayList<Double>();
		percentages = new ArrayList<Double>();
		this.taxedAmounts = new ArrayList<Double>();	
		this.income = income; 
		this.country = country; 
		
		// setting brackets and adding them to 
		// the array list
		this.bracket1 = bracket1; 
		brackets.add(bracket1);
		
		this.bracket2 = bracket2; 
		brackets.add(bracket2);
		
		this.bracket3 = bracket3; 
		brackets.add(bracket3);
		
		this.bracket4 = bracket4; 
		brackets.add(bracket4);
		
		this.bracket5 = bracket5; 
		brackets.add(bracket5);
		
		this.bracket6 = bracket6;
		brackets.add(bracket6);
		
		this.bracket7 = bracket7;
		brackets.add(bracket7);
		
		// setting percentages and adding them to
		// the array list
		this.percentage1 = percentage1;
		percentages.add(percentage1);
		
		this.percentage2 = percentage2; 
		percentages.add(percentage2);
		
		this.percentage3 = percentage3;
		percentages.add(percentage3);
		
		this.percentage4 = percentage4;
		percentages.add(percentage4);
		
		this.percentage5 = percentage5; 
		percentages.add(percentage5);
		
		this.percentage6 = percentage6;
		percentages.add(percentage6);
		
		this.percentage7 = percentage7;
		percentages.add(percentage7);		
	}
	
	public String getCountry()
	{
		return country; 
	}
	
	public void setCountry(String country)
	{
		this.country = country; 
	}
	
	public double getIncome()
	{
		return income;
	}
	
	public void setIncome(Double income)
	{
		this.income = income;
	}
	
	
	public double getTotal()
	{
		return total;
	}
	
	// returns an arraylist of all the deductions + a total
	public ArrayList<Double> calculateTotal()
	{
		if(income <= bracket1)
		{
			  deduction1 = income * percentage1;
			  taxedAmounts.add(deduction1);
		}
		if(income > bracket1)
		{
			 deduction1 = bracket1 * percentage1;
			 taxedAmounts.add(deduction1);
		}
		if(income < bracket2 && income > bracket1)
		{
			 deduction2 = (income - bracket1) * percentage2;
			 taxedAmounts.add(deduction2);
		}
		if(income >= bracket2)
		{
			 deduction2 = (bracket2 - bracket1) * percentage2;
			 taxedAmounts.add(deduction2);
		}
		if(income < bracket3 && income > bracket2)
		{
			 deduction3 = (income - bracket2) * percentage3;
			 taxedAmounts.add(deduction3);
		}
		if(income >= bracket3)
		{
			 deduction3 = (bracket3 - bracket2) * percentage3;
			 taxedAmounts.add(deduction3);
		}
		if(income < bracket4 && income > bracket3)
		{
			 deduction4 = (income - bracket3) * percentage4;
			 taxedAmounts.add(deduction4);
		}
		if(income >= bracket4)
		{
			 deduction4 = (income - bracket3) * percentage4;
			 taxedAmounts.add(deduction4);
		}
		
		total = deduction1 + deduction2 + deduction3 + deduction4;
		taxedAmounts.add(total);
		return taxedAmounts;
	}
	
	public String getFormattedTotal()
	{
		
		String total = ("Country: "+this.country);
		total += 	   ("\nIncome: $"+this.income + "\n");
		
		
		for (int i = 0; i < taxedAmounts.size(); i++) {
			if (i != taxedAmounts.size()-1) {
				total += ("\nTax Deduction #"+(i+1)+": $"+taxedAmounts.get(i));
			}
			else {
				total += ("\n\nTotal Tax Deductions: $"+taxedAmounts.get(i));
			}	
		}	
		return total += ("\n Income After Tax: $" + Double.toString(+this.income - this.total));
	}
	
	
	// this toString only prints out the
	// brackets and percentages if there is a value other than 0
	@Override public String toString()
	{
		String string = "\nCountry: " + country + 
						"\nIncome: " + income + "\n";
		
		for (int i = 0; i < brackets.size(); i++) {
			if (brackets.get(i) != 0) {
				string += "\nBracket " + (i + 1) + ": " + brackets.get(i);
			}
		}
		
		string += "\n";
				
		for (int i = 0; i < percentages.size(); i++) {
			if (percentages.get(i) != 0) {
				string += "\nPercentage " + (i + 1) + ": " + percentages.get(i) * 100 + "%";
			}
		}
		
		return string;
	}
	
	// this toString prints everything
	public String fullToString()
	{
		String extra = "";
		if (country == "Canada") { extra = "\nIt's a great day for Canada, and therefore, the world!";}
		String string = 
				("\nCountry: " + country + extra +
				"\nIncome: " + income + "\n" +
				"\nBracket 1: " + bracket1 + 
				"\nBracket 2: " + bracket2 +
				"\nBracket 3: " + bracket3 +
				"\nBracket 4: " + bracket4 +
				"\nBracket 5: " + bracket5 +
				"\nBracket 6: " + bracket6 +
				"\nBracket 7: " + bracket7 + "\n" +
				
				"\nPercentage 1: " + (percentage1 * 100) + "%" +
				"\nPercentage 2: " + (percentage2 * 100) + "%" +
				"\nPercentage 3: " + (percentage3 * 100) + "%" +
				"\nPercentage 4: " + (percentage4 * 100) + "%" +
				"\nPercentage 5: " + (percentage5 * 100) + "%" +
				"\nPercentage 6: " + (percentage6 * 100) + "%" +
				"\nPercentage 7: " + (percentage7 * 100) + "%");
				
		
		return string;
	}

}