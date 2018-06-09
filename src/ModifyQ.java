import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;

public class ModifyQ
{

	JFrame fi;
	private JTextField textField;
	private JTextField tt;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	Connection co=null;
	private JTextField te;
	private JTable table;
	public ModifyQ()
	{
		co=sqliteCon.dbCon();
		initialize();
	}
	
	public void refreshTable()
	{
		try
		{
		String s="select qid,ques from qbank";
		PreparedStatement pst=co.prepareStatement(s);
		ResultSet rs=pst.executeQuery();
		table.setModel(DbUtils.resultSetToTableModel(rs));
		rs.close();
		pst.close();
		
		}
		catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		};
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		fi = new JFrame();
		fi.setTitle("Modify");
		fi.setVisible(true);
		fi.setBounds(450, 200, 488, 534);
		fi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fi.getContentPane().setLayout(null);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setBounds(67, 173, 55, 16);
		fi.getContentPane().add(lblSubject);
		
		JTextField comboBox = new JTextField();
		comboBox.setBounds(273, 168, 131, 26);
		fi.getContentPane().add(comboBox);
		
		JLabel lblTypeOfQuestion = new JLabel("Type of question");
		lblTypeOfQuestion.setBounds(67, 210, 90, 16);
		fi.getContentPane().add(lblTypeOfQuestion);
		
		JPanel panel = new JPanel();
		panel.setBounds(54, 314, 103, 58);
		fi.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(67, 307, 171, 147);
		fi.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JComboBox comboBo = new JComboBox();
		comboBo.setBounds(273, 205, 131, 26);
		comboBo.addItem("MCQ");
		comboBo.addItem("Fill in the blanks");
		comboBo.addItem("True/false");
		comboBo.setSelectedItem("MCQ");
		fi.getContentPane().add(comboBo);
		
		tt = new JTextField();
		tt.setColumns(10);
		tt.setBounds(128, 283, 276, 28);
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
		lblQuestion.setBounds(67, 249, 55, 16);
		fi.getContentPane().add(lblQuestion);
		
		textField = new JTextField();
		textField.setBounds(128, 243, 276, 28);
		fi.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblAnswer = new JLabel("Answer");
		lblAnswer.setBounds(67, 286, 55, 16);
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
		
		JButton btnNewButton = new JButton("Modify");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String q,a1,a2,a3,a4,c,t,s;int id;
				q=textField.getText();
				t=comboBo.getSelectedItem().toString();
				s=comboBox.getText();
				id=Integer.parseInt(te.getText());
				
				try
				{
				String ss="select * from qbank where qid="+id;
				PreparedStatement pst2=co.prepareStatement(ss);
				ResultSet rs=pst2.executeQuery();
				int c1=0;
				while(rs.next())
				{
					c1++;
				}
				if(c1>0)
				{
				
				
				rs.close();
				pst2.close();
				
				switch(t)
				{
					case "MCQ":
						try {
							a1=textField_2.getText();
							a2=textField_3.getText();
							a3=textField_4.getText();
							a4=textField_5.getText();
							c=bg.getSelection().getActionCommand();
							String q1="update qbank set ques=?,type=?,ans1=?,ans2=?,ans3=?,ans4=?,subject=?,choice=? where qid="+id;
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
							JOptionPane.showMessageDialog(null, "Modify successful");
							pst1.close();
							}
							catch(Exception ex) {
								JOptionPane.showMessageDialog(null, ex);
							};
						break;
					case "Fill in the blanks":
						try {
							a1=tt.getText();
							String q1="update qbank set ques=?,type=?,ans1=?,subject=? where qid="+id;
							PreparedStatement pst1=co.prepareStatement(q1);
							pst1.setString(1,q);
							pst1.setString(2,t);
							pst1.setString(3,a1);
							pst1.setString(4,s);
							pst1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modify successful");
							pst1.close();
							}
							catch(Exception ex) {
								JOptionPane.showMessageDialog(null, ex);
							};
						break;
					case "True/false":
						try {
							a1=bg1.getSelection().getActionCommand();
							String q1="update qbank set ques=?,type=?,ans1=?,subject=? where qid="+id;
							PreparedStatement pst1=co.prepareStatement(q1);
							pst1.setString(1,q);
							pst1.setString(2,t);
							pst1.setString(3,a1);
							pst1.setString(4,s);
							pst1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modify successful");
							pst1.close();
							}
							catch(Exception ex) {
								JOptionPane.showMessageDialog(null, ex);
							};
						break;
					default: ;
				}
				}
				else JOptionPane.showMessageDialog(null, "The requested question does not exist");
				}
				catch(Exception p) {
					JOptionPane.showMessageDialog(null, p);
				};
				refreshTable();
			}
		});
		btnNewButton.setBounds(250, 444, 90, 28);
		fi.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fi.dispose();
				home h=new home();
			}
		});
		btnBack.setBounds(352, 444, 90, 28);
		fi.getContentPane().add(btnBack);
		
		JLabel lblInsertQuestion = new JLabel("Modify Question");
		lblInsertQuestion.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblInsertQuestion.setBounds(25, 6, 164, 41);
		fi.getContentPane().add(lblInsertQuestion);
		
		JLabel lblQid = new JLabel("QID");
		lblQid.setBounds(67, 132, 55, 16);
		fi.getContentPane().add(lblQid);
		
		te = new JTextField();
		te.setBounds(360, 126, 44, 28);
		fi.getContentPane().add(te);
		te.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 46, 336, 74);
		fi.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		refreshTable();
		
		
		
		
	}
}
