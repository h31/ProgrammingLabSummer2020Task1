package com.example.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CrossZero {
    private List<Integer> listLengthsO = new ArrayList<>();//листы для нескольких длин,чтобы вернуть максимальную
    private List<Integer> listLengthsX = new ArrayList<>();
    private final int size;

    private int counterX = 1;//поля для метода поиска последовательности
    private int counterO = 1;
    private int timeColumnX = 0;
    private int timeRowX = 0;
    private int timeColumnO = 0;
    private int timeRowO = 0;
    private int iteratorForDiagonal = 1;
    private int row = 1;
    private int column = 1;
    private int begin;

    public enum Symbols {
        CROSS, ZERO, EMPTY
    }

    private final Symbols[][] gameField;

    Cell cellBeginX = new Cell(0, 0);
    Cell cellEndX = new Cell(0, 0);
    Cell cellBeginO = new Cell(0, 0);
    Cell cellEndO = new Cell(0, 0);

    Sequence sequence = new Sequence(cellBeginX, cellEndX, cellBeginO, cellEndO, 0, 0);

    /**
     * геттер поля
     */
    public Symbols[][] getGameField() {
        return gameField;
    }

    /**
     * геттер начальных координат
     */
    public Cell getCellBeginO() {
        return cellBeginO;
    }

    public Cell getCellBeginX() {
        return cellBeginX;
    }

    /**
     * геттер начальных координат
     */
    public int getSize() {
        return size;
    }

    public CrossZero(int size) {
        this.size = size;
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        gameField = new Symbols[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameField[i][j] = Symbols.EMPTY;
            }
        }
    }

    /**
     * Метод добавления крестика в клетку
     */
    public void setX(int row, int column) {
        gameField[row][column] = Symbols.CROSS;
    }

    /**
     * Метод добавления нолика в клетку
     */
    public void setO(int row, int column) {
        gameField[row][column] = Symbols.ZERO;
    }

    /**
     * Метод очищения клетки
     */
    public void clear(int row, int column) {
        gameField[row][column] = Symbols.EMPTY;
    }

    /**
     * Возвращение начальных значений
     */
    public void reSetterForBegin() {
        counterX = 1;
        counterO = 1;
        timeColumnO = 0;
        timeColumnX = 0;
        timeRowO = 0;
        timeRowX = 0;
        listLengthsX.clear();
        listLengthsO.clear();
    }

    public void setValuesX() {
        cellEndX.setCellRow(timeRowX);
        cellEndX.setCellColumn(timeColumnX);
        sequence.setEndX(cellEndX);
        sequence.setBeginX(cellBeginX);
    }

    public void setValueO() {
        cellEndO.setCellRow(timeRowO);
        cellEndO.setCellColumn(timeColumnO);
        sequence.setEndO(cellEndO);
        sequence.setBeginO(cellBeginO);
    }

    public void setZeroO() {
        sequence.setLengthO(0);
        cellEndO.setCellRow(0);
        cellEndO.setCellColumn(0);
        cellBeginO.setCellRow(0);
        cellBeginO.setCellColumn(0);
    }

    public void setZeroX() {
        sequence.setLengthX(0);
        cellEndX.setCellRow(0);
        cellEndX.setCellColumn(0);
        cellBeginX.setCellRow(0);
        cellBeginX.setCellColumn(0);
    }

    /**
     * Проход по горизонтали
     */
    public void longestHorizontal() {
        if (gameField[row - 1][column - 1] == Symbols.CROSS && gameField[row - 1][column] == Symbols.CROSS) {
            timeRowX = row - 1;
            timeColumnX = column;
            counterX++;
            listLengthsX.add(counterX);
        } else
            counterX = 1;
        if (gameField[row - 1][column - 1] == Symbols.ZERO && gameField[row - 1][column] == Symbols.ZERO) {
            counterO++;
            timeRowO = row - 1;
            timeColumnO = column;
            listLengthsO.add(counterO);
        } else
            counterO = 1;
        column++; //переход на другую строку после прохода по всем столбцам

        if (column == size) {
            if (!listLengthsX.isEmpty()) {
                sequence.setLengthX(Collections.max(listLengthsX));
                begin = (timeColumnX - (sequence.getLengthX() - 1));
                if (Math.abs(timeColumnX - begin + 1) == sequence.getLengthX()) {
                    setValuesX();
                    cellBeginX.setCellRow(timeRowX);
                    cellBeginX.setCellColumn(timeColumnX - (sequence.getLengthX() - 1));
                }
            }
            if (!listLengthsO.isEmpty()) {
                sequence.setLengthO(Collections.max(listLengthsO));
                begin = (timeColumnO - (sequence.getLengthO() - 1));
                if (Math.abs(timeColumnO - begin + 1) == sequence.getLengthO()) {
                    setValueO();
                    cellBeginO.setCellRow(timeRowO);
                    cellBeginO.setCellColumn(timeColumnO - (sequence.getLengthO() - 1));
                }
            }
            column = 1;
            row++;
            reSetterForBegin();
        }
    }

    /**
     * Проход по вертикали
     */
    public void longestSequenceVertical() {
        if (gameField[row - 1][column - 1] == Symbols.CROSS && gameField[row][column - 1] == Symbols.CROSS) {
            counterX++;
            listLengthsX.add(counterX);
            timeRowX = row;
            timeColumnX = column - 1;
        } else {
            counterX = 1;
        }
        if (gameField[row - 1][column - 1] == Symbols.ZERO && gameField[row][column - 1] == Symbols.ZERO) {
            counterO++;
            listLengthsO.add(counterO);
            timeColumnO = column - 1;
            timeRowO = row;
        } else {
            counterO = 1;
        }
        row++;//переход на другой столбец после прохода по всем строкам

        if (row == size) {
            if (!listLengthsX.isEmpty()) {
                sequence.setLengthX(Collections.max(listLengthsX));
                begin = (timeRowX - (sequence.getLengthX() - 1));
                if (Math.abs(timeRowX - begin + 1) == sequence.getLengthX()) {
                    setValuesX();
                    cellBeginX.setCellRow(timeRowX - (sequence.getLengthX() - 1));
                    cellBeginX.setCellColumn(timeColumnX);
                }
            }
            if (!listLengthsO.isEmpty()) {
                sequence.setLengthO(Collections.max(listLengthsO));
                begin = (timeRowO - (sequence.getLengthO() - 1));
                if (Math.abs(timeRowO - begin + 1) == sequence.getLengthO()) {
                    setValueO();
                    cellBeginO.setCellRow(timeRowO - (sequence.getLengthO() - 1));
                    cellBeginO.setCellColumn(timeColumnO);
                }
            }
            column++;
            row = 1;
            reSetterForBegin();
        }
    }

    /**
     * Проход по основной и побочной диагонали
     */
    public void longestDiagonal(int x, int y) {
        if (x == 1 && y == 1) {
            if (gameField[row][column] == Symbols.CROSS && gameField[row - 1][column - 1] == Symbols.CROSS) {
                timeRowX = row;
                timeColumnX = column;
                counterX++;
            } else if (gameField[row][column] == Symbols.ZERO && gameField[row - 1][column - 1] == Symbols.ZERO) {
                counterO++;
                timeRowO = row;
                timeColumnO = column;
            }
            column++;
            row++;

            if (column == size && row == size) {
                if (counterO == 1) {
                    setZeroO();
                } else {
                    sequence.setLengthO(counterO);
                    cellEndO.setCellRow(timeRowO);
                    cellEndO.setCellColumn(timeColumnO);
                    cellBeginO.setCellRow(timeRowO - (counterO - 1));
                    cellBeginO.setCellColumn(timeColumnO - (counterO - 1));
                }

                if (counterX == 1) {
                    setZeroX();

                } else {
                    sequence.setLengthX(counterX);
                    cellEndX.setCellRow(timeRowX);
                    cellEndX.setCellColumn(timeColumnX);
                    cellBeginX.setCellRow(timeRowX - (counterX - 1));
                    cellBeginX.setCellColumn(timeColumnX - (counterX - 1));
                }
            }
        } else if (x == -1 && y == 1) {
            column = size - iteratorForDiagonal;
            if (gameField[row - 1][column] == Symbols.CROSS && gameField[row][column - 1] == Symbols.CROSS) {
                counterX++;
                timeColumnX = column;
                timeRowX = row;
            } else if (gameField[row - 1][column] == Symbols.ZERO && gameField[row][column - 1] == Symbols.ZERO) {
                counterO++;
                timeColumnO = column;
                timeRowO = row;
            }
            column--;
            row++;
            iteratorForDiagonal++;
            if (column == 0) {
                if (counterX == 1) {
                    setZeroX();
                } else {
                    sequence.setLengthX(counterX);
                    cellEndX.setCellRow(timeRowX);
                    cellEndX.setCellColumn(timeColumnX - 1);
                    cellBeginX.setCellRow(timeRowX - (counterX - 1));
                    cellBeginX.setCellColumn((timeColumnX - 1) + (counterX - 1));
                }
                if (counterO == 1) {
                    setZeroO();
                } else {
                    sequence.setLengthO(counterO);
                    cellEndO.setCellRow(timeRowO);
                    cellEndO.setCellColumn(timeColumnO - 1);
                    cellBeginO.setCellRow(timeRowO - (counterO - 1));
                    cellBeginO.setCellColumn((timeColumnO - 1) + (counterO - 1));
                }
            }
        }
        sequence.setEndX(cellEndX);
        sequence.setBeginX(cellBeginX);
        sequence.setEndO(cellEndO);
        sequence.setBeginO(cellBeginO);
    }

    /**
     * Универсальный метод поиска максимальной длины и координат
     */
    public Sequence longestSequence(int x, int y) {
        if (x == 0 && y == 1) {
            while (column < size)
                longestSequenceVertical();
            column = 1;
        } else if (x == 1 && y == 0) {
            while (row < size)
                longestHorizontal();
            row = 1;
        } else if (x == 1 && y == 1) {
            while (row < size && column < size)
                longestDiagonal(1, 1);
            row = 1;
            column = 1;
            reSetterForBegin();
        } else if (x == -1 && y == 1) {
            while (column != 0)
                longestDiagonal(-1, 1);
        } else
            throw new IllegalArgumentException();

        return sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrossZero crossZero = (CrossZero) o;
        return size == crossZero.size &&
                Arrays.equals(gameField, crossZero.gameField) &&
                Objects.equals(cellBeginX, crossZero.cellBeginX) &&
                Objects.equals(cellEndX, crossZero.cellEndX) &&
                Objects.equals(cellBeginO, crossZero.cellBeginO) &&
                Objects.equals(cellEndO, crossZero.cellEndO) &&
                Objects.equals(sequence, crossZero.sequence);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, cellBeginX, cellEndX, cellBeginO, cellEndO, sequence);
        result = 31 * result + Arrays.hashCode(gameField);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                switch (gameField[i][j]) {
                    case CROSS:
                        str.append("|X|");
                        break;
                    case ZERO:
                        str.append("|O|");
                        break;
                    case EMPTY:
                        str.append("|_|");
                        break;
                }
            }
            str.append("\n");
        }
        return str.toString();
    }
}
