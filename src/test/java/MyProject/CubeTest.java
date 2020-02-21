package MyProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CubeTest {
    Cube duo = new Cube(2);

    //тесты для проверки правильности поворота отдельных граней (проверка "белым ящиком")
    @Test
    void turns() {
        //поворачиваю грань кубика и сравниваю с ожидаемым результатом
        Colour[][] side = {{Colour.RED, Colour.YELLOW}, {Colour.RED, Colour.YELLOW}};
        duo.rightFaceClockwise(0);
        Assertions.assertArrayEquals(duo.status(), side);
        //проверяю как работает поворот с другим аргументом
        side[0][0] = Colour.YELLOW;
        side[1][0] = Colour.YELLOW;
        duo.rightFaceClockwise(1);
        Assertions.assertArrayEquals(duo.status(), side);
        //аналогичные действия для другого метода поворота грани
        duo.leftFaceClockwise(0);
        Assertions.assertNotEquals(duo.status(), side);
        duo.leftFaceClockwise(1);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.RED;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        //аналогично
        duo.upFaceClockwise(0);
        Assertions.assertNotEquals(duo.status(), side);
        duo.upFaceClockwise(1);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.BLUE;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        //аналогично
        duo.downFaceClockwise(0);
        side[1][0] = Colour.RED;
        side[1][1] = Colour.RED;
        Assertions.assertArrayEquals(duo.status(), side);
        duo.downFaceClockwise(1);
        Assertions.assertNotEquals(duo.status(), side);
        side[0][0] = Colour.RED;
        side[0][1] = Colour.RED;
        Assertions.assertArrayEquals(duo.status(), side);
        //несколько поворотов всего кубика для проверки правильности поворотов граней
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

    //тесты для проверки правильности поворотов кубика (проверка "белым ящиком")
    @Test
    void flips() {
        //поворачиваю весь кубик и сравниваю с ожидаемым результатом
        Colour[][] side = {{Colour.BLUE, Colour.BLUE}, {Colour.BLUE, Colour.BLUE}};
        duo.turnLeft();
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnLeft();
        Assertions.assertNotEquals(duo.status(), side);
        //аналогично, но поворот в другую сторону
        duo.turnDown();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.WHITE;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        //поворот в другую сторону
        duo.turnUp();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.ORANGE;
            }
        }
        Assertions.assertArrayEquals(duo.status(), side);
        //поворот в другую сторону
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

    //тесты для проверки правильности поворотов передней и задней грани (проверка "белым ящиком")
    @Test
    void frontTurns() {
        //поворачиваю переднюю грань, затем поворачиваю кубик, чтобы проверить правильность
        Colour[][] side = {{Colour.RED, Colour.RED}, {Colour.RED, Colour.RED}};
        Assertions.assertArrayEquals(duo.status(), side);
        duo.frontFaceClockwise(0);
        duo.turnLeft();
        Assertions.assertNotEquals(duo.status(), side);
        for (int i = 0; i < 2; i++) { //задаю значение грани которое было бы без поворота передней грани
            for (int j = 0; j < 2; j++) {
                side[i][j] = Colour.BLUE;
            }
        }
        Assertions.assertNotEquals(duo.status(), side); //проверяю, что не должно сходиться
        side[0][0] = Colour.WHITE;
        side[1][0] = Colour.WHITE;
        Assertions.assertArrayEquals(duo.status(), side); // задаю как должно быть и проверяю
        duo.turnRight();
        //аналогично, но поворачиваю заднюю грань
        duo.backFaceClockwise(0);
        duo.turnLeft();
        Assertions.assertNotEquals(duo.status(), side);
        side[0][1] = Colour.YELLOW;
        side[1][1] = Colour.YELLOW;
        Assertions.assertArrayEquals(duo.status(), side);
        duo.turnRight();
        //проверяю эти методы с другими аргументами
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

    //тесты для проверки что перемешивание кубика работает правильно (проверка "чёрным ящиком")
    @Test
    void randomPosition() {
        Cube test = new Cube(2);
        Assertions.assertEquals(duo, test);
        duo.shuffle();
        Assertions.assertNotEquals(duo, test);
        duo.turnLeft();
        Assertions.assertNotEquals(duo, test);
        duo.shuffle();
        Assertions.assertNotEquals(duo, test);
    }

    //тесты для проверки правильности выбрасывания исключений
    @Test
    void illegalArguments() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> duo.rightFaceClockwise(3));
        Assertions.assertThrows(IllegalArgumentException.class, ()-> duo.upFaceClockwise(3));
        Assertions.assertThrows(IllegalArgumentException.class, ()->duo.frontFaceClockwise(3));
        Assertions.assertThrows(IllegalArgumentException.class, ()->{Cube a = new Cube (-1);});
    }

    //тесты для проверки что перемешивание кубика работает правильно (проверка "чёрным ящиком")
    @Test
    void solutionTest() {
        duo = new Cube(2);
        duo.shuffle();
        duo.solution();
        Cube c = new Cube (2);
        Assertions.assertEquals(c, duo);
    }

    //тесты для проверки правильности equals() (проверка "чёрным ящиком")
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