
import java.util.HashMap;
import java.util.Map;

class Pricelist {
    private HashMap<String, Product> map = new HashMap<>();
    Pricelist(){
        map = new HashMap<String, Product>();
    }

    void add(String name, Product product){
        for(String element: this.map.keySet()){
            if(element.contains(name)) throw new IllegalArgumentException();
        }
        map.put(name, product);
    }

    void changeName(String oldName, String newName) {
        for(String element: this.map.keySet()){
            if(element.contains(oldName)) throw new IllegalArgumentException();
        }
        Product value = map.get(oldName);
        map.remove(oldName);
        map.put(newName, value);
    }
    boolean contains (String name){
        return this.map.containsKey(name);
    }

    void remove(String name){

        map.remove(name);
    }
    double costOfProductCode(int code){
        double sum = 0;
        for (Map.Entry<String,Product> element : this.map.entrySet()){
            if(code == element.getValue().getCode()) sum += element.getValue().getPrice();
        }
        return sum;
    }
    int size(){
        return this.map.size();
    }
    Product getProduct(String name){
        for(String element: this.map.keySet()){
            if(element.contains(name)) throw new IllegalArgumentException();
        }
        return map.get(name);
    }
}
class Product{
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
    int getCode(){

        return code;
    }
}