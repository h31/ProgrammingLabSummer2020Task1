import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AddressBookTest {

    @Test
    public void add() {
        assertEquals(8,
                new AddressBook(
                        new Pair<>("Krivitskii", new Address("Komendantskii", "1", "1")),
                        new Pair<>("Shadaev", new Address("Komendantskii", "22", "269")),
                        new Pair<>("Galieva", new Address("Nevskiy", "35", "3")),
                        new Pair<>("Shomov", new Address("Komendantskii", "35", "5")),
                        new Pair<>("Kuznecova", new Address("Nevskiy", "35", "3")),
                        new Pair<>("Sergeev", new Address("Primorskaya", "19", "2")),
                        new Pair<>("Petryaeva", new Address("Lesnaya", "22", "269"))
                ).add("Sidorov", new Address("Lesnaya", "52", "24")).size());
        assertEquals(1, new AddressBook().add("Krivitskii", new Address("Komendantskii", "1", "1")).size());
    }

    @Test
    public void remove() {
        assertEquals(2,
                new AddressBook(
                        new Pair<>("Krivitskii", new Address("Komendantskii", "1", "1")),
                        new Pair<>("Shadaev", new Address("Komendantskii", "22", "269")),
                        new Pair<>("Galieva", new Address("Nevskiy", "35", "3"))
                ).remove("Galieva").size());
        assertNull(new AddressBook().remove("Krivitskii"));
    }

    @Test
    public void changeAdds() {
        assertEquals("Komendantskii", new AddressBook(
                new Pair<>("Krivitskii", new Address("Komendantskii", "1", "1")),
                new Pair<>("Shadaev", new Address("Komendantskii", "22", "269")),
                new Pair<>("Galieva", new Address("Nevskiy", "35", "3"))
        ).changeAdds("Galieva", new Address("Komendantskii", "35", "3")).getAdds("Galieva").getStreet());
        assertNull(new AddressBook().changeAdds("Krivitskii", new Address("Nevskiy", "35", "3")));
    }

    @Test
    public void getAdds() {
        assertTrue(new AddressBook(
                new Pair<>("Shadaev", new Address("Komendantskii", "22", "269"))).getAdds("Shadaev")
                .equals(new Address("Komendantskii", "22", "269")));
        assertNull(new AddressBook().getAdds("Krivitskii"));
    }

    @Test
    public void filterStreet() {
        /*assertTrue(new AddressBook(
                new Pair<>("Krivitskii", new Address("Komendantskii", "1", "1")),
                new Pair<>("Mukaddam", new Address("Komendantskii", "22", "269")),
                new Pair<>("Chuprov", new Address("Nevskiy", "35", "3"))
        ).filterStreet("Komendantskii");*/
    }

    @Test
    public void filterHouse() {
    }
}