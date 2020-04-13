/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tul.alg2.cv;

/**
 *
 * @author MP
 */
public class Ellipse extends Shape {

    private double a;
    private double b;

    public Ellipse(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    @Override
    public double computeArea() {
        return Math.PI * a * b;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" a = %.2f, b = %.2f", a, b);
    }

}
