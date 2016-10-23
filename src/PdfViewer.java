import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jpedal.examples.viewer.Commands;
import org.jpedal.examples.viewer.Viewer;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.awt.event.ActionEvent;

public class PdfViewer extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public PdfViewer(String q,final JFrame f) {
		final Object[] path = new Object[]{q};
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1277, 741);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setLocation(1200,700);	
		
		
		JScrollPane rootContainer = new JScrollPane();
		rootContainer.setBounds(0, 0, 1260, 649);
		contentPane.add(rootContainer);
		addWindowStateListener(new WindowStateListener() {
			
			@Override
			public void windowStateChanged(WindowEvent e) {
				// TODO Auto-generated method stub
				if(e.getNewState()==JFrame.MAXIMIZED_BOTH){
					setExtendedState(JFrame.NORMAL);
				}
			}
		});
		final Viewer viewer = new Viewer(rootContainer, null);
		    viewer.setupViewer();
		    getRootPane().add(rootContainer,BorderLayout.CENTER);
		    
		    JButton btnNewButton = new JButton("Exit");
		    btnNewButton.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent arg0) {
		    		viewer.executeCommand(Commands.EXIT, path);
		    		dispose();
		    		f.setVisible(true);
		    	}
		    });
		    btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		    btnNewButton.setBounds(545, 656, 115, 29);
		    contentPane.add(btnNewButton);
		    rootContainer.setVisible(true);
		    viewer.executeCommand(Commands.OPENFILE, path);
	}
	
}
