package PriceList;

import java.util.ArrayList;
import java.util.Objects;

public class PriceList {

    private ArrayList<Item> items;

    public PriceList() {
        items = new ArrayList<Item>();
    }

    public PriceList add(Item newItem) {
        for (Item i : items) {
            if (i.getName().equals(newItem.getName())) throw new IllegalArgumentException();
            if (i.getId() == newItem.getId()) throw new IllegalArgumentException();
        }
        items.add(newItem);
        return this;
    }

    public PriceList add(String name, Integer id, Double price) {
        for (Item i : items) {
            if (i.getName().equals(name)) throw new IllegalArgumentException();
            if (i.getId() == id) throw new IllegalArgumentException();
        }
        items.add(new Item(name, id, Math.floor(price * 100) / 100));
        return this;
    }

    public PriceList changePrice(int id, Double newPrice) {
        for (Item i : items) {
            if (i.getId() == id) {
                i.changePrice(Math.floor(newPrice * 100) / 100);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList changePrice(String name, Double newPrice) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                i.changePrice(Math.floor(newPrice * 100) / 100);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList changeName(int id, String newName) {
        for (Item i : items) {
            if (i.getName().equals(newName)) throw new IllegalArgumentException();
            if (i.getId() == id) {
                i.changeName(newName);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList changeName(String oldName, String newName) {
        for (Item i : items) {
            if (i.getName().equals(newName)) throw new IllegalArgumentException();
            if (i.getName().equals(oldName)) {
                i.changeName(newName);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList remove(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                items.remove(i);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList remove(int id) {
        for (Item i : items) {
            if (i.getId() == id) {
                items.remove(i);
                return this;
            }
        }
        throw new IllegalArgumentException();
    }

    public double getPrice(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return i.getPrice();
            }
        }
        throw new IllegalArgumentException();

    }

    public double getPrice(int id) {
        for (Item i : items) {
            if (i.getId() == id) {
                return i.getPrice();
            }
        }
        throw new IllegalArgumentException();
    }

    public int size() {
        return items.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceList priceList = (PriceList) o;
        return items.equals(priceList.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "PriceList{" + items + '}';
    }
}


class Item {

    private String name;
    private Integer id;
    private Double price;

    public Item(String newName, int newId, double newPrice) {
        name = newName;
        id = newId;
        price = Math.floor(newPrice * 100) / 100;
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
        price = Math.floor(newPrice * 100) / 100;
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
                ", id=" + id + ", price=" + price + '}';
    }
}
