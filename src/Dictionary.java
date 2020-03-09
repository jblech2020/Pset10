import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class Dictionary {
    /**
     * Array of words, automatically populated from the JSON file
     */
    public static Words[] wordList;
    public static Words[] addAllWords() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
        wordList = new Gson().fromJson(new FileReader("./json/words.json"), Words[].class);
        return wordList;
    }

    public static ArrayList<String> listWords() {
        ArrayList<String> listOfWords = new ArrayList<String>();
        for (Words word : wordList) {
            listOfWords.add(word.getWord());
        }
        return listOfWords;
    }
}