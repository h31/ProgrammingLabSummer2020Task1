import java.util.Objects;

public class Pair {

    private final String key;
    private final Address value;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Objects.equals(getKey(), pair.getKey()) &&
                Objects.equals(getValue(), pair.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKey(), getValue());
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
