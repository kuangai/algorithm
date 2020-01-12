package queue;

public interface Queue<E> {

    void enQueue(E e);

    E deQueue();

    boolean isEmpty();

    int getSize();

    E getFront();
}
