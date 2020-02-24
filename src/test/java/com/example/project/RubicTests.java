package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RubicTests {
    @Test
    void showTheCurrentSize() {
        Rubic cube = new Rubic();
        String[][] front = {
                {Rubic.red, Rubic.red, Rubic.red},
                {Rubic.red, Rubic.red, Rubic.red},
                {Rubic.red, Rubic.red, Rubic.red}
        };
        String[][] right = {
                {Rubic.green, Rubic.green, Rubic.green},
                {Rubic.green, Rubic.green, Rubic.green},
                {Rubic.green, Rubic.green, Rubic.green}
        };
        assertArrayEquals(front, cube.getSide(Rubic.CubeSides.FRONT));
        assertArrayEquals(right, cube.getSide(Rubic.CubeSides.RIGHT));
        assertThrows(IllegalArgumentException.class, () -> cube.getSide(Rubic.CubeSides.DOWN_));
    }

    @Test
    void rotate() {
        Rubic cube = new Rubic();
        String[][] expected1 = {
                {Rubic.green, Rubic.green, Rubic.green},
                {Rubic.green, Rubic.green, Rubic.green},
                {Rubic.green, Rubic.green, Rubic.green}
        };
        String[][] expected2 = {
                {Rubic.red, Rubic.red, Rubic.red},
                {Rubic.red, Rubic.red, Rubic.red},
                {Rubic.red, Rubic.red, Rubic.red}
        };
        cube.rotate(Rubic.CubeSides.X);
        assertArrayEquals(expected1, cube.getSide(Rubic.CubeSides.FRONT));
        cube.rotate(Rubic.CubeSides.X_);
        assertArrayEquals(expected2, cube.getSide(Rubic.CubeSides.FRONT));
        assertThrows(IllegalArgumentException.class, () -> cube.rotate(Rubic.CubeSides.FRONT));
    }
}
