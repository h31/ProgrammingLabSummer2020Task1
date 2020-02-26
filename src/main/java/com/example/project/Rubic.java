package com.example.project;

import java.util.Arrays;

public class Rubic {
    private int size;

    Rubic() {
        this.setSize(3);
    }
    Rubic(int s) {
        this.setSize(s);
    }

    private String[][] frontSide;
    private String[][] backSide;
    private String[][] upSide;
    private String[][] downSide;
    private String[][] rightSide;
    private String[][] leftSide;


    private void setSize(int s) {
        this.size = s;
        frontSide = new String[s][s];
        backSide = new String[s][s];
        upSide = new String[s][s];
        downSide = new String[s][s];
        rightSide = new String[s][s];
        leftSide = new String[s][s];
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                frontSide[i][j] = "R";
                backSide[i][j] = "O";
                upSide[i][j] = "Y";
                downSide[i][j] = "W";
                rightSide[i][j] = "G";
                leftSide[i][j] = "B";
            }
        }
    }

    public enum Sides {
        LEFT, FRONT, RIGHT, BACK, UP, DOWN,
    }
    public enum Layers {
        MID, EQUATOR, STANDING
    }
    public enum Rotates {
         X, Y, Z
    }
    public enum Positions {
        BEFORE, AFTER
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rubic rubic = (Rubic) o;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (sidesEqual(rubic, this)) return true;
                rubic.rotateCW(Rotates.Z);
            }
            rubic.rotateCW(Rotates.X);
        }
        for (int i = 0; i < 3; i++) {
            rubic.rotateCW(Rotates.Y);
            for (int j = 0; j < 4; j++) {
                if (sidesEqual(rubic, this)) return true;
                rubic.rotateCW(Rotates.Z);
            }
        }
        rubic.rotateCW(Rotates.Y);
        return false;
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(frontSide);
        result = 31 * result + Arrays.deepHashCode(backSide);
        result = 31 * result + Arrays.deepHashCode(upSide);
        result = 31 * result + Arrays.deepHashCode(downSide);
        result = 31 * result + Arrays.deepHashCode(rightSide);
        result = 31 * result + Arrays.deepHashCode(leftSide);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String[][][] cube = {leftSide, frontSide, rightSide, downSide, backSide, upSide};

        for (int i = 0; i < size; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < size; j++) {
                    result.append(cube[k][i][j]);
                    if (j != size - 1) result.append(" ");
                }
                if (k != 2) result.append("  ");
            }
            result.append("\n");
        }
        result.append("\n");
        for (int k = 3; k < 6; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 2 * size + 1; j++) {
                    result.append(" ");
                }
                for (int j = 0; j < size; j++) {
                    result.append(cube[k][i][j]);
                    if (j != size - 1) result.append(" ");
                }
                result.append("\n");
            }
            if (k != 5) result.append("\n");
        }

        return result.toString();
    }

    //Запрос нужной грани
    public String[][] getSide(Sides side) {
        switch (side) {
            case FRONT:
                return frontSide;
            case BACK:
                return backSide;
            case UP:
                return upSide;
            case DOWN:
                return downSide;
            case LEFT:
                return leftSide;
            case RIGHT:
                return rightSide;
            default:
                throw new IllegalArgumentException();
        }
    }

    //Поворот кубика
    public void rotateCW(Rotates rotation) {
        String[][] t = frontSide;
        switch (rotation) {
            case X: {
                frontSide = rightSide;
                rightSide = backSide;
                backSide = leftSide;
                leftSide = t;
                rotateSideCW(upSide);
                rotateSideAntiCW(downSide);
                break;
            }
            case Y: {
                frontSide = upSide;
                upSide = backSide;
                backSide = downSide;
                downSide = t;
                rotateSideCW(rightSide);
                rotateSideAntiCW(leftSide);
                break;
            }
            case Z: {
                t = upSide;
                upSide = leftSide;
                leftSide = downSide;
                downSide = rightSide;
                rightSide = t;
                rotateSideCW(frontSide);
                rotateSideAntiCW(backSide);
                break;
            }

        }
    }

    public void rotateAntiCW(Rotates rotation) {
        String[][] t = frontSide;
        switch (rotation) {
            case X: {
                frontSide = leftSide;
                leftSide = backSide;
                backSide = rightSide;
                rightSide = t;
                rotateSideAntiCW(upSide);
                rotateSideCW(downSide);
                break;
            }
            case Y: {
                frontSide = downSide;
                downSide = backSide;
                backSide = upSide;
                upSide = t;
                rotateSideAntiCW(rightSide);
                rotateSideCW(leftSide);
                break;
            }
            case Z: {
                t = upSide;
                upSide = rightSide;
                rightSide = downSide;
                downSide = leftSide;
                leftSide = t;
                rotateSideAntiCW(frontSide);
                rotateSideCW(backSide);
                break;
            }
        }
    }

    //Поворот грани:

    //***лицевой:
    public void turnFaceSideCW(Sides side) {//по часовой
        switch (side) {
            case FRONT: {
                rotateLayerCW(Layers.STANDING, 1);
                rotateSideCW(frontSide);
                break;
            }
            case BACK: {
                rotateLayerCW(Layers.STANDING, size);
                rotateSideAntiCW(backSide);
                break;
            }
            case RIGHT: {
                rotateLayerCW(Layers.MID, 1);
                rotateSideCW(rightSide);
                break;
            }
            case LEFT: {
                rotateLayerCW(Layers.MID, size);
                rotateSideAntiCW(leftSide);
                break;
            }
            case UP: {
                rotateLayerCW(Layers.EQUATOR, 1);
                rotateSideCW(upSide);
                break;
            }
            case DOWN: {
                rotateLayerCW(Layers.EQUATOR, size);
                rotateSideAntiCW(downSide);
                break;
            }

        }
    }

    public void turnFaceSideCW(Sides side, int amount) {//с указанием количества
        if (amount >= size || amount < 1) throw new IllegalArgumentException();

        turnFaceSideCW(side);
        if (amount > 1) switch (side) {
            case FRONT: {
                turnInnerSideCW(Layers.STANDING, 1, amount - 1, Positions.AFTER);
                break;
            }
            case BACK: {
                 turnInnerSideCW(Layers.STANDING, size - 1, amount - 1, Positions.BEFORE);
                 break;
            }
            case UP: {
                turnInnerSideCW(Layers.EQUATOR, 1, amount - 1, Positions.AFTER);
                break;
            }
            case DOWN: {
                turnInnerSideCW(Layers.EQUATOR, size - 1, amount - 1, Positions.BEFORE);
                break;
            }
            case RIGHT: {
                turnInnerSideCW(Layers.MID, 1, amount - 1, Positions.AFTER);
                break;
            }
            case LEFT: {
                turnInnerSideCW(Layers.MID, size - 1, amount - 1, Positions.BEFORE);
            }
        }
    }


    public void turnFaceSideAntiCW(Sides side) {//против часовой
        switch (side) {
            case FRONT: {
                rotateLayerAntiCW(Layers.STANDING, 1);
                rotateSideAntiCW(frontSide);
                break;
            }
            case BACK: {
                rotateLayerCW(Layers.STANDING, size);
                rotateSideAntiCW(backSide);
                break;
            }
            case RIGHT: {
                rotateLayerAntiCW(Layers.MID, size);
                rotateSideAntiCW(rightSide);
                break;
            }
            case LEFT: {
                rotateLayerCW(Layers.MID, 1);
                rotateSideAntiCW(leftSide);
            }
            case UP: {
                rotateLayerAntiCW(Layers.EQUATOR, 1);
                rotateSideAntiCW(upSide);
                break;
            }
            case DOWN: {
                rotateLayerCW(Layers.EQUATOR, size);
                rotateSideAntiCW(downSide);
            }
        }
    }



    //***внутренней:
    public void turnInnerSideCW(Layers layer, int order) {
        if (order < 1 || order > size - 2) throw new IllegalArgumentException();
        rotateLayerCW(layer, order + 1);
    }

    public void turnInnerSideCW(Layers layer, int order, int amount, Positions position) {
        if (amount < 1) throw new IllegalArgumentException();
        if (amount == 1) turnInnerSideCW(layer, order);
        if (amount > 1) switch (position) {
            case AFTER: {
                if (amount > size - order - 1) throw new IllegalArgumentException();
                for (int i = 0; i < amount; i++) {
                    turnInnerSideCW(layer, order + i);
                }
                break;
            }
            case BEFORE: {
                if (amount > order - 1) throw new IllegalArgumentException();
                for (int i = 0; i < amount; i++) {
                    turnInnerSideCW(layer, order - i);
                }
            }
        }
    }

    public void turnInnerSideAntiCW(Layers layer, int order) {
        if (order < 1 || order > size - 1) throw new IllegalArgumentException();
        rotateLayerAntiCW(layer, order + 1);
    }

    public void turnInnerSideAntiCW(Layers layer, int order, int amount, Positions position) {
        switch (position) {
            case AFTER: {
                if (amount > size - order) throw new IllegalArgumentException();
                for (int i = 0; i < amount; i++) {
                    turnInnerSideAntiCW(layer, order + i);
                }
                break;
            }
            case BEFORE: {
                if (amount > order)
                    for (int i = 0; i < amount; i++) {
                        turnInnerSideAntiCW(layer, order - i);
                    }
            }
        }
    }
    //Вспомогательные методы:

    private void rotateSideCW(String[][] side) {
        for (int i = 0; i < size / 2; i++) {
            int s = size - 1 - i;
            for (int j = i; j < size - i; j++) {
                String t = side[i][j];
                side[i][j] = side[s - j][i];
                side[s - j][i] = side[s - i][s - j];
                side[s - i][s - j] = side[j][s - i];
                side[j][s - i] = t;
            }
        }
    }

    private void rotateSideAntiCW(String[][] side) {
        for (int i = 0; i < size / 2; i++) {
            int s = size - 1 - i;
            for (int j = i; j < size - i; j++) {
                String t = side[i][j];
                side[i][j] = side[j][s - i];
                side[j][s - i] = side[s - i][s - j];
                side[s - i][s - j] = side[s - j][i];
                side[s - j][i] = t;
            }
        }
    }

    private void rotateLayerCW(Layers layer, int order) {
        if (order < 1 || order > size) throw new IllegalArgumentException();
        switch (layer) {
            case STANDING: {
                for (int i = 0; i < size; i++) {
                    String t = upSide[size - order][i];
                    upSide[size - order][i] = leftSide[size - 1 - i][order - 1];
                    leftSide[size - 1 - i][order - 1] = downSide[order - 1][size - 1 - i];
                    downSide[order - 1][size - 1 - i] = rightSide[i][order - 1];
                    rightSide[i][order - 1] = t;
                }
                break;
            }
            case EQUATOR: {
                for (int i = 0; i < size; i++) {
                    String[] t = frontSide[order - 1];
                    frontSide[order - 1] = rightSide[order - 1];
                    rightSide[order - 1] = backSide[size - order];
                    backSide[size - order] = leftSide[order - 1];
                    leftSide[order - 1] = t;
                }
                break;
            }
            case MID: {
                for (int i = 0; i < size; i++) {
                    String t = frontSide[size - 1 - i][size - order];
                    frontSide[size - 1 - i][size - order] = downSide[size - 1 - i][size - order];
                    downSide[size - 1 - i][size - order] = backSide[size - 1 - i][size - order];
                    backSide[size - 1 - i][size - order] = upSide[size - 1 - i][size - order];
                    upSide[size - 1 - i][size - order] = t;
                }
            }
        }
    }

    private void rotateLayerAntiCW(Layers layer, int order) {
        if (order < 1 || order > size) throw new IllegalArgumentException();
        switch (layer) {
            case STANDING: {
                for (int i = 0; i < 3; i++) {
                    String t = upSide[size - order][i];
                    upSide[size - order][i] = rightSide[i][order - 1];
                    rightSide[i][order - 1] = downSide[order - 1][size - 1 - i];
                    downSide[order - 1][size - 1 - i] = leftSide[size - 1 - i][size - order];
                    leftSide[size - 1 - i][size - order] = t;
                }
                break;
            }
            case EQUATOR: {
                for (int i = 0; i < size; i++) {
                    String[] t = frontSide[order - 1];
                    frontSide[order - 1] = leftSide[order - 1];
                    leftSide[order - 1] = backSide[size - order];
                    backSide[size - order] = rightSide[order - 1];
                    rightSide[order - 1] = t;
                }
                break;
            }
            case MID: {
                for (int i = 0; i < size; i++) {
                    String t = frontSide[size - 1 - i][size - order];
                    frontSide[size - 1 - i][size - order] = upSide[size - 1 - i][size - order];
                    upSide[size - 1 - i][size - order] = backSide[size - 1 - i][size - order];
                    backSide[size - 1 - i][size - order] = downSide[size - 1 - i][size - order];
                    downSide[size - 1 - i][size - order] = t;
                }
            }
        }
    }

    private boolean sidesEqual(Rubic current, Rubic other) {
        return Arrays.deepEquals(current.frontSide, other.frontSide) &&
                Arrays.deepEquals(current.backSide, other.backSide) &&
                Arrays.deepEquals(current.leftSide, other.leftSide) &&
                Arrays.deepEquals(current.rightSide, other.rightSide) &&
                Arrays.deepEquals(current.upSide, other.upSide) &&
                Arrays.deepEquals(current.downSide, other.downSide);
    }
}
