package com.example.project;

import java.util.Objects;

public final class Line {
    private final int length;
    private final Point point1;
    private final Point point2;

    public Line(int length, Point point1, Point point2) {
        this.length = length;
        this.point1 = point1;
        this.point2 = point2;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return length == line.length &&
                point1.equals(line.point1) &&
                point2.equals(line.point2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, point1, point2);
    }

    @Override
    public String toString() {
        return "Line{" +
                "length=" + length +
                ", point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }
}
