package stack;

import array.Array;

public class ArrayStack<E> implements Stack<E> {
    private Array<E> array;

    public ArrayStack(int cap){
        array = new Array<>(cap);
    }

    public ArrayStack(){
        this(4);
    }

    @Override
    public void push(E o) {
        array.addLast(o);
    }

    /**
     * 从栈顶去除一个元素并返回
     * @return
     */
    @Override
    public E pop() {
        return (E)array.removelast();
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 查看栈顶元素但不取出
     * @return
     */
    @Override
    public E see() {
        return (E)array.getLast();
    }

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.format("stack  size: [%s]\n",array.getSize()));
        stringBuffer.append("[");
        for (int i = 0 ; i < array.getSize(); i++){
            stringBuffer.append(array.get(i));
            if(i != array.getSize() - 1){
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
