import java.util.ArrayList;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;

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
