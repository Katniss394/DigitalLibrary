import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class MyProfile extends JFrame {

	JFrame frame;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	public JTextField textField_3;
	public JTextField textField_4;
	public JTable table;
	Connection con=null;
	final Login g = new Login();
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @throws  
	 */

	public MyProfile(final String id) {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		frame = new JFrame();
		con=sqliteConnection.dbConnector();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1287, 697);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
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
		textField.setBounds(119, 130, 191, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(false);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(25, 131, 69, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Id");
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(25, 84, 79, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(119, 83, 191, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		textField_1.setEditable(false);
		
		JLabel lblNewLabel_2 = new JLabel("Class");
		lblNewLabel_2.setForeground(SystemColor.textHighlight);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(25, 174, 69, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(119, 168, 191, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		textField_2.setEditable(false);
		
		JLabel lblNewLabel_3 = new JLabel("Date of join");
		lblNewLabel_3.setForeground(SystemColor.textHighlight);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_3.setBounds(376, 126, 132, 20);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(536, 125, 192, 26);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		textField_3.setEditable(false);
		
		JLabel lblNewLabel_4 = new JLabel("Date of expiry");
		lblNewLabel_4.setForeground(SystemColor.textHighlight);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(376, 169, 146, 20);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setBounds(537, 168, 191, 26);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		textField_4.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 329, 1115, 276);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblBooksYouveBorrowed = new JLabel("Books you've borrowed..");
		lblBooksYouveBorrowed.setForeground(SystemColor.textHighlight);
		lblBooksYouveBorrowed.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblBooksYouveBorrowed.setBounds(35, 293, 302, 20);
		frame.getContentPane().add(lblBooksYouveBorrowed);
		
		JLabel lblNewLabel_5 = new JLabel("Search");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setForeground(SystemColor.textHighlight);
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				Search s = new Search(id);
				s.frame.setVisible(true);
			}
		});
		lblNewLabel_5.setBounds(10, 0, 69, 20);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblRequestedBooks = new JLabel("Requested Books");
		lblRequestedBooks.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblRequestedBooks.setForeground(SystemColor.textHighlight);
		lblRequestedBooks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Requested_books b = new Requested_books(id);
				String query = "Select BookTitle,Author,Subject from RequestedBooks";
				PreparedStatement pst;
				try {
					pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					b.table_1.setModel(DbUtils.resultSetToTableModel(rs));
					pst.close();
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error!");
				}
				
				frame.setVisible(false);
				b.frame.setVisible(true);

			}
		});
		lblRequestedBooks.setBounds(87, 0, 170, 20);
		frame.getContentPane().add(lblRequestedBooks);
		
		JLabel lblLogOut = new JLabel("Log Out");
		lblLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLogOut.setForeground(SystemColor.textHighlight);
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				
				g.frame.setVisible(true);
			}
		});
		lblLogOut.setBounds(257, 2, 80, 20);
		frame.getContentPane().add(lblLogOut);
		
		JLabel lblI = new JLabel("i");
		lblI.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\img8.jpg"));
		lblI.setBounds(0, 0, 2568, 1600);
		frame.getContentPane().add(lblI);
		
		setSize(getMaximumSize());
		
		
	}
}
