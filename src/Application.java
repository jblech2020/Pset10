import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Application {
	private JFrame frmDictionary;
    private final ButtonGroup buttonGroup = new ButtonGroup();

    /**
     * Launch the application.
     */
    public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        getWordsDLM();
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
     * DLM of words, sorted in ascending order
     */
    private static DefaultListModel<String> getWordsDLM() throws FileNotFoundException, NullPointerException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader("./json/words.json"));
        Words[] words = gson.fromJson(br, Words[].class);
        DefaultListModel<String> listOfWords = new DefaultListModel<String>();
        for (Words word : words) {
            listOfWords.addElement(word.getWord());
        };
        return sortWordsAsc(listOfWords);
    }
	

	/**
	 * Create the application.
	 */
	public Application() throws FileNotFoundException {
		initialize();
	}

	/**
     * Initialize the contents of the frame.
     * @throws FileNotFoundException
     */
    private void initialize() throws FileNotFoundException {
    	frmDictionary = new JFrame();
    	frmDictionary.setResizable(false);
    	frmDictionary.setTitle("Kyle's eDictionary");
    	frmDictionary.setBounds(100, 100, 965, 512);
    	frmDictionary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frmDictionary.getContentPane().setLayout(null);
        Words[] wordList = Dictionary.wordList;

        /**
         * Main container for word information and dialogues
         */
        JScrollPane wordInfo = new JScrollPane();
        wordInfo.setBounds(214, 11, 725, 452);
        frmDictionary.getContentPane().add(wordInfo);

        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        wordInfo.setViewportView(textPane);
        StyledDocument doc = textPane.getStyledDocument();
        DefaultCaret caret = (DefaultCaret) textPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        textPane.setBorder(BorderFactory.createCompoundBorder(textPane.getBorder(), BorderFactory.createEmptyBorder(10, 10 ,10 , 10)));

        /**
         * Scroll pane that contains the list of words
         */
        JScrollPane wordListSP = new JScrollPane();
        wordListSP.setBounds(10, 99, 188, 364);
        frmDictionary.getContentPane().add(wordListSP);

        /**
         * List of words
         */
        JList<String> list = new JList<String>();
        list.addListSelectionListener(new ListSelectionListener() {
            boolean ranOnce = false;
            public void valueChanged(ListSelectionEvent arg0) {
                if(ranOnce) {
                    ranOnce = false;
                }else {
                    ranOnce = true;

                    String selectedWord = list.getSelectedValue();
                    System.out.println(selectedWord);

                    try {
                        ArrayList<Words> Words = getWordClass();
                        for (Words word: Words) {
                            if (word.getWord().equals(selectedWord)) {
                                doc.remove(0, doc.getLength());
                                Style bigWord = textPane.addStyle("Style", null);
                                Style header = textPane.addStyle("Style", null);
                                StyleConstants.setFontSize(header, 20);
                                StyleConstants.setFontSize(bigWord, 36);
                                StyleConstants.setBold(bigWord, true);
                                doc.insertString(doc.getLength(), selectedWord.substring(0, 1).toUpperCase() + selectedWord.substring(1) + "\n", bigWord );
                                doc.insertString(doc.getLength(), "\n" ,null );
                                doc.insertString(doc.getLength(), "Definitions\n", header );
                                doc.insertString(doc.getLength(), "\n" ,null );
                                Definitions[] definitions = word.getDefinitions();
                                int definitionCounter = 1;
                                for (Definitions definition : definitions) {
                                    doc.insertString(doc.getLength(), definitionCounter + ". " + selectedWord +" (" + definition.getPartOfSpeech() +")\n\n    "  +  definition.getDefinition() + "\n\n", null);
                                    definitionCounter++;
                                }
                                String[] synonyms = word.getSynonyms();
                                if(synonyms.length != 0) {
                                    doc.insertString(doc.getLength(),"Synonyms\n" ,header );
                                    doc.insertString(doc.getLength(),"\n" ,null );
                                    int synonymCounter = 1;
                                    for(String synonym : synonyms) {

                                        doc.insertString(doc.getLength(), synonymCounter + ". " + synonym + "\n", null);
                                        synonymCounter++;
                                    }
                                }
                                String[] antonyms = word.getAntonyms();
                                if (antonyms.length != 0) {
                                    doc.insertString(doc.getLength(),"\n" ,null );
                                    doc.insertString(doc.getLength(),"Antonyms\n" ,header );
                                    doc.insertString(doc.getLength(),"\n" ,null );
                                    int antonymCounter = 1;
                                    for(String antonym : antonyms) {
                                        doc.insertString(doc.getLength(), antonymCounter + ". " + antonym + "\n", null);
                                        antonymCounter++;
                                    }
                                }

                            }
                        }
                    } catch (FileNotFoundException | BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        /**
         * Button to add a word
         */
        JButton btnAdd = new JButton("ADD");
        btnAdd.addActionListener(e -> {
            System.out.println("Add");
            /**
             * Need to open window to do stuff
             */
//            addWord(wordToAdd, wordList);
        });
        btnAdd.setBounds(10, 11, 89, 23);
        frmDictionary.getContentPane().add(btnAdd);

        /**
         * Button to remove a word
         */
        JButton btnRemove = new JButton("REMOVE");
        btnRemove.addActionListener(e -> {
            System.out.println("Remove");
//            delWord(wordsToDelete, wordList);
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
        rdbtnAsc.addActionListener(e -> {
        });
        rdbtnAsc.setToolTipText("Sorts in ascending order");
        rdbtnAsc.setSelected(true);
        buttonGroup.add(rdbtnAsc);
        rdbtnAsc.setBounds(36, 70, 63, 23);
        frmDictionary.getContentPane().add(rdbtnAsc);

        /**
         * Radio button to sort in descending order
         */
        JRadioButton rdbtnDesc = new JRadioButton("Desc");
        rdbtnDesc.addActionListener(e -> {
        });
        rdbtnDesc.setToolTipText("Sorts in descending order");
        buttonGroup.add(rdbtnDesc);
        rdbtnDesc.setBounds(122, 70, 54, 23);
        frmDictionary.getContentPane().add(rdbtnDesc);
        rdbtnDesc.addItemListener(new ItemListener() {
            
        	/**
             * Changed between sorting in ascended or descended order
             */
            @Override
            public void itemStateChanged(ItemEvent event) {

                int state = event.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    try {
                        searchBox.setText("");
                        list.setModel(reverseOrder(getWordsDLM()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                } else if (state == ItemEvent.DESELECTED) {
                    try {
                        searchBox.setText("");
                        list.setModel(getWordsDLM());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        /**
         * Populates list with Words
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
         * Search
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
	 * Sorts words in ascending order
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
	 * Reverse Order
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
	
	/**
	 * Deletes words from the Json
	 */
	public static void delWord(Words[] wordsToDel, Words[] wordList) {
        Words newWordList[] = new Words[wordList.length - wordsToDel.length]; 
        Boolean kill = false;
        for (int i = 0; i < newWordList.length; i++) {
        	kill = false;
        	for (Words deadWord : wordsToDel) {
        		if (wordList[i] == deadWord) {
        			kill = true;
        		}
        		if (!kill) {
            		newWordList[i] = wordList[i];
            	} else {
            		newWordList[i] = wordList[i + 1];
            	}
        	}
        	
        }

        wordList = newWordList;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(wordList);
		try {
			FileWriter writer = new FileWriter("./json/words.json");
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	/**
	 * Adds to the arraylist
	 */
	public static Words[] addToList(int n, Words wordList[], Words word) { 
	    Words newWordList[] = new Words[n + 1]; 
	
	    for (int i = 0; i < n; i++) {
	        newWordList[i] = wordList[i]; 
	    }
	    newWordList[n] = word; 
	
	    return newWordList; 
	}
	
	/**
	 * Adds all of the collected words
	 */
	public static Words[] addAllWords(Words[] wordList) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		wordList = new Gson().fromJson(new FileReader("./json/words.json"), Words[].class);
		return wordList;
	}
}