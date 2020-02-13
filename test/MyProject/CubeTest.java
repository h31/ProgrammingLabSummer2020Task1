package MyProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CubeTest {
    Cube duo = new Cube(2);

    //тесты для проверки правильности поворота отдельных граней
    @Test
    void turns() {
        Colour[][] side = {{Colour.RED, Colour.YELLOW}, {Colour.RED, Colour.YELLOW}};
        duo.rightFaceClockwise(0);
        Assertions.assertArrayEquals(duo.status(), side);
        side[0][0] = Colour.YELLOW;
        side[1][0] = Colour.YELLOW;
        duo.rightFaceClockwise(1);
        Assertions.assertArrayEquals(duo.status(), side);
        duo.leftFaceClockwise(0);
        Assertions.assertNotEquals(duo.status(), side);
        duo.leftFaceClockwise(1);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.RED;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        duo.upFaceClockwise(0);
        Assertions.assertNotEquals(duo.status(), side);
        duo.upFaceClockwise(1);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.BLUE;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        duo.downFaceClockwise(0);
        side[1][0] = Colour.RED;
        side[1][1] = Colour.RED;
        Assertions.assertArrayEquals(duo.status(), side);
        duo.downFaceClockwise(1);
        Assertions.assertNotEquals(duo.status(), side);
        side[0][0] = Colour.RED;
        side[0][1] = Colour.RED;
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnClockwise();
        duo.turnRight();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.YELLOW;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnLeft();
        duo.turnCounterClockwise();
        duo.turnRight();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.GREEN;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnLeft();
    }

    //тесты для проверки правильности поворотов кубика
    @Test
    void flips() {
        Colour[][] side = {{Colour.BLUE, Colour.BLUE}, {Colour.BLUE, Colour.BLUE}};
        duo.turnLeft();
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnLeft();
        Assertions.assertNotEquals(duo.status(), side);
        duo.turnDown();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.WHITE;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnUp();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.ORANGE;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnRight();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.BLUE;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnRight();
        Assertions.assertNotEquals(duo.status(), side);
    }

    //тесты для проверки правильности поворотов передней и задней грани
    @Test
    void frontTurns() {
        Colour[][] side = {{Colour.RED, Colour.RED}, {Colour.RED, Colour.RED}};
        Assertions.assertArrayEquals(duo.status(), side);
        duo.frontFaceClockwise(0);
        duo.turnLeft();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.BLUE;
            }
        }
        Assertions.assertNotEquals(duo.status(), side);
        side[0][0] = Colour.WHITE;
        side[1][0] = Colour.WHITE;
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnRight();
        duo.backFaceClockwise(0);
        duo.turnLeft();
        Assertions.assertNotEquals(duo.status(), side);
        side[0][1] = Colour.YELLOW;
        side[1][1] = Colour.YELLOW;
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnRight();
        duo.backFaceClockwise(1);
        duo.frontFaceClockwise(1);
        duo.turnLeft();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.BLUE;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnRight();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.RED;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
    }

    //тесты для проверки что перемешивание кубика работает правильно
    @Test
    void randomPosition() {
        Colour[][] side = {{Colour.RED, Colour.RED}, {Colour.RED, Colour.RED}};
        Assertions.assertArrayEquals(duo.status(), side);
        duo.shuffle();
        Assertions.assertNotEquals(duo.status(), side);
        duo.turnLeft();
        Assertions.assertNotEquals(duo.status(), side);
        duo.shuffle();
        Assertions.assertNotEquals(duo.status(), side);
        side = duo.status();
        duo.shuffle();
        Assertions.assertNotEquals(duo.status(), side);
    }

    //тесты для проверки правильности выбрасывания исключений
    @Test
    void illegalArguments() {
        try {
            duo.rightFaceClockwise(3);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            duo.upFaceClockwise(3);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            duo.frontFaceClockwise(3);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
        }
        try {
            Cube a = new Cube (-1);
            Assertions.fail();
        } catch (IllegalArgumentException e) {
        }
    }

    //тесты для проверки правильности equals()
    @Test
    void equalsTest() {
        Cube a = new Cube(2);
        Cube b = new Cube(2);
        Assertions.assertEquals(a, b); //проверка что два одинаковых кубика равны
        a.turnUp(); //поворачивем один из кубиков
        Assertions.assertEquals(a, b); //проверяем что кубики всё равно равны, не смотря на поворот
        a.turnDown(); //возвращаем в изначальное положение
        a.rightFaceClockwise(0); //поворачиваем одну из граней
        a.turnClockwise(); //делаем несколько поворотов всего кубика
        a.turnDown();
        b.rightFaceClockwise(0); // поворачиваем ту же грань что и у кубика a
        Assertions.assertEquals(a, b); ////проверяем что кубики всё равно равны, не смотря на манипуляции
        b.rightFaceClockwise(0);
        Assertions.assertNotEquals(a, b);
        b.leftFaceClockwise(1);
    }
}