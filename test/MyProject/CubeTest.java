package MyProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CubeTest {
    Cube duo = new Cube(2);

    @Test
    void turns() {
        String[][] side = {{"\u001B[31m" + "▮", "\u001B[33m" + "▮"}, {"\u001B[31m" + "▮", "\u001B[33m" + "▮"}};
        Cube.r(0);
        Assertions.assertArrayEquals(Cube.status(), side);
        side[0][0] = "\u001B[33m" + "▮";
        side[1][0] = "\u001B[33m" + "▮";
        Cube.r(1);
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.l(0);
        Assertions.assertNotEquals(Cube.status(), side);
        Cube.l(1);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = "\u001B[31m" + "▮";
            }
        }
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.u(0);
        Assertions.assertNotEquals(Cube.status(), side);
        Cube.u(1);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = "\u001B[34m" + "▮";
            }
        }
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.d(0);
        side[1][0] = "\u001B[31m" + "▮";
        side[1][1] = "\u001B[31m" + "▮";
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.d(1);
        Assertions.assertNotEquals(Cube.status(), side);
        side[0][0] = "\u001B[31m" + "▮";
        side[0][1] = "\u001B[31m" + "▮";
        Assertions.assertArrayEquals(Cube.status(), side);
    }

    @Test
    void flips() {
        String[][] side = {{"\u001B[34m" + "▮", "\u001B[34m" + "▮"}, {"\u001B[34m" + "▮", "\u001B[34m" + "▮"}};
        Cube.turnLeft();
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.turnLeft();
        Assertions.assertNotEquals(Cube.status(), side);
        Cube.turnDown();
        Assertions.assertNotEquals(Cube.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = "\u001B[37m" + "▮";
            }
        }
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.turnUp();
        Assertions.assertNotEquals(Cube.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = "\u001B[35m" + "▮";
            }
        }
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.turnRight();
        Assertions.assertNotEquals(Cube.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = "\u001B[34m" + "▮";
            }
        }
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.turnRight();
        Assertions.assertNotEquals(Cube.status(), side);
    }

    @Test
    void frontTurns() {
        String[][] side = {{"\u001B[31m" + "▮", "\u001B[31m" + "▮"}, {"\u001B[31m" + "▮", "\u001B[31m" + "▮"}};
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.f(0);
        Cube.turnLeft();
        Assertions.assertNotEquals(Cube.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = "\u001B[34m" + "▮";
            }
        }
        Assertions.assertNotEquals(Cube.status(), side);
        side[0][0] = "\u001B[37m" + "▮";
        side[1][0] = "\u001B[37m" + "▮";
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.turnRight();
        Cube.b(0);
        Cube.turnLeft();
        Assertions.assertNotEquals(Cube.status(), side);
        side[0][1] = "\u001B[33m" + "▮";
        side[1][1] = "\u001B[33m" + "▮";
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.turnRight();
        Cube.b(1);
        Cube.f(1);
        Cube.turnLeft();
        Assertions.assertNotEquals(Cube.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = "\u001B[34m" + "▮";
            }
        }
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.turnRight();
        Assertions.assertNotEquals(Cube.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = "\u001B[31m" + "▮";
            }
        }
        Assertions.assertArrayEquals(Cube.status(), side);
    }

    @Test
    void randomPosition() {
        String[][] side = {{"\u001B[31m" + "▮", "\u001B[31m" + "▮"}, {"\u001B[31m" + "▮", "\u001B[31m" + "▮"}};
        Assertions.assertArrayEquals(Cube.status(), side);
        Cube.shuffle();
        Assertions.assertNotEquals(Cube.status(), side);
        Cube.turnLeft();
        Assertions.assertNotEquals(Cube.status(), side);
        Cube.shuffle();
        Assertions.assertNotEquals(Cube.status(), side);
        side = Cube.status();
        Cube.shuffle();
        Assertions.assertNotEquals(Cube.status(), side);
    }

    @Test
    void illegalArguments() {
        try {
            Cube.r(3);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            assert true;
        }
        try {
            Cube.u(3);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            assert true;
        }
        try {
            Cube.f(3);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            assert true;
        }
        try {
            Cube a = new Cube (-1);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }
}