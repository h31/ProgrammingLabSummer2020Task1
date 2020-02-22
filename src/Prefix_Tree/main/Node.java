import java.util.HashMap;
import java.util.Map;

public final class Node {
    public char symbol;
    public Map<Character, Node> nextNodes;
    public String prefix;
    public boolean isEnd;

    Node(char symbol) {
        this.symbol = symbol;
        nextNodes = new HashMap<>();
    }

    /*public void add(Node newNode) {
        nextNodes.put(newNode.symbol, newNode);
    }
     */

    @Override
    public String toString() {
        return "Узел{" +
                "символ: " + symbol +
                ", следующие символы: " + nextNodes +
                ", префикс: " + prefix + '\'' +
                '}';
    }
}
