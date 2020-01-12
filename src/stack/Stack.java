package stack;

public interface Stack<E> {

    void  push(E e);

    E pop();

    E see();

    int getSize();

    boolean isEmpty();


}
