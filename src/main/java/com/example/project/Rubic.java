package com.example.project;

import java.util.Arrays;

public class Rubic {
    private int size;

    Rubic() { this.setSize(3); }
    Rubic(int size) { this.setSize(size); }

    private String[][] frontSide;
    private String[][] backSide;
    private String[][] upSide;
    private String[][] downSide;
    private String[][] rightSide;
    private String[][] leftSide;


    private void setSize(int size) {
        if (size < 2) throw new IllegalArgumentException();
        this.size = size;
        frontSide = new String[size][size];
        backSide = new String[size][size];
        upSide = new String[size][size];
        downSide = new String[size][size];
        rightSide = new String[size][size];
        leftSide = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
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
                turnFaceSideCW(Sides.UP, size - 1);
                turnFaceSideCW(Sides.DOWN);
                break;
            }
            case Y: {
                turnFaceSideCW(Sides.RIGHT, size - 1);
                turnFaceSideCW(Sides.LEFT);
                break;
            }
            case Z: {
                turnFaceSideCW(Sides.FRONT, size - 1);
                turnFaceSideCW(Sides.BACK);
                break;
            }

        }
    }

    public void rotateAntiCW(Rotates rotation) {
        String[][] t = frontSide;
        switch (rotation) {
            case X: {
                turnFaceSideAntiCW(Sides.UP, size - 1);
                turnFaceSideAntiCW(Sides.DOWN);
                break;
            }
            case Y: {
                turnFaceSideAntiCW(Sides.RIGHT, size - 1);
                turnFaceSideAntiCW(Sides.LEFT);
                break;
            }
            case Z: {
                turnFaceSideAntiCW(Sides.FRONT, size - 1);
                turnFaceSideAntiCW(Sides.BACK);
                break;
            }
        }
    }

    //Поворот грани:

    //***лицевой:
    public void turnFaceSideCW(Sides side) {//по часовой
        switch (side) {
            case FRONT: {
                rotateLayerCW(Layers.STANDING, 0);
                rotateSideCW(frontSide);
                break;
            }
            case BACK: {
                rotateLayerCW(Layers.STANDING, size -1);
                rotateSideAntiCW(backSide);
                break;
            }
            case RIGHT: {
                rotateLayerCW(Layers.MID, 0);
                rotateSideCW(rightSide);
                break;
            }
            case LEFT: {
                rotateLayerCW(Layers.MID, size - 1);
                rotateSideAntiCW(leftSide);
                break;
            }
            case UP: {
                rotateLayerCW(Layers.EQUATOR, 0);
                rotateSideCW(upSide);
                break;
            }
            case DOWN: {
                rotateLayerCW(Layers.EQUATOR, size - 1);
                rotateSideAntiCW(downSide);
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
                 turnInnerSideCW(Layers.STANDING, size - 2, amount - 1, Positions.BEFORE);
                 break;
            }
            case UP: {
                turnInnerSideCW(Layers.EQUATOR, 1, amount - 1, Positions.AFTER);
                break;
            }
            case DOWN: {
                turnInnerSideCW(Layers.EQUATOR, size - 2, amount - 1, Positions.BEFORE);
                break;
            }
            case RIGHT: {
                turnInnerSideCW(Layers.MID, 1, amount - 1, Positions.AFTER);
                break;
            }
            case LEFT: {
                turnInnerSideCW(Layers.MID, size - 2, amount - 1, Positions.BEFORE);
            }
        }
    }

    //***************************************************

    public void turnFaceSideAntiCW(Sides side) {//против часовой
        switch (side) {
            case FRONT: {
                rotateLayerAntiCW(Layers.STANDING, 0);
                rotateSideAntiCW(frontSide);
                break;
            }
            case BACK: {
                rotateLayerAntiCW(Layers.STANDING, size - 1);
                rotateSideCW(backSide);
                break;
            }
            case RIGHT: {
                rotateLayerAntiCW(Layers.MID, 0);
                rotateSideAntiCW(rightSide);
                break;
            }
            case LEFT: {
                rotateLayerAntiCW(Layers.MID, size - 1);
                rotateSideCW(leftSide);
                break;
            }
            case UP: {
                rotateLayerAntiCW(Layers.EQUATOR, 0);
                rotateSideAntiCW(upSide);
                break;
            }
            case DOWN: {
                rotateLayerAntiCW(Layers.EQUATOR, size - 1);
                rotateSideCW(downSide);
            }
        }
    }

    public void turnFaceSideAntiCW(Sides side, int amount) {//с указанием количества
        if (amount >= size || amount < 1) throw new IllegalArgumentException();
        turnFaceSideAntiCW(side);
        if (amount > 1) switch (side) {
            case FRONT: {
                turnInnerSideAntiCW(Layers.STANDING, 1, amount - 1, Positions.AFTER);
                break;
            }
            case BACK: {
                turnInnerSideAntiCW(Layers.STANDING, size - 2, amount - 1, Positions.BEFORE);
                break;
            }
            case UP: {
                turnInnerSideAntiCW(Layers.EQUATOR, 1, amount - 1, Positions.AFTER);
                break;
            }
            case DOWN: {
                turnInnerSideAntiCW(Layers.EQUATOR, size - 2, amount - 1, Positions.BEFORE);
                break;
            }
            case RIGHT: {
                turnInnerSideAntiCW(Layers.MID, 1, amount - 1, Positions.AFTER);
                break;
            }
            case LEFT: {
                turnInnerSideAntiCW(Layers.MID, size - 2, amount - 1, Positions.BEFORE);
            }
        }
    }

    //***внутренней:
    public void turnInnerSideCW(Layers layer, int order) {//по часовай
        if (order < 1 || order > size - 2) throw new IllegalArgumentException();
        rotateLayerCW(layer, order);
    }

    public void turnInnerSideCW(Layers layer, int order, int amount, Positions position) {//с указанием количества
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

    //**************************************************

    public void turnInnerSideAntiCW(Layers layer, int order) {//против часовой
        if (order < 1 || order > size - 1) throw new IllegalArgumentException();
        rotateLayerAntiCW(layer, order);
    }

    public void turnInnerSideAntiCW(Layers layer, int order, int amount, Positions position) {//количество
        switch (position) {
            case AFTER: {
                if (amount > size - order) throw new IllegalArgumentException();
                for (int i = 0; i < amount; i++) {
                    turnInnerSideAntiCW(layer, order + i);
                }
                break;
            }
            case BEFORE: {
                if (amount > order) throw new IllegalArgumentException();
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
            for (int j = i; j < s - i; j++) {
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
            for (int j = i; j < s - i; j++) {
                String t = side[i][j];
                side[i][j] = side[j][s - i];
                side[j][s - i] = side[s - i][s - j];
                side[s - i][s - j] = side[s - j][i];
                side[s - j][i] = t;
            }
        }
    }

    private void rotateLayerCW(Layers layer, int order) {
        if (order < 0 || order > size) throw new IllegalArgumentException();
        int s = size - 1;
        switch (layer) {
            case STANDING: {
                for (int i = 0; i < size; i++) {
                    String t = upSide[s - order][i];
                    upSide[s - order][i] = leftSide[s - i][s - order];
                    leftSide[s - i][s - order] = downSide[order][s - i];
                    downSide[order][s - i] = rightSide[i][order];
                    rightSide[i][order] = t;
                }
                break;
            }
            case EQUATOR: {
                for (int i = 0; i < size; i++) {
                    String t = frontSide[order][i];
                    frontSide[order][i] = rightSide[order][i];
                    rightSide[order][i] = backSide[s - order][s - i];
                    backSide[s - order][s - i] = leftSide[order][i];
                    leftSide[order][i] = t;
                }
                break;
            }
            case MID: {
                for (int i = 0; i < size; i++) {
                    String t = frontSide[s - i][s - order];
                    frontSide[s - i][s - order] = downSide[s - i][s - order];
                    downSide[s - i][s - order] = backSide[s - i][s - order];
                    backSide[s - i][s - order] = upSide[s - i][s - order];
                    upSide[s - i][s - order] = t;
                }
            }
        }
    }

    private void rotateLayerAntiCW(Layers layer, int order) {
        if (order < 0 || order > size) throw new IllegalArgumentException();
        int s = size - 1;
        switch (layer) {
            case STANDING: {
                for (int i = 0; i < size; i++) {
                    String t = upSide[s - order][i];
                    upSide[s - order][i] = rightSide[i][order];
                    rightSide[i][order] = downSide[order][s - i];
                    downSide[order][s - i] = leftSide[s - i][s - order];
                    leftSide[s - i][s - order] = t;
                }
                break;
            }
            case EQUATOR: {
                for (int i = 0; i < size; i++) {
                    String t = frontSide[order][i];
                    frontSide[order][i] = leftSide[order][i];
                    leftSide[order][i] = backSide[s - order][s - i];
                    backSide[s - order][s - i] = rightSide[order][i];
                    rightSide[order][i] = t;
                }
                break;
            }
            case MID: {
                for (int i = 0; i < size; i++) {
                    String t = frontSide[s - i][s - order];
                    frontSide[s - i][s - order] = upSide[s - i][s - order];
                    upSide[s - i][s - order] = backSide[s - i][s - order];
                    backSide[s - i][s - order] = downSide[s - i][s - order];
                    downSide[s - i][s - order] = t;
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
