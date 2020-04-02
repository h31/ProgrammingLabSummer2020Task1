package com.example.project;

import java.util.Objects;

public class Cell {
    private int cellRow;
    private int cellColumn;

    public Cell(int cellRow, int cellColumn) {
        this.cellRow = cellRow;
        this.cellColumn = cellColumn;

        if (cellColumn < 0 || cellRow < 0)
            throw new IllegalArgumentException();
    }

    public void setCellColumn(int cellColumn) {
        this.cellColumn = cellColumn;
    }

    public void setCellRow(int cellRow) {
        this.cellRow = cellRow;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "cellRow=" + cellRow +
                ", cellColumn=" + cellColumn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com.example.project.Cell cell = (com.example.project.Cell) o;
        return cellRow == cell.cellRow &&
                cellColumn == cell.cellColumn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellRow, cellColumn);
    }
}

