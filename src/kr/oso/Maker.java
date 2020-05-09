package kr.oso;

class Maker extends Thread {
    private final int number;
    private final float timeK;
    private final String[] products;
    private int records;

    Maker (String name, int number, float k) {
        this.products = new String[] {"Acer", "Dell", "Asus", "Mac"};
        setName(name);
        this.number = number + 1;
        this.timeK = k;
        this.records = 0;
    }

    private String getProduct() {
        return this.products[(int)Math.floor(Math.random() * this.products.length)];
    }

    public void run() {
        while (true) {
            try {
                sleep((long) this.timeK * 1000 * this.number);
            } catch (InterruptedException e) {}
            this.records++;
            Shop.supply(getProduct(), this.records);
        }
    }
}
