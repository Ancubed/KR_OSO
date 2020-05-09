package kr.oso;

class Consumer extends Thread {
    private int number;
    private float timeL;

    Consumer (String name, int number, float l) {
        setName(name);
        this.number = number + 1;
        this.timeL = l;
    }

    public void run() {
        while (true) {
            try {
                sleep((int) this.timeL * 1000 * number);
            } catch (InterruptedException e){}
            Shop.buy();
        }
    }
}
