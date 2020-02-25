package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RubicTests {
    @Test
    void equals() {
        Rubic cube1 = new Rubic();
        Rubic cube2 = new Rubic();
        cube1.rotateCW(Rubic.Rotates.Z);
        cube1.rotateAntiCW(Rubic.Rotates.X);
        assertEquals(cube1, cube2);
        assertEquals(cube1.hashCode(), cube2.hashCode());
    }

    @Test
    void showTheCurrentSize() {
        Rubic cube = new Rubic();
        String[][] front = {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}};
        String[][] right = {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}};

        assertArrayEquals(front, cube.getSide(Rubic.Sides.FRONT));
        assertArrayEquals(right, cube.getSide(Rubic.Sides.RIGHT));
    }

    @Test
    void rotate() {
        Rubic cube = new Rubic();
        String[][] expected1 = {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}};
        String[][] expected2 = {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}};
        cube.rotateCW(Rubic.Rotates.X);
        assertArrayEquals(expected1, cube.getSide(Rubic.Sides.FRONT));
        cube.rotateAntiCW(Rubic.Rotates.X);
        assertArrayEquals(expected2, cube.getSide(Rubic.Sides.FRONT));
    }

    @Test
    void turn() {
        Rubic cube = new Rubic();

    }

}
