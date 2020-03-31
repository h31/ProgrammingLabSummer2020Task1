package com.example.project;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CrossZeroTest {

    CrossZero game = new CrossZero(5);

    Cell cellBeginX = new Cell(0, 0);
    Cell cellEndX = new Cell(0, 0);
    Cell cellBeginO = new Cell(0, 0);
    Cell cellEndO = new Cell(0, 0);

    Sequence sequenceTest = new Sequence(cellBeginX, cellEndX, cellBeginO, cellEndO, 0, 0);

    @Test
    public void longestVerticalSequence() {
        for (int i = 0; i <= 4; i++) {
            game.setO(i, 0);
        }
        for (int i = 0; i <= 3; i++) {
            game.setX(i, 2);
        }

        cellEndX.setCellRow(3);
        cellEndX.setCellColumn(2);
        cellBeginX.setCellRow(0);
        cellBeginX.setCellColumn(2);

        cellBeginO.setCellColumn(0);
        cellBeginO.setCellRow(0);
        cellEndO.setCellRow(4);
        cellEndO.setCellColumn(0);

        sequenceTest.setBeginX(cellBeginX);
        sequenceTest.setEndX(cellEndX);
        sequenceTest.setBeginO(cellBeginO);
        sequenceTest.setEndO(cellEndO);

        sequenceTest.setLengthO(5);
        sequenceTest.setLengthX(4);

        assertEquals(sequenceTest, game.longestSequence(0, 1));
        for (int i = 0; i <= 4; i++) {//очистка поля
            game.clear(i, 0);
            game.clear(i, 2);
        }
    }

    @Test
    public void longestHorizontalSequence() {
        //последовательность по горизонтали
        for (int i = 0; i < 4; i++) {
            game.setO(0, i);
            game.setX(3, i);
        }

        cellEndO.setCellColumn(3);
        cellEndO.setCellRow(0);
        cellBeginO.setCellRow(0);
        cellBeginO.setCellColumn(0);

        cellBeginX.setCellRow(3);
        cellBeginX.setCellColumn(0);
        cellEndX.setCellRow(3);
        cellEndX.setCellColumn(3);

        sequenceTest.setBeginX(cellBeginX);
        sequenceTest.setEndX(cellEndX);
        sequenceTest.setBeginO(cellBeginO);
        sequenceTest.setEndO(cellEndO);

        sequenceTest.setLengthO(4);
        sequenceTest.setLengthX(4);

        assertEquals(sequenceTest, game.longestSequence(1, 0));
    }


    @Test
    void longestDiagonalX() {
        for (int i = 0; i < 4; ) {
            for (int j = 0; j < 4; ) {
                game.setX(i, j);
                i++;
                j++;
            }
        }
        cellEndX.setCellRow(3);
        cellEndX.setCellColumn(3);
        cellBeginX.setCellRow(0);
        cellBeginX.setCellColumn(0);

        cellBeginO.setCellColumn(0);
        cellBeginO.setCellRow(0);
        cellEndO.setCellRow(0);
        cellEndO.setCellColumn(0);

        sequenceTest.setBeginX(cellBeginX);
        sequenceTest.setEndX(cellEndX);
        sequenceTest.setBeginO(cellBeginO);
        sequenceTest.setEndO(cellEndO);

        sequenceTest.setLengthO(0);
        sequenceTest.setLengthX(4);

        assertEquals(sequenceTest, game.longestSequence(1, 1));
    }

    @Test
    void longestDiagonalO() {
        for (int i = 0; i < 4; ) {
            for (int j = 0; j < 4; ) {
                game.setO(i, j);
                i++;
                j++;
            }
        }
        cellEndX.setCellRow(0);
        cellEndX.setCellColumn(0);
        cellBeginX.setCellRow(0);
        cellBeginX.setCellColumn(0);

        cellBeginO.setCellColumn(0);
        cellBeginO.setCellRow(0);
        cellEndO.setCellRow(3);
        cellEndO.setCellColumn(3);

        sequenceTest.setBeginX(cellBeginX);
        sequenceTest.setEndX(cellEndX);
        sequenceTest.setBeginO(cellBeginO);
        sequenceTest.setEndO(cellEndO);

        sequenceTest.setLengthO(4);
        sequenceTest.setLengthX(0);
        assertEquals(sequenceTest, game.longestSequence(1, 1));
    }

    @Test
    void longestSideDiagonalX() {
        for (int i = 0; i < 4; ) {
            for (int j = 4; j >= 0; ) {
                game.setX(i, j);
                i++;
                j--;
            }
        }
        cellEndX.setCellRow(4);
        cellEndX.setCellColumn(0);
        cellBeginX.setCellRow(0);
        cellBeginX.setCellColumn(4);

        cellBeginO.setCellColumn(0);
        cellBeginO.setCellRow(0);
        cellEndO.setCellRow(0);
        cellEndO.setCellColumn(0);

        sequenceTest.setBeginX(cellBeginX);
        sequenceTest.setEndX(cellEndX);
        sequenceTest.setBeginO(cellBeginO);
        sequenceTest.setEndO(cellEndO);

        sequenceTest.setLengthO(0);
        sequenceTest.setLengthX(5);
        assertEquals(sequenceTest, game.longestSequence(-1, 1));
    }

    @Test
    void longestSideDiagonalO() {
        for (int i = 0; i < 4; ) {
            for (int j = 4; j >= 0; ) {
                game.setO(i, j);
                i++;
                j--;
            }
        }

        cellEndX.setCellRow(0);
        cellEndX.setCellColumn(0);
        cellBeginX.setCellRow(0);
        cellBeginX.setCellColumn(0);

        cellBeginO.setCellColumn(4);
        cellBeginO.setCellRow(0);
        cellEndO.setCellRow(4);
        cellEndO.setCellColumn(0);

        sequenceTest.setBeginX(cellBeginX);
        sequenceTest.setEndX(cellEndX);
        sequenceTest.setBeginO(cellBeginO);
        sequenceTest.setEndO(cellEndO);

        sequenceTest.setLengthO(5);
        sequenceTest.setLengthX(0);
        assertEquals(sequenceTest, game.longestSequence(-1, 1));
    }

    @Test
    void field() {
        CrossZero newGame = new CrossZero(5);

        assertTrue(Arrays.deepEquals(newGame.getGameField(), game.getGameField()));
    }

    @Test
    void getStartPosition() {
        for (int i = 1; i <= 3; i++) {
            game.setO(0, i);
        }
        game.longestSequence(1, 0);

        cellBeginO.setCellColumn(1);
        cellBeginO.setCellRow(0);

        assertEquals(cellBeginO, game.getCellBeginO());
        for (int i = 1; i <= 3; i++) {
            game.clear(0, i);
        }

        for (int i = 1; i <= 3; i++) {
            game.setX(0, i);
        }
        game.longestSequence(1, 0);

        cellBeginX.setCellColumn(1);
        cellBeginX.setCellRow(0);

        assertEquals(cellBeginX, game.getCellBeginX());
        for (int i = 1; i <= 3; i++) {
            game.clear(0, i);
        }
    }

    @Test
    void setX() {
        game.setX(0, 0);
        game.setX(2, 2);
        game.setX(0, 2);

        CrossZero.Symbols[][] testArray = new CrossZero.Symbols[game.getSize()][game.getSize()];

        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                testArray[i][j] = CrossZero.Symbols.EMPTY;
            }
        }
        testArray[0][0] = CrossZero.Symbols.CROSS;
        testArray[2][2] = CrossZero.Symbols.CROSS;
        testArray[0][2] = CrossZero.Symbols.CROSS;

        assertArrayEquals(testArray, game.getGameField());
    }

    @Test
    void setO() {
        game.setO(0, 3);
        game.setO(2, 1);
        game.setO(3, 3);

        CrossZero.Symbols[][] testArray = new CrossZero.Symbols[game.getSize()][game.getSize()];

        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                testArray[i][j] = CrossZero.Symbols.EMPTY;
            }
        }
        testArray[2][1] = CrossZero.Symbols.ZERO;
        testArray[3][3] = CrossZero.Symbols.ZERO;
        testArray[0][3] = CrossZero.Symbols.ZERO;

        assertArrayEquals(testArray, game.getGameField());
    }

    @Test
    void clear() {
        game.setO(0, 3);
        game.setO(2, 1);
        game.setO(3, 3);

        game.clear(0, 3);
        game.clear(3, 3);

        CrossZero.Symbols[][] testArray = new CrossZero.Symbols[game.getSize()][game.getSize()];

        for (int i = 0; i < game.getSize(); i++) {
            for (int j = 0; j < game.getSize(); j++) {
                testArray[i][j] = CrossZero.Symbols.EMPTY;
            }
        }
        testArray[2][1] = CrossZero.Symbols.ZERO;
        assertArrayEquals(testArray, game.getGameField());

    }

    @Test
    void ExceptionTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            game.setX(6, 6);
            game.setO(5, 6);
            game.clear(5, 5);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Cell(-1, -2);
            new CrossZero(-1);
            game.longestSequence(2, 2);
            game.longestSequence(0, 3);
            game.longestSequence(-1, -1);
        });

    }
}