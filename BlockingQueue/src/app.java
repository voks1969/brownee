/**
 * Created by voks1969 on 3/24/2016.
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class app {
    static boolean gExit;
    static BlockingQueue q;
    public static void main(String[] args) {
        q = new BlockingQueue(10);
        gExit = false;

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                super.run();
                while(app.gExit==false) {
                    int val = 0;
                    try {
                        val = (int)q.dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println ("Thread1 --- Got value="+val);
                    //q.print("Thread1 ---");
                }
                System.out.println ("Thread1 --- exiting");
            }

        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                super.run();
                for(int ii=0;ii<100;ii++) {
                    System.out.println ("Thread2 --- adding val="+ii);
                    try {
                        q.enqueue((Object)ii);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //q.print("Thread2 ---");
                }
                gExit=true;
                System.out.println ("Thread2 --- exiting");
            }

        };

        thread1.start();
        thread2.start();
    }

    public static class BlockingQueue {

        private List queue = new LinkedList();
        private int  limit = 10;

        public BlockingQueue(int limit){
            this.limit = limit;
        }


        public synchronized void enqueue(Object item)
                throws InterruptedException  {
            while(this.queue.size() == this.limit) {
                wait();
            }
            if(this.queue.size() == 0) {
                notifyAll();
            }
            this.queue.add(item);
        }


        public synchronized Object dequeue()
                throws InterruptedException{
            while(this.queue.size() == 0){
                wait();
            }
            if(this.queue.size() == this.limit){
                notifyAll();
            }

            return this.queue.remove(0);
        }

        public void print(String prepend) {
            System.out.println (prepend + "BlockingQueue:");
            for(int ii=0;ii<this.queue.size();ii++)
                System.out.print (" "+this.queue.get(ii));
            System.out.print ("\n");
        }
    }
}

