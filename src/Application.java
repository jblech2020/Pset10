import java.awt.EventQueue;

import javax.swing.*;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.awt.event.*;

public class Application {

	private JFrame frmDictionary;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Dictionary.addAllWords();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmDictionary.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDictionary = new JFrame();
		frmDictionary.setResizable(false);
		frmDictionary.setTitle("Word Dictionary");
		frmDictionary.setBounds(100, 100, 965, 512);
		frmDictionary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDictionary.getContentPane().setLayout(null);

		/**
		 * Button to add word
		 */
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        System.out.println("Add");
		    }
		});
		btnAdd.setBounds(10, 11, 89, 23);
		frmDictionary.getContentPane().add(btnAdd);

		/**
		 * Button to remove word
		 */
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        System.out.println("Remove");
		    }
		});
		btnRemove.setBounds(109, 11, 89, 23);
		frmDictionary.getContentPane().add(btnRemove);


		/**
		 * Search box to... well... search
		 */
		JTextField searchBox = new JTextField();
		searchBox.setBounds(10, 45, 188, 20);
		frmDictionary.getContentPane().add(searchBox);
		searchBox.setColumns(10);

		/**
		 * Radio button to sort in ascending order
		 */
		JRadioButton rdbtnAsc = new JRadioButton("Asc");
		rdbtnAsc.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        System.out.println("Ascending");
		    }
		});
		rdbtnAsc.setToolTipText("Sorts in ascending order");
		buttonGroup.add(rdbtnAsc);
		rdbtnAsc.setBounds(36, 70, 63, 23);
		frmDictionary.getContentPane().add(rdbtnAsc);

		/**
		 * Radio button to sort in descending order
		 */
		JRadioButton rdbtnDesc = new JRadioButton("Desc");
		rdbtnDesc.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evt) {
		        System.out.println("Descending");
		    }
		});
		rdbtnDesc.setToolTipText("Sorts in descending order");
		buttonGroup.add(rdbtnDesc);
		rdbtnDesc.setBounds(122, 70, 54, 23);
		frmDictionary.getContentPane().add(rdbtnDesc);

		/**
		 * Main container for word information and dialogues
		 */
		JScrollPane wordInfo = new JScrollPane();
		wordInfo.setBounds(214, 11, 725, 452);
		frmDictionary.getContentPane().add(wordInfo);

		/**
		 * Scroll Bar
		 */
		JScrollPane wordList = new JScrollPane();
		wordList.setBounds(10, 99, 188, 364);
		frmDictionary.getContentPane().add(wordList);

		/**
		 * Word List
		 */
		JList<String> list = new JList<String>();
		wordList.setViewportView(list);

		DefaultListModel<String> DLM = new DefaultListModel<String>();
		for (String i : Dictionary.listWords()) {
			DLM.addElement(i);
		}
		list.setModel(DLM);
	}
}