package kr.oso;

class Consumer extends Thread {
    private final int number;
    private final float timeL;
    private int reads;

    Consumer (String name, int number, float l) {
        setName(name);
        this.number = number + 1;
        this.timeL = l;
        this.reads = 0;
    }

    public void run() {
        while (true) {
            try {
                sleep((long) this.timeL * 1000 * this.number);
            } catch (InterruptedException e) {}
            this.reads++;
            Shop.buy(this.reads);
        }
    }
}
