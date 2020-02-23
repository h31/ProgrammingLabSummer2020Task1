package com.example.project;

public class Rubic {
    private String[][] frontSide = {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}};
    private String[][] backSide = {{"O", "O", "O"}, {"O", "O", "O"}, {"O", "O", "O"}};
    private String[][] upSide = {{"Y", "Y", "Y"}, {"Y", "Y", "Y"}, {"Y", "Y", "Y"}};
    private String[][] downSide = {{"W", "W", "W"}, {"W", "W", "W"}, {"W", "W", "W"}};
    private String[][] leftSide = {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}};
    private String[][] rightSide = {{"B", "B", "B"}, {"B", "B", "B"}, {"B", "B", "B"}};

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

}
