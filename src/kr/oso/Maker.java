package kr.oso;

class Maker extends Thread {
    private int number;
    private float timeK;
    private String[] products;

    Maker (String name, int number, float k) {
        this.products = new String[] {"Acer", "Dell", "Asus", "Mac"};
        setName(name);
        this.number = number + 1;
        this.timeK = k;
    }

    String getProduct() {
        return this.products[(int)Math.floor(Math.random() * this.products.length)];
    }

    public void run() {
        while (true) {
            try {
                sleep((int) this.timeK * 1000 * number);
            } catch (InterruptedException e){}
            Shop.supply(getProduct());
        }
    }
}
