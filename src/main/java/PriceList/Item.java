package PriceList;

import java.util.Objects;

public class Item {

    private String name;
    private int id;
    private int rubles;
    private int pennies;

    public Item(String newName, int newId, int newRubles, int newPennies) {
        if (newRubles < 0 || newPennies < 0) throw new IllegalArgumentException("Отрицательная стоимость");
        name = newName;
        id = newId;
        if (newPennies > 99){
            rubles = newRubles + newPennies/100;
            pennies = newPennies % 100;
        } else {
            rubles = newRubles;
            pennies = newPennies;
        }
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getRubles() {
        return rubles;
    }

    public int getPennies() {
        return pennies;
    }

    public String getPrice(){
        if (pennies < 10) return rubles+".0"+pennies;
        else return rubles+"."+pennies;
    }
    public Item changeName(String newName) {
        name = newName;
        return this;
    }

    public Item changeId(int newId) {
        id = newId;
        return this;
    }

    public Item changeRubles(int newRubles) {
        if (newRubles < 0) throw new IllegalArgumentException("Отрицательная стоимость");
        rubles = newRubles;
        return this;
    }

    public Item changePennies(int newPennies) {
        if (newPennies < 0) throw new IllegalArgumentException("Отрицательная стоимость");
        else if (newPennies > 99) {
            rubles += newPennies/100;
            pennies = newPennies % 100;
        }
        else {
            pennies = newPennies;
        }
        return this;
    }

    public Item changePrice(int newRubles, int newPennies) {
        changeRubles(newRubles);
        changePennies(newPennies);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name) && id == item.id && rubles == item.rubles && pennies == item.pennies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, rubles, pennies);
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", id=" + id + ", rubles=" + rubles + ", pennies=" + pennies +'}';
    }
}
