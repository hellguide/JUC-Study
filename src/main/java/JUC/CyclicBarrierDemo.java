package JUC;

import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierDemo {


    public static void main(String[] args) {

        //栅栏，等所有线程都准备就绪之后，才开始跑🏃🏻‍♀️
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Runner r1 = new Runner(cyclicBarrier,"a");
        Runner r2 = new Runner(cyclicBarrier,"b");
        Runner r3 = new Runner(cyclicBarrier,"c");



        ExecutorService ex = Executors.newFixedThreadPool(3);
        ex.execute(r1);
        ex.execute(r2);
        ex.execute(r3);
        ex.execute(r4);
        ex.execute(r5);
        //启动有序关闭，执行先前提交的任务，但不接受新的任务。如果已关闭调用，则没有其他影响。
        ex.shutdown();







    }




    static class Runner implements Runnable{

        private CyclicBarrier cyclicBarrier ;
        private String name;


        public Runner(CyclicBarrier cyclicBarrier, String name) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
        }

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.name+"准备ok");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(name+"开跑。");


        }
    }

}
