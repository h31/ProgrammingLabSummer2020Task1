import javafx.util.Pair;

public class Main {
    public static void main(String[] args) {
        AddressBook dictionary = new AddressBook(
                new Pair<>("Krivitskii", new Address("Komendantskii", "1", "1")),
                new Pair<>("Shadaev", new Address("Komendantskii", "22", "269")),
                new Pair<>("Galieva", new Address("Nevskiy", "35", "3")),
                new Pair<>("Shomov", new Address("Komendantskii", "35", "5")),
                new Pair<>("Kuznecova", new Address("Nevskiy", "35", "3")),
                new Pair<>("Sergeev", new Address("Primorskaya", "19", "2")),
                new Pair<>("Petryaeva", new Address("Lesnaya", "22", "269"))
        );
    }
}