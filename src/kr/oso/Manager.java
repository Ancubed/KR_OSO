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

    int getFileSize() {
        return this.s;
    }

    Manager(int n, int m, float k, float l, int s) {
        this.makersNumber = n;
        this.consumersNumber = m;
        this.k = k;
        this.l = l;
        this.s = s;
    }

    Manager() {
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
        MainWindow.addFileContentToWindow();
        int priority = 5;
        for (int i = 0; i < this.makersNumber; i++) {
            if (i % 3 == 0 && priority < 10) priority++;
            Maker newMaker = new Maker("Maker-" + (i + 1), i, this.k);
            newMaker.setPriority(priority);
            makers.add(newMaker);
        }
        MainWindow.addMakerToWindow();
        for (int i = 0; i < this.makersNumber; i++) {
            makers.get(i).start();
        }
        ////////////////////////////////////////////////
        priority = 5;
        for (int i = 0; i < this.consumersNumber; i++) {
            if (i % 2 == 0 && priority < 10) priority++;
            Consumer newConsumer = new Consumer("Consumer-" + (i + 1), i, this.k);
            newConsumer.setPriority(priority);
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
