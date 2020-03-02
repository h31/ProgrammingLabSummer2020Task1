import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void addWord() {
        Trie trie = new Trie();
        trie.addWord("Остров");
        trie.addWord("ост");
        trie.addWord("острый");
        trie.addWord("барабан");
        assertTrue(trie.findWord("остров"));
        assertTrue(trie.findWord("ОСТ"));
        assertFalse(trie.findWord(""));
        assertFalse(trie.findWord("остр"));
        assertFalse(trie.findWord("острыв"));
        assertTrue(trie.findWord("БАРАБАН"));
        assertFalse(trie.findWord("б"));
        assertFalse(trie.findWord("бараб"));
    }

    @Test
    void deleteWord() {
        Trie trie = new Trie();
        trie.addWord("Остров");
        trie.addWord("ост");
        trie.addWord("острый");
        trie.addWord("островитянин");
        trie.deleteWord("остров");
        trie.deleteWord("");
        assertTrue(trie.findWord("островитянин"));
        assertFalse(trie.findWord("остров"));
        assertTrue(trie.findWord("ост"));
        assertFalse(trie.findWord("ост рый"));

        //for another first letter
        trie.addWord("Ворон");
        trie.addWord("воро");
        trie.addWord("вор");
        trie.deleteWord("воро");
        assertFalse(trie.findWord("Воро"));
        assertTrue(trie.findWord("Вор"));
        assertTrue(trie.findWord("Ворон"));

        trie.deleteWord("ворон");
        assertTrue(trie.findWord("Вор"));
        assertFalse(trie.findWord("Ворон"));

        trie.deleteWord("ворона");
        assertTrue(trie.findWord("вор"));
    }

    @Test
    void findWord() {
        Trie trie = new Trie();
        assertFalse(trie.findWord("абв"));
        assertFalse(trie.findWord(""));

        trie.addWord("корабль");
        assertFalse(trie.findWord("кора"));
        assertTrue(trie.findWord("Корабль"));
        assertFalse(trie.findWord("корабльб"));
    }

    @Test
    void findByPrefix() {
        Trie trie = new Trie();
        trie.addWord("вор");
        trie.addWord("ворд");
        trie.addWord("ворс");
        trie.addWord("ворсинка");
        trie.addWord("верх");
        trie.addWord("договор");
        trie.addWord("ворог");
        trie.addWord("ворон");
        trie.addWord("во");

        assertEquals(
                trie.findByPrefix("вор"),
                List.of("вор", "ворс", "ворсинка", "ворд", "ворог", "ворон")
        );
        assertEquals(
                trie.findByPrefix("ворс"),
                List.of("ворс", "ворсинка")
        );
        assertEquals(
                trie.findByPrefix("воро"),
                List.of("ворог", "ворон")
        );
        assertEquals(
                trie.findByPrefix("вон"),
                Collections.emptyList()
        );
    }

    @Test
    void testEquals() {
        Trie trie1 = new Trie();
        Trie trie2 = new Trie();

        trie1.addWord("Воск");
        trie1.addWord("Алебастр");
        trie1.addWord("Лестница");
        trie1.addWord("Голос");
        trie1.addWord("восковой");
        trie1.addWord("во");

        trie2.addWord("голос");
        trie2.addWord("во");
        trie2.addWord("Восковой");
        trie2.addWord("Воск");
        trie2.addWord("Лестница");
        trie2.addWord("Алебастр");

        assertEquals(trie1, trie2);
        assertEquals(trie2, trie1);

        trie1.deleteWord("воск");
        assertNotEquals(trie1, trie2);
        assertNotEquals(trie2, trie1);

        trie1.addWord("воск");
        trie2.addWord("вол");
        assertNotEquals(trie1, trie2);
        assertNotEquals(trie2, trie1);

        Trie trie3 = new Trie();
        Trie trie4 = new Trie();
        assertEquals(trie3, trie4);
        assertEquals(trie4, trie3);
    }
}