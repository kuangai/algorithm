package queue;

import array.Array;

public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    /**
     * 入队
     * @param e
     */
    @Override
    public void enQueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队
     * @return
     */
    @Override
    public E deQueue() {
        if(isEmpty()){
            return null;
        }
        return array.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("queue size: ");
        stringBuilder.append(getSize());
        stringBuilder.append("\n");
        stringBuilder.append("queue: front [");
        for (int i = 0; i < getSize(); i++) {
            stringBuilder.append(array.get(i));
            if (i != getSize()-1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }
}
