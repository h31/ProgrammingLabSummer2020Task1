package com.example.project;

public class Rubic {
    private String[][] frontSide = {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}};
    private String[][] backSide = {{"O", "O", "O"}, {"O", "O", "O"}, {"O", "O", "O"}};
    private String[][] upSide = {{"Y", "Y", "Y"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}};
    private String[][] downSide = {{"W", "W", "W"}, {"W", "W", "W"}, {"W", "W", "W"}};
    private String[][] rightSide = {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}};
    private String[][] leftSide = {{"B", "B", "B"}, {"B", "B", "B"}, {"B", "B", "B"}};

    public String[][] getSide(final char side) {
        switch (side) {
            case 'F':
                return frontSide;
            case 'B':
                return backSide;
            case 'U':
                return upSide;
            case 'D':
                return downSide;
            case 'L':
                return leftSide;
            case 'R':
                return rightSide;
            default:
                throw new IllegalArgumentException();
        }
    }

    public void rotate(String rotation) {
        String[][] t = frontSide;
        switch (rotation) {
            case "x": {
                frontSide = rightSide;
                rightSide = backSide;
                backSide = leftSide;
                leftSide = t;
                break;
            }
            case "x'": {
                frontSide = leftSide;
                leftSide = backSide;
                backSide = rightSide;
                rightSide = t;
                break;
            }
            case "y": {
                frontSide = upSide;
                upSide = backSide;
                backSide = downSide;
                downSide = t;
                break;
            }
            case "y'": {
                frontSide = downSide;
                downSide = backSide;
                backSide = upSide;
                upSide = t;
                break;
            }
            case "z": {
                t = upSide;
                upSide = leftSide;
                leftSide = downSide;
                downSide = rightSide;
                rightSide = t;
                break;
            }
            case "z'": {
                t = upSide;
                upSide = rightSide;
                rightSide = downSide;
                downSide = leftSide;
                leftSide = t;
                break;
            }
        }
    }

    /*public void turn(String layer) {
        switch (layer) {
            case "F":
                String[] t =
        }
    }*/
}
