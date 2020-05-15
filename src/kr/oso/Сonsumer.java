package kr.oso;

class Consumer extends Thread {
    private final int number;
    private final float timeL;
    private int reads;
    private boolean work;

    Consumer (String name, int number, float l) {
        setName(name);
        this.number = number + 1;
        this.timeL = l;
        this.reads = 0;
        this.work = true;
    }

    public void run() {
        while(true) {
            try {
                sleep(1);
                while (this.work) {
                    sleep((long) this.timeL * 1000 * this.number);
                    if (Shop.buy(this.reads)) {
                        this.reads++;
                    }
                }
            } catch (InterruptedException e) {
                this.work = !this.work;
                System.out.println("Interrupt Consumer");
            }
        }
    }
}
