import java.awt.EventQueue;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

public class Login {

	JFrame frame;
	public String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
					window.frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	JTextField UN;
	
	/**
	 * Create the application.
	 */
	public void loadprofile(String id){
		MyProfile p = new MyProfile(id);
		String q ="select * from Members where id_no=?";
		PreparedStatement pt;
		try {
			pt = connection.prepareStatement(q);
			pt.setString(1, id);
			ResultSet r = pt.executeQuery();
			while(r.next()){
				p.textField_1.setText(id);
				p.textField.setText(r.getString("Name"));
				p.textField_2.setText(r.getString("Class"));
				p.textField_3.setText(r.getString("Join_Date"));
				p.textField_4.setText(r.getString("Expiry_Date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String q1="Select  Name,B_Name,Issue_Date,Due_Date from Members m inner join IssueLog i on m.id_no=i.id_no inner join Books b on b.Book_id = i.Book_id where i.id_no=? ";
		try {
			PreparedStatement p1=connection.prepareStatement(q1);
			p1.setString(1, id);
			ResultSet r1 = p1.executeQuery();
			p.table.setModel(DbUtils.resultSetToTableModel(r1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.frame.setVisible(true);
			
		
	}
	public Login() {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e) {
			e.printStackTrace();
			}
		initialize();
		connection = sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1277, 716);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
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
		lblUserId.setForeground(SystemColor.info);
		lblUserId.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblUserId.setBounds(294, 218, 92, 20);
		frame.getContentPane().add(lblUserId);
		
		UN = new JTextField();
		UN.setBounds(401, 217, 608, 26);
		frame.getContentPane().add(UN);
		UN.setColumns(10);
		
		
		
		
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setForeground(SystemColor.textHighlight);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
			
					String query="select * from Members where Id_no =?";
					id=UN.getText();
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1,id );
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next())
					{
						count++;
					}
						if(count==1){
							frame.dispose();
							Search s = new Search(id);
							s.frame.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "User Id is Incorrect");
						
					pst.close();
					rs.close();
					
			
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		
		JLabel lblLogInAs = new JLabel("Log In as admin?");
		lblLogInAs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				AdminLogin l =new AdminLogin();
				l.frame.setVisible(true);
			}
		});
		lblLogInAs.setForeground(Color.CYAN);
		lblLogInAs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblLogInAs.setBounds(613, 298, 191, 20);
		frame.getContentPane().add(lblLogInAs);
		
		btnNewButton.setBounds(640, 259, 115, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\user\\Pictures\\l3.jpg"));
		lblNewLabel.setBounds(0, 0, 1294, 1080);
		frame.getContentPane().add(lblNewLabel);
		
		
	}
}
