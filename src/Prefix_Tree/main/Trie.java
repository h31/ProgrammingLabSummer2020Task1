import java.util.*;

public final class Trie {
    private static Node root;

    public Trie() {
        root = new Node('\0', null);
        root.isEnd = true;
    }

    public void addWord(String word) {
        Node node = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            if (!node.nextNodes.containsKey(ch)) {
                node.nextNodes.put(ch, new Node(ch, node));
            }
            node = node.nextNodes.get(ch);
        }
        node.isEnd = true;
    }

    private Node findNode(String word) {
        Node node = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            if (!node.nextNodes.containsKey(ch)) {
                return null;
            }
            node = node.nextNodes.get(ch);
        }
        return node;
    }

    public void deleteWord(String word) {
        Node node = findNode(word);
        if (node != null) {
            Node previous = node.previous;
            node.isEnd = false;
            while (node.nextNodes.size() == 0 && !node.isEnd) {
                previous.nextNodes.remove(node.symbol, node);
                node = previous;
                previous = node.previous;
            }
        }
    }

    public boolean findWord(String word) {
        Node founded = findNode(word);
        return founded != null && founded.isEnd;
    }

    public List<String> findByPrefix(String word) {
        Node node = findNode(word);
        for (Node subNode : node.nextNodes.values()) {

        }

        return Collections.emptyList();
    }

    /*public List<String> findByPrefix(String word) {
        Node node = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            if (!node.nextNodes.containsKey(ch)) {
                return Collections.emptyList();
            }
        }
        return Collections.emptyList(); //не доделано, поставил, чтобы не было ошибок
    }
     */
}