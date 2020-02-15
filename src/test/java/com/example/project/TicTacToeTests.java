package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TicTacToeTests {
    @Test
    public void addCross() {
        TicTacToe game = new TicTacToe(3);
        game.addCross(2, 0);
        assertEquals("X", game.getFieldAt(2, 0));
    }

    @Test
    public void addZero() {
        TicTacToe game = new TicTacToe(3);
        game.addZero(1, 0);
        game.addZero(2, 0);
        assertEquals("O", game.getFieldAt(1, 0));
        assertEquals("O", game.getFieldAt(2, 0));
    }

    @Test
    public void CleanCell() {
        TicTacToe game = new TicTacToe(3);
        game.addCross(0, 2);
        game.cleanCell(0, 2);
        assertEquals("*", game.getFieldAt(0, 2));
    }


    @Test
    public void getDesk() {
        TicTacToe game = new TicTacToe(3);
        game.getDesk();
        assertTrue(game.desk.equals(game.getDesk()));
    }

    @Test
    public void getFieldAt() {
        TicTacToe game = new TicTacToe(3);
        game.addCross(0,1);
        assertEquals("X", game.getFieldAt(0, 1));
        assertEquals("*", game.getFieldAt(1, 0));
    }

    @Test
    public void findVertical () {
        TicTacToe game = new TicTacToe(4);
        game.addCross(0, 1);
        game.addCross(1, 1);
        game.addCross(2, 1);
        game.addZero(0,0);
        game.addZero(1, 0);
        assertEquals("максимальная длина 3 координаты начала (0, 1) координаты конца (2, 1)", game.findVertical("X"));
        assertEquals("максимальная длина 2 координаты начала (0, 0) координаты конца (1, 0)", game.findVertical("O"));
    }

    @Test
    public void findHorizontal() {
        TicTacToe game = new TicTacToe(4);
        game.addCross(0, 0);
        game.addCross(0, 1);
        game.addCross(0, 2);
        game.addZero(3, 0);
        game.addZero(3,1);
        assertEquals("максимальная длина 3 координаты начала (0, 0) координаты конца (0, 2)", game.findHorizontal("X"));
        assertEquals("максимальная длина 2 координаты начала (3, 0) координаты конца (3, 1)", game.findHorizontal("O"));
    }

    @Test
    public void findDiagonal() {
        TicTacToe game = new TicTacToe(4);
        game.addCross(0, 0);
        game.addCross(1, 1);
        game.addCross(2, 2);
        game.addZero(3, 0);
        game.addZero(2, 1);
        game.addZero(1, 2);
        game.addZero(0, 3);
        assertEquals("максимальная длина 3 координаты начала (0, 0) координаты конца (2, 2)", game.findDiagonal("X"));
        assertEquals("максимальная длина 4 координаты начала (0, 3) координаты конца (3, 0)", game.findDiagonal("O"));
    }
}
