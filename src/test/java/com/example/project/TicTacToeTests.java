package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TicTacToeTests {
    @Test
    public void addCross() {
        TicTacToe game = new TicTacToe(3);
        assertEquals(TicTacSymbol.EMPTY, game.getFieldAt(2, 0));
        game.setTicTacSymbol(2, 0, TicTacSymbol.CROSS);
        assertEquals(TicTacSymbol.CROSS, game.getFieldAt(2, 0));
        assertThrows(IllegalArgumentException.class, () -> { game.setTicTacSymbol(-1, 0, TicTacSymbol.CROSS);});
    }

    @Test
    public void addZero() {
        TicTacToe game = new TicTacToe(3);
        assertEquals(TicTacSymbol.EMPTY, game.getFieldAt(1, 0));
        assertEquals(TicTacSymbol.EMPTY, game.getFieldAt(2, 0));
        game.setTicTacSymbol(1, 0, TicTacSymbol.ZERO);
        game.setTicTacSymbol(2, 0, TicTacSymbol.ZERO);
        assertEquals(TicTacSymbol.ZERO, game.getFieldAt(1, 0));
        assertEquals(TicTacSymbol.ZERO, game.getFieldAt(2, 0));
        assertThrows(IllegalArgumentException.class, () -> { game.setTicTacSymbol(-1, 0, TicTacSymbol.ZERO);});
    }

    @Test
    public void cleanCell() {
        TicTacToe game = new TicTacToe(3);
        game.setTicTacSymbol(0, 2, TicTacSymbol.CROSS);
        game.cleanCell(0, 2);
        assertEquals(TicTacSymbol.EMPTY, game.getFieldAt(0, 2));
        assertThrows(IllegalArgumentException.class, () -> { game.cleanCell(-1, 0);});
    }


    @Test
    public void getDesk() {
        TicTacToe game = new TicTacToe(3);
        assertEquals(game.desk, game.getDesk());
        game.desk[0][2] = TicTacSymbol.CROSS;
        assertEquals(game.desk, game.getDesk());
    }

    @Test
    public void getFieldAt() {
        TicTacToe game = new TicTacToe(3);
        game.setTicTacSymbol(0,1, TicTacSymbol.CROSS);
        assertEquals(TicTacSymbol.CROSS, game.getFieldAt(0, 1));
        assertEquals(TicTacSymbol.EMPTY, game.getFieldAt(1, 0));
        assertThrows(IllegalArgumentException.class, () -> { game.getFieldAt(-1, 0);});
    }

    @Test
    public void getWinner() {
        TicTacToe game = new TicTacToe(4);
        game.setTicTacSymbol(0, 1, TicTacSymbol.CROSS);
        game.setTicTacSymbol(1, 1, TicTacSymbol.CROSS);
        game.setTicTacSymbol(2, 1, TicTacSymbol.CROSS);
        game.setTicTacSymbol(0,0, TicTacSymbol.ZERO);
        game.setTicTacSymbol(1, 0, TicTacSymbol.ZERO);
        game.setTicTacSymbol(3, 0,TicTacSymbol.CROSS);
        game.setTicTacSymbol(3,2, TicTacSymbol.ZERO);
        game.setTicTacSymbol(0,2, TicTacSymbol.CROSS);
        game.setTicTacSymbol(0, 3, TicTacSymbol.CROSS);
        game.setTicTacSymbol(3,3, TicTacSymbol.ZERO);
        game.setTicTacSymbol(2,2, TicTacSymbol.ZERO);
        assertEquals(new Line(3, new Point(0, 1), new Point(2, 1)), game.getWinner(TicTacSymbol.CROSS, DirectionSymbol.VERTICAL));
        assertEquals(new Line(2, new Point(0, 0), new Point(1, 0)), game.getWinner(TicTacSymbol.ZERO, DirectionSymbol.VERTICAL));
        assertEquals(new Line(2, new Point(3, 2), new Point(3, 3)), game.getWinner(TicTacSymbol.ZERO, DirectionSymbol.HORIZONTAL));
        assertEquals(new Line(2, new Point(3, 0), new Point(2, 1)), game.getWinner(TicTacSymbol.CROSS, DirectionSymbol.DIAGONAL));
        assertEquals(new Line(2, new Point(2, 2), new Point(3, 3)), game.getWinner(TicTacSymbol.ZERO, DirectionSymbol.DIAGONAL));
    }
}
