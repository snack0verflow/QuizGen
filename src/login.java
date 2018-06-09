import java.awt.*;



import java.sql.*;

import javax.swing.*;
import java.awt.event.*;

public class login
{

	JFrame f;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (Throwable e)
			{
				e.printStackTrace();
			}
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					login window = new login();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	Connection co=null;
	/**
	 * Create the application.
	 */
	public login()
	{
		co=sqliteCon.dbCon();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize()
	{
		f = new JFrame();
		f.setTitle("Welcome!");
		f.setVisible(true);
		f.setBounds(450, 200, 450, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().setLayout(null);
		
		JButton b = new JButton("Login");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				String q="select * from cred where user=? and pass=?";
				PreparedStatement pst=co.prepareStatement(q);
				pst.setString(1, textField.getText() );
				pst.setString(2, passwordField.getText());
				ResultSet rs=pst.executeQuery();
				int c=0;
				while(rs.next())
				{
					c++;
				}
				if(c==1)
				{
					pst.close();
					rs.close();
					f.dispose();
					home h=new home();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Username/Password combination");
				}
				
				
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				};
				
			}
		});
		b.setBounds(112, 172, 102, 28);
		f.getContentPane().add(b);
		
		JButton btnNewButton = new JButton("Sign-up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String q="select * from cred where user=?";
					PreparedStatement pst=co.prepareStatement(q);
					pst.setString(1, textField.getText() );
					ResultSet rs=pst.executeQuery();
					int c=0;
					while(rs.next())
					{
						c++;
					}
					if(c>0)
					{
						JOptionPane.showMessageDialog(null, "Username already exists");
					}
					else
					{
						try {
							String q1="insert into cred (user,pass) values ( ? , ? )";
							PreparedStatement pst1=co.prepareStatement(q1);
							pst1.setString(1, textField.getText() );
							pst1.setString(2, passwordField.getText());
							pst1.executeUpdate();
							JOptionPane.showMessageDialog(null, "Sign-up Successful");
							pst1.close();
							}
							catch(Exception ex) {
								JOptionPane.showMessageDialog(null, ex);
							};
						
					}
					
					pst.close();
					rs.close();
					}
					catch(Exception ex) {};
				
					
				}
			
		});
		btnNewButton.setBounds(218, 172, 102, 28);
		f.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(112, 82, 208, 28);
		f.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(112, 132, 208, 28);
		f.getContentPane().add(passwordField);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(112, 61, 70, 16);
		f.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(112, 117, 70, 16);
		f.getContentPane().add(lblPassword);
		
		JLabel lblHelloUser = new JLabel("Hello! User.");
		lblHelloUser.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblHelloUser.setBounds(27, 21, 122, 28);
		f.getContentPane().add(lblHelloUser);
		
		
	}
}
