package PriceList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceListTest {

    private PriceList createPriceList() {
        PriceList test = new PriceList();
        test.add("Товар 1", 1, 99.99);
        test.add("Товар 2", 12, 0.0);
        test.add("Товар 3", 123, 11.111);
        test.add("Товар 4", 1234, 1.23);
        test.add("Товар 5", 12345, 4.90);
        return test;
    }

    @Test
    public void add() {
        assertEquals(6, createPriceList()
                .add(new Item("Товар 6", 2, 34.99)).size());
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .add(new Item("name", 1, 34.99))
                    .size();
        });
    }

    @Test
    public void changePrice() {

        assertEquals(10.44, createPriceList().changePrice(1, 10.444).getPrice(1));

        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .changePrice(0, 34.99);

        });

        assertEquals(10.44, createPriceList().changePrice("Товар 1", 10.444).getPrice(1));

        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .changePrice("", 34.99);

        });
    }

    @Test
    public void changeName() {
        PriceList test = new PriceList().add("Товар 1", 1, 10.44);
        PriceList newTest = new PriceList().add("name", 1, 10.44);

        assertEquals(newTest.toString(), test.changeName(1, "name").toString());

        PriceList test2 = new PriceList().add("Товар 1", 1, 10.44);

        assertEquals(newTest.toString(), test2.changeName("Товар 1", "name").toString());

        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .changeName("", "Товар 6");

        });

    }

    @Test
    public void getPrice() {
        assertEquals(11.11, createPriceList().getPrice("Товар 3")
        );
        assertEquals(11.11, createPriceList().getPrice(123)
        );
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .getPrice("name");

        });
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .getPrice(0);

        });


    }

    @Test
    public void remove() {

        assertEquals(4, createPriceList().remove(123).size()
        );
        assertEquals(4, createPriceList().remove("Товар 3").size()
        );
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .remove("name");

        });
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .remove(0);

        });

    }

}

