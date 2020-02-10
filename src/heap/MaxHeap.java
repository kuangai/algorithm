package heap;

import array.Array;

public class MaxHeap<E extends Comparable<E>> {

    private Array<E> date;

    public MaxHeap() {
        this.date = new Array<>();
    }

    public MaxHeap(int capacity) {
        this.date = new Array<>(capacity);
    }

    public MaxHeap(E [] arr) {
        this.date = date;
    }

    private int parent(int index){
        return (index - 1) / 2;
    }

    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }


    private void siftDown(int index){
        if (index >= date.getSize() - 1)
            return;
        int left = leftChild(index);
        int right = rightChild(index);

        if(date.get(index).compareTo(date.get(left)) < 0){
            date.swap(index, left);
            siftDown(left);
        }else {
            if(date.get(index).compareTo(date.get(right)) < 0){
                //swap
                date.swap(index, right);
                siftDown(right);
            }else
                return;
        }

    }
}
