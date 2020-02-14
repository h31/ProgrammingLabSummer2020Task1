import java.util.Collections;

public final class Trie {
    Node root = new Node('\0', Collections.emptyMap());

    public static void addStr(String string) {

    }
    public static void deleteStr(String string) {

    }
    public static void findStr(String string) {

    }
    public static void findAllStr(String prefix) {

    }

    Node bcd = new Node('a', Collections.emptyMap());
    Node abc = new Node('b', Collections.emptyMap());
    Node def = abc.add(bcd);
}