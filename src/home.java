import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class home
{

	JFrame fr;


	public home()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		fr = new JFrame();
		fr.setTitle("Homepage");
		fr.setVisible(true);
		fr.setBounds(450, 200, 506, 341);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.getContentPane().setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome to QuizGenerator v1.0");
		lblWelcome.setFont(new Font("Segoe UI", Font.PLAIN, 22));
		lblWelcome.setBounds(31, 22, 399, 46);
		fr.getContentPane().add(lblWelcome);
		
		JLabel lblWhatDoYou = new JLabel("What do you want to do?");
		lblWhatDoYou.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWhatDoYou.setBounds(31, 79, 180, 21);
		fr.getContentPane().add(lblWhatDoYou);
		
		JButton btnGenerateTest = new JButton("Generate Test");
		btnGenerateTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				GenT g=new GenT();
			}
		});
		btnGenerateTest.setBounds(171, 111, 132, 28);
		fr.getContentPane().add(btnGenerateTest);
		
		JButton btnInsertAQuestion = new JButton("Insert a question");
		btnInsertAQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				InsertQ i=new InsertQ();
			}
		});
		btnInsertAQuestion.setBounds(171, 150, 132, 28);
		fr.getContentPane().add(btnInsertAQuestion);
		
		JButton btnModifyAQuestion = new JButton("Modify a question");
		btnModifyAQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				ModifyQ m=new ModifyQ();
			}
		});
		btnModifyAQuestion.setBounds(171, 189, 132, 28);
		fr.getContentPane().add(btnModifyAQuestion);
		
		JButton btnDeleteAQuestion = new JButton("Delete a question");
		btnDeleteAQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				DeleteQ d=new DeleteQ();
			}
		});
		btnDeleteAQuestion.setBounds(171, 228, 132, 28);
		fr.getContentPane().add(btnDeleteAQuestion);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fr.dispose();
				login l=new login();
			}
		});
		btnLogout.setBounds(375, 263, 90, 28);
		fr.getContentPane().add(btnLogout);
	}
}
