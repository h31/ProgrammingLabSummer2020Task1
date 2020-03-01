import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacTest {
    TicTac exampleTicTacT(){
        TicTac myField = new TicTac(5);
        return myField;
    }
    TicTac myField = exampleTicTacT();
    String reaserch1() {
        myField.add(0,0,1);
        myField.add(1,0,1);
        myField.add(2,0,1);
        return myField.search( 1);
    }

    String reaserch2() {
        myField.add(0,0,1);
        myField.add(1,1,1);
        myField.add(2,2,1);
        myField.add(3,3,1);
        myField.add(4,4,1);
        myField.add(2,0,1);
        return myField.search( 1);
    }
    String reaserch3() {
        myField.add(4,0,2);
        myField.add(3,1,2);
        myField.add(2,2,2);
        myField.add(1,3,2);
        myField.add(0,4,2);
        return myField.search( 2);
    }
    int addField0(){
        myField.add(0, 0, 1);
        return myField.getValue(0,0);
    }
    int addField1(){
        myField.add(1, 0, 1);
        return myField.getValue(1,0);
    }
    int addField2(){
        myField.add(2, 0, 1);
        return myField.getValue(2,0);
    }
    int deleteField(){
        myField.delete(2, 0);
        return myField.getValue(2,0);
    }

    @Test
    void add() {
        assertEquals(1, addField0());
        assertEquals(1, addField1());
    }

    @Test
    void delete() {
        assertEquals(0, deleteField());
    }

    @Test
    void search() {
        assertEquals("Максимальное число 1 - 3", reaserch1());
        assertEquals("Максимальное число 1 - 5", reaserch2());
        assertEquals("Максимальное число 2 - 5", reaserch3());

    }
    @Test
    void fieldSize() {
    }

}