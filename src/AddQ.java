import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class AddQ
{

	private JFrame af;
	
	String a,b;
	int n;
	Connection co=null;
	public AddQ(String s1,int n1)
	{
		co=sqliteCon.dbCon();
		a=s1.trim();
		n=n1;
		initialize();
	}

	
	
	private void initialize()
	{
		af = new JFrame();
		af.setTitle("Add");
		af.setVisible(true);
		af.setBounds(450, 200, 483, 338);
		af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		af.getContentPane().setLayout(null);
		
		JButton btnSelectMoreTypes = new JButton("Back");
		btnSelectMoreTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				af.dispose();
				GenT g=new GenT();
			}
		});
		btnSelectMoreTypes.setBounds(367, 262, 90, 28);
		af.getContentPane().add(btnSelectMoreTypes);
		
		JLabel lblAddToQuiz = new JLabel("Almost there..");
		lblAddToQuiz.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblAddToQuiz.setBounds(24, 21, 164, 41);
		af.getContentPane().add(lblAddToQuiz);
		int ll=0;
		JButton btnAddToFile = new JButton("Generate");
		btnAddToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					
					for( int i=0;i<n;)
					{
					String s="select * from qbank where subject=?";
					PreparedStatement pst=co.prepareStatement(s);
					pst.setString(1, a);
					ResultSet rs=pst.executeQuery();
					int c1=0;
					while(rs.next())
					{
						c1++;
					}
					rs.close();
					pst.close();
					if(n<=c1 && n>=0)
					{
					s="select * from qbank where subject=?";
					pst=co.prepareStatement(s);
					pst.setString(1, a);
					rs=pst.executeQuery();
					Random r=new Random();
					int ra=r.nextInt(c1)+1;
					for(int j=0;j<ra-1;j++)
						rs.next();
					if(rs.getInt(10)==0)
					{
						i++;
					
					String q=rs.getString(2)+"\n\n";
					String sol;
					if(rs.getString(3).equals("MCQ"))
					{
						q+="1. "+rs.getString(4)+"\n"+"2. "+rs.getString(5)+"\n"+"3. "+rs.getString(6)+"\n"+"4. "+rs.getString(7)+"\n\n";
						sol="Option "+rs.getString(9)+"\n\n";
					}
					else sol=rs.getString(4)+"\n\n";
					
					FileWriter fw=new FileWriter("Question Paper.txt",true);
					
					fw.write(q);
					fw.close();
					
					fw=new FileWriter("Solutions.txt",true);
					
					fw.write(sol);
					fw.close();
					
					
					
					String p="update qbank set mark=1 where qid="+rs.getString(1);
					
					PreparedStatement f=co.prepareStatement(p);
					rs.close();
					pst.close();
					f.executeUpdate();
					f.close();
					}
					
					}
					else { JOptionPane.showMessageDialog(null, "Not enough questions");break;}
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
					ex.printStackTrace();
				};
				JOptionPane.showMessageDialog(null, "Question added to quiz file Question Paper.txt\nSolutions at Solutions.txt");
			}
		});
		btnAddToFile.setBounds(173, 262, 90, 28);
		af.getContentPane().add(btnAddToFile);
		
		JButton btnClearFile = new JButton("Clear files");
		btnClearFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String q="update qbank set mark=0 where mark=1";
					PreparedStatement k=co.prepareStatement(q);
					k.executeUpdate();
					k.close();
					FileWriter fw=new FileWriter("Question Paper.txt");
					fw.write("~~~~~~~~~~~  QUIZ  ~~~~~~~~~~~\n\n\n");
					fw.close();
					fw=new FileWriter("Solutions.txt");
					fw.write("~~~~~~~~~~~  SOLUTIONS  ~~~~~~~~~~~\n\n\n");
					fw.close();

				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				};
				JOptionPane.showMessageDialog(null,"Question Paper and Solutions file cleared");
				
			}
		});
		btnClearFile.setBounds(273, 262, 90, 28);
		af.getContentPane().add(btnClearFile);
		
		JTextPane pana = new JTextPane();
		pana.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		pana.setEditable(false);
		pana.setText("Please make sure that you clear the existing files before generating a new quiz. The quiz will be exported to Question Paper.txt and the solutions to Solutions.txt in the Project directory.");
		pana.setBounds(90, 101, 272, 108);
		af.getContentPane().add(pana);
		//refreshTable();
	}
}
