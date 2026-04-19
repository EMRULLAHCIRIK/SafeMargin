# 🛡️ SafeMargin: Otonom Araç Navigasyon Güvenlik Modülü

**SafeMargin**, otonom araçlar için geliştirilmiş, 2 boyutlu düzlemdeki engel kümelerini analiz ederek araç için "maksimum güvenli" geçiş koridorunu hesaplayan bir navigasyon güvenlik modülüdür.

## 📌 Proje Özeti
Bu proje, sadece engelleri ayıran herhangi bir çizgi bulmakla yetinmez; her iki engel sınıfına olan uzaklığı **maksimize** ederek sensör hatalarına ve belirsizliklere karşı en yüksek toleransı sağlayan **Maksimum Marjlı Hiper-düzlemi (Maximum Margin Hyperplane)** oluşturur.

---

## 🚀 Öne Çıkan Özellikler
* **Matematiksel Model:** Destek Vektör Makineleri (Linear SVM) prensipleri üzerine inşa edilmiştir.
* **Optimizasyon:** Hinge Loss fonksiyonu ve iteratif Gradient Descent kullanılarak yüksek performanslı çözüm üretilir.
* **Mimari:** Nesne Yönelimli Programlama (OOP) prensiplerine ve katmanlı mimariye uygun geliştirilmiştir.
* **Bellek Dostu:** "Zero Memory Thrashing" prensibiyle döngü içi nesne üretiminden kaçınılarak stabilize edilmiştir.

---

## 🛠️ Yazılım Mimarisi
Sistem, sorumlulukların ayrılması (Separation of Concerns) prensibiyle 3 temel katmanda yapılandırılmıştır:

* **`com.klu.autonomous.model`**: Veri yapılarını ve engel koordinatlarını (Point) yönetir.
* **`com.klu.autonomous.core`**: SVM algoritmasının kalbidir; ağırlık optimizasyonunu (w, b) gerçekleştirir.
* **`com.klu.autonomous.utils`**: Test senaryoları için deterministik veri setleri üretir.

---

## 📈 Algoritmik Analiz ve Performans

Proje, gerçek zamanlı sistemlerin gereksinim duyduğu hız ve verimlilik dengesini gözetir:

| Analiz Türü | Karmaşıklık | Açıklama |
| :--- | :--- | :--- |
| **Zaman Karmaşıklığı** | $O(E \times N)$ | $E$: İterasyon (Epoch), $N$: Engel sayısı. Doğrusal ölçeklenme sağlar. |
| **Alan Karmaşıklığı** | $O(1)$ | Eğitim sırasında sabit bellek kullanımı ile gömülü sistemlere uygundur. |

> **Mühendislik Kararı:** Quadratic Programming ($O(N^3)$) yerine Gradient Descent ($O(E \cdot N)$) tercih edilerek, otonom araçlardaki "gerçek zamanlılık" kısıtı karşılanmıştır.

---

## 💻 Kurulum ve Çalıştırma
Proje, **IntelliJ IDEA 2025** ve **Java 21+** ortamında geliştirilmiştir.

1.  Repoyu klonlayın:
    ```bash
    git clone [https://github.com/kullanici_adiniz/SafeMargin.git](https://github.com/kullanici_adiniz/SafeMargin.git)
    ```
2.  IntelliJ IDEA ile açın.
3.  `Main.java` dosyasını çalıştırın.

**Örnek Çıktı:**
```text
Ayrıştırıcı Sınır Denklemi : (-0.1537)x + (-0.1786)y + (4.1470) = 0
Optimum Güvenlik Koridoru (Margin) Genişliği : 8.4887 birim
Sistem Durumu: Başarıyla tamamlandı (Exit Code 0)
