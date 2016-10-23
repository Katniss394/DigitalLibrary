import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import net.proteanit.sql.DbUtils;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;


public class Search extends JFrame {

	public JFrame frame;
	JTextField textField;
	int flag;
	String key,id;
	Search_Results s;


	

	/**
	 * Create the application.
	 */
	public Search(String i) {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e) {
			e.printStackTrace();
			}
		id=i;
		
		initialize();
	}

	Connection connection = null;

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		connection= sqliteConnection.dbConnector();
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1287, 695);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
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
		
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				key=textField.getText();
			}
		});
		textField.setBounds(193, 187, 963, 56);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				s=new Search_Results(flag,key,id);
				s.frame.setVisible(true);
			}
		});
		btnSearch.setBounds(558, 259, 115, 29);
		frame.getContentPane().add(btnSearch);
		
		JRadioButton rdbtnAuthor = new JRadioButton("Author");
		rdbtnAuthor.setFont(new Font("Tahoma", Font.BOLD, 20));
		rdbtnAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flag=0;
				
			}
		});
		rdbtnAuthor.setBounds(0, 173, 170, 29);
		frame.getContentPane().add(rdbtnAuthor);
		
		JRadioButton rdbtnTitle = new JRadioButton("Title");
		rdbtnTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		rdbtnTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag=1;
				
			}
		});
		rdbtnTitle.setBounds(0, 210, 104, 29);
		frame.getContentPane().add(rdbtnTitle);
		
		
		JRadioButton rdbtnSubjetc = new JRadioButton("Subject");
		rdbtnSubjetc.setFont(new Font("Tahoma", Font.BOLD, 20));
		rdbtnSubjetc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag = 2;
				
			}
		});
		rdbtnSubjetc.setBounds(0, 247, 134, 21);
		frame.getContentPane().add(rdbtnSubjetc);
		
		JLabel lblSearchBy = new JLabel("Search By");
		lblSearchBy.setFont(new Font("Tahoma", Font.ITALIC, 22));
		lblSearchBy.setBounds(0, 141, 124, 20);
		frame.getContentPane().add(lblSearchBy);
		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtnSubjetc);
		bg.add(rdbtnAuthor);
		bg.add(rdbtnTitle);
			
		
		
		JLabel lblMyProfile = new JLabel("My Profile");
		lblMyProfile.setForeground(Color.BLACK);
		lblMyProfile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMyProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				
				Login l = new Login();
				l.loadprofile(id);
				
				
			}
		});
		lblMyProfile.setBounds(15, 16, 89, 20);
		frame.getContentPane().add(lblMyProfile);
		
		
		JLabel lblRequestedBooks = new JLabel("Requested Books");
		lblRequestedBooks.setForeground(Color.BLACK);
		lblRequestedBooks.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRequestedBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Requested_books b = new Requested_books(id);
				String query = "Select BookTitle,Author,Subject from RequestedBooks";
				PreparedStatement pst;
				try {
					pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					b.table_1.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				frame.dispose();
				b.frame.setVisible(true);
			}
		});
		lblRequestedBooks.setBounds(119, 16, 154, 20);
		frame.getContentPane().add(lblRequestedBooks);
		
		JLabel lblLogOut = new JLabel("Log Out");
		lblLogOut.setForeground(Color.BLACK);
		lblLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				Login g = new Login();
				g.frame.setVisible(true);
			}
		});
		lblLogOut.setBounds(287, 16, 89, 20);
		frame.getContentPane().add(lblLogOut);
		
		JLabel lblB = new JLabel("");
		lblB.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\444.jpg"));
		lblB.setBounds(0, 0, 1920, 1200);
		frame.getContentPane().add(lblB);
		
		
		

		
	
	
		
		
	}
}
