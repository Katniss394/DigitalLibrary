import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminLogin {

	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	String password;
	Connection con;
	int id;
	String ids;
	/**
	 * Create the application.
	 */
	public AdminLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		con=sqliteConnection.dbConnector();
		frame = new JFrame();
		frame.setBounds(100, 100, 1282, 699);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setResizable(false);
		frame.setSize(1200, 700);
		frame.setLocation(400,200);
		frame.addWindowStateListener(new WindowStateListener() {
			
			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub
				if(e.getNewState()==JFrame.MAXIMIZED_BOTH){
					frame.setExtendedState(JFrame.NORMAL);
				}
				
			}
		});
		
		JLabel lblUserId = new JLabel("User Id");
		lblUserId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUserId.setForeground(Color.BLACK);
		lblUserId.setBounds(321, 207, 87, 20);
		frame.getContentPane().add(lblUserId);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				id=Integer.parseInt(textField.getText());
				ids=textField.getText();
				
			}
		});
		textField.setBounds(432, 206, 310, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPassword.setBounds(321, 252, 105, 20);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				password=passwordField.getText();
			}
		});
		passwordField.setBounds(432, 248, 310, 26);
		frame.getContentPane().add(passwordField);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int count=0;
				String query="Select * from Admin where UserId=? and Password=?";
				try {
					PreparedStatement ps=con.prepareStatement(query);
					ps.setString(1,ids);
					ps.setString(2, password);
					ResultSet rs=ps.executeQuery();
					while(rs.next()){
						count++;
					}
					rs.close();
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Authentication Failed");
				}
				
				if(count==1){
					frame.dispose();
					AdminHome h =new AdminHome();
					h.frame.setVisible(true);
					
				}
				else{
					JOptionPane.showMessageDialog(null, "Authentication Failed");
				
				}
			}
		});
		btnLogIn.setForeground(SystemColor.textHighlight);
		btnLogIn.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnLogIn.setBounds(491, 305, 115, 29);
		frame.getContentPane().add(btnLogIn);
		JLabel lblChangePassword = new JLabel("Change Password?");
		lblChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ChangePassword p=new ChangePassword();
				p.setVisible(true);
			}
		});
		lblChangePassword.setForeground(Color.CYAN);
		lblChangePassword.setBackground(Color.CYAN);
		lblChangePassword.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblChangePassword.setBounds(467, 350, 178, 20);
		frame.getContentPane().add(lblChangePassword);
		
		JLabel lblK = new JLabel("");
		lblK.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\img8.jpg"));
		lblK.setBounds(0, 0, 2560, 1600);
		frame.getContentPane().add(lblK);
		
		
		
	}
}
