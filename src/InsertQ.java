import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.*;
import javax.swing.*;

public class InsertQ
{

	JFrame fi;
	private JTextField textField;
	private JTextField tt;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	Connection co=null;
	public InsertQ()
	{
		initialize();
		co=sqliteCon.dbCon();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		fi = new JFrame();
		fi.setTitle("Insert");
		fi.setVisible(true);
		fi.setBounds(450, 200, 509, 442);
		fi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fi.getContentPane().setLayout(null);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(65, 80, 55, 16);
		fi.getContentPane().add(lblSubject);
		
		JTextField comboBox = new JTextField();
		comboBox.setBounds(271, 75, 131, 26);
		fi.getContentPane().add(comboBox);
		
		JLabel lblTypeOfQuestion = new JLabel("Type of question");
		lblTypeOfQuestion.setBounds(65, 117, 90, 16);
		fi.getContentPane().add(lblTypeOfQuestion);
		
		JPanel panel = new JPanel();
		panel.setBounds(52, 221, 103, 58);
		fi.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(65, 214, 171, 147);
		fi.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JComboBox comboBo = new JComboBox();
		comboBo.setBounds(271, 112, 131, 26);
		comboBo.addItem("MCQ");
		comboBo.addItem("Fill in the blanks");
		comboBo.addItem("True/false");
		comboBo.setSelectedItem("MCQ");
		fi.getContentPane().add(comboBo);
		
		tt = new JTextField();
		tt.setColumns(10);
		tt.setBounds(126, 190, 276, 28);
		fi.getContentPane().add(tt);
		
		tt.setVisible(false);
		panel.setVisible(false);
		panel_1.setVisible(true);
		comboBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=comboBo.getSelectedIndex();
				switch(n)
				{
					case 0: 
						tt.setVisible(false);
						panel.setVisible(false);
						panel_1.setVisible(true);
					
						break;
					case 1:
						tt.setVisible(true);
						panel.setVisible(false);
						panel_1.setVisible(false);
						
						break;
					case 2:
						tt.setVisible(false);
						panel.setVisible(true);
						panel_1.setVisible(false);
						
						break;
					default: ;
				}
			}
		});
		
		JLabel lblQuestion = new JLabel("Question");
		lblQuestion.setBounds(65, 156, 55, 16);
		fi.getContentPane().add(lblQuestion);
		
		textField = new JTextField();
		textField.setBounds(126, 150, 276, 28);
		fi.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setBounds(65, 193, 55, 16);
		fi.getContentPane().add(lblAnswer);
		
		
		
		JRadioButton rdbtnTrue = new JRadioButton("True");     
		rdbtnTrue.setBounds(29, 5, 47, 18);
		rdbtnTrue.setActionCommand(rdbtnTrue.getText());
		panel.add(rdbtnTrue);
		
		JRadioButton rdbtnFalse = new JRadioButton("False");
		rdbtnFalse.setBounds(29, 34, 53, 18);
		rdbtnFalse.setActionCommand(rdbtnFalse.getText());
		panel.add(rdbtnFalse);
		ButtonGroup bg1=new ButtonGroup();    
		bg1.add(rdbtnFalse);bg1.add(rdbtnTrue);
		
		
		
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(27, 5, 122, 28);
		panel_1.add(textField_2);
		
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(27, 38, 122, 28);
		panel_1.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(27, 71, 122, 28);
		panel_1.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(27, 103, 122, 28);
		panel_1.add(textField_5);
		
		JRadioButton b1 = new JRadioButton("4");
		b1.setBounds(6, 103, 18, 18);
		b1.setActionCommand(b1.getText());
		panel_1.add(b1);
		
		JRadioButton b2 = new JRadioButton("1");
		b2.setBounds(6, 5, 18, 18);
		b2.setActionCommand(b2.getText());
		panel_1.add(b2);
		
		JRadioButton b3 = new JRadioButton("3");
		b3.setBounds(6, 71, 18, 18);
		b3.setActionCommand(b3.getText());
		panel_1.add(b3);
		
		JRadioButton b4 = new JRadioButton("2");
		b4.setBounds(6, 38, 18, 18);
		b4.setActionCommand(b4.getText());
		panel_1.add(b4);
		
		ButtonGroup bg=new ButtonGroup();    
		bg.add(b1);bg.add(b2);bg.add(b3);bg.add(b4);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String q,a1,a2,a3,a4,c,t,s;
				q=textField.getText();
				t=comboBo.getSelectedItem().toString();
				s=comboBox.getText();
				switch(t)
				{
					case "MCQ":
						try {
							a1=textField_2.getText();
							a2=textField_3.getText();
							a3=textField_4.getText();
							a4=textField_5.getText();
							c=bg.getSelection().getActionCommand();
							String q1="insert into qbank (ques,type,ans1,ans2,ans3,ans4,subject,choice) values ( ? , ? , ? , ? , ? , ? , ? , ? )";
							PreparedStatement pst1=co.prepareStatement(q1);
							pst1.setString(1,q);
							pst1.setString(2,t);
							pst1.setString(3,a1);
							pst1.setString(4,a2);
							pst1.setString(5,a3);
							pst1.setString(6,a4);
							pst1.setString(7,s);
							pst1.setString(8,c);
							pst1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Insert successful");
							pst1.close();
							}
							catch(Exception ex) {
								JOptionPane.showMessageDialog(null, ex);
							};
						break;
					case "Fill in the blanks":
						try {
							a1=tt.getText();
							String q1="insert into qbank (ques,type,ans1,subject) values ( ? , ? , ? , ?  )";
							PreparedStatement pst1=co.prepareStatement(q1);
							pst1.setString(1,q);
							pst1.setString(2,t);
							pst1.setString(3,a1);
							pst1.setString(4,s);
							pst1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Insert successful");
							pst1.close();
							}
							catch(Exception ex) {
								JOptionPane.showMessageDialog(null, ex);
							};
						break;
					case "True/false":
						try {
							a1=bg1.getSelection().getActionCommand();
							String q1="insert into qbank (ques,type,ans1,subject) values ( ? , ? , ? , ?  )";
							PreparedStatement pst1=co.prepareStatement(q1);
							pst1.setString(1,q+"[T/F]");
							pst1.setString(2,t);
							pst1.setString(3,a1);
							pst1.setString(4,s);
							pst1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Insert successful");
							pst1.close();
							}
							catch(Exception ex) {
								JOptionPane.showMessageDialog(null, ex);
							};
						break;
					default: ;
				}
			}
		});
		btnNewButton.setBounds(271, 354, 90, 28);
		fi.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fi.dispose();
				home h=new home();
			}
		});
		btnBack.setBounds(373, 354, 90, 28);
		fi.getContentPane().add(btnBack);
		
		JLabel lblInsertQuestion = new JLabel("Insert Question");
		lblInsertQuestion.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblInsertQuestion.setBounds(25, 19, 164, 41);
		fi.getContentPane().add(lblInsertQuestion);
		
		
		
		
	}
}
