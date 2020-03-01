import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JList;

public class Dictionary {

	private JFrame frame;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dictionary window = new Dictionary();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dictionary() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 613, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel Panel = new JPanel();
		Panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(Panel, BorderLayout.WEST);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnRemove = new JButton("Remove");
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.LEFT);
		txtSearch.setText("Search...");
		txtSearch.setColumns(10);
		
		JCheckBox chckbxAsc = new JCheckBox("Asc");
		
		JCheckBox chckbxDesc = new JCheckBox("Desc");
		
		JList list = new JList();
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_Panel = new GroupLayout(Panel);
		gl_Panel.setHorizontalGroup(
			gl_Panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Panel.createSequentialGroup()
					.addGroup(gl_Panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_Panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(list, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_Panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_Panel.createSequentialGroup()
								.addGap(6)
								.addComponent(txtSearch))
							.addGroup(gl_Panel.createSequentialGroup()
								.addComponent(btnNewButton)
								.addComponent(btnRemove)))
						.addGroup(Alignment.LEADING, gl_Panel.createSequentialGroup()
							.addGap(24)
							.addComponent(chckbxAsc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxDesc)))
					.addContainerGap())
		);
		gl_Panel.setVerticalGroup(
			gl_Panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_Panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxDesc)
						.addComponent(chckbxAsc))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		Panel.setLayout(gl_Panel);
	}
}