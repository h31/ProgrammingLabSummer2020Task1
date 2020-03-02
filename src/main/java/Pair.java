public class Pair {

    public final String key;
    public final Address value;

    Pair(String key, Address value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public Address getValue() {
        return this.value;
    }
}
