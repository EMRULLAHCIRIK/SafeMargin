package com.klu.autonomous.core;

import com.klu.autonomous.model.Point;
import java.util.List;

public class LinearSVM {
    // Çizginin denklemi: w1*x + w2*y + b = 0
    private double w1;
    private double w2;
    private double b;

    // Hiperparametreler
    private final double learningRate;
    private final int epochs;
    private final double lambda; // Margin maksimizasyonu için düzenlileştirme (regularization) parametresi

    public LinearSVM(double learningRate, int epochs, double lambda) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.lambda = lambda;
        // Başlangıç ağırlıkları sıfır olarak atanır
        this.w1 = 0.0;
        this.w2 = 0.0;
        this.b = 0.0;
    }

    /**
     * Gradient Descent ve Hinge Loss kullanarak modeli eğitir.
     * Zaman Karmaşıklığı: O(Epochs * N)
     * Alan Karmaşıklığı: O(1) - Döngü içinde nesne türetilmez (Zero object thrashing).
     */
    public void train(List<Point> dataset) {
        // Bellek optimizasyonu: Değişkenler döngü dışında tanımlanır
        double x, y;
        int label;
        double condition;

        for (int epoch = 0; epoch < epochs; epoch++) {
            for (Point p : dataset) {
                x = p.getX();
                y = p.getY();
                label = p.getLabel();

                // Hinge Loss şartı: y_i * (w * x_i + b) >= 1
                condition = label * (w1 * x + w2 * y + b);

                if (condition >= 1) {
                    // Nokta güvenli koridorun dışında ve doğru tarafta.
                    // Sadece margin'i maksimize etmek için ağırlıkları küçült.
                    w1 -= learningRate * (2 * lambda * w1);
                    w2 -= learningRate * (2 * lambda * w2);
                } else {
                    // Nokta koridoru ihlal ediyor veya yanlış tarafta.
                    // Hem ağırlıkları hem de bias değerini cezalandırarak güncelle.
                    w1 -= learningRate * (2 * lambda * w1 - x * label);
                    w2 -= learningRate * (2 * lambda * w2 - y * label);
                    b -= learningRate * (-label);
                }
            }
        }
    }

    // w1, w2 ve b değerlerini dışarıdan okumak için
    public double getW1() { return w1; }
    public double getW2() { return w2; }
    public double getBias() { return b; }

    /**
     * Güvenlik koridorunun genişliğini hesaplar.
     * Margin = 2 / ||w||
     */
    public double calculateMargin() {
        double wNorm = Math.sqrt(w1 * w1 + w2 * w2);
        return 2.0 / wNorm;
    }
}