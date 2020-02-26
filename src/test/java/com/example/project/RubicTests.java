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

        cube2.turnFaceSideAntiCW(Rubic.Sides.FRONT);
        assertNotEquals(cube1, cube2);
        assertNotEquals(cube1.hashCode(), cube2.hashCode());
    }

    @Test
    void getSize() {
        Rubic cube = new Rubic();
        String[][] front = {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}};
        String[][] right = {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}};
        String[][] left =  {
                {"B", "B", "B", "B", "B"},
                {"B", "B", "B", "B", "B"},
                {"B", "B", "B", "B", "B"},
                {"B", "B", "B", "B", "B"},
                {"B", "B", "B", "B", "B"}
        };
        assertArrayEquals(front, cube.getSide(Rubic.Sides.FRONT));
        assertArrayEquals(right, cube.getSide(Rubic.Sides.RIGHT));

        cube = new Rubic(5);
        assertArrayEquals(left, cube.getSide(Rubic.Sides.LEFT));
    }

    @Test
    void toStringRubic() {
        Rubic cube = new Rubic(2);
        String expected =
                "B B  R R  G G\n" +
                "B B  R R  G G\n" +
                "\n" +
                        "     W W\n" +
                        "     W W\n" +
                        "\n" +
                        "     O O\n" +
                        "     O O\n" +
                        "\n" +
                        "     Y Y\n" +
                        "     Y Y\n";
        assertEquals(expected, cube.toString());
    }

    @Test
    void rotate() {
        Rubic cube = new Rubic();
        String[][] expected1 = {{"G", "G", "G"}, {"G", "G", "G"}, {"G", "G", "G"}};
        String[][] expected2 = {{"R", "R", "R"}, {"R", "R", "R"}, {"R", "R", "R"}};
        cube.rotateCW(Rubic.Rotates.X);
        assertArrayEquals(expected1, cube.getSide(Rubic.Sides.FRONT));
        assertArrayEquals(expected2, cube.getSide(Rubic.Sides.LEFT));
        cube.rotateAntiCW(Rubic.Rotates.Y);
        assertArrayEquals(expected2, cube.getSide(Rubic.Sides.LEFT));
        assertArrayEquals(expected1, cube.getSide(Rubic.Sides.DOWN));
    }

    @Test
    void turnFaceSide() {
        Rubic cube = new Rubic();
        String expected = "Y R G  Y Y O  Y Y B\n" +
                "B B B  Y R W  G G W\n" +
                "O O G  W W W  B R W\n" +
                "\n" +
                "       R R R\n" +
                "       B W G\n" +
                "       B O O\n" +
                "\n" +
                "       W G G\n" +
                "       W O O\n" +
                "       R Y Y\n" +
                "\n" +
                "       B O O\n" +
                "       B Y G\n" +
                "       R R G\n";
        cube.turnFaceSideCW(Rubic.Sides.FRONT);
        cube.turnFaceSideCW(Rubic.Sides.RIGHT);
        cube.turnFaceSideAntiCW(Rubic.Sides.BACK);
        cube.turnFaceSideAntiCW(Rubic.Sides.LEFT);
        cube.turnFaceSideCW(Rubic.Sides.UP);
        cube.turnFaceSideAntiCW(Rubic.Sides.DOWN);
        System.out.println(cube);
        assertEquals(expected, cube.toString());

        Rubic cube2 = new Rubic();
        String expected2 = "B W W  R R R  Y Y G\n" +
                "B W W  R R R  Y Y G\n" +
                "B W W  R R R  Y Y G\n" +
                "\n" +
                "       G G G\n" +
                "       G G G\n" +
                "       W W W\n" +
                "\n" +
                "       O O O\n" +
                "       O O O\n" +
                "       O O O\n" +
                "\n" +
                "       Y Y Y\n" +
                "       B B B\n" +
                "       B B B\n";
        cube2.turnFaceSideCW(Rubic.Sides.FRONT, 2);
        assertEquals(expected2, cube2.toString());
        cube2.turnFaceSideAntiCW(Rubic.Sides.FRONT, 2);
        cube2.turnFaceSideAntiCW(Rubic.Sides.BACK, 2);
        cube2.turnFaceSideAntiCW(Rubic.Sides.DOWN, 2);
        cube2.turnFaceSideCW(Rubic.Sides.RIGHT, 2);
        cube2.turnFaceSideAntiCW(Rubic.Sides.BACK, 2);
        cube2.turnFaceSideCW(Rubic.Sides.RIGHT, 2);
        cube2.turnFaceSideAntiCW(Rubic.Sides.LEFT, 2);
        System.out.println(cube2);
        assertEquals(cube, cube2);

    }

}
