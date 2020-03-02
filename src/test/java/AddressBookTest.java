import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class AddressBookTest {

    private static AddressBook createDictionary() {
        return new AddressBook(
                new Pair("Krivitskii", new Address("Komendantskii", "1", "1")),
                new Pair("Shadaev", new Address("Komendantskii", "22", "269")),
                new Pair("Galieva", new Address("Nevskiy", "35", "3")),
                new Pair("Shomov", new Address("Komendantskii", "35", "5")),
                new Pair("Kuznecova", new Address("Nevskiy", "35", "3")),
                new Pair("Sergeev", new Address("Primorskaya", "19", "2")),
                new Pair("Petryaeva", new Address("Lesnaya", "22", "269")));
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

    @Test(expected = IllegalArgumentException.class)
    public void removeNull() {
        new AddressBook()
                .remove("Test");
    }

    @Test
    public void changeAddress() {
        assertEquals("Komendantskii", createDictionary()
                .changeAddress("Galieva", new Address("Komendantskii", "35", "3"))
                .getAddress("Galieva").getStreet());
    }

    @Test(expected = NullPointerException.class)
    public void changeAddressNull() {
        new AddressBook()
                .changeAddress("Krivitskii", new Address("Nevskiy", "35", "3"));
    }

    @Test
    public void getAdds() {
        assertEquals(new AddressBook(
                new Pair("Shadaev", new Address("Komendantskii", "22", "269")))
                .getAddress("Shadaev"), new Address("Komendantskii", "22", "269"));
    }

    @Test(expected = NullPointerException.class)
    public void getAddressNull() {
        new AddressBook()
                .getAddress("Krivitskii");
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
    public void filterBuilding() {
        assertTrue(Arrays.asList("Galieva", "Kuznecova")
                .containsAll(createDictionary().filterBuilding("Nevskiy", "35")));
        assertTrue(createDictionary().filterBuilding("Komendantskii", "228")
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

    @Test
    public void getStreet() {
        assertEquals("Nevskiy", new Address("Nevskiy", "35", "261")
                .getStreet());
    }

    @Test
    public void getBuilding() {
        assertEquals("35", new Address("Nevskiy", "35", "261")
                .getBuilding());
    }

    @Test
    public void getApartment() {
        assertEquals("261", new Address("Nevskiy", "35", "261")
                .getApartment());
    }

    @Test
    public void testEquals() {
        assertEquals(new Address("Primorskaya", "24", "118"), new Address("Primorskaya", "24", "118"));
        assertNotEquals(new Address("Shavrova", "54", "32"), new Address("Shavrova", "54", "87"));
    }
}