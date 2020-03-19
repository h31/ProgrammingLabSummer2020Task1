import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pricelist {
    private Map<String, Product> map = new HashMap<String, Product>();

    public Product add(String name, Product product) {
        return map.put(name, product);
    }

    public void changeName(String oldName, String newName) {
        if (!map.containsKey(oldName)) throw new IllegalArgumentException();
        Product value = map.get(oldName);
        map.remove(oldName);
        map.put(newName, value);
    }

    public boolean contains(String name) {
        return this.map.containsKey(name);
    }

    public void remove(String name) {
        if (!map.containsKey(name)) throw new IllegalArgumentException();
        map.remove(name);
    }

    public BigDecimal costOfProductCode(int code) {
        BigDecimal res = BigDecimal.ZERO;
        for (Map.Entry<String, Product> element : this.map.entrySet()) {
            if (code == element.getValue().getCode()) res = res.add(element.getValue().getPrice());
        }
        return res;
    }

    public int size() {
        return this.map.size();
    }

    public Product getProduct(String name) {
        return map.get(name);
    }

    @Override
    public String toString() {
        return "Pricelist{" + map + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pricelist pricelist = (Pricelist) o;
        return Objects.equals(map, pricelist.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}

class Product {
    private int code;
    private BigDecimal cost;

    Product(int code, BigDecimal cost) {
        if (code < 0 || cost.compareTo(BigDecimal.ZERO) == 0) throw new IllegalArgumentException();
        this.code = code;
        this.cost = cost;
    }

    BigDecimal getPrice() {

        return cost;
    }

    void setPrice(BigDecimal cost) {
        this.cost = cost;
    }

    int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code=" + code +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return code == product.code &&
                product.cost.compareTo(cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, cost);
    }
}