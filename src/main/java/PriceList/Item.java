package PriceList;

import java.util.Objects;

public class Item {

    private String name;
    private Integer id;
    private Double price;

    public Item(String newName, int newId, double newPrice) {
        name = newName;
        id = newId;
        price = Math.floor(newPrice*100)/100;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public Item changeName(String newName) {
        name = newName;
        return this;
    }

    public Item changeId(int newId) {
        id = newId;
        return this;
    }

    public Item changePrice(double newPrice) {
        price = Math.floor(newPrice*100)/100;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name) && id.equals(item.id) && price.equals(item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", id=" + id + ", price=" + price +'}';
    }
}
