import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ChangePassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	Connection con;
	private JPasswordField passwordField;
	
	public ChangePassword() {
		con=sqliteConnection.dbConnector();
		setBounds(100, 100, 450, 300);
		setLocation(700, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
			JLabel lblUserId = new JLabel("User Id");
			lblUserId.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblUserId.setBounds(76, 54, 69, 20);
			contentPanel.add(lblUserId);
		
		
		textField = new JTextField();
		textField.setBounds(160, 51, 146, 26);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewPassword.setBounds(15, 138, 130, 20);
		contentPanel.add(lblNewPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(160, 93, 146, 26);
		contentPanel.add(passwordField);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblOldPassword.setBounds(15, 90, 115, 32);
		contentPanel.add(lblOldPassword);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(160, 135, 146, 26);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
			JLabel lblK = new JLabel("");
			lblK.setIcon(new ImageIcon("E:\\workspace\\Library\\img\\img6.jpg"));
			lblK.setBounds(0, 0, 1932, 1080);
			contentPanel.add(lblK);
		
	
		
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int count=0;
						String q="Select * from Admin where UserId=? and Password=?";
						try {
						PreparedStatement pst=con.prepareStatement(q);
						pst.setString(1, textField.getText());
						pst.setString(2, passwordField.getText());
						ResultSet rs=pst.executeQuery();
						while(rs.next()){
							count++;
						}
						rs.close();
						pst.close();
						con.close();
						if(count==1){
						String query="Update Admin set Password= '"+textField_1.getText()+"'where UserId=?";
							con=sqliteConnection.dbConnector();
							PreparedStatement p=con.prepareStatement(query);
							p.setString(1, textField.getText());
							p.executeUpdate();
							JOptionPane.showMessageDialog(null, "Password Changed");
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "Error!");
						}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			
			
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			
		
	}
}
