import java.util.*;

public final class Trie {
    private static final class Node {
        char symbol;
        Map<Character, Node> nextNodes;
        StringBuilder prefix;
        boolean isEnd;
        Node previous;

        Node(char symbol, Node previous) {
            this.symbol = symbol;
            nextNodes = new HashMap<>();
            this.previous = previous;
            prefix = new StringBuilder();
            if (previous != null) prefix.append(previous.prefix);
            prefix.append(symbol);
        }
    }

    private Node root;
    private List<String> used = new ArrayList<>(); //for depthFirstSearch
    private List<String> words = new ArrayList<>(); //for findByPrefix

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
        Node found = findNode(word);
        return found != null && found.isEnd;
    }

    private void depthFirstSearch(Node node) {
        used.add(node.prefix.toString());
        if (node.isEnd) {
            words.add(node.prefix.toString());
        }
        for (Node next : node.nextNodes.values()) {
            if (!used.contains(next.prefix.toString())) depthFirstSearch(next);
        }
    }

    public List<String> findByPrefix(String word) {
        Node node = findNode(word);
        if (node != null) {
            used.clear();
            words.clear();
            depthFirstSearch(node);
            return words;
        }
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        used.clear();
        words.clear();
        depthFirstSearch(root);
        return words.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trie trie = (Trie) o;
        return this.toString().equals(trie.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(root, used, words);
    }
}