import java.io.FileNotFoundException;


import java.util.ArrayList;
import java.util.Collections;

import com.google.gson.*;

public class Words {
	
	public String word;
	ArrayList<Definitions> definitions = new ArrayList<Definitions>();
	ArrayList<String> synonyms = new ArrayList<String>();
	ArrayList<String> antonyms = new ArrayList<String>();

	public Words(String word, Definitions definitions, ArrayList<String> synonoyms, ArrayList<String> antonyms) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		this.word = word;
		this.definitions.add(definitions);
		Collections.copy(this.synonyms, synonyms);
		Collections.copy(this.antonyms, antonyms);

	}

	public String getWord() {
		return this.word;
	}
	
	public void setDefinition(Definitions definitions) {
		this.definitions.add(definitions);
	}

	public void addSynonym(String synonym) {
		this.synonyms.add(synonym);
	}

	public void addAntonym(String antonym) {
		this.antonyms.add(antonym);
	}

	public String getSpelling() {
		return this.word;
	}

	public ArrayList<Definitions> getDefintion() {
		return this.definitions;
	}

	public ArrayList<String> getSynonyms() {
		return this.synonyms;
	}

	public ArrayList<String> getAntonyms() {
		return this.antonyms;
	}
}
