package kr.oso;

import java.util.concurrent.Semaphore;

class Shop {

    private static int productCount = 0;
    private static Semaphore sem = new Semaphore(1, false);

    static synchronized void buy() {
        try {
            if (productCount > 0) {
                sem.acquire();
                String purchase = MyFile.readFromFile();
                if (purchase != null && purchase.matches("\\S+")) {
                    productCount--;
                    System.out.printf("Покупатель %s приобрел товар %s. На складе - еще %dшт\n",
                            Thread.currentThread().getName(), purchase, productCount);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        sem.release();
    }

    static synchronized void supply(String product) {
            try {
                if (productCount < 4) {
                    sem.acquire();
                    MyFile.writeToFile(product);
                    productCount++;
                    System.out.printf("Производитель %s поставил товар %s. На складе - теперь %dшт\n",
                        Thread.currentThread().getName(), product, productCount);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        sem.release();
    }

    static int getProductCount() {
        return productCount;
    }
}
