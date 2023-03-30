package JUC;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {


    //场景：张三和李四，一起分工解决。终于，当他们两个都做完了自己所需要做的任务之后，领导才可以答复客户，客户也就消气了
    public static void main(String[] args) throws InterruptedException {
        //等待为2：张三、李四
        CountDownLatch cdl = new CountDownLatch(2);
        Work w1 = new Work("张三",1000,cdl);
        Work w2 = new Work("李四",1000,cdl);
        w1.start();
        w2.start();
        //阻塞线程，直到CountDownLatch 为0，激活线程
        cdl.await();
        System.out.println("bug解决完毕！！！");
        System.out.println("领导检验，完成的非常好，上线。");





    }



    static class Work extends Thread{
        String name;
        long workTime;
        CountDownLatch countDownLatch;

        public Work(String name, long workTime, CountDownLatch latch) {
            this.name = name;
            this.workTime = workTime;
            this.countDownLatch = latch;
        }

        /**
         * If this thread was constructed using a separate
         * <code>Runnable</code> run object, then that
         * <code>Runnable</code> object's <code>run</code> method is called;
         * otherwise, this method does nothing and returns.
         * <p>
         * Subclasses of <code>Thread</code> should override this method.
         *
         * @see #start()
         * @see #stop()
         * @see #Thread(ThreadGroup, Runnable, String)
         */
        @Override
        public void run() {
            System.out.println(name+"开始修复bug");
            try {
                //模拟工作时间
                sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+"修复bug结束");
            countDownLatch.countDown();
        }
    }




}
