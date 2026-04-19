package com.klu.autonomous.model;

/**
 * 2 Boyutlu düzlemdeki engel koordinatlarını ve sınıfını temsil eder.
 */
public class Point {
    private final double x;
    private final double y;
    private final int label; // +1 veya -1

    public Point(double x, double y, int label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public int getLabel() { return label; }
}