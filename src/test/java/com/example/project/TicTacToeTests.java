package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TicTacToeTests {
    @Test
    public void addCross() {
        TicTacToe game = new TicTacToe(3);
        game.addCross(2, 0);
        assertEquals('X', game.getFieldAt(2, 0));
    }

    @Test
    public void addZero() {
        TicTacToe game = new TicTacToe(3);
        game.addZero(1, 0);
        game.addZero(2, 0);
        assertEquals('O', game.getFieldAt(1, 0));
        assertEquals('O', game.getFieldAt(2, 0));
    }

    @Test
    public void CleanCell() {
        TicTacToe game = new TicTacToe(3);
        game.addCross(0, 2);
        game.cleanCell(0, 2);
        assertEquals('*', game.getFieldAt(0, 2));
    }


    @Test
    public void getDesk() {
        TicTacToe game = new TicTacToe(3);
        assertTrue(game.desk.equals(game.getDesk()));
    }

    @Test
    public void getFieldAt() {
        TicTacToe game = new TicTacToe(3);
        game.addCross(0,1);
        assertEquals('X', game.getFieldAt(0, 1));
        assertEquals('*', game.getFieldAt(1, 0));
    }

    @Test
    public void getWinner() {
        TicTacToe game = new TicTacToe(4);
        game.addCross(0, 1);
        game.addCross(1, 1);
        game.addCross(2, 1);
        game.addZero(0,0);
        game.addZero(1, 0);
        game.addCross(3, 0);
        game.addZero(3,2);
        game.addCross(0,2);
        game.addCross(0, 3);
        game.addZero(3,3);
        game.addZero(2,2);
        assertEquals(new Line(3, new Point(0, 1), new Point(2, 1)), game.getWinner(TicTacSymbol.CROSS, DirectionSymbol.VERTICAL));
        assertEquals(new Line(2, new Point(0, 0), new Point(1, 0)), game.getWinner(TicTacSymbol.ZERO, DirectionSymbol.VERTICAL));
        assertEquals(new Line(2, new Point(3, 2), new Point(3, 3)), game.getWinner(TicTacSymbol.ZERO, DirectionSymbol.HORIZONTAL));
        assertEquals(new Line(2, new Point(3, 0), new Point(2, 1)), game.getWinner(TicTacSymbol.CROSS, DirectionSymbol.DIAGONAL));
        assertEquals(new Line(2, new Point(2, 2), new Point(3, 3)), game.getWinner(TicTacSymbol.ZERO, DirectionSymbol.DIAGONAL));
    }
}
