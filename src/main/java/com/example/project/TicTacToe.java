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

public final class TicTacToe {
    private final int size;
    /*
    поле хранится в виде двухмерного массива
     */
    public TicTacSymbol[][] desk;

    /*
    конструктор
     */
    public TicTacToe(int size) {
        if (size <= 0) throw new IllegalArgumentException();
        this.size = size;
        this.desk = new TicTacSymbol[size][size];
        /*
        @author Никита Пудов
        заполнение поля по умолчанию
         */
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                this.desk[i][j] = TicTacSymbol.EMPTY;
    }

    // методы
    /*
    добавить крестик
     */
    public void setTicTacSymbol(int x, int y, TicTacSymbol ticTacSymbol) {
        if ((x < 0) || (x > size - 1) || (y < 0) || (y > size - 1)) throw new IllegalArgumentException();
        desk[x][y] = ticTacSymbol;
    }

    /*
    Очистить заданную клетку
     */
    public void cleanCell(int x, int y) {
        if ((x < 0) || (x > size - 1) || (y < 0) || (y > size - 1)) throw new IllegalArgumentException();
        desk[x][y] = TicTacSymbol.EMPTY;
    }

    /*
    запрос текущего состояния поля
     */
    public TicTacSymbol[][] getDesk() {
        return this.desk;
    }

    /*
    получение конкретного элемента поля по координатам
     */
    public TicTacSymbol getFieldAt(int x, int y) {
        if ((x < 0) || (x > size - 1) || (y < 0) || (y > size - 1)) throw new IllegalArgumentException();
        return this.desk[x][y];
    }

    public Line getWinner(TicTacSymbol symbol, DirectionSymbol direction) {
        TicTacSymbol findSymbol = symbol;
        int max = 0;
        Line answer = new Line(0, new Point(0, 0), new Point(0, 0));
        Line result;
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

    private Line check(TicTacSymbol findSymbol, int xCoef, int xShift, int yCoef, int yShift) {
        int i = 0;
        int cnt = 0;
        int max = 0;
        Point startPoint = new Point(-1, -1);
        Point endPoint = new Point(-1, -1);
        Point maxStartPoint = new Point(-1, -1);
        Point maxEndPoint = new Point(-1, -1);
        int previousX = i * xCoef + xShift;
        int previousY = i * yCoef + yShift;
        for (i = 0; i < size; i++) {
            int x = i * xCoef + xShift;
            int y = i * yCoef + yShift;
            TicTacSymbol symbol = desk[x][y];
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
                    endPoint = new Point(previousX, previousY);
                } else if (cnt != 0) {
                    endPoint = new Point(previousX, previousY);
                }
                if (cnt > max) {
                    max = cnt;
                    maxStartPoint = startPoint;
                    maxEndPoint = endPoint;
                }
                cnt = 0;
            }
            previousX = x;
            previousY = y;
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
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                str.append(desk[i][j]).append(" ");
            }
            str.append("\n");
        }
        return str.toString();
    }
}