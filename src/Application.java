import java.awt.EventQueue;

import javax.swing.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
	
	/**
	 * Automatically populates list from the Array in Dictionary.java
	 */
	list.addListSelectionListener(new ListSelectionListener() {
		boolean ranOnce = false;
		public void valueChanged(ListSelectionEvent arg0) {
			if(ranOnce) {
				ranOnce = false;
			}else {
				ranOnce = true;

				String selectedWord = list.getSelectedValue();
				System.out.println("Selected word: " + selectedWord);

				try {
					ArrayList<Words> Words = getWordClass();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	});
	
	wordListSP.setViewportView(list);
	DefaultListModel<String> DLM = getWordsDLM();;
	list.setModel(DLM);

	/**
	 * Searches through words based on searchBox's contents
	 */
	searchBox.addKeyListener(new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			String searched = searchBox.getText().toLowerCase();
			System.out.println("Searched term: " + searched);
			DefaultListModel<String> words = new DefaultListModel<String>();
			if (!rdbtnAsc.isSelected()) {
			    try {
			    	words = reverseOrder(getWordsDLM());
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
			} else {
				try {
					words = getWordsDLM();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
			DefaultListModel<String> filtered = new DefaultListModel<String>();
			for (int i = 0 ; i < words.size(); i++) {
				if((words.get(i).startsWith(searched))) {
					filtered.addElement(words.get(i));
				}
			}
			list.setModel(filtered);

		}
	});
	searchBox.setToolTipText("Search");
	}
	
	/**
	 * Sorts the DLM of words in ascending order
	 */
	public static DefaultListModel<String> sortWordsAsc(DefaultListModel<String> listOfWords) {
		String temp;
		int n = listOfWords.getSize();
		for (int i = 0; i < n; i++)  {
            for (int j = i + 1; j < n; j++) {
                if ((listOfWords.get(i).compareTo(listOfWords.get(j)) > 0)) {
	                temp = listOfWords.get(i);
	                listOfWords.set(i, listOfWords.get(j));
	                listOfWords.set(j, temp);
                }
            }
        }
		return listOfWords;
	}
	
	/**
	 * Reverses the order of a DLM (used when searching in reverse order)
	 */
	public static DefaultListModel<String> reverseOrder(DefaultListModel<String> words) {
		DefaultListModel<String> b = new DefaultListModel<String>();
		int n = words.getSize();
        int j = n;
        for (int i = 0; i < n; i++) {
            b.addElement(words.get(j-1));
            j = j - 1;
        }
        return b;
	}
	
	/**
	 * Adds words to listOfWords and returns it
	 */
	private static ArrayList<Words> getWordClass() throws FileNotFoundException{
		Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("./json/words.json"));
        Words[] words = gson.fromJson(br, Words[].class);
        ArrayList<Words> listOfWords = new ArrayList<Words>();
        for (Words word : words) {
        	listOfWords.add(word);
        };
        return listOfWords;
	}
	
	/**
	 * Adds words to Json file
	 */
	public static void addWord(Words word, Words[] wordList) {
		Gson gson=new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(addToList(wordList.length, wordList, word));
		wordList = addToList(wordList.length, wordList, word);
		try {
			FileWriter writer = new FileWriter("./json/words.json");
			wordList = addAllWords(wordList);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}