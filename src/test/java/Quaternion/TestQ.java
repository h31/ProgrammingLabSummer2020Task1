package Quaternion;

import org.junit.jupiter.api.Test;
import quaternion.Quaternion;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestQ {
    public static void eq(Quaternion a, Quaternion b) {
        assertEquals(a.w, b.w, 10e-5);
        assertEquals(a.x, b.x, 10e-5);
        assertEquals(a.y, b.y, 10e-5);
        assertEquals(a.z, b.z, 10e-5);
    }

    @Test
    public void sum() {
        eq(new Quaternion(4.0, -2.0, 5.0, 25.0),
                (new Quaternion(0.0, 0.0, 0.0, 0.0)).sum(new Quaternion(4.0, -2.0, 5.0, 25.0)));
    }

    @Test
    public void sub() {
        eq(new Quaternion(4.0, -2.0, 5.0, 25.0),
                new Quaternion(8.0, -4.0, 10.0, 0.0).sub(new Quaternion(4.0, -2.0, 5.0, -25.0)));
    }

    @Test
    public void norm() {
        assertEquals(25.63201123595259, new Quaternion(25.0, 4.0, 0.0, -4.0).norm(), 10e-10);
    }

    @Test
    public void normalize() {
        eq(new Quaternion(1.0 / 7.0, 4.0 / 7.0, 4.0 / 7.0, -4.0 / 7.0),
                new Quaternion(1.0, 4.0, 4.0, -4.0).normalize());
        assertThrows(ArithmeticException.class, () -> {
            new Quaternion(0.0, 0.0, 0.0, 0.0).normalize();
        });

    }

    @Test
    public void conj() {
        eq(new Quaternion(4.0, -2.0, 5.0, 25.0), new Quaternion(4.0, 2.0, -5.0, -25.0).conj());
    }

    @Test
    public void mult() {
        eq(new Quaternion(10.0, -5.0, 12.5, 62.5),
                new Quaternion(4.0, -2.0, 5.0, 25.0).mult(2.5));
        eq(new Quaternion(-17.6, -10.3, -2.3, 39.1),
                new Quaternion(-5.0, 3.0, 4.0, 3.0).mult(new Quaternion(2.8, -1.0, 3.9, -3.0)));
    }

    @Test
    public void inv() {
        eq(new Quaternion(-0.0358543, 0.0179272, -0.0291317, -0.0806723), new Quaternion(-4.0, -2.0, 3.25, 9).inv());
        assertThrows(ArithmeticException.class, () -> {
            new Quaternion(0.0, 0.0, 0.0, 0.0).inv();
        });
    }

    @Test
    public void scal() {
        assertEquals(2.8, new Quaternion(2.8, 0.0, 0.0, 0.0).scal(), 10e-10);
    }

    @Test
    public void vec() {
        assertArrayEquals(new double[]{2.1, 3.5, -9.1}, new Quaternion(0.0, 2.1, 3.5, -9.1).vec(), 10e-10);
    }

    @Test
    public void build() {
        double a = sin(PI / 6);
        eq(new Quaternion(cos(PI / 6), a * 2.0, a * 2.5, a * 9.8), Quaternion.build(60.0, 2.0, 2.5, 9.8));
    }

    @Test
    public void get() {
        double a = sin(PI / 6);
        assertArrayEquals(new double[]{60.0, 2.0, 2.5, 9.8}, Quaternion.build(60.0, 2.0, 2.5, 9.8).get(), 10e-10);
        assertThrows(ArithmeticException.class, () -> {
            new Quaternion(1.0, 0.0, 0.0, 0.0).get();
        });
    }


}
