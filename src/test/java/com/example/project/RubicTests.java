package com.example.project;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RubicTests {
    @Test
    void showTheCurrentSize() {
        Rubic cube = new Rubic();
        String[][] front = {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}};
        String[][] right = {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}};
        assertArrayEquals(front, cube.getSide('F'));
        assertArrayEquals(right, cube.getSide('R'));
    }

    @Test
    void rotate() {
        Rubic cube = new Rubic();
        String[][] expected1 = {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}};
        String[][] expected2 = {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}};
        cube.rotate("x");
        assertArrayEquals(expected1, cube.getSide('F'));
        cube.rotate("x'");
        assertArrayEquals(expected2, cube.getSide('F'));
    }
}
