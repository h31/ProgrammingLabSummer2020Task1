package com.example.project;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RubicTests {
    @Test
    void showTheCurrentSize() {
        Rubic cube = new Rubic();
        String[][] front = {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}};
        String[][] left = {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}};
        assertArrayEquals(front, cube.getSide('F'));
        assertArrayEquals(left, cube.getSide('L'));
    }
}
