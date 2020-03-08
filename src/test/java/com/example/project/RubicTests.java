package com.example.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RubicTests {
    @Test
    void equals() {
        Rubic cube1 = new Rubic();
        Rubic cube2 = new Rubic();
        assertEquals(cube1, cube2);
        assertEquals(cube1.hashCode(), cube2.hashCode());

        cube2.turnFaceSideAntiCW(Sides.FRONT);
        assertNotEquals(cube1, cube2);
        assertNotEquals(cube1.hashCode(), cube2.hashCode());
    }

    @Test
    void getSide() {
        Rubic cube = new Rubic();
        Cells[][] front = {{Cells.R, Cells.R, Cells.R}, {Cells.R, Cells.R, Cells.R}, {Cells.R, Cells.R, Cells.R}};
        Cells[][] right = {{Cells.G, Cells.G, Cells.G}, {Cells.G, Cells.G, Cells.G}, {Cells.G, Cells.G, Cells.G}};
        Cells[][] left =  {
                {Cells.B, Cells.B, Cells.B, Cells.B, Cells.B},
                {Cells.B, Cells.B, Cells.B, Cells.B, Cells.B},
                {Cells.B, Cells.B, Cells.B, Cells.B, Cells.B},
                {Cells.B, Cells.B, Cells.B, Cells.B, Cells.B},
                {Cells.B, Cells.B, Cells.B, Cells.B, Cells.B}
        };
        assertArrayEquals(front, cube.getSide(Sides.FRONT));
        assertArrayEquals(right, cube.getSide(Sides.RIGHT));

        cube = new Rubic(5);
        assertArrayEquals(left, cube.getSide(Sides.LEFT));
    }

    @Test
    void toStringRubic() {
        Rubic cube = new Rubic(2);
        String expected =
                "left face right\n" +
                "B B  R R  G G\n" +
                "B B  R R  G G\n" +
                "     down\n" +
                "     W W\n" +
                "     W W\n" +
                "     back\n" +
                "     O O\n" +
                "     O O\n" +
                "     up\n" +
                "     Y Y\n" +
                "     Y Y\n";
        assertEquals(expected, cube.toString());
    }

    @Test
    void rotate() {
        Rubic cube = new Rubic();
        Cells[][] expected1 = {{Cells.G, Cells.G, Cells.G}, {Cells.G, Cells.G, Cells.G}, {Cells.G, Cells.G, Cells.G}};
        Cells[][] expected2 = {{Cells.R, Cells.R, Cells.R}, {Cells.R, Cells.R, Cells.R}, {Cells.R, Cells.R, Cells.R}};
        cube.rotateCW(Rotates.X);
        assertArrayEquals(expected1, cube.getSide(Sides.FRONT));
        assertArrayEquals(expected2, cube.getSide(Sides.LEFT));
        cube.rotateAntiCW(Rotates.Y);
        assertArrayEquals(expected2, cube.getSide(Sides.LEFT));
        assertArrayEquals(expected1, cube.getSide(Sides.DOWN));
    }

    @Test
    void turnFaceSide() {
        Rubic cube = new Rubic();
        String expected =
                "left   face   right\n" +
                "Y R G  Y Y O  Y Y B\n" +
                "B B B  Y R W  G G W\n" +
                "O O G  W W W  B R W\n" +
                "       down\n" +
                "       R R R\n" +
                "       B W G\n" +
                "       B O O\n" +
                "       back\n" +
                "       W G G\n" +
                "       W O O\n" +
                "       R Y Y\n" +
                "       up\n" +
                "       B O O\n" +
                "       B Y G\n" +
                "       R R G\n";
        cube.turnFaceSideCW(Sides.FRONT);
        cube.turnFaceSideCW(Sides.RIGHT);
        cube.turnFaceSideAntiCW(Sides.BACK);
        cube.turnFaceSideAntiCW(Sides.LEFT);
        cube.turnFaceSideCW(Sides.UP);
        cube.turnFaceSideAntiCW(Sides.DOWN);
        assertEquals(expected, cube.toString());

        Rubic cube2 = new Rubic();
        String expected2 =
                "left   face   right\n" +
                "B W W  R R R  Y Y G\n" +
                "B W W  R R R  Y Y G\n" +
                "B W W  R R R  Y Y G\n" +
                "       down\n" +
                "       G G G\n" +
                "       G G G\n" +
                "       W W W\n" +
                "       back\n" +
                "       O O O\n" +
                "       O O O\n" +
                "       O O O\n" +
                "       up\n" +
                "       Y Y Y\n" +
                "       B B B\n" +
                "       B B B\n";
        cube2.turnFaceSideCW(Sides.FRONT, 2);
        assertEquals(expected2, cube2.toString());
        cube2.turnFaceSideAntiCW(Sides.FRONT, 2);
        cube2.turnFaceSideAntiCW(Sides.BACK, 2);
        cube2.turnFaceSideAntiCW(Sides.DOWN, 2);
        cube2.turnFaceSideCW(Sides.RIGHT, 2);
        cube2.turnFaceSideAntiCW(Sides.BACK, 2);
        cube2.turnFaceSideCW(Sides.RIGHT, 2);
        cube2.turnFaceSideAntiCW(Sides.LEFT, 2);
        cube2.rotateCW(Rotates.Z);
        cube2.rotateCW(Rotates.X);
        assertEquals(cube, cube2);
    }


    @Test
    void turnInnerSide() {
        Rubic cube = new Rubic(5);
        String expected =
                "left       face       right\n" +
                "B Y Y Y B  R R W W R  G W W W G\n" +
                "B R R R B  R R W W R  G O O O G\n" +
                "R R R R R  G G G G G  O O O O O\n" +
                "B Y Y Y B  R R W W R  G W W W G\n" +
                "B Y Y Y B  R R W W R  G W W W G\n" +
                "           down\n" +
                "           W W O O W\n" +
                "           B B W B B\n" +
                "           B B W B B\n" +
                "           B B R B B\n" +
                "           W W O O W\n" +
                "           back\n" +
                "           O O Y Y O\n" +
                "           O O Y Y O\n" +
                "           B B B B B\n" +
                "           O O Y Y O\n" +
                "           O O Y Y O\n" +
                "           up\n" +
                "           Y Y R R Y\n" +
                "           G G O G G\n" +
                "           G G Y G G\n" +
                "           G G Y G G\n" +
                "           Y Y R R Y\n";
        cube.turnInnerSideCW(Layers.MID, 1, 2, Positions.AFTER);
        cube.turnInnerSideCW(Layers.EQUATOR, 2);
        cube.turnInnerSideAntiCW(Layers.STANDING, 3, 3, Positions.BEFORE);
        assertEquals(expected, cube.toString());
    }

    @Test
    void exceptions() {
        Rubic test = new Rubic();
        assertThrows(IllegalArgumentException.class, () -> { Rubic cube = new Rubic(0); });
        assertThrows(IllegalArgumentException.class, () -> { Rubic cube = new Rubic(-3); });
        assertThrows(IllegalArgumentException.class, () -> test.turnInnerSideAntiCW(Layers.MID, 4));
        assertThrows(IllegalArgumentException.class, () -> test.turnInnerSideCW(Layers.EQUATOR, 1, 3, Positions.AFTER));
        assertThrows(IllegalArgumentException.class, () -> test.turnFaceSideCW(Sides.FRONT, 3));
    }
}
