import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PricelistTest {
    Pricelist examplePricelist = new Pricelist() {{
        add("Kit kat", new Product(123, new BigDecimal(45.50)));
        add("Coca cola", new Product(12, new BigDecimal(56.50)));
        add("Danone classic", new Product(1234, new BigDecimal(35.00)));
        add("Danone mango", new Product(1234, new BigDecimal(35.00)));
        add("Danone banana", new Product(1234, new BigDecimal(35.00)));
        add("Tess", new Product(12345, new BigDecimal(105.55)));
    }};

    int newPricelistSize() {
        examplePricelist.add("Bon Aqua", new Product(1234455, new BigDecimal(38.00)));
        return examplePricelist.size();
    }

    boolean checkName() {
        examplePricelist.changeName("Kit kat", "Twix");
        return (examplePricelist.contains("Twix"));
    }

    int afterRemoveProduct() {
        examplePricelist.remove("Tess");
        return examplePricelist.size();
    }

    @Test
    public void contains() {
        assertTrue(examplePricelist.contains("Coca cola"));
    }

    @Test
    public void size() {
        assertEquals(6, examplePricelist.size());
    }

    @Test
    public void add() {
        assertEquals(7, newPricelistSize());
    }

    @Test
    public void changeName() {
        assertTrue(this::checkName);
        assertThrows(IllegalArgumentException.class, () -> {
            examplePricelist.changeName("Mars", "Twix");
        });
    }

    @Test
    public void remove() {
        assertEquals(5, afterRemoveProduct());
        assertThrows(IllegalArgumentException.class, () -> {
            examplePricelist.remove("Mars");
        });
    }

    @Test
    public void getProduct() {
        assertEquals(new Product(12, new BigDecimal(56.50)).toString(), examplePricelist.getProduct("Coca cola").toString());
    }

    @Test
    public void costOfProductCode() {
        assertEquals(new BigDecimal(105.00), examplePricelist.costOfProductCode(1234));
    }

    @Test
    void getPrice() {
        assertEquals(new BigDecimal(56.50), examplePricelist.getProduct("Coca cola").getPrice());
    }

    BigDecimal newPrice() {
        examplePricelist.getProduct("Coca cola").setPrice(new BigDecimal(26.00));
        return examplePricelist.getProduct("Coca cola").getPrice();
    }

    @Test
    void setPrice() {
        assertEquals(new BigDecimal(26.00), newPrice());
    }

    @Test
    void getCode() {
        assertEquals(12, examplePricelist.getProduct("Coca cola").getCode());
    }
}