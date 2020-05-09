package kr.oso;

import java.util.concurrent.Semaphore;

class Manager extends Thread {
    private int n;
    private int m;
    private float k;
    private float l;
    private int s;
    private Maker[] makers;
    private Consumer[] consumers;

    Manager (int n, int m, float k, float l, int s) {
        this.n = n;
        this.m = m;
        this.k = k;
        this.l = l;
        this.s = s;
    }

    Manager () {
        this.n = 3;
        this.m = 5;
        this.k = 1.0f;
        this.l = 1.2f;
        this.s = 4;
    }

    public void run () {
        setName("Manager");
        this.makers = new Maker[this.n];
        this.consumers = new Consumer[this.m];
        for (int i = 0; i < this.n; i++) {
            //makers[i] = new Maker("Maker - " + (i + 1));
            new Maker("Maker-" + (i + 1), i, this.k).start();
        }
        for (int i = 0; i < this.m; i++) {
            //consumers[i] = new Consumer("Consumer - " + (i + 1));
            new Consumer("Consumer-" + (i + 1),  i, this.k).start();
        }
    }
}
