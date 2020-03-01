package PriceList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

        @Test
        public void getName() {
             assertEquals("name", new Item("name", 0).getName());
        }

        @Test
        public void getId() {
            assertEquals(0, new Item("name", 0).getId());
        }

        @Test
        public void ChangeName() {
            Item test = new Item("newName", 0);
            assertEquals(test, new Item("name", 0).changeName("newName"));
        }

        @Test
        public void changeId() {
            Item test = new Item("name", 123);
            assertEquals(test, new Item("name", 0).changeId(123));
        }
}
