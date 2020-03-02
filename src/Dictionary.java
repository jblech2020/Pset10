import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import com.google.gson.*;

public class Dictionary {
	
	public static Words[] wordList;
	public static Words[] addAllWords() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		wordList = new Gson().fromJson(new FileReader(".\\Jsons\\words.json"), Words[].class);
		return wordList;
	}

	public static ArrayList<String> listWords() {
		ArrayList<String> listOfWords = new ArrayList<String>();
		for(Words word : wordList) {
			listOfWords.add(word.getSpelling());
		}
		return listOfWords;
	}
}
