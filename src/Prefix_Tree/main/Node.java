import java.util.Collections;
import java.util.Map;

public final class Node {
    private char symbol;
    private Map<Character, Node> nextNodes;
    public String prefix;
    public boolean isEnd;

    Node(char symbol, Map<Character, Node> nextNodes) {
        this.symbol = symbol;
        this.nextNodes = nextNodes;
    }

    public Node root = new Node('\0', Collections.emptyMap());

    public Node add(Node newNode) {
        return nextNodes.put(newNode.symbol, newNode);
    }
}
