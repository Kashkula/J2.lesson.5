package com.company;

import java.util.concurrent.CountDownLatch;

public class Uploader extends Thread {
    protected CountDownLatch uploader;

    public Uploader(CountDownLatch uploader) {
        // Uploader() - это конструктор, который принимает в параметрах Countdownlatch
        this.uploader = uploader;
    }

    @Override
    public void run() {
        // run() - это метод, в котором прописывается дейтсвия в этом потоке
        try {
            System.out.println("Файл скачивается из сервера!");
            sleep(1500);
            System.out.println("Файл скачан из сервера!");
            uploader.countDown();

        } catch (Exception ignore) {
        }
    }
}
