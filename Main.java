import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Association<String, String>> dictionary = new BinaryTree<>(new Association<>("", ""));
        ArrayList<String> result = new ArrayList<>();
        
        // Insertar las asociaciones del archivo diccionario.txt en el árbol binario
        dictionary.insert(new Association<>("house", "casa"));
        dictionary.insert(new Association<>("dog", "perro"));
        dictionary.insert(new Association<>("homework", "tarea"));
        dictionary.insert(new Association<>("woman", "mujer"));
        dictionary.insert(new Association<>("town", "pueblo"));
        dictionary.insert(new Association<>("yes", "si"));
        
        // Recorrer el árbol binario en orden e imprimir las palabras ordenadas por inglés
        inOrderTraversal(dictionary, result);
        System.out.println(result);
        
        // Leer la oración del archivo Oracion.txt y traducirla
        String docInEnglish = readSentenceFromFile("Oracion.txt");
        if (docInEnglish != null) {
            String[] words = docInEnglish.split(" ");
            StringBuilder translatedDoc = new StringBuilder();
            
            for (String word : words) {
                String translated = translateWord(word.toLowerCase(), dictionary);
                translatedDoc.append(translated).append(" ");
            }
            System.out.println(translatedDoc);
        }
    }
    
    public static void inOrderTraversal(BinaryTree<Association<String, String>> tree, ArrayList<String> result) {
        if (tree != null) {
            inOrderTraversal(tree.left, result);
            result.add("(" + tree.value.key + ", " + tree.value.value + ")");
            inOrderTraversal(tree.right, result);
        }
    }
    
    public static String translateWord(String word, BinaryTree<Association<String, String>> dictionary) {
        Association<String, String> searchResult = dictionary.search(new Association<>(word, ""));
        if (searchResult != null) {
            return searchResult.value;
        } else {
            return "*" + word + "*";
        }
    }

    public static String readSentenceFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                content.append(scanner.next()).append(" ");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error al leer el archivo. Asegúrate de que el archivo Oracion.txt existe en el directorio.");
            e.printStackTrace();
            return null;
        }
        return content.toString().trim();
    }
}

