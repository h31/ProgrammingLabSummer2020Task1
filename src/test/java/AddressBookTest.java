import org.junit.Test;

import static org.junit.Assert.*;

public class AddressBookTest {

    private AddressBook createDictionary() {
        AddressBook dictionary = new AddressBook();
        dictionary.add("Krivitskii", new Address("Komendantskii", "22", "269"));
        dictionary.add("Galieva", new Address("Nevskiy", "35", "3"));
        dictionary.add("Shomov", new Address("Komendantskii", "35", "5"));
        dictionary.add("Kuznecova", new Address("Nevskiy", "35", "5"));
        dictionary.add("Sergeev", new Address("Primorskaya", "19", "2"));
        dictionary.add("Petryaeva", new Address("Lesnaya", "22", "269"));
        return dictionary;
    }

    @Test
    public void add() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void changeAdds() {
    }

    @Test
    public void getAdds() {
    }

    @Test
    public void filterStreet() {
    }

    @Test
    public void filterHouse() {
    }
}