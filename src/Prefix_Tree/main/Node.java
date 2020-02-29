import java.util.HashMap;
import java.util.Map;

public final class Node {
    public char symbol;
    public Map<Character, Node> nextNodes;
    public StringBuilder prefix;
    public boolean isEnd;
    public Node previous;

    Node(char symbol, Node previous) {
        this.symbol = symbol;
        nextNodes = new HashMap<>();
        this.previous = previous;
        prefix = new StringBuilder();
        if (previous != null) prefix.append(previous.prefix);
        prefix.append(symbol);
    }

    @Override
    public String toString() {
        return "Node{" +
                "symbol: " + symbol +
                ", next symbols: " + nextNodes +
                ", prefix: " + prefix + '\'' +
                ", is node end: " + isEnd + '\'' +
                '}';
    }
}
