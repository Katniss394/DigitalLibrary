import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;

public class SearchDetails extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField id;
	private JTextArea title;
	private JTextField Cost;
	private JTextField Author;
	private JTextField Pub;
	private JTextField Subject;
	Connection con;
	String path;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public SearchDetails(final ResultSet rs,final String bid,final JFrame f) {
	
		con = sqliteConnection.dbConnector();
		setBounds(100, 100, 766, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocation(700, 400);  
		
		
		JLabel lblNewLabel = new JLabel("Book Id");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(25, 63, 69, 20);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Title");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(25, 114, 69, 20);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Author");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(25, 164, 69, 20);
		contentPanel.add(lblNewLabel_2);
		
		id = new JTextField();
		id.setBounds(129, 63, 116, 26);
		contentPanel.add(id);
		id.setColumns(10);
		id.setEditable(false);
		
		title = new JTextArea();
		title.setWrapStyleWord(true);
		title.setLineWrap(true);
		title.setBounds(129, 114, 577, 26);
		contentPanel.add(title);
		title.setEditable(false);
		
		Cost = new JTextField();
		Cost.setColumns(10);
		Cost.setBounds(560, 158, 146, 26);
		contentPanel.add(Cost);
		Cost.setEditable(false);
		
		Author = new JTextField();
		Author.setColumns(10);
		Author.setBounds(129, 161, 303, 26);
		contentPanel.add(Author);
		Author.setEditable(false);
		
		Pub = new JTextField();
		Pub.setColumns(10);
		Pub.setBounds(129, 203, 303, 26);
		contentPanel.add(Pub);
		Pub.setEditable(false);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCost.setBounds(474, 164, 86, 20);
		contentPanel.add(lblCost);
		
		JLabel lblPublication = new JLabel("Publication");
		lblPublication.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPublication.setBounds(25, 206, 98, 20);
		contentPanel.add(lblPublication);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubject.setBounds(474, 206, 86, 20);
		contentPanel.add(lblSubject);
		
		Subject = new JTextField();
		Subject.setFont(new Font("Tahoma", Font.PLAIN, 13));
		Subject.setBounds(560, 203, 169, 26);
		contentPanel.add(Subject);
		Subject.setColumns(10);
		Subject.setEditable(false);
		
		JLabel lblK = new JLabel();
		lblK.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\14289-thewallpaper.jpg"));
		lblK.setBounds(0, 0, 1920, 1080);
		contentPanel.add(lblK);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnOk = new JButton("Preview Contents");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						if(path==null)
							JOptionPane.showMessageDialog(null, "No Pdf Available");
						else{
							PdfViewer p= new PdfViewer(path,f);
							p.setVisible(true);
							f.setVisible(false);
							dispose();
							
						}
				}
				
			});
			buttonPane.add(btnOk);
			
			JButton btnCheckAvailability = new JButton("Check Availability");
			btnCheckAvailability.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					int count=0;
					String q="Select * from IssueLog where Book_id= ?";
					try{
					PreparedStatement p=con.prepareStatement(q);
					p.setString(1,bid);
					ResultSet rst=p.executeQuery();
					
						while(rst.next()){
							count++;
							
						String q2 ="select date('now')";
							
							PreparedStatement pst5 = con.prepareStatement(q2);
							ResultSet rs55 = pst5.executeQuery();
							//JOptionPane.showMessageDialog(null,rs55.getString(1));	
							
							if(rs55.getString(1).compareTo(rst.getString("Due_Date"))>0){
								String qw="Select Name from Members where Id_no=?";
								PreparedStatement pst0=con.prepareStatement(qw);
								pst0.setString(1, rst.getString("id_no"));
								ResultSet r = pst0.executeQuery();
								JOptionPane.showMessageDialog(null,"It is currently with "+r.getString("Name")+"\nCome back after "+rst.getString("Due_Date"));
								pst5.close();
								pst0.close();
								rs55.close();
								r.close();
							}
							else
								JOptionPane.showMessageDialog(null, "Available");
						
						
							}
						
					rst.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(count!=1)
						JOptionPane.showMessageDialog(null, "Available");
					
				}
			
			});
			buttonPane.add(btnCheckAvailability);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		try {
			while(rs.next()){
				id.setText(rs.getString("Book_id"));
				title.setText(rs.getString("B_Name"));
				Subject.setText(rs.getString("Subject"));
				Pub.setText(rs.getString("Publisher"));
				Cost.setText(rs.getString("Cost"));
				Author.setText(rs.getString("Author"));
				path=rs.getString("Content");
				
			}
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	
	
}
