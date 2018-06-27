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
public class TaxFrame1 extends JFrame {

	private JPanel contentPane;
	private JTextField textField_1;
	private Scanner scanner; 
	private Scanner lineScanner;
	private CountryTax countryOperator = new CountryTax(); 
	private JTextArea txtr;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaxFrame1 frame = new TaxFrame1();
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
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(30))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 712, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblTaxCalculatorV = new JLabel("Tax Calculator V1");
		lblTaxCalculatorV.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTaxCalculatorV.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JLabel lblOperations = new JLabel("Operations");
		lblOperations.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		JButton btnNewButton_1 = new JButton("Set Income");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) throws NumberFormatException {
				if(!textField_1.getText().equals("")) {
				countryOperator.setIncome(Double.parseDouble(textField_1.getText()));
				textField.setText(Double.toString(countryOperator.getIncome()));
				}
			}
		});
		
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				countryOperator.setCountry(comboBox.getSelectedItem().toString());
			}
		});
		comboBox.setActionCommand("");
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 14));
		try {
		scanner = new Scanner(new File("taxes.txt"));
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine(); 
			lineScanner = new Scanner(line);
			lineScanner.useDelimiter(",");
			if (!line.startsWith("#")) {
				String country = lineScanner.next(); 
				comboBox.addItem(country);
			}
		}
		lineScanner.close(); 
		
		
		JButton btnCalculateTax = new JButton("Calculate Tax");
		btnCalculateTax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try {
					 countryOperator.calculateTotal();
					 txtr.setText(Double.toString(countryOperator.getTotal()));
				 }
				 catch(NullPointerException e)
				 {
					 System.out.println("Invalid Input");
				 }
			}
		});
		
		txtr = new JTextArea();
		
		JLabel lblSetCountry = new JLabel("Set Country");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblIncome = new JLabel("Income:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblOperations)
									.addGap(480)
									.addComponent(lblTaxCalculatorV, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(18)
										.addComponent(lblSetCountry)
										.addGap(43)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_panel.createSequentialGroup()
												.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
												.addComponent(lblIncome)
												.addGap(18)
												.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)))
									.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
										.addComponent(btnCalculateTax)
										.addGap(27)
										.addComponent(txtr, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE))))
							.addGap(563))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1)
							.addContainerGap(1118, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(49)
							.addComponent(lblOperations))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(26)
							.addComponent(lblTaxCalculatorV, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSetCountry))
					.addGap(36)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIncome))
					.addGap(44)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCalculateTax)
						.addComponent(txtr, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(groupLayout);
	}
	public CountryTax getCount()
	{
		return countryOperator; 
	}
}