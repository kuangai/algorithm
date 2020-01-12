package array;

public class Array <E> {

    protected Object[] data;
    protected int size;

    public Array(int cap){
        if(cap < 0){
            throw new IllegalArgumentException("cap is > 0");
        }
        data = new Object[cap];
        size = 0;
    }

    public Array(){
        this(10);
    }
    public void addLast(E e) {
        add(size,e);
    }

    public void addFirst(E e) {
        add(0, e);
    }



    public boolean isEmpty(){
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    public int getCap(){
        return data.length;
    }

    public E[] getData(){
        return (E[]) data;
    }

    void setData(int index,E e) {
        if(index < 0 || index >= size ){
            throw new IllegalArgumentException("Set fail, index is >= 0 || < size \n");
        }
        data[index] = e;
    }

    public E get(int index) {
        if(index < 0 || index > size - 1){
            throw new IllegalArgumentException("Set fail, index is >= 0 || < size");
        }
        return (E)data[index];
    }

    public E getLast(){
        return get(size-1);
    }

    public E getFirst(){
        return get(0);
    }

    public boolean contains(E e){
        for (int i = 0; i < size; i++){
            if (data[i] == e){
                return true;
            }
        }
        return false;
    }

    public int find(E e){
        for (int i = 0; i < size ; i++){
            if (data[i] == e){
                return i;
            }
        }
        return -1;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removelast(){
        return remove(size-1);
    }

    public boolean removeElement(E e){
        int index = find(e);
        if(index == -1){
            return false;
        }

        remove(index);
        return true;
    }

    public boolean removeAll(E e){
        int index = -1;
        boolean ressult = false;
        while( ( index = find(e) )!= -1){
            ressult = true;
            remove(index);
        }
        return ressult;
    }

    public void add(int index,E e){
        if(index < 0 ){
            throw new IllegalArgumentException("add fail, index must be >= 0");
        }

        if( index > data.length - 1){
            resize(data.length*2);
        }

        for (int i = size - 1; i >= index; i --){
            data[i+1] = data[i];
        }
        data[index]=e;
        size++;
    }

    public E remove(int index){
        if(index < 0 || index > size - 1){
            throw new IllegalArgumentException("Set fail, index is >= 0 || < size");
        }
        E result = (E)data[index];
        for (int i = index+1; i < size; i ++){
            data[i-1] = data[i];
        }
        size--;

        data[size] = null;
        return result;
    }

    private void resize(int cap){
        Object [] newArr = new Object[cap] ;
        for (int i = 0; i< size; i++)
            newArr[i] = data[i];
        data = newArr;
    }

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("Array size : [%d], cap :[%s] \n", size, data.length));
        stringBuffer.append("[");
        for (int i = 0 ; i < size; i++){
            stringBuffer.append(data[i]);
            if(i != size - 1){
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
