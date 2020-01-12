package queue;

public class Main {

    public static void main(String[] args) {

        int test = 1000000;
      /*  long t1 = System.currentTimeMillis();
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>(10);
        for (int i = 0; i < test; i++) {
            arrayQueue.enQueue(i);
            *//*System.out.println(arrayQueue);*//*
        }
        long t2 = System.currentTimeMillis();

        System.out.println("===================================================queue入队耗时："+ (t2-t1)+"ms");
        for (int i = 0; i < test; i++) {
            Integer front = arrayQueue.deQueue();
            *//*System.out.println(front);
            System.out.println(arrayQueue);*//*
        }*/
        long t3 = System.currentTimeMillis();

      //  System.out.println("===================================================queue 出队耗时："+(t3-t2)+"ms");

        LoopQueue<Integer> loopQueue = new LoopQueue<>(10);
        for (int i = 0; i < test; i++) {
            loopQueue.enQueue(i);
            /*System.out.println(loopQueue);*/
        }
        long t4 = System.currentTimeMillis();
        System.out.println("===================================================loopQueue入队耗时："+(t4-t3)+"ms");
        for (int i = 0; i < test; i++) {
            Integer front = loopQueue.deQueue();
            /*System.out.println(front);
            System.out.println(loopQueue);*/
        }
        long t5 = System.currentTimeMillis();
        System.out.println("===================================================loopQueue出队耗时："+(t5-t4)+"ms");
    }
}
