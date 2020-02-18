package PriceList;




import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PriceListTest {
    private static PriceList createPriceList() {
        PriceList test = new PriceList();
        test.add("Товар 1",1, 99.99);
        test.add("Товар 2",12, 0.0);
        test.add("Товар 3",123, 11.111);
        test.add("Товар 4",1234, 1.23);
        test.add("Товар 5",12345, 4.90);
        return  test;
    }
    @Test
    public void add() {
        assertEquals(6, createPriceList()
                .add(new Item("Товар 6", 2), 34.99)
                .size());
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .add(new Item("Товар 4", 2), 34.99)
                    .size();
        });
    }
    @Test
    public void changePrice() {

        assertEquals(10.44, createPriceList().changePrice(1, 10.444).getPrice(1)
                );
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .changePrice(0, 34.99);

        });
        assertEquals(10.44, createPriceList().changePrice("Товар 1", 10.444).getPrice(1)
        );
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .changePrice("test", 34.99);

        });
    }
    @Test
    public void changeName() {
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .changeName("name", "Товар 3");

        });
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .changeName("Товар 2", "Товар 3");

        });
    }
    @Test
    public void getPrice() {

        assertEquals(11.11, createPriceList().getPrice(123)
        );
        assertEquals(4.90, createPriceList().getPrice("Товар 5")
        );
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .getPrice(0);

        });
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .getPrice("test");

        });
    }
    @Test
    public void remove() {
        assertEquals(4, createPriceList().remove(123).size()
        );
        assertEquals(4, createPriceList().remove("Товар 1").size()
        );
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .remove(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            createPriceList()
                    .remove("test");

        });
    }
    @Test
    public void itemGetName() {
        assertEquals("name", new Item("name", 0).getName());
    }
    @Test
    public void itemGetId() {
        assertEquals(0, new Item("name", 0).getId());
    }
    @Test
    public void itemChangeName() {
        Item test = new Item("newName", 0);
        assertEquals(test, new Item("name", 0).changeName("newName"));
    }
    @Test
    public void itemChangeId() {
        Item test = new Item("name", 123);
        assertEquals(test, new Item("name", 0).changeId(123));
    }
}

