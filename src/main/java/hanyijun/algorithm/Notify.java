package hanyijun.algorithm;


/**
 * 启动两个线程, 一个输出 1,3,5,7…99, 另一个输出 2,4,6,8…100 最后 STDOUT 中按序输出 1,2,3,4,5…100
 */
public class Notify {
    private final Object flag = new Object();
    public static void main(String[] args) throws Exception {
        Notify notify = new Notify();
        ThreadA threadA = notify.new ThreadA();
        threadA.start();
        Thread.currentThread().sleep(1000);
        ThreadB threadB = notify.new ThreadB();
        threadB.start();
    }
    class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (flag) {
                for (int i = 2; i <= 100; i += 2) {
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    flag.notify();
                }
            }
        }
    }
    class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (flag) {
                for (int i = 1; i < 100; i += 2) {
                    System.out.println(i);
                    flag.notify();
                    try {
                        flag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
