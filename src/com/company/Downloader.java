package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloader extends Thread {
    // попытки продавца Азиза
    protected CountDownLatch uploader;
    // попытки покупателей, то есть Карлыгач
    protected CountDownLatch downloader;
    // семофор - это количество кассиров
    protected Semaphore semaphore;

    public Downloader(String name, CountDownLatch uploader, CountDownLatch downloader, Semaphore semaphore) {
        super(name);
        this.uploader = uploader;
        this.downloader = downloader;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            // await() - он сообщает, об исходе попыток (желаний)
            uploader.await();
            // acquire() - начало обязательств кассира
            semaphore.acquire();
            System.out.println(getName() + " начинает скачивать ");
            sleep(2000);
            System.out.println(getName() + " уже скачал");
            // downloader - тратит свои попытки (желания)
            downloader.countDown();
            // release() - конец обязательств кассира
            semaphore.release();

        } catch (Exception ignore) {
        }
    }
}
