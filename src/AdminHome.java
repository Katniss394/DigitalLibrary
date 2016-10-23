import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.BevelBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JMenu;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;
import java.awt.Font;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class AdminHome {

	JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	Connection con=null;
	private JTextField Id;
	private JTextField Name;
	private JTextField Class;
	private JTextField Expiry;
	private JTextField Join;
	private JTextField Bookid;
	private JTextField Publisher;
	private JTextField BTitle;
	private JTextField BAuthor;
	private JTextField BSubject;
	private JTextField Cost;
	private JTextField Content;
	private JTextField RTitle;
	private JTextField By;
	private JTextField RSubject;
	private JTextField RAuthor;
	private JTextField IBookid;
	private JTextField Ino;
	private JTextField Issue;
	private JTextField Due;
	
	public void load(){
		String query="Select * from Books";
		String query1="Select * from Members";
		String query2="Select * from IssueLog";
		String query3="Select * from RequestedBooks";
		PreparedStatement pst,pst1,pst2,pst3;
		try {
			pst = con.prepareStatement(query);
			pst1=con.prepareStatement(query1);
			pst2=con.prepareStatement(query2);
			pst3=con.prepareStatement(query3);
			ResultSet rs,rs1,rs2,rs3;
			rs= pst.executeQuery();
			rs1=pst1.executeQuery();
			rs2=pst2.executeQuery();
			rs3=pst3.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			table_1.setModel(DbUtils.resultSetToTableModel(rs3));
			table_2.setModel(DbUtils.resultSetToTableModel(rs2));
			table_3.setModel(DbUtils.resultSetToTableModel(rs1));
			
			rs.close();
			rs1.close();
			rs2.close();
			rs3.close();
			pst.close();
			pst1.close();
			pst2.close();
			pst3.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the application.
	 */
	public AdminHome() {
		initialize();
		load();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		con=sqliteConnection.dbConnector();
		frame = new JFrame();
		frame.setBounds(100, 100, 1285, 721);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_HORIZ);
		frame.getContentPane().setLayout(null);
		frame.setSize(1270, 720);
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tabbedPane.setBounds(0, 0, 1263, 649);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, null, null, null));
		panel_1.setForeground(Color.BLUE);
		tabbedPane.addTab("Members", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(282, 93, 961, 452);
		panel_1.add(scrollPane_3);
		
		table_3 = new JTable(new MyTableModel());
		table_3.setRowHeight(25);
		table_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table_3.getSelectedRow();
				String query = "Select * from Members where Id_no=?";
				try {
					PreparedStatement pst = con.prepareStatement(query);
					String id = (table_3.getModel().getValueAt(row, 0)).toString();
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					while(rs.next()){ 
						Id.setText(rs.getString("Id_no"));
						Name.setText(rs.getString("Name"));
						Join.setText(rs.getString("Join_Date"));
						Expiry.setText(rs.getString("Expiry_Date"));
						Class.setText(rs.getString("Class"));
				
					}
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error!");
				}
				
				
			}
		});
		scrollPane_3.setViewportView(table_3);
		
		JLabel lblIdno = new JLabel("Id_no");
		lblIdno.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblIdno.setBounds(15, 119, 56, 20);
		panel_1.add(lblIdno);
		
		Id = new JTextField();
		Id.setBounds(86, 116, 146, 26);
		panel_1.add(Id);
		Id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(15, 155, 69, 20);
		panel_1.add(lblNewLabel);
		
		Name = new JTextField();
		Name.setBounds(86, 152, 146, 26);
		panel_1.add(Name);
		Name.setColumns(10);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblClass.setBounds(15, 197, 69, 20);
		panel_1.add(lblClass);
		
		Class = new JTextField();
		Class.setBounds(86, 194, 146, 26);
		panel_1.add(Class);
		Class.setColumns(10);
		
		JLabel lblExpirydate = new JLabel("Expiry");
		lblExpirydate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblExpirydate.setBounds(15, 271, 69, 20);
		panel_1.add(lblExpirydate);
		
		Expiry = new JTextField();
		Expiry.setBounds(86, 268, 146, 26);
		panel_1.add(Expiry);
		Expiry.setColumns(10);
		
		JLabel lblJoin = new JLabel("Join");
		lblJoin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblJoin.setBounds(15, 233, 69, 20);
		panel_1.add(lblJoin);
		
		Join = new JTextField();
		Join.setBounds(86, 226, 146, 26);
		panel_1.add(Join);
		Join.setColumns(10);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="update Members set Name='"+Name.getText()+"' ,Class='"+Class.getText()+"' ,Join_Date='"+Join.getText()+"' ,Expiry_Date = '"+Expiry.getText()+"'where Id_no=?";
				try {
					PreparedStatement p =con.prepareStatement(query);
					p.setString(1, Id.getText());
					int k=p.executeUpdate();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
						JOptionPane.showMessageDialog(null, "Data Updated");
					
					p.close();
					load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		btnEdit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEdit.setBounds(58, 327, 115, 29);
		panel_1.add(btnEdit);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Integer.parseInt(Name.getText());
					JOptionPane.showMessageDialog(null, "Inalid Input");
				}
				catch(NumberFormatException b){
				String query="insert into Members (Id_no,Name,Class,Join_Date,Expiry_Date) values (?,?,?,?,?)";
				try {
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, Id.getText());
					ps.setString(2,Name.getText());
					ps.setString(3, Class.getText());
					ps.setString(4, Join.getText());
					ps.setString(5, Expiry.getText());
					int k=ps.executeUpdate();
					ps.close();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
						JOptionPane.showMessageDialog(null, "Data Added");
					load();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error!");
				}
				}	
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAdd.setBounds(58, 372, 115, 29);
		panel_1.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String query="delete from Members where Id_no='"+Id.getText()+"'";
				try {
					PreparedStatement ps=con.prepareStatement(query);
					int k=ps.executeUpdate();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
						JOptionPane.showMessageDialog(null, "Data Deleted!");
					load();
					ps.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBounds(58, 417, 115, 29);
		panel_1.add(btnDelete);
		
		JLabel lblNewLabel1 = new JLabel("");
		lblNewLabel1.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\morning-dew-on-the-branches,-colorful-background-152717.jpg"));
		lblNewLabel1.setBounds(0, 0, 1288, 651);
		panel_1.add(lblNewLabel1);
		tabbedPane.setForegroundAt(0, Color.BLUE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, null, null, null));
		panel.setForeground(Color.BLUE);
		tabbedPane.addTab("Books", null, panel, null);
		tabbedPane.setForegroundAt(1, Color.BLUE);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(266, 40, 965, 430);
		panel.add(scrollPane);
		
		table = new JTable(new MyTableModel());
		table.setRowHeight(25);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				String query = "Select * from Books where Book_id=?";
				try {
					PreparedStatement pst = con.prepareStatement(query);
					String id = (table.getModel().getValueAt(row, 0)).toString();
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					while(rs.next()){
						Bookid.setText(rs.getString("Book_id"));
						BTitle.setText(rs.getString("B_Name"));
						BAuthor.setText(rs.getString("Author"));
						BSubject.setText(rs.getString("Subject"));
						Cost.setText(rs.getString("Cost"));
						Publisher.setText(rs.getString("Publisher"));
						Content.setText(rs.getString("Content"));
					}
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblI = new JLabel("Id");
		lblI.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblI.setBounds(15, 49, 56, 20);
		panel.add(lblI);
		
		BTitle = new JTextField();
		BTitle.setBounds(86, 94, 146, 26);
		panel.add(BTitle);
		BTitle.setColumns(10);
		
		JLabel lblN = new JLabel("Title");
		lblN.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblN.setBounds(15, 97, 69, 20);
		panel.add(lblN);
		
		Cost = new JTextField();
		Cost.setBounds(86, 136, 146, 26);
		panel.add(Cost);
		Cost.setColumns(10);
		
		JLabel lblC = new JLabel("Cost");
		lblC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblC.setBounds(15, 139, 69, 20);
		panel.add(lblC);
		
		BAuthor = new JTextField();
		BAuthor.setBounds(86, 178, 146, 26);
		panel.add(BAuthor);
		BAuthor.setColumns(10);
		
		JLabel lblS = new JLabel("Subject");
		lblS.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblS.setBounds(15, 229, 69, 20);
		panel.add(lblS);
		
		Content = new JTextField();
		Content.setBounds(86, 268, 146, 26);
		panel.add(Content);
		Content.setColumns(10);
		
		JLabel lblA = new JLabel("Author");
		lblA.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblA.setBounds(15, 181, 69, 20);
		panel.add(lblA);
		
		BSubject = new JTextField();
		BSubject.setBounds(86, 226, 146, 26);
		panel.add(BSubject);
		BSubject.setColumns(10);
		
		JButton btnEdit3 = new JButton("Update");
		btnEdit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="update Books setB_Name='"+BTitle.getText()+"' ,Cost='"+Cost.getText()+"' ,Author='"+BAuthor.getText()+"' ,Subject = '"+BSubject.getText()+"' ,Publisher='"+Publisher.getText()+"' ,Content='"+Content.getText()+"'where Book_id=?";
				try {
					PreparedStatement p1 =con.prepareStatement(query);
					p1.setString(1, Bookid.getText());
					int k=p1.executeUpdate();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
						JOptionPane.showMessageDialog(null, "Data Updated!");
					load();
					p1.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		btnEdit3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEdit3.setBounds(58, 455, 115, 29);
		panel.add(btnEdit3);
		
		JButton btnAdd3 = new JButton("Add");
		btnAdd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="insert into Books (Book_id,B_Name,Publisher,Author,Subject,Cost,Content) values (?,?,?,?,?,?,?)";
				try{
					Integer.parseInt(BAuthor.getText());
					JOptionPane.showMessageDialog(null, "Invalid Input");
					Integer.parseInt(BSubject.getText());
					JOptionPane.showMessageDialog(null, "Invalid Input");
				}
				catch(NumberFormatException b){
				try {
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, Bookid.getText());
					ps.setString(2,BTitle.getText());
					ps.setString(3, Publisher.getText());
					ps.setString(4, BAuthor.getText());
					ps.setString(5, BSubject.getText());
					ps.setString(6, Cost.getText());
					ps.setString(7, Content.getText());
					int k=ps.executeUpdate();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
						JOptionPane.showMessageDialog(null, "Data Added");
					ps.close();
					load();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
				}
		});
		btnAdd3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAdd3.setBounds(58, 372, 115, 29);
		panel.add(btnAdd3);
		
		JButton btnDelete4 = new JButton("Delete");
		btnDelete4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="delete from Books where Book_id='"+Bookid.getText()+"'";
				try {
					PreparedStatement ps1=con.prepareStatement(query);
					int k=ps1.executeUpdate();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
					JOptionPane.showMessageDialog(null, "Data Deleted!");
					load();
					ps1.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error!");				}
			}
		});
		btnDelete4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete4.setBounds(58, 417, 115, 29);
		panel.add(btnDelete4);
		
		JLabel lblNewLabel_3 = new JLabel("Publisher");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_3.setBounds(15, 309, 69, 20);
		panel.add(lblNewLabel_3);
		
		JLabel lblC_1 = new JLabel("Content");
		lblC_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblC_1.setBounds(15, 271, 69, 20);
		panel.add(lblC_1);
		
		Bookid = new JTextField();
		Bookid.setBounds(86, 46, 146, 26);
		panel.add(Bookid);
		Bookid.setColumns(10);
		
		Publisher = new JTextField();
		Publisher.setBounds(86, 305, 146, 26);
		panel.add(Publisher);
		Publisher.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLUE, null, null, null));
		panel_2.setForeground(Color.BLUE);
		tabbedPane.addTab("Requested Books", null, panel_2, null);
		tabbedPane.setForegroundAt(2, Color.BLUE);
		panel_2.setLayout(null);
		
		JLabel lbl1 = new JLabel("Title");
		lbl1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lbl1.setBounds(15, 119, 56, 20);
		panel_2.add(lbl1);
		
		RTitle = new JTextField();
		RTitle.setBounds(86, 116, 146, 26);
		panel_2.add(RTitle);
		RTitle.setColumns(10);
		
		JLabel lbl2 = new JLabel("By");
		lbl2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lbl2.setBounds(15, 155, 69, 20);
		panel_2.add(lbl2);
		
		By = new JTextField();
		By.setBounds(86, 152, 146, 26);
		panel_2.add(By);
		By.setColumns(10);
		
		JLabel lbl3 = new JLabel("Author");
		lbl3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lbl3.setBounds(15, 197, 69, 20);
		panel_2.add(lbl3);
		
		RAuthor = new JTextField();
		RAuthor.setBounds(86, 194, 146, 26);
		panel_2.add(RAuthor);
		RAuthor.setColumns(10);
		
		JLabel lbl4 = new JLabel("Subject");
		lbl4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lbl4.setBounds(15, 239, 69, 20);
		panel_2.add(lbl4);
		
		RSubject = new JTextField();
		RSubject.setBounds(86, 236, 146, 26);
		panel_2.add(RSubject);
		RSubject.setColumns(10);
		
		
		JButton btnDelete2 = new JButton("Delete");
		btnDelete2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="delete from RequestedBooks where BookTitle='"+RTitle.getText()+"'and RequestedBy='"+By.getText()+"'";
				try {
					PreparedStatement ps2=con.prepareStatement(query);
					int k=ps2.executeUpdate();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
					JOptionPane.showMessageDialog(null, "Data Deleted!");
					ps2.close();
					load();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		btnDelete2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete2.setBounds(39, 275, 115, 29);
		panel_2.add(btnDelete2);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(274, 122, 969, 357);
		panel_2.add(scrollPane_1);
		
		table_1 = new JTable(new MyTableModel());
		table_1.setRowHeight(25);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_1.getSelectedRow();
				String query = "Select * from RequestedBooks where BookTitle=?";
				try {
					PreparedStatement pst = con.prepareStatement(query);
					String bt = (table_1.getModel().getValueAt(row, 0)).toString();
					pst.setString(1, bt);
					
					ResultSet rs = pst.executeQuery();
					while(rs.next()){
						
						RTitle.setText(rs.getString("BookTitle"));
						By.setText(rs.getString("RequestedBy"));
						RSubject.setText(rs.getString("Subject"));
						RAuthor.setText(rs.getString("Author"));
						
					}
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\444.jpg"));
		label.setBounds(-31, 0, 1289, 647);
		panel_2.add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.addTab("Issue Log", null, panel_3, null);
		tabbedPane.setForegroundAt(3, Color.BLUE);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(273, 104, 944, 359);
		panel_3.add(scrollPane_2);
		
		table_2 = new JTable(new MyTableModel());
		table_2.setRowHeight(25);
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_2.getSelectedRow();
				String query = "Select * from IssueLog where Book_id=?";
				try {
					PreparedStatement pst = con.prepareStatement(query);
					String id = (table_2.getModel().getValueAt(row, 0)).toString();
					pst.setString(1, id);
					ResultSet rs = pst.executeQuery();
					while(rs.next()){
						IBookid.setText(rs.getString("Book_id"));
						Ino.setText(rs.getString("id_no"));
						Issue.setText(rs.getString("Issue_Date"));
						Due.setText(rs.getString("Due_Date"));
				
					}
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		scrollPane_2.setViewportView(table_2);
		
		JLabel lblId = new JLabel("BId");
		lblId.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblId.setBounds(15, 119, 56, 20);
		panel_3.add(lblId);
		
		IBookid = new JTextField();
		IBookid.setBounds(86, 116, 146, 26);
		panel_3.add(IBookid);
		IBookid.setColumns(10);
		
		JLabel lblNo = new JLabel("Id no");
		lblNo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNo.setBounds(15, 155, 69, 20);
		panel_3.add(lblNo);
		
		Ino = new JTextField();
		Ino.setBounds(86, 152, 146, 26);
		panel_3.add(Ino);
		Ino.setColumns(10);
		
		JLabel lbli = new JLabel("Issued");
		lbli.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lbli.setBounds(15, 197, 69, 20);
		panel_3.add(lbli);
		
		Issue = new JTextField();
		Issue.setBounds(86, 194, 146, 26);
		panel_3.add(Issue);
		Issue.setColumns(10);
		
		JLabel lbldate = new JLabel("Due");
		lbldate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lbldate.setBounds(15, 233, 69, 20);
		panel_3.add(lbldate);
		
		Due = new JTextField();
		Due.setBounds(86, 236, 146, 26);
		panel_3.add(Due);
		Due.setColumns(10);
		
	
		
		JButton btnEdit2 = new JButton("Update");
		btnEdit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query="update IssueLog set id_no='"+Ino.getText()+"' ,Issue_Date='"+Issue.getText()+"' ,Due_Date='"+Due.getText()+"'where Book_id=?";
				try {
					PreparedStatement p2 =con.prepareStatement(query);
					p2.setString(1, IBookid.getText());
					int k=p2.executeUpdate();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
					JOptionPane.showMessageDialog(null, "Data Updated!");
					p2.close();
					load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		btnEdit2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEdit2.setBounds(58, 327, 115, 29);
		panel_3.add(btnEdit2);
		
		JButton btnAdd2 = new JButton("Add");
		btnAdd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="insert into IssueLog (Book_id,id_no,Issue_Date,Due_Date) values (?,?,?,?)";
				try {
					PreparedStatement ps = con.prepareStatement(query);
					ps.setString(1, IBookid.getText());
					ps.setString(2,Ino.getText());
					ps.setString(3, Issue.getText());
					ps.setString(4, Due.getText());
					int k=ps.executeUpdate();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
						JOptionPane.showMessageDialog(null, "Data Added");
					ps.close();
					load();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error!");
				}
			}
		});
		btnAdd2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAdd2.setBounds(58, 372, 115, 29);
		panel_3.add(btnAdd2);
		
		JButton btnDelete3 = new JButton("Delete");
		btnDelete3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query="delete from IssueLog where Book_id='"+IBookid.getText()+"'";
				try {
					PreparedStatement ps3=con.prepareStatement(query);
					int k=ps3.executeUpdate();
					if(k!=1)
						JOptionPane.showMessageDialog(null,"Error");
					else
						JOptionPane.showMessageDialog(null, "Data Deleted!");
					ps3.close();
					load();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error!");				}
			}
		});
		btnDelete3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete3.setBounds(58, 278, 115, 29);
		panel_3.add(btnDelete3);
		
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\i.jpg"));
		lblNewLabel_1.setBounds(0, 0, 2560, 1535);
		panel_3.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Issue Date");
		lblNewLabel_2.setBounds(15, 215, 69, 20);
		panel_3.add(lblNewLabel_2);
		
		
		JLabel lblK = new JLabel("");
		lblK.setBounds(0, 0, 1258, 648);
		lblK.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\img6.jpg"));
		panel.add(lblK);
		
		JButton btnExit = new JButton("Log Out");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				Login g=new Login();
				g.frame.setVisible(true);
			}
		});
		btnExit.setBackground(SystemColor.textHighlight);
		btnExit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnExit.setBounds(561, 652, 115, 29);
		frame.getContentPane().add(btnExit);
		load();
		
	}
	
	}

