package com.example.service;

import com.example.controller.DemoController;

import javax.transaction.Synchronization;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoMain {

    public int a = 0;

    public void reference() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 10; i++) {
            WorkerThread worker = new DemoMain.WorkerThread("" + i);
            executor.submit(worker);
        }
    }


    public static void main(String[] args) throws InterruptedException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        DemoMain demoMain = new DemoMain();
        demoMain.Demo("");
        demoMain.reference();


        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;

        Constructor<?>[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
      Field field = EagerInitializedSingleton.class.getDeclaredField("instance");
        field.setAccessible(true);
        for (Constructor<?> constructor : constructors) {
            constructor.setAccessible(true);
            instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
        }

        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());


    }

    public class WorkerThread implements Runnable {

        private String task;

        public WorkerThread(String s) {
            this.task = s;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " Starting. Task = " + task);
            processCommand();
            System.out.println(Thread.currentThread().getName() + " Finished.");
            System.out.println(a);
        }

        private void processCommand() {
            try {
                a++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private  void Demo(String sender) throws InterruptedException {
        synchronized (sender) {
            for (int i = 1; i <= 10; i++) {
                a++;
            }
        }


    }



}

class EagerInitializedSingleton {

    private static  EagerInitializedSingleton instance;

    // Private constructor to avoid client applications to use constructor
    private EagerInitializedSingleton() {

    }

    public static EagerInitializedSingleton getInstance() {

        if (instance == null) {
            instance = new EagerInitializedSingleton();
        }
        return instance;
    }
}

