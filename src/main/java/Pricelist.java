
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pricelist {
    private HashMap<String, Product> map = new HashMap<String, Product>();

    public void add(String name, Product product) {
        map.put(name, product);
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
        map.remove(name);
    }

    public double costOfProductCode(int code) {
        double sum = 0;
        for (Map.Entry<String, Product> element : this.map.entrySet()) {
            if (code == element.getValue().getCode()) sum += element.getValue().getPrice();
        }
        return sum;
    }

    public int size() {
        return this.map.size();
    }

    public Product getProduct(String name) {
        return map.get(name);
    }

    @Override
    public String toString() {
        return "Pricelist{" + map +'}';
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
    private double cost;

    Product(int code, double cost) {
        this.code = code;
        this.cost = cost;
    }

    double getPrice() {
        return cost;
    }

    void setPrice(double cost) {
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
                Double.compare(product.cost, cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, cost);
    }
}