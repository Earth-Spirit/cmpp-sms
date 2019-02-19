package com.iwhalecloud.nmyd.cmppsms;

public class Test {




    public static void main(String[] args) {



        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread 1 startd");

                try{

                    while (true){
                        Thread.sleep(3* 1000);
                        System.out.println("sleep ....");
                    }

                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }
        });

        thread1.start();

        try{
            Thread.sleep(10 * 1000);

        }catch (InterruptedException e){

        }
        thread1.interrupt();

        System.out.println("thread1 = " + thread1.isInterrupted());
    }
}
