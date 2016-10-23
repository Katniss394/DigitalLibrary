import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class AddRequest extends JFrame {
	private JTextField textField;
	JFrame frame;
	private JTextField textField_1;
	private JTextField textField_2;
	Connection con = null;
	/**
	 * Create the frame.
	 */
	public AddRequest(final String id) {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		con = sqliteConnection.dbConnector();
		final Login l = new Login();
		
		frame= new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setBounds(100, 100, 1285, 719);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
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
	
		JLabel lblBookTitle = new JLabel("Book Title");
		lblBookTitle.setForeground(Color.BLACK);
		lblBookTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblBookTitle.setBounds(198, 196, 115, 20);
		frame.getContentPane().add(lblBookTitle);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(336, 191, 525, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Author");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(198, 257, 88, 20);
		frame.getContentPane().add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_1.setBounds(336, 252, 525, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setForeground(Color.BLACK);
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSubject.setBounds(198, 322, 99, 20);
		frame.getContentPane().add(lblSubject);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField_2.setBounds(336, 317, 525, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try{
					try{
					Integer.parseInt(textField_1.getText());
					JOptionPane.showMessageDialog(null,"Invalid Input");}
					catch(NumberFormatException b8){
					Integer.parseInt(textField_2.getText());
					JOptionPane.showMessageDialog(null,"Invalid Input");
					}
				}
				catch(NumberFormatException b){
				String i=null;
				String query1 = "select Name from Members where Id_no =?";
				String query = "insert into RequestedBooks (BookTitle,RequestedBy,Author,Subject) values (?, ?, ?, ?)";
				PreparedStatement pst,pst1;
			
				try {
					pst1 = con.prepareStatement(query1);
					pst1.setString(1,id);
					ResultSet rs= pst1.executeQuery();
					

					while(rs.next()){
					i=rs.getString(1);
					}
					
					
					pst1.close();
					rs.close();
					pst=con.prepareStatement(query);
					pst.setString(1, textField.getText());
					pst.setString(2, i);
					pst.setString(3, textField_1.getText());
					pst.setString(4, textField_2.getText());
					int k=pst.executeUpdate();
					if(k==1){
						JOptionPane.showMessageDialog(null, "Request Added");}
					else{
						JOptionPane.showMessageDialog(null, "Error");}
					
					pst.close();
					rs.close();
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error!");
				}
				}
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAdd.setBounds(552, 371, 115, 29);
		frame.getContentPane().add(btnAdd);
		
		JLabel lblMyProfile = new JLabel("My Profile");
		lblMyProfile.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMyProfile.setForeground(SystemColor.menuText);
		lblMyProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				l.loadprofile(id);
			}
		});
		lblMyProfile.setBounds(11, 16, 101, 20);
		frame.getContentPane().add(lblMyProfile);
		
		JLabel lblNewLabel_1 = new JLabel("Requested Books");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(SystemColor.desktop);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Requested_books b = new Requested_books(id);
				String query = "Select BookTitle,Author,Subject from RequestedBooks";
				PreparedStatement pst;
				try {
					pst = con.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					b.table_1.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error!");
				}
				
				frame.dispose();
				b.frame.setVisible(true);
			}
		});
		lblNewLabel_1.setBounds(112, 16, 159, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Search");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(SystemColor.desktop);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Search s = new Search(id);
				s.frame.setVisible(true);
			}
		});
		lblNewLabel_2.setBounds(277, 16, 93, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Log Out");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setForeground(SystemColor.desktop);
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				
				l.frame.setVisible(true);
			}
		});
		lblNewLabel_3.setBounds(351, 16, 97, 20);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\img6.jpg"));
		lblNewLabel_4.setBounds(0, 0, 2400, 1800);
		frame.getContentPane().add(lblNewLabel_4);
		
	}
}
