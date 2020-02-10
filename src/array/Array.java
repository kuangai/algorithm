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

    /**
     * 在末端添加元素
     * @param e
     */
    public void addLast(E e) {
        add(size,e);
    }

    /**
     * 在首端添加元素
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 元素个数
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 数组长度
     * @return
     */
    public int getCap(){
        return data.length;
    }

    public E[] getData(){
        return (E[]) data;
    }

    /**
     * 更新
     * @param index
     * @param e
     */
    void set(int index,E e) {
        if(index < 0 || index >= size ){
            throw new IllegalArgumentException("Set fail, index is >= 0 || < size \n");
        }
        data[index] = e;
    }

    /**
     * 查询
     * @param index
     * @return
     */
    public E get(int index) {
        if(index < 0 || index > size - 1){
            throw new IllegalArgumentException("Set fail, index is >= 0 || < size");
        }
        return (E)data[index];
    }

    /**
     * 查询最后一个元素
     * @return
     */
    public E getLast(){
        return get(size-1);
    }

    /**
     * 查询第一个元素
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0; i < size; i++){
            if (data[i] == e){
                return true;
            }
        }
        return false;
    }

    /**
     * 查询元素下标
     * @param e
     * @return
     */
    public int findIndex(E e){
        for (int i = 0; i < size ; i++){
            if (data[i] == e){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除第一个元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除最后一个元素
     * @return
     */
    public E removelast(){
        return remove(size-1);
    }

    /**
     * 删除指定元素（第一个）
     * @param e
     * @return
     */
    public boolean removeElement(E e){
        int index = findIndex(e);
        if(index == -1){
            return false;
        }

        remove(index);
        return true;
    }

    /**
     * 删除指定元素e（所有的）
     * @param e
     * @return
     */
    public boolean removeAll(E e){
        int index = -1;
        boolean ressult = false;
        while( ( index = findIndex(e) )!= -1){
            ressult = true;
            remove(index);
        }
        return ressult;
    }

    /**
     * 添加
     * @param index
     * @param e
     */
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

    /**
     * 删除指定下标的元素
     * @param index
     * @return
     */
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

    /**
     * 扩容
     * @param cap
     */
    private void resize(int cap){
        Object [] newArr = new Object[cap] ;
        for (int i = 0; i< size; i++)
            newArr[i] = data[i];
        data = newArr;
    }

    public void swap(int i, int j){
        if (i == j) return;
        if (i < 0 || j < 0 || i >= size || j >= size) {
            throw new IllegalArgumentException("参数非法");
        }
        E temp = (E)data[i];
        data[i] = data[j];
        data[j] = temp;
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
