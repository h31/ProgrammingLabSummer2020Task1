package com.example.project;

import java.util.Arrays;

public class Rubic {
    public static final String red = "\u001b[31mR\u001b[0m";
    public static final String blue = "\u001b[34mB\u001b[0m";
    public static final String yellow = "\u001b[33mY\u001b[0m";
    public static final String green = "\u001b[32mG\u001b[0m";
    public static final String white = "\u001b[37mW\u001b[0m";
    public static final String orange = "\u001b[38;5;202mO\u001b[0m";


    private String[][] frontSide = {{red, red, red}, {red, red, red}, {red, red, red}};
    private String[][] backSide = {{orange, orange, orange}, {orange, orange, orange}, {orange, orange, orange}};
    private String[][] upSide = {{yellow, yellow, yellow}, {yellow, yellow, yellow}, {yellow, yellow, yellow}};
    private String[][] downSide = {{white, white, white}, {white, white, white}, {white, white, white}};
    private String[][] rightSide = {{green, green, green}, {green, green, green}, {green, green, green}};
    private String[][] leftSide = {{blue, blue, blue}, {blue, blue, blue}, {blue, blue, blue}};

    public enum CubeSides {
        LEFT, FRONT, RIGHT, BACK, UP, DOWN,
        FRONT_, BACK_, LEFT_, RIGHT_, UP_, DOWN_,
        X, Y, Z, X_, Y_, Z_;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rubic rubic = (Rubic) o;
        while (rubic.frontSide[1][1].equals(frontSide[1][1])) {
            rubic.rotate(CubeSides.X);
        }
        while (rubic.upSide[1][1].equals(upSide[1][1])) {
            rubic.rotate(CubeSides.Z);
        }
        return Arrays.equals(frontSide, rubic.frontSide) &&
                Arrays.equals(backSide, rubic.backSide) &&
                Arrays.equals(upSide, rubic.upSide) &&
                Arrays.equals(downSide, rubic.downSide) &&
                Arrays.equals(rightSide, rubic.rightSide) &&
                Arrays.equals(leftSide, rubic.leftSide);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(frontSide);
        result = 31 * result + Arrays.hashCode(backSide);
        result = 31 * result + Arrays.hashCode(upSide);
        result = 31 * result + Arrays.hashCode(downSide);
        result = 31 * result + Arrays.hashCode(rightSide);
        result = 31 * result + Arrays.hashCode(leftSide);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String[][][] cube = {leftSide, frontSide, rightSide, downSide, backSide, upSide};

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 3; j++) {
                    result.append(cube[k][i][j]).append(" ");
                }
            }
            result.append("\n");
        }
        for (int k = 3; k < 6; k++) {
            for (int i = 0; i < 3; i++) {
                result.append("      ");
                for (int j = 0; j < 3; j++) {
                    result.append(cube[k][i][j]).append(" ");
                }
                result.append("\n");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Rubic cube = new Rubic();
        System.out.println(cube.toString());
    }


    //Запрос нужной грани
    public String[][] getSide(CubeSides side) {
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
    public void rotate(CubeSides rotation) {
        String[][] t = frontSide;
        switch (rotation) {
            case X: {
                frontSide = rightSide;
                rightSide = backSide;
                backSide = leftSide;
                leftSide = t;
                rotateSide(CubeSides.X, upSide);
                rotateSide(CubeSides.X_, downSide);
                break;
            }
            case X_: {
                frontSide = leftSide;
                leftSide = backSide;
                backSide = rightSide;
                rightSide = t;
                rotateSide(CubeSides.X_, upSide);
                rotateSide(CubeSides.X, downSide);
                break;
            }
            case Y: {
                frontSide = upSide;
                upSide = backSide;
                backSide = downSide;
                downSide = t;
                rotateSide(CubeSides.X, rightSide);
                rotateSide(CubeSides.X_, leftSide);
                break;
            }
            case Y_: {
                frontSide = downSide;
                downSide = backSide;
                backSide = upSide;
                upSide = t;
                rotateSide(CubeSides.X_, rightSide);
                rotateSide(CubeSides.X, leftSide);
                break;
            }
            case Z: {
                t = upSide;
                upSide = leftSide;
                leftSide = downSide;
                downSide = rightSide;
                rightSide = t;
                rotateSide(CubeSides.X, frontSide);
                rotateSide(CubeSides.X_, backSide);
                break;
            }
            case Z_: {
                t = upSide;
                upSide = rightSide;
                rightSide = downSide;
                downSide = leftSide;
                leftSide = t;
                rotateSide(CubeSides.X_, frontSide);
                rotateSide(CubeSides.X, backSide);
                break;
            }
            default: throw new IllegalArgumentException();
        }
    }

    /*public void turn(CubeSides layer) {

    }*/


    //Вспомогательные методы:


    private void rotateSide(CubeSides rotation, String[][] side) {
        String t = side[0][0];
        switch (rotation) {
            case X: {
                side[0][0] = side[2][0];
                side[2][0] = side[2][2];
                side[2][2] = side[0][2];
                side[0][2] = t;
                t = side[0][1];
                side[0][1] = side[1][0];
                side[1][0] = side[2][1];
                side[2][1] = side[1][2];
                side[1][2] = t;
                break;
            }
            case X_: {
                side[0][0] = side[0][2];
                side[0][2] = side[2][2];
                side[2][2] = side[2][0];
                side[2][0] = t;
                t = side[0][1];
                side[0][1] = side[1][2];
                side[1][2] = side[2][1];
                side[2][1] = side[1][0];
                side[1][0] = t;
            }
        }
    }
}
