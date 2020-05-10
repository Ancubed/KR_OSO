package kr.oso;

class Maker extends Thread {
    private final int number;
    private final float timeK;
    private final String[] products;
    private int records;
    private boolean work;

    Maker (String name, int number, float k) {
        this.products = new String[] {"Acer", "Dell", "Asus", "Mac"};
        setName(name);
        this.number = number + 1;
        this.timeK = k;
        this.records = 0;
        this.work = true;
    }

    private String getProduct() {
        return this.products[(int)Math.floor(Math.random() * this.products.length)];
    }

    public void run() {
        while(true) {
            try {
                sleep(1);
                while (this.work) {
                    sleep((long) this.timeK * 1000 * this.number);
                    this.records++;
                    Shop.supply(getProduct(), this.records);
                }
            } catch (InterruptedException e) {
                this.work = !this.work;
                System.out.println("Interrupt Maker");
            }
        }
    }
}
