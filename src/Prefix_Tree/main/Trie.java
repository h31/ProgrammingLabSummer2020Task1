import java.util.*;

public final class Trie {
    private static Node root = new Node('\0');

    public static void addWord(String word) {
        Node node = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            if (!node.nextNodes.containsKey(ch)) {
                node.nextNodes.put(ch, new Node(ch));
            }
            node = node.nextNodes.get(ch);
        }
        node.isEnd = true;
    }

    public static void deleteWord(String word) {

    }

    public static boolean findWord(String word) {
        Node node = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            if (!node.nextNodes.containsKey(ch)) {
                return false;
            }
            node = node.nextNodes.get(ch);
        }
        return true;
    }

    public static void findByPrefix(String word) {

    }
}