package hanyijun.algorithm;


/**
 * 启动两个线程, 一个输出 1,3,5,7…99, 另一个输出 2,4,6,8…100 最后 STDOUT 中按序输出 1,2,3,4,5…100
 */
public class Notify {
    private final Object lock = new Object();
    private boolean isWait = false;

    public static void main(String[] args) throws Exception {
        Notify notify = new Notify();
        ThreadB threadB = notify.new ThreadB();
        threadB.start();
        ThreadA threadA = notify.new ThreadA();
        threadA.start();
    }

    class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                for (int i = 2; i <= 100; i += 2) {
                    try {
                        isWait = true;
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    lock.notify();
                }
            }
        }
    }

    class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                //为了确保ThreadA已经进入wait状态
                while (!isWait) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 1; i < 100; i += 2) {
                    System.out.println(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
