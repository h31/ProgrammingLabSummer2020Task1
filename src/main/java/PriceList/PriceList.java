package PriceList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class PriceList {

    private ArrayList<Item> items = new ArrayList<Item>();;

    public PriceList(ArrayList<Item> items) {
        this.items = items;
    }

    public PriceList(Item... items) {
        this.items.addAll(Arrays.asList(items));
    }

    public PriceList add(Item newItem) {
        for (Item i : items) {
            if (i.getName().equals(newItem.getName())) throw new IllegalArgumentException();
            if (i.getId() == newItem.getId()) throw new IllegalArgumentException();
        }
        items.add(newItem);
        return this;
    }

    public PriceList add(String name, Integer id, double price) {
        for (Item i : items) {
            if (i.getName().equals(name)) throw new IllegalArgumentException();
            if (i.getId() == id) throw new IllegalArgumentException();
        }
        items.add(new Item(name, id, Math.floor(price * 100) / 100));
        return this;
    }

    public PriceList changePrice(int id, double newPrice) {
        for (Item i : items) {
            if (i.getId() == id) {
                i.changePrice(Math.floor(newPrice * 100) / 100);
                return this;
            }

        }
        throw new IllegalArgumentException();
    }

    public PriceList changePrice(String name, double newPrice) {
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


