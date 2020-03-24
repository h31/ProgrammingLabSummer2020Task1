package com.example.project;

import java.util.Objects;

public class Sequence {

    private Cell beginX;
    private Cell endX;
    private Cell beginO;
    private Cell endO;

    private int lengthO;
    private int lengthX;

    public void setLengthO(int lengthO) {
        this.lengthO = lengthO;
    }

    public void setLengthX(int lengthX) {
        this.lengthX = lengthX;
    }

    public Sequence(Cell beginX, Cell endX, Cell beginO, Cell endO, int lengthX, int lengthO) {
        this.lengthX = lengthX;
        this.lengthO = lengthO;
        this.beginX = beginX;
        this.endX = endX;
        this.beginO = beginO;
        this.endO = endO;
    }

    public void setEndX(Cell endX) {
        this.endX = endX;
    }

    public void setBeginX(Cell beginX) {
        this.beginX = beginX;
    }

    public void setBeginO(Cell beginO) {
        this.beginO = beginO;
    }

    public void setEndO(Cell endO) {
        this.endO = endO;
    }

    public int getLengthO() {
        return lengthO;
    }

    public int getLengthX() {
        return lengthX;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sequence sequence = (Sequence) o;
        return lengthO == sequence.lengthO &&
                lengthX == sequence.lengthX &&
                Objects.equals(beginX, sequence.beginX) &&
                Objects.equals(endX, sequence.endX) &&
                Objects.equals(beginO, sequence.beginO) &&
                Objects.equals(endO, sequence.endO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beginX, endX, beginO, endO, lengthO, lengthX);
    }

    @Override
    public String toString() {
        return "beginX=" + beginX +
                ", endX=" + endX +
                ", lengthX=" + lengthX
                + "\n" +
                "beginO=" + beginO +
                ", endO=" + endO +
                ", lengthO=" + lengthO;
    }
}
