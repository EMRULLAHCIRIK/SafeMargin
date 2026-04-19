package com.klu.autonomous.utils;

import com.klu.autonomous.model.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DatasetGenerator {

    /**
     * Doğrusal olarak ayrılabilir rastgele iki sınıf üretir.
     */
    public static List<Point> generateSeparableData(int numPoints) {
        List<Point> dataset = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numPoints / 2; i++) {
            // Sınıf 1 (+1 Etiketi): Sol alt bölgede engeller
            double x1 = random.nextDouble() * 10;
            double y1 = random.nextDouble() * 10;
            dataset.add(new Point(x1, y1, 1));

            // Sınıf 2 (-1 Etiketi): Sağ üst bölgede engeller (arada boşluk var)
            double x2 = 15 + random.nextDouble() * 10;
            double y2 = 15 + random.nextDouble() * 10;
            dataset.add(new Point(x2, y2, -1));
        }

        return dataset;
    }
}