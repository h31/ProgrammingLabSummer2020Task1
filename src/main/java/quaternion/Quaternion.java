package quaternion;

import static java.lang.Math.*;

public class Quaternion {
    public double w;
    public double x;
    public double y;
    public double z;

    Quaternion() {
        w = 0.0;
        x = 0.0;
        y = 0.0;
        z = 0.0;
    }

    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getW() {
        return w;
    }

    public double getX() {
        return x;
    }

    public double getY() { return y; }

    public double getZ() {
        return z;
    }

    public void setW(double w) {
        this.w = w;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double norm() {
        return sqrt(w * w + x * x + y * y + z * z);
    }

    public Quaternion sum(Quaternion a) {
        return new Quaternion(a.w + w, a.x + x, a.y + y, a.z + z);
    }

    public Quaternion sub(Quaternion b) {
        return new Quaternion(w - b.w, x - b.x, y - b.y, z - b.z);
    }

    public Quaternion normalize() {
        double p = this.norm();
        if (abs(p) < 10e-10) throw new ArithmeticException();
        return new Quaternion(w / p, x / p, y / p, z / p);
    }

    public Quaternion conj() {
        return new Quaternion(w, -x, -y, -z);
    }

    public Quaternion mult(double scal) {
        return new Quaternion(w * scal, x * scal, y * scal, z * scal);
    }

    public Quaternion mult(Quaternion b) {
        return new Quaternion(w * b.w - x * b.x - y * b.y - z * b.z,
                w * b.x + b.w * x + y * b.z - b.y * z,
                w * b.y + b.w * y + z * b.x - b.z * x,
                w * b.z + b.w * z + x * b.y - b.x * y);
    }

    public Quaternion div(double scal) {
        if (abs(scal) < 10e-5) throw new ArithmeticException();
        return new Quaternion(w / scal, x / scal, y / scal, z / scal);
    }

    public Quaternion inv() {
        if (abs(this.norm()) < 10e-10) throw new ArithmeticException();
        return this.conj().div(this.norm() * this.norm());
    }

    public double scal() {
        return w;
    }

    public double[] vec() {
        return new double[]{x, y, z};
    }

    public static Quaternion build(double angle, double x, double y, double z) {
        double an = toRadians(angle) / 2 % (2*PI);
        return new Quaternion(cos(an), sin(an) * x, sin(an) * y, sin(an) * z);
    }

    public double[] get() {
        double s = (sin(acos(w)));
        if (abs(s) < 10e-10) throw new ArithmeticException();
        return new double[]{toDegrees(acos(w)) * 2, x / s, y / s, z / s};
    }
}
