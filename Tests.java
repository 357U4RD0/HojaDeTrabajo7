import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    public void testTranslateWordsInSentence() {
        BinaryTree<Association<String, String>> dictionary = new BinaryTree<>(new Association<>("", ""));
        
        dictionary.insert(new Association<>("dog", "perro"));
        dictionary.insert(new Association<>("cat", "gato"));
        
        String sentenceToTranslate = "the dog and the cat";
        String expectedTranslation = "the perro and the gato";
        
        String[] words = sentenceToTranslate.split(" ");
        StringBuilder translatedSentence = new StringBuilder();

        for (String word : words) {
            String translated = translateWord(word.toLowerCase(), dictionary);
            translatedSentence.append(translated).append(" ");
        }
        
        String actualTranslation = translatedSentence.toString().trim();

        assertEquals(expectedTranslation, actualTranslation);
    }

    private String translateWord(String word, BinaryTree<Association<String, String>> dictionary) {
        Association<String, String> translatedWord = dictionary.search(new Association<>(word, ""));
        return translatedWord != null ? translatedWord.value : word;
    }
}
