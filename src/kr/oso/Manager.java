package kr.oso;

import java.util.ArrayList;

class Manager extends Thread {
    private int makersNumber;
    private int consumersNumber;
    private float k;
    private float l;
    private int s;
    static ArrayList<Maker> makers = new ArrayList<>();
    static ArrayList<Consumer> consumers = new ArrayList<>();

    int getMakersNumber() {
        return this.makersNumber;
    }

    int getConsumersNumber() {
        return this.consumersNumber;
    }

    Manager (int n, int m, float k, float l, int s) {
        this.makersNumber = n;
        this.consumersNumber = m;
        this.k = k;
        this.l = l;
        this.s = s;
    }

    Manager () {
        this.makersNumber = 3;
        this.consumersNumber = 5;
        this.k = 1.0f;
        this.l = 1.2f;
        this.s = 4;
    }

    public void run () {
        setName("Manager");
        try {
            sleep(500);
        } catch (InterruptedException e){
            System.out.println("Exception in func run(Manager)");
        }
        for (int i = 0; i < this.makersNumber; i++) {
            Maker newMaker = new Maker("Maker-" + (i + 1), i, this.k);
            makers.add(newMaker);
        }
        MainWindow.addMakerToWindow();
        for (int i = 0; i < this.makersNumber; i++) {
            makers.get(i).start();
        }
        ////////////////////////////////////////////////
        for (int i = 0; i < this.consumersNumber; i++) {
            Consumer newConsumer = new Consumer("Consumer-" + (i + 1), i, this.k);
            consumers.add(newConsumer);
        }
        MainWindow.addConsumerToWindow();
        for (int i = 0; i < this.consumersNumber; i++) {
            consumers.get(i).start();
        }
        ////////////////////////////////////////////////
        for (int i = 0; i < this.makersNumber; i++) {
            try {
                makers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < this.consumersNumber; i++) {
            try {
                consumers.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}