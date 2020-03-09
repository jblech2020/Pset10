public class Definitions {

    private String definition;
    private String partOfSpeech;

    public void definitions(String definition, String partsOfSpeech) {
        this.definition = definition;
        this.partOfSpeech = partsOfSpeech;
    }

    public String getDefinition() {
        return this.definition;
    }

    public String getPartOfSpeech() {
        return this.partOfSpeech;
    }
}