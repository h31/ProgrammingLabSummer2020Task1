import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

    @Test
    public void getStreet() {
        assertEquals("Nevskiy", new Address("Nevskiy", "35", "261")
                .getStreet());
    }

    @Test
    public void getHouse() {
        assertEquals("35", new Address("Nevskiy", "35", "261")
                .getHouse());
    }

    @Test
    public void getApartment() {
        assertEquals("261", new Address("Nevskiy", "35", "261")
                .getApartment());
    }

    @Test
    public void testEquals() {
        assertTrue(new Address("Primorskaya", "24", "118")
                .equals(new Address("Primorskaya", "24", "118")));
        assertFalse(new Address("Shavrova", "54", "32")
                .equals(new Address("Shavrova", "54", "87")));
    }
}