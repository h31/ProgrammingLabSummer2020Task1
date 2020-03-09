import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;

import static org.junit.jupiter.api.Assertions.*;

class TicTacTest {
    TicTac exampleTicTacT(){
        TicTac myField = new TicTac(5);
        return myField;
    }
    TicTac myField = exampleTicTacT();
    int reaserch1() {
        myField.set(0,0,'X');
        myField.set(1,0,'X');
        myField.set(2,0,'X');
        return myField.search( 'X');
    }

    int reaserch2() {
        myField.set(0,0,'1');
        myField.set(1,1,'1');
        myField.set(2,2,'1');
        myField.set(3,3,'1');
        myField.set(4,4,'1');
        myField.set(2,0,'1');
        return myField.search( '1');
    }
    int reaserch3() {
        myField.set(4,4,'X');
        myField.set(3,3,'X');
        myField.set(2,2,'X');
        myField.set(1,1,'X');
        myField.set(0,0,'X');
        myField.set(4,3,'X');
        myField.set(2,1,'X');
        return myField.search( 'X');
    }

    int addField0(){
        myField.set(0, 0, 'X');
        return myField.getValue(0,0);
    }
    int addField1(){
        myField.set(1, 0, '0');
        return myField.getValue(1,0);
    }
    int addField2(){
        myField.set(2, 0, '1');
        return myField.getValue(2,0);
    }
    int deleteField(){
        myField.delete(2, 0);
        return myField.getValue(2,0);
    }

    @Test
    void add() {
        assertEquals('X', addField0());
        assertEquals('0', addField1());
    }

    @Test
    void delete() {
        assertEquals('1', deleteField());
    }

    @Test
    void search() {
        assertEquals(3, reaserch1());
        assertEquals(5, reaserch2());
        assertEquals(5, reaserch3());
    }


    @Test
    void fieldSize() {
    }

}