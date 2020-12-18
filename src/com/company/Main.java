package com.company;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {
        // количество желаний продавца
        CountDownLatch uploader = new CountDownLatch(1);

        // количество желаний покупателей
        CountDownLatch downloader = new CountDownLatch(10);

        // semaphore - задаём три кассира
        Semaphore semaphore = new Semaphore(3, true);

        // сам загрузчик - создаётся
        Uploader aziz = new Uploader(uploader);
        // aziz - запускает метод run() при помощи метода start()
        aziz.start();

        // создаём 10 продавцов
        for (int i = 1; i < 11; i++) {
            new Downloader("Человек " + i, uploader, downloader, semaphore).start();
        }

        try {
            // await() сообщает об исполнения желания покупателей!
            downloader.await();
            System.out.println("Файл удалён из сервера");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /*
    Вариант А (Дэдлайн 9.12.2020 23:59)
a)  Написать многопоточное приложение, которое бы симулировало загрузку файлов на сервер.
 И их скачивание. Используя классы Semaphore и CountDownLatch.
  Либо можно использовать методы wait(), notify(), notifyAll().
b)  Uploader загружает 1 файл 500 мб на сервер. Скорость загрузки 20 мб в секунду.
 Нужно в консоли отобразить процесс загрузки симулируя через sleep().
c)  После того как весь файл загружен на сервер Downloaders начинают его скачивать со скоростью 100 мб в секунду.
Должны скачать файл 10 человек, одновременно могут скачивать не более 3х человек.
d)  После того как файл был скачан 10 раз, он удаляется с сервера
     */
}
