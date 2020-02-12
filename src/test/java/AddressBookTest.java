import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class AddressBookTest {

    private static AddressBook createDictionary() {
        return new AddressBook(
                new Pair<>("Krivitskii", new Address("Komendantskii", "1", "1")),
                new Pair<>("Shadaev", new Address("Komendantskii", "22", "269")),
                new Pair<>("Galieva", new Address("Nevskiy", "35", "3")),
                new Pair<>("Shomov", new Address("Komendantskii", "35", "5")),
                new Pair<>("Kuznecova", new Address("Nevskiy", "35", "3")),
                new Pair<>("Sergeev", new Address("Primorskaya", "19", "2")),
                new Pair<>("Petryaeva", new Address("Lesnaya", "22", "269")));
    }

    @Test
    public void add() {
        assertEquals(8, createDictionary()
                .add("Sidorov", new Address("Lesnaya", "52", "24"))
                .size());
        assertEquals(1, new AddressBook()
                .add("Krivitskii", new Address("Komendantskii", "1", "1"))
                .size());
    }

    @Test
    public void remove() {
        assertEquals(6, createDictionary()
                .remove("Galieva")
                .size());
    }

    @Test(expected = NullPointerException.class)
    public void removeNull() {
        new AddressBook()
                .remove("Test");
    }

    @Test
    public void changeAdds() {
        assertEquals("Komendantskii", createDictionary()
                .changeAdds("Galieva", new Address("Komendantskii", "35", "3"))
                .getAdds("Galieva").getStreet());
    }

    @Test(expected = NullPointerException.class)
    public void changeAddsNull() {
        new AddressBook()
                .changeAdds("Krivitskii", new Address("Nevskiy", "35", "3"));
    }

    @Test
    public void getAdds() {
        assertTrue(new AddressBook(
                new Pair<>("Shadaev", new Address("Komendantskii", "22", "269")))
                .getAdds("Shadaev")
                .equals(new Address("Komendantskii", "22", "269")));
    }

    @Test(expected = NullPointerException.class)
    public void getAddsNull() {
        new AddressBook()
                .getAdds("Krivitskii");
    }

    @Test
    public void filterStreet() {
        assertTrue(Arrays.asList("Krivitskii", "Shadaev", "Shomov")
                .containsAll(createDictionary().filterStreet("Komendantskii")));
        assertTrue(createDictionary()
                .filterStreet("Spasskaya")
                .isEmpty());
    }

    @Test
    public void filterHouse() {
        assertTrue(Arrays.asList("Galieva", "Kuznecova")
                .containsAll(createDictionary().filterHouse("Nevskiy", "35")));
        assertTrue(createDictionary().filterHouse("Komendantskii", "228")
                .isEmpty());
    }

    @Test
    public void size() {
        assertEquals(8, createDictionary()
                .add("Orlov", new Address("Nevskiy", "35", "222"))
                .size());
        assertEquals(0, new AddressBook()
                .size());
    }
}