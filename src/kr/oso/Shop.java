package kr.oso;
import java.util.concurrent.Semaphore;

class Shop {

    private static int productCount = 0;
    private static final Semaphore sem = new Semaphore(1, false);

    static synchronized void buy(int reads) {
        try {
            if (productCount > 0) {
                sem.acquire();
                String purchase = MyFile.readFromFile();
                if (purchase != null && purchase.matches("\\S+")) {
                    productCount--;
                    long id = Thread.currentThread().getId();
                    System.out.printf("Покупатель %s c id %d приобрел товар %s (%d покупок всего). На складе - еще %dшт\n",
                            Thread.currentThread().getName(), id,
                            purchase, reads, productCount);
                    MainWindow.currentConsumerBuy(id, reads);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        sem.release();
    }

    static synchronized void supply(String product, int records) {
            try {
                if (productCount < 4) {
                    sem.acquire();
                    MyFile.writeToFile(product);
                    productCount++;
                    long id = Thread.currentThread().getId();
//                    long id = Thread.currentThread().getId();
//                    int numberOfRecords = Manager.numberOfRecords.get(id);
//                    Manager.numberOfRecords.put(id, numberOfRecords + 1);
                    System.out.printf("Производитель %s c id %d поставил товар %s (%d поставок всего). На складе - теперь %dшт\n",
                        Thread.currentThread().getName(), id,
                            product, records, productCount);
                    MainWindow.currentMakerSupply(id, records);
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
