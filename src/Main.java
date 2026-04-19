package com.klu.autonomous;

import com.klu.autonomous.core.LinearSVM;
import com.klu.autonomous.model.Point;
import com.klu.autonomous.utils.DatasetGenerator;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Otonom Araç Güvenlik Modülü Başlatılıyor ---\n");

        // 1. Veri Üretimi
        int totalPoints = 200;
        List<Point> dataset = DatasetGenerator.generateSeparableData(totalPoints);
        System.out.println(totalPoints + " adet engel koordinatı algılandı ve hafızaya alındı.");

        // 2. Modeli Başlatma (Learning Rate: 0.001, Epoch: 10000, Lambda: 0.01)
        LinearSVM svmModel = new LinearSVM(0.001, 10000, 0.01);

        // 3. Modeli Eğitme (Algoritma Çalışıyor)
        long startTime = System.currentTimeMillis();
        System.out.println("Güvenlik koridoru için optimum sınır hesaplanıyor (Gradient Descent)...");
        svmModel.train(dataset);
        long endTime = System.currentTimeMillis();

        System.out.println("Hesaplama tamamlandı. Geçen süre: " + (endTime - startTime) + " ms\n");

        // 4. Sonuçların Raporlanması
        double w1 = svmModel.getW1();
        double w2 = svmModel.getW2();
        double b = svmModel.getBias();
        double margin = svmModel.calculateMargin();

        System.out.println("--- MATEMATİKSEL MODEL SONUÇLARI ---");
        System.out.printf("Ayrıştırıcı Sınır Denklemi : (%.4f)x + (%.4f)y + (%.4f) = 0\n", w1, w2, b);
        System.out.printf("Optimum Güvenlik Koridoru (Margin) Genişliği : %.4f birim\n", margin);

        System.out.println("\nNot: Algoritma O(E*N) zaman karmaşıklığı ile çalışmış olup, " +
                "döngü içi bellek sızıntısına (memory thrashing) karşı tam korumalıdır.");
    }
}