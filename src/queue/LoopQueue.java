package queue;

public class LoopQueue<E> implements Queue<E> {

    /**
     * 队列中元素
     */
    private E[] data;

    /**
     * 队首下标，初始为0
     */
    private int front;

    /**
     * 队列尾部元素的下一个位置的下标，初始为0
     */
    private int tail;

    /**
     * 队列中元素个数
     */
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity+1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    @Override
    public void enQueue(E e) {
        if((tail + 1)% data.length == front){
            resize(2* data.length + 1);
        }
        data[tail] = e;
        tail ++;
    }

    @Override
    public E deQueue() {

        if(isEmpty()){
            return null;
        }

        E result = data[front];
        front ++;
        if (getSize() == data.length/4){
            resize(data.length /2 + 1);
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return tail == front;
    }

    @Override
    public int getSize() {
        if (isEmpty()){
            return 0;
        }
        return front < tail ? tail - front : data.length - front + tail;
    }

    @Override
    public E getFront() {
        return isEmpty() ? null : data[front];
    }

    private void resize(int newCapacity){

        E [] newData = (E[])new Object[newCapacity];
        for (int i = 0 ; i < data.length ; i++) {
            if(tail == (i + front) % data.length){
                tail = i;
                break;
            }
            newData[i] = data[(i + front) % data.length];
        }
        front = 0;
        data = newData;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("LoopQueue : size: ");
        stringBuilder.append(getSize());
        stringBuilder.append("\n");
        stringBuilder.append("[");
        for (int i = front; i%data.length != tail; i++) {
            stringBuilder.append(data[i]);
            if (i % data.length != tail - 1){
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
