package com.example.project;


import java.util.Arrays;
import java.util.Objects;

enum TicTacSymbol {
    CROSS,
    ZERO,
    EMPTY
}

enum DirectionSymbol {
    VERTICAL,
    HORIZONTAL,
    DIAGONAL
}

final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

final class Line {
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

public final class TicTacToe {
    private final int size;
    public char[][] desk; // поле хранится в виде двухмерного массива

    public TicTacToe(int size) { // конструктор
        this.size = size;
        this.desk = new char[size][size];
        for (int i = 0; i < size; i++) // заполнение поля по умолчанию
            for (int j = 0; j < size; j++)
                this.desk[i][j] = '*';
    }

    // методы
    public void addCross(int x, int y) { // добавить крестик
        if ((x < 0) || (x > size - 1) || (y < 0) || (y > size - 1)) throw new IllegalArgumentException();
        desk[x][y] = 'X';
    }

    public void addZero(int x, int y) { // добавить нолик
        if ((x < 0) || (x > size - 1) || (y < 0) || (y > size - 1)) throw new IllegalArgumentException();
        desk[x][y] = 'O';
    }

    public void cleanCell(int x, int y) { // очистить заданную клетку
        desk[x][y] = '*';
    }

    public char[][] getDesk() { // запрос текущего состояния поля
        return this.desk;
    }

    public char getFieldAt(int x, int y) { // получение конкретного элемента поля по координатам
        return this.desk[x][y];
    }

    public Line getWinner(TicTacSymbol symbol, DirectionSymbol direction) {
        char findSymbol = '*';
        int max = 0;
        Line answer = new Line(0, new Point(0, 0), new Point(0, 0));
        Line result = new Line(0, new Point(0, 0), new Point(0, 0));
        switch (symbol) {
            case CROSS:
                findSymbol = 'X';
                break;
            case ZERO:
                findSymbol = 'O';
                break;
            case EMPTY:
                findSymbol = '*';
                break;
        }
        switch (direction) {
            case VERTICAL:
                for (int i = 0; i < size; i++) {
                    result = check(findSymbol, 1, 0, 0, i);
                    if (result.getLength() > max) {
                        max = result.getLength();
                        answer = result;
                    }
                }
                break;
            case HORIZONTAL:
                for (int i = 0; i < size; i++) {
                    result = check(findSymbol, 0, i, 1, 0);
                    if (result.getLength() > max) {
                        max = result.getLength();
                        answer = result;
                    }
                }
                break;
            case DIAGONAL:
                for (int i = 0; i < size; i++) {
                    Line result1 = check(findSymbol, 1, 0, 1, 0);
                    Line result2 = check(findSymbol, -1, size - 1, 1, 0);
                    answer = (result1.getLength() > result2.getLength()) ? result1 : result2;
                }
                break;
        }
        return answer;
    }

    private Line check(char findSymbol, int xCoef, int xShift, int yCoef, int yShift) {
        int i = 0;
        int cnt = 0;
        int max = 0;
        Point startPoint = new Point(-1, -1);
        Point endPoint = new Point(-1, -1);
        Point maxStartPoint = new Point(-1, -1);
        Point maxEndPoint = new Point(-1, -1);
        int previousx = i * xCoef + xShift;
        int previousy = i * yCoef + yShift;
        for (i = 0; i < size; i++) {
            int x = i * xCoef + xShift;
            int y = i * yCoef + yShift;
            char symbol = desk[x][y];
            if (symbol == findSymbol) {
                if (cnt == 0) {
                    startPoint = new Point(x, y);
                }
                cnt++;
                if (i == size - 1) {
                    endPoint = new Point(x, y);
                    if (cnt > max) {
                        max = cnt;
                        maxStartPoint = startPoint;
                        maxEndPoint = endPoint;
                    }
                }
            } else {
                if ((cnt != 0) && (i == size - 1)) {
                    endPoint = new Point(previousx, previousy);
                } else if (cnt != 0) {
                    endPoint = new Point(previousx, previousy);
                }
                if (cnt > max) {
                    max = cnt;
                    maxStartPoint = startPoint;
                    maxEndPoint = endPoint;
                }
                cnt = 0;
            }
            previousx = x;
            previousy = y;
        }
        return new Line(max, maxStartPoint, maxEndPoint);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof TicTacToe) {
            TicTacToe other = (TicTacToe) obj;
            return this.size == other.size &&
                    Arrays.equals(this.desk, other.desk);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(desk);
        return result;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                str += desk[i][j] + " ";
            }
            str += "\n";
        }
        return str;
    }
}