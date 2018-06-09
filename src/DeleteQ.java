import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
public class DeleteQ
{

	JFrame df;
	private JTextField textField;

	Connection co=null;
	private JTable table;
	public DeleteQ()
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

	private void initialize()
	{
		df = new JFrame();
		df.setTitle("Delete");
		df.setVisible(true);
		df.setBounds(450, 200, 467, 380);
		df.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		df.getContentPane().setLayout(null);
		
		JLabel lblDeleteQuestion = new JLabel("Delete Question");
		lblDeleteQuestion.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblDeleteQuestion.setBounds(29, 25, 164, 41);
		df.getContentPane().add(lblDeleteQuestion);
		
		JLabel lblEnterQid = new JLabel("Enter QID");
		lblEnterQid.setBounds(39, 243, 77, 16);
		df.getContentPane().add(lblEnterQid);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(377, 237, 44, 28);
		df.getContentPane().add(textField);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int act=JOptionPane.showConfirmDialog(null,"Do you really want to delete question "+textField.getText());
				if(act==0)
				{
				try {
					
					String ss="select * from qbank where qid=?";
					PreparedStatement pst1=co.prepareStatement(ss);
					pst1.setString(1, textField.getText() );
					
					ResultSet rs=pst1.executeQuery();
					int c=0;
					while(rs.next())
					{
						c++;
					}
					if(c>0);
					else JOptionPane.showMessageDialog(null, "The requested question does not exist");
					
					rs.close();
					pst1.close();
					
					String q="delete from qbank where qid=?";
					PreparedStatement pst=co.prepareStatement(q);
					pst.setString(1, textField.getText() );
					
					pst.executeUpdate();
					pst.close();
					}
					catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex);
					};
				
				refreshTable();
				}
			}
		});
		btnDelete.setBounds(229, 292, 90, 28);
		df.getContentPane().add(btnDelete);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				df.dispose();
				home h=new home();
			}
		});
		button_1.setBounds(331, 292, 90, 28);
		df.getContentPane().add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 65, 382, 166);
		df.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		refreshTable();
		
	}
}
