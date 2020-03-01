package MyProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CubeBlackBoxTest {
    Cube duo = new Cube(2);

    //тесты для проверки что перемешивание кубика работает правильно
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

    //тесты для проверки что решение кубика работает правильно
    @Test
    void solutionTest() {
        duo = new Cube(2);
        duo.shuffle();
        duo.solution();
        Cube c = new Cube(2);
        Assertions.assertEquals(c, duo);
        for (int i = 0; i < 3; i++) {
            Assertions.assertEquals(c, duo);
            c.turnRight();
            duo.turnRight();
        }
        c.turnUp();
        duo.turnUp();
        Assertions.assertEquals(c, duo);
        c.turnDown();
        c.turnDown();
        duo.turnDown();
        duo.turnDown();
        Assertions.assertEquals(c, duo);
        c.turnUp();
        duo.turnUp();
    }

    //тесты для проверки правильности equals()
    @Test
    void equalsTest() {
        Cube a = new Cube(2);
        Cube b = new Cube(2);
        Assertions.assertEquals(a, b); //проверка что два одинаковых кубика равны
        a.turnUp(); //поворачивем один из кубиков
        Assertions.assertNotEquals(a, b); //проверяем что кубики не равны
        a.turnDown(); //возвращаем в изначальное положение
        Assertions.assertEquals(a, b); //проверяем что кубики вновь равны
        b.rightFaceClockwise(0);
        Assertions.assertNotEquals(a, b);
    }
}
