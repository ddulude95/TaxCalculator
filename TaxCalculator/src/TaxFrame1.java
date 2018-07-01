import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.DefaultComboBoxModel;
import java.util.Scanner; 
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Component;
import java.lang.NumberFormatException;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import java.awt.Dimension; 
public class TaxFrame1 extends JFrame {

	private JPanel contentPane;
	private JTextField incomeTextField;
	private Scanner scanner; 
	private Scanner lineScanner;
	private CountryTax countryOperator; 
	private String country;
	private Double income;
	private JTextArea taxDisplayArea;
	private JTextField incomeField2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaxFrame1 frame = new TaxFrame1();
					frame.setTitle("Tax Calculator");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public TaxFrame1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1279, 771);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		
		JPanel taxPanel = new JPanel();
		taxPanel.setPreferredSize(new Dimension(40, 40));
		taxPanel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		taxPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		taxPanel.setForeground(Color.GRAY);
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(taxPanel, GroupLayout.DEFAULT_SIZE, 1257, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(taxPanel, GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
		);
		
		JLabel lblTaxCalculatorV = new JLabel("Tax Calculator V1");
		lblTaxCalculatorV.setForeground(Color.BLUE);
		lblTaxCalculatorV.setOpaque(true);
		lblTaxCalculatorV.setBackground(Color.LIGHT_GRAY);
		lblTaxCalculatorV.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblTaxCalculatorV.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTaxCalculatorV.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblOperations = new JLabel("Operations");
		lblOperations.setForeground(Color.BLUE);
		lblOperations.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblOperations.setOpaque(true);
		lblOperations.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblOperations.setBackground(Color.LIGHT_GRAY);
		lblOperations.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JButton btn_setIncome = new JButton("Set Income");
		
		btn_setIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event)  {
				
				Scanner incomeScanner = new Scanner(incomeTextField.getText());
					if(incomeScanner.hasNextDouble() && incomeScanner.nextDouble() > 0) {
						income = Double.parseDouble(incomeTextField.getText());
						incomeField2.setText(Double.toString(income));
							
				
					
							incomeScanner.close(); 
					}
				}
		});
		
		btn_setIncome.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		incomeTextField = new JTextField();
		incomeTextField.setBorder(new LineBorder(Color.BLACK));
		incomeTextField.setColumns(10);
		
		
		
		JComboBox<String> countryBox = new JComboBox();
		countryBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				country = countryBox.getSelectedItem().toString();
			}
		});
		countryBox.setActionCommand("");
		countryBox.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		countryBox.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		countryBox.setToolTipText("");
		countryBox.setFont(new Font("Times New Roman", Font.BOLD, 14));
		try {
		scanner = new Scanner(new File("taxes.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
			System.exit(-1);
		}
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine(); 
			lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");
			if (!line.startsWith("#")) {
				String country = lineScanner.next(); 
				countryBox.addItem(country);
			}
		}
		lineScanner.close(); 
		
		
		JButton btnCalculateTax = new JButton("Calculate Tax");
		btnCalculateTax.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnCalculateTax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
					 	if(Double.parseDouble(incomeTextField.getText()) > 0)
					 {
					 income = Double.parseDouble(incomeTextField.getText());
					 country = countryBox.getSelectedItem().toString();
					 }
					 try {
						initTaxObject(country,income);
					} catch (FileNotFoundException e) {
						System.out.println(e);;
					}
					 if((Double.parseDouble(incomeTextField.getText()) > 0))
					 {
					 countryOperator.calculateTotal();
					 taxDisplayArea.setText(countryOperator.getFormattedTotal());
					 }
				 }
				 catch(NumberFormatException e)
				 {
					 
				 }
			}
		});
		
		taxDisplayArea = new JTextArea();
		taxDisplayArea.setSelectionColor(Color.BLACK);
		taxDisplayArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		taxDisplayArea.setEditable(false);
		
		JLabel lblSetCountry = new JLabel("Set Country");
		lblSetCountry.setBorder(new EmptyBorder(0, 0, 0, 0));
		lblSetCountry.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		incomeField2 = new JTextField();
		incomeField2.setBorder(new LineBorder(new Color(0, 0, 0)));
		incomeField2.setForeground(Color.BLACK);
		incomeField2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		incomeField2.setBackground(Color.GREEN);
		incomeField2.setEditable(false);
		incomeField2.setSelectionColor(Color.GREEN);
		incomeField2.setColumns(10);
		
		JLabel lblIncome = new JLabel("Income:");
		lblIncome.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		GroupLayout gl_taxPanel = new GroupLayout(taxPanel);
		gl_taxPanel.setHorizontalGroup(
			gl_taxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_taxPanel.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_taxPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_taxPanel.createSequentialGroup()
							.addGap(442)
							.addComponent(lblTaxCalculatorV))
						.addGroup(gl_taxPanel.createSequentialGroup()
							.addGap(271)
							.addGroup(gl_taxPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_taxPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(btn_setIncome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnCalculateTax, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_taxPanel.createSequentialGroup()
										.addComponent(lblOperations, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(23)))
								.addComponent(lblSetCountry, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(gl_taxPanel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(taxDisplayArea, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_taxPanel.createSequentialGroup()
									.addComponent(incomeTextField, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(lblIncome)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(incomeField2, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
								.addComponent(countryBox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(560, Short.MAX_VALUE))
		);
		gl_taxPanel.setVerticalGroup(
			gl_taxPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_taxPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTaxCalculatorV, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblOperations, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addGroup(gl_taxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(countryBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSetCountry, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(gl_taxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(incomeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIncome)
						.addGroup(gl_taxPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(incomeField2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btn_setIncome, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(46)
					.addGroup(gl_taxPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(taxDisplayArea, GroupLayout.PREFERRED_SIZE, 385, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCalculateTax))
					.addGap(8))
		);
		taxPanel.setLayout(gl_taxPanel);
		contentPane.setLayout(groupLayout);
	}
	
	private void initTaxObject(String country, Double income) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File("taxes.txt"));
		String token = "";
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
				if (line.startsWith(country)) {
					lineScanner.next();
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
		
		countryOperator = new CountryTax(country,bracket1,bracket2,bracket3,bracket4,bracket5,bracket6,bracket7,
				percentage1,percentage2,percentage3,percentage4,percentage5,percentage6,percentage7,income);
	}
}