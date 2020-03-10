import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.*;

public class AddWordPage {
	public JFrame addWordFrame;
    private JTextField wordTextField;
    private JTextField posTextField;
    private JTextField definitionTextField;
    private JTextField synonymsTextField;
    private JTextField antonymsTextField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddWordPage window = new AddWordPage();
                    window.addWordFrame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public AddWordPage() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        addWordFrame = new JFrame();
        addWordFrame.setTitle("Add Word");
        addWordFrame.setBounds(100, 100, 465, 350);
        addWordFrame.setResizable(false);
        addWordFrame.setLocationRelativeTo(null);
        addWordFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addWordFrame.getContentPane().setLayout(null);

        JLabel wordLabel = new JLabel("Word");
        wordLabel.setBounds(6, 6, 61, 16);
        wordLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(wordLabel);

        wordTextField = new JTextField("Word");
        wordTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (wordTextField.getText().equals("Word")) {
                    wordTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (wordTextField.getText().equals("")) {
                    wordTextField.setText("Word");
                }
            }
        });
        wordTextField.setBounds(6, 23, 130, 26);
        wordTextField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(wordTextField);
        wordTextField.setColumns(10);

        JLabel addWordLabel = new JLabel("Add Word");
        addWordLabel.setBounds(235, 16, 166, 40);
        addWordLabel.setFont(new Font("SansSerif", Font.PLAIN, 32));
        addWordFrame.getContentPane().add(addWordLabel);

        posTextField = new JTextField("Part of Speech");
        posTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (posTextField.getText().equals("Part of Speech")) {
                    posTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (posTextField.getText().equals("")) {
                    posTextField.setText("Part of Speech");
                }
            }
        });
        posTextField.setBounds(6, 78, 130, 26);
        posTextField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        posTextField.setColumns(10);
        addWordFrame.getContentPane().add(posTextField);

        JLabel posLabel = new JLabel("Part of Speech");
        posLabel.setBounds(6, 61, 113, 16);
        posLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(posLabel);

        definitionTextField = new JTextField("Definition");
        definitionTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (definitionTextField.getText().equals("Definition")) {
                    definitionTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (definitionTextField.getText().equals("")) {
                    definitionTextField.setText("Definition");
                }
            }
        });
        definitionTextField.setBounds(6, 133, 283, 26);
        definitionTextField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        definitionTextField.setColumns(10);
        addWordFrame.getContentPane().add(definitionTextField);

        JLabel definitionLabel = new JLabel("Definition");
        definitionLabel.setBounds(6, 116, 61, 16);
        definitionLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(definitionLabel);

        synonymsTextField = new JTextField("Synonyms");
        synonymsTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (synonymsTextField.getText().equals("Synonyms")) {
                    synonymsTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (synonymsTextField.getText().equals("")) {
                    synonymsTextField.setText("Synonyms");
                }
            }
        });
        synonymsTextField.setBounds(6, 188, 130, 26);
        synonymsTextField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        synonymsTextField.setColumns(10);
        addWordFrame.getContentPane().add(synonymsTextField);

        JLabel synonymsLabel = new JLabel("Synonyms");
        synonymsLabel.setBounds(6, 171, 65, 16);
        synonymsLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(synonymsLabel);

        antonymsTextField = new JTextField("Antonyms");
        antonymsTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (antonymsTextField.getText().equals("Antonyms")) {
                    antonymsTextField.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (antonymsTextField.getText().equals("")) {
                    antonymsTextField.setText("Antonyms");
                }
            }
        });
        antonymsTextField.setBounds(6, 243, 130, 26);
        antonymsTextField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        antonymsTextField.setColumns(10);
        addWordFrame.getContentPane().add(antonymsTextField);

        JLabel antonymsLabel = new JLabel("Antonyms");
        antonymsLabel.setBounds(6, 226, 61, 16);
        antonymsLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(antonymsLabel);

        JButton closeWindowButton = new JButton("Close Window");
        closeWindowButton.setBounds(320, 274, 117, 26);
        closeWindowButton.setFocusPainted(false);
        closeWindowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addWordFrame.dispose();
            }
        });
        closeWindowButton.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(closeWindowButton);

        JButton addWordButton = new JButton("Add");
        addWordButton.setBounds(138, 23, 61, 26);
        addWordButton.setFocusPainted(false);
        addWordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        addWordButton.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(addWordButton);

        JButton synonymsAddButton = new JButton("Add");
        synonymsAddButton.setBounds(138, 188, 61, 26);
        synonymsAddButton.setFocusPainted(false);
        synonymsAddButton.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(synonymsAddButton);

        JButton antonymsAddButton = new JButton("Add");
        antonymsAddButton.setBounds(138, 243, 61, 26);
        antonymsAddButton.setFocusPainted(false);
        antonymsAddButton.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(antonymsAddButton);

        JButton addWordPOSbtn = new JButton("Add Word & PoS");
        addWordPOSbtn.setBounds(292, 133, 150, 26);
        addWordPOSbtn.setFocusPainted(false);
        addWordPOSbtn.setFont(new Font("SansSerif", Font.PLAIN, 13));
        addWordFrame.getContentPane().add(addWordPOSbtn);
    }
}
