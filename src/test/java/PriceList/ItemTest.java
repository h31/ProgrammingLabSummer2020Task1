package PriceList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    public void getName() {
        assertEquals("name", new Item("name", 0, 1.00).getName());
    }

    @Test
    public void getId() {
        assertEquals(0, new Item("name", 0, 1.00).getId());
    }

    @Test
    public void getPrice() {
        assertEquals(1.00, new Item("name", 0, 1.00).getPrice());
    }

    @Test
    public void changeName() {
        Item test = new Item("newName", 0, 1.00);
        assertEquals(test, new Item("name", 0, 1.00).changeName("newName"));
    }

    @Test
    public void changeId() {
        Item test = new Item("name", 123, 1.00);
        assertEquals(test, new Item("name", 0, 1.00).changeId(123));
    }


    @Test
    public void changePrice() {
        Item test = new Item("name", 123, 1.00);
        assertEquals(test, new Item("name", 123, 0.00).changePrice(1.00));
    }
}
