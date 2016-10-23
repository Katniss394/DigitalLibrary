import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class Requested_books {
	Login g = new Login();
	JFrame frame;
	String id;
	private JTable table;
	JTable table_1;
	private JLabel lblRequestedBooks;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public Requested_books(String i) {
		id=i;
		try {
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.setBounds(100, 100, 1285, 709);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		scrollPane.setBounds(67, 130, 1090, 334);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable(new MyTableModel());
		table_1.setRowHeight(25);
		scrollPane.setViewportView(table_1);
		
		lblRequestedBooks = new JLabel("Requested Books");
		lblRequestedBooks.setForeground(Color.BLACK);
		lblRequestedBooks.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRequestedBooks.setBounds(67, 83, 209, 20);
		frame.getContentPane().add(lblRequestedBooks);
		
		JButton btnAddNewRequest = new JButton("Add New Request");
		btnAddNewRequest.setForeground(SystemColor.textHighlight);
		btnAddNewRequest.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAddNewRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				AddRequest r = new AddRequest(id);
				r.frame.setVisible(true);
			}
		});
		btnAddNewRequest.setBounds(528, 480, 222, 29);
		frame.getContentPane().add(btnAddNewRequest);
		
		JLabel lblNewLabel = new JLabel("My Profile");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				
				g.loadprofile(id);
			}
		});
		lblNewLabel.setBounds(15, 16, 94, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearch.setForeground(Color.BLACK);
		lblSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				Search s = new Search(id);
				s.frame.setVisible(true);
			}
		});
		lblSearch.setBounds(127, 16, 69, 20);
		frame.getContentPane().add(lblSearch);
		
		JLabel lblLogOut = new JLabel("Log Out");
		lblLogOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLogOut.setForeground(Color.BLACK);
		lblLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.dispose();
				
				g.frame.setVisible(true);

			}
		});
		lblLogOut.setBounds(211, 16, 94, 20);
		frame.getContentPane().add(lblLogOut);
		
		JLabel lblK = new JLabel();
		lblK.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\img0.jpg"));
		lblK.setBounds(0, 0, 1932, 1200);
		frame.getContentPane().add(lblK);
		frame.setVisible(true);
		
	}
}
