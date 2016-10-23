import javax.swing.*;

import org.eclipse.swt.internal.win32.TOUCHINPUT;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

public class Search_Results {
	Connection connection=null;
	JFrame frame;
	JList list;
	String k,id;
	private JTable table;
	private JTable table_1;
	int f;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	
	public Search_Results(int flag,String key,String i) {
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e) {
			e.printStackTrace();
			}
		id=i;
		f=flag;
		k=key;
		initialize();
		loadlist();
	}
	public void loadlist(){
		
		String query = null;
		switch(f){
			case 0: query = "Select Book_id,B_Name,Publisher,Author,Subject from Books where upper(Author) like ?";break;
			case 1: query="Select Book_id,B_Name,Publisher,Author,Subject from Books where upper(B_Name) like ?";break; 
			case 2: query="Select Book_id,B_Name,Publisher,Author,Subject from Books where upper(Subject) like ?";break;
			}
		PreparedStatement ps;
		try {

			ps = connection.prepareStatement(query);
			k=k.toUpperCase();
			ps.setString(1, "%"+k+"%");
			ResultSet rs=ps.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(rs));			
			ps.close();
			rs.close();
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Error!");
			}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection= sqliteConnection.dbConnector();
		frame = new JFrame();
		final Login l = new Login();
		
		frame.setBounds(100, 100, 1285, 709);
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
	
		
		JLabel lblResultsThatMatch = new JLabel("Results that match your search...");
		lblResultsThatMatch.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		//lblResultsThatMatch.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblResultsThatMatch.setBounds(67, 83, 411, 20);
		frame.getContentPane().add(lblResultsThatMatch);
		
		JLabel lblMyProfile = new JLabel("My Profile");
		lblMyProfile.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMyProfile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				l.loadprofile(id);
			}
		});
		
		lblMyProfile.setBounds(0, 16, 92, 20);
		frame.getContentPane().add(lblMyProfile);
		
		JLabel lblRequestedBooks = new JLabel("Requested Books");
		lblRequestedBooks.setFont(new Font("Tahoma", Font.PLAIN, 19));
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
					JOptionPane.showMessageDialog(null, "Error!");
				}
				
				frame.dispose();
				b.frame.setVisible(true);
			}
		});
		
		lblRequestedBooks.setBounds(105, 16, 143, 20);
		frame.getContentPane().add(lblRequestedBooks);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				Search s = new Search(id);
				s.frame.setVisible(true);
			}
		});
		
		lblSearch.setBounds(263, 15, 58, 23);
		frame.getContentPane().add(lblSearch);
		
		JLabel lblLogOut = new JLabel("Log Out");
		lblLogOut.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				
				l.frame.setVisible(true);
			}
		});
	
		lblLogOut.setBounds(336, 14, 69, 20);
		frame.getContentPane().add(lblLogOut);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 132, 1048, 492);
		frame.getContentPane().add(scrollPane);
		
		table_2 = new JTable(new MyTableModel());
		table_2.setRowHeight(25);
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table_2.getSelectedRow();
				String bid=(table_2.getModel().getValueAt(row, 0)).toString(); 
				String q = "Select * from Books where book_id =?";
				
				PreparedStatement p;
				try {
					p = connection.prepareStatement(q);
					
					p.setString(1, bid);
					
					ResultSet rs;
					rs= p.executeQuery();
					
					SearchDetails d = new SearchDetails(rs,bid,frame);
					d.setVisible(true);
					p.close();
					
					

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error!");
				}
				
				
				
			}
		});
		scrollPane.setViewportView(table_2);
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\i.jpg"));
		lblNewLabel.setBounds(0, 0, 2560, 1535);
		frame.getContentPane().add(lblNewLabel);
		
	
	}
}
