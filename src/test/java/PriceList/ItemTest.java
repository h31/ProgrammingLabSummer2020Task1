package PriceList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

    @Test
    public void getName() {
        assertEquals("name", new Item("name", 0, 1, 0).getName());
    }

    @Test
    public void getId() {
        assertEquals(0, new Item("name", 0, 1, 0).getId());
    }

    @Test
    public void getRubles() {
        assertEquals(1, new Item("name", 0, 1, 0).getRubles());
    }

    @Test
    public void getPennies() {
        assertEquals(0, new Item("name", 0, 1, 0).getPennies());
    }

    @Test
    public void getPrice() {
        assertEquals("4.99", new Item("name", 0, 4, 99).getPrice());
        assertEquals("4.09", new Item("name", 0, 4, 9).getPrice());
    }


    @Test
    public void changeName() {
        Item test = new Item("newName", 0, 1, 0);
        assertEquals(test, new Item("name", 0, 1, 0).changeName("newName"));
    }

    @Test
    public void changeId() {
        Item test = new Item("name", 123, 2, 11);
        assertEquals(test, new Item("name", 0, 2, 11).changeId(123));
    }

    @Test
    public void changeRubles() {
        Item test = new Item("name", 0, 123, 11);
        assertEquals(test, new Item("name", 0, 2, 11).changeRubles(123));
        assertThrows(IllegalArgumentException.class, () -> {
            new Item("name", 0, 2, 11).changeRubles(-1);
        });
    }

    @Test
    public void changePennies() {
        Item test = new Item("name", 0, 2, 99);
        assertEquals(test, new Item("name", 0, 2, 11).changePennies(99));
        assertThrows(IllegalArgumentException.class, () -> {
            new Item("name", 0, 2, 11).changePennies(-1);
        });
        assertEquals(test, new Item("name", 0, 1, 11).changePennies(199));
    }

    @Test
    public void changePrice() {
        Item test = new Item("name", 123, 2, 11);
        assertEquals(test, new Item("name", 123, 1, 0).changePrice(2, 11));
        assertEquals(test, new Item("name", 123, 1, 11).changePrice(1, 111));
    }
}
