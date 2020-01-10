package array;

public class Array <E> {

    private E[] data;
    private int size;

    public Array(int cap){
        if(cap < 0){
            throw new IllegalArgumentException("cap is > 0");
        }
        data = (E [])new Object[cap];
        size = cap;
    }

    public Array(){
        this(10);
    }
    public void addLast(E e) {
        add(size,e);
    }

    public void add(int index,E e){
        if(index < 0 || index > data.length - 1){
            throw new IllegalArgumentException("addFirst fail, com.xxx.Array is full");
        }
        for (int i = size - 1; i >= index; i --){
            data[i+1] = data[i];
        }
        data[index]=e;
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("com.xxx.Array size : [%d], cap :[%s]", size, data.length));
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
        return data;
    }

    void setData(int index,E e) {
        if(index < 0 || index >= size ){
            throw new IllegalArgumentException("Set fail, index is >= 0 || < size \n");
        }
        data[index] = e;
    }

    E get(int index) {
        if(index < 0 || index > size - 1){
            throw new IllegalArgumentException("Set fail, index is >= 0 || < size");
        }
        return data[index];
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



    public E remove(int index){
        if(index < 0 || index > size - 1){
            throw new IllegalArgumentException("Set fail, index is >= 0 || < size");
        }
        E result = data[index];
        for (int i = index+1; i < size; i ++){
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;//loitering objects != memory leak;
        return result;
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


}
