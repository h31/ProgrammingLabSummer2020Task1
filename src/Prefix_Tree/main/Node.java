import java.util.HashMap;
import java.util.Map;

public final class Node {
    public char symbol;
    public Map<Character, Node> nextNodes;
    public String prefix;
    public boolean isEnd;
    public Node previous;

    Node(char symbol, Node previous) {
        this.symbol = symbol;
        nextNodes = new HashMap<>();
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Узел{" +
                "символ: " + symbol +
                ", следующие символы: " + nextNodes +
                ", префикс: " + prefix + '\'' +
                '}';
    }
}
