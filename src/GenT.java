import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;

public class GenT
{

	JFrame gf;
	private JTextField textField;


	public GenT()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		gf = new JFrame();
		gf.setTitle("Generate");
		gf.setVisible(true);
		gf.setBounds(450, 200, 450, 300);
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.getContentPane().setLayout(null);
		
		JLabel lblSelectSubject = new JLabel("Select subject");
		lblSelectSubject.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblSelectSubject.setBounds(67, 55, 100, 22);
		gf.getContentPane().add(lblSelectSubject);
		
		JTextField comboBox = new JTextField();
		comboBox.setBounds(245, 55, 64, 26);
		gf.getContentPane().add(comboBox);
		
		JLabel lblGenerateTest = new JLabel("Generate test");
		lblGenerateTest.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblGenerateTest.setBounds(17, 6, 164, 41);
		gf.getContentPane().add(lblGenerateTest);
		
		JButton btnGenerate = new JButton("Proceed");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gf.dispose();
				try {
				AddQ a=new AddQ(comboBox.getText(),Integer.parseInt(textField.getText()));
				}
				catch(Exception ee) {JOptionPane.showMessageDialog(null, "Invalid request");};
			}
		});
		btnGenerate.setBounds(219, 221, 90, 28);
		gf.getContentPane().add(btnGenerate);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gf.dispose();
				home h=new home();
			}
		});
		btnBack.setBounds(321, 221, 90, 28);
		gf.getContentPane().add(btnBack);
		
		JLabel lblNumerOfQuestions = new JLabel("Number of questions");
		lblNumerOfQuestions.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		lblNumerOfQuestions.setBounds(67, 116, 139, 22);
		gf.getContentPane().add(lblNumerOfQuestions);
		
		textField = new JTextField();
		textField.setBounds(263, 115, 44, 28);
		gf.getContentPane().add(textField);
		textField.setColumns(10);
		
		
	}
}
