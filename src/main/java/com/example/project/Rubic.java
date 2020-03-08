package com.example.project;

import java.util.*;

enum Sides {
    LEFT, FRONT, RIGHT, BACK, UP, DOWN,
}
enum Layers {
    MID, EQUATOR, STANDING
}
enum Rotates {
    X, Y, Z
}
enum Positions {
    BEFORE, AFTER
}

enum Cells {
    R, G, B, Y, O, W
}

public class Rubic {
    private int size;

    public Rubic() { setSize(3); }
    public Rubic(int size) { setSize(size); }

    private Cells[][] frontSide;
    private Cells[][] backSide;
    private Cells[][] upSide;
    private Cells[][] downSide;
    private Cells[][] rightSide;
    private Cells[][] leftSide;


    private void setSize(int size) {
        if (size < 2) throw new IllegalArgumentException();
        this.size = size;
        frontSide = new Cells[size][size];
        backSide = new Cells[size][size];
        upSide = new Cells[size][size];
        downSide = new Cells[size][size];
        rightSide = new Cells[size][size];
        leftSide = new Cells[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                frontSide[i][j] = Cells.R;
                backSide[i][j] = Cells.O;
                upSide[i][j] = Cells.Y;
                downSide[i][j] = Cells.W;
                rightSide[i][j] = Cells.G;
                leftSide[i][j] = Cells.B;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rubic rubic = (Rubic) o;
        return size == rubic.size &&
                Arrays.deepEquals(frontSide, rubic.frontSide) &&
                Arrays.deepEquals(backSide, rubic.backSide) &&
                Arrays.deepEquals(upSide, rubic.upSide) &&
                Arrays.deepEquals(downSide, rubic.downSide) &&
                Arrays.deepEquals(rightSide, rubic.rightSide) &&
                Arrays.deepEquals(leftSide, rubic.leftSide);
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
        Cells[][][] cube = {leftSide, frontSide, rightSide, downSide, backSide, upSide};
        String[] sides = {"left", "face", "right", "back", "up"};

        for (int i = 0; i < 3; i++) {
            result.append(sides[i]);
            if (i != 2)
                for (int j = 0; j < size * 2 - sides[i].length() + 1; j++) result.append(" ");
        }
        result.append("\n");
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
        for (int i = 0; i < size * 2 + 1; i++) result.append(" ");
        result.append("down\n");
        for (int k = 3; k < 6; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < 2 * size; j++) {
                    result.append(" ");
                }
                for (int j = 0; j < size; j++) {
                    result.append(" ");
                    result.append(cube[k][i][j]);
                }
                result.append("\n");
            }
            if (k != 5) {
                for (int i = 0; i < size * 2 + 1; i++) result.append(" ");
                result.append(sides[k]).append("\n");
            }
        }

        return result.toString();
    }

    public void randomize() {
        Random num = new Random();
        ArrayList<Sides> sides = new ArrayList<Sides>(Arrays.asList(Sides.values()));
        for (int i = 0; i < num.nextInt(5) + 5; i++) {
            Collections.shuffle(sides);
            for (Sides side : sides) {
                Random numOfTurnings = new Random();
                Random numOfSides = new Random();
                for (int j = 0; j < numOfTurnings.nextInt(3) + 1; j++) {
                    turnFaceSideCW(side, numOfSides.nextInt(size - 1) + 1);
                }
            }
        }
    }

    /**
     * Запрос нужной грани:
     * на вход принимает название грани, возвращает массив
     */

    public Cells[][] getSide(Sides side) {
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

    /**
     * Поворот кубика:
     * реализует поворот кубика по уквзвнной оси X, Y, Z; CW - по часовой, AntiCW - против
     */
    public void rotateCW(Rotates rotation) {
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
        Cells[][] t = frontSide;
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

    /**
     * Поворот грани:
     * реализует поворот указанной лицевой грани (FaceSide) или внутренней (InnerSide):
     * CW - по часовой, AntiCW - против часовой стрелки;
     * amount - количество граней, которые необходимо повернуть (по умолчанию - 1),
     * amount не может быть меньше 1 и должен быть меньше размера кубика
     */
    public void turnFaceSideCW(Sides side) {
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

    public void turnFaceSideCW(Sides side, int amount) {
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

    public void turnFaceSideAntiCW(Sides side) {
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

    public void turnFaceSideAntiCW(Sides side, int amount) {
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


    public void turnInnerSideCW(Layers layer, int order) {
        if (order < 1 || order > size - 2) throw new IllegalArgumentException();
        rotateLayerCW(layer, order);
    }

    public void turnInnerSideCW(Layers layer, int order, int amount, Positions position) {
        if (amount < 1) throw new IllegalArgumentException();
        if (amount == 1) turnInnerSideCW(layer, order);
        if (amount > 1) switch (position) {
            case AFTER: {
                if (amount > size - order) throw new IllegalArgumentException();
                for (int i = 0; i < amount; i++) {
                    turnInnerSideCW(layer, order + i);
                }
                break;
            }
            case BEFORE: {
                if (amount > order) throw new IllegalArgumentException();
                for (int i = 0; i < amount; i++) {
                    turnInnerSideCW(layer, order - i);
                }
            }
        }
    }

    //**************************************************

    public void turnInnerSideAntiCW(Layers layer, int order) {
        if (order < 1 || order > size - 1) throw new IllegalArgumentException();
        rotateLayerAntiCW(layer, order);
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
                if (amount > order) throw new IllegalArgumentException();
                for (int i = 0; i < amount; i++) {
                    turnInnerSideAntiCW(layer, order - i);
                }
            }
        }
    }


    /**
     * Вспомогательные методы:
     * используются в реализуемых функциях
     */

    private void rotateSideCW(Cells[][] side) {
        for (int i = 0; i < size / 2; i++) {
            int s = size - 1 - i;
            for (int j = i; j < s - i; j++) {
                Cells t = side[i][j];
                side[i][j] = side[s - j][i];
                side[s - j][i] = side[s - i][s - j];
                side[s - i][s - j] = side[j][s - i];
                side[j][s - i] = t;
            }
        }
    }

    private void rotateSideAntiCW(Cells[][] side) {
        for (int i = 0; i < size / 2; i++) {
            int s = size - 1 - i;
            for (int j = i; j < s - i; j++) {
                Cells t = side[i][j];
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
                    Cells t = upSide[s - order][i];
                    upSide[s - order][i] = leftSide[s - i][s - order];
                    leftSide[s - i][s - order] = downSide[order][s - i];
                    downSide[order][s - i] = rightSide[i][order];
                    rightSide[i][order] = t;
                }
                break;
            }
            case EQUATOR: {
                for (int i = 0; i < size; i++) {
                    Cells t = frontSide[order][i];
                    frontSide[order][i] = rightSide[order][i];
                    rightSide[order][i] = backSide[s - order][s - i];
                    backSide[s - order][s - i] = leftSide[order][i];
                    leftSide[order][i] = t;
                }
                break;
            }
            case MID: {
                for (int i = 0; i < size; i++) {
                    Cells t = frontSide[s - i][s - order];
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
                    Cells t = upSide[s - order][i];
                    upSide[s - order][i] = rightSide[i][order];
                    rightSide[i][order] = downSide[order][s - i];
                    downSide[order][s - i] = leftSide[s - i][s - order];
                    leftSide[s - i][s - order] = t;
                }
                break;
            }
            case EQUATOR: {
                for (int i = 0; i < size; i++) {
                    Cells t = frontSide[order][i];
                    frontSide[order][i] = leftSide[order][i];
                    leftSide[order][i] = backSide[s - order][s - i];
                    backSide[s - order][s - i] = rightSide[order][i];
                    rightSide[order][i] = t;
                }
                break;
            }
            case MID: {
                for (int i = 0; i < size; i++) {
                    Cells t = frontSide[s - i][s - order];
                    frontSide[s - i][s - order] = upSide[s - i][s - order];
                    upSide[s - i][s - order] = backSide[s - i][s - order];
                    backSide[s - i][s - order] = downSide[s - i][s - order];
                    downSide[s - i][s - order] = t;
                }
            }
        }
    }
}
