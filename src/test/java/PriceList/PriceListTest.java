package PriceList;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriceListTest {
    private static PriceList createPriceList() {
        return new PriceList(
                new Pair<Item, Double>(new Item("Товар 1", 1), 34.99),
                new Pair<Item, Double>(new Item("Товар 2", 12), 0.99),
                new Pair<Item, Double>(new Item("Товар 3", 123), 55.0),
                new Pair<Item, Double>(new Item("Товар 4", 1234), 0.99));
    }
    @Test
    public void add() {
        assertEquals(5, createPriceList()
                .add(new Item("Товар 1", 1), 34.99)
                .size());

}
}

