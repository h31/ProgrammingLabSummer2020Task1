import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacTest {
    TicTac exampleTicTacT(){
        TicTac myField = new TicTac(3);
        return myField;
    }
    TicTac myField = exampleTicTacT();
    String reaserch1() {
        myField.add(0,0,1);
        myField.add(1,0,1);
        myField.add(2,0,1);
        return myField.search(3, 1);
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
    void search() { assertEquals("3 0 0 2 0", reaserch1());

    }
    @Test
    void fieldSize() {
    }

}