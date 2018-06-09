import java.sql.*;

import javax.swing.JOptionPane;
public class sqliteCon
{
	Connection con=null;
	public static Connection dbCon()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:Z:\\COding\\eclipse-workspa\\OOP_Mini_Project\\Data.sqlite");
			JOptionPane.showMessageDialog(null, "connected");
			return con;
		}
		catch(Exception e) {return null;}
	}
}
