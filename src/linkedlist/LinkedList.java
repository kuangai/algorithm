package linkedlist;

public class LinkedList<E> {

    private class Node<E>{
        private Node<E> next;
        private E e;

        public Node(Node next, E e) {
            this.next = next;
            this.e = e;
        }

        public E getE() {
            return e;
        }
    }

    private Node<E> dummyHead;//虚拟头节点,不存储元素，next指向第一个节点
    private Node<E> tail;//尾节点，即最后一个元素
    private int size;//长度

    public LinkedList() {
        this.dummyHead = new Node(null,null);
        this.tail = null;
        this.size = 0;
    }

    /**
     * 链表长度
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 链表是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 在头部添加
     * @param e
     */
    public void addFirst(E e){
        add(0,e);
    }

    /**
     * 在尾部添加
     * @param e
     */
    public void addLast(E e){
        if (isEmpty()){
            addFirst(e);
        }else {
            tail.next = new Node(null,e);
            tail = tail.next;
            size ++;
        }
    }

    /**
     * 在指定位置添加元素，该位置及其后面的元素向后移
     * @param index
     * @param e
     */
    public void add (int index,E e){
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index illgal");
        }
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = new Node(pre.next,e);
        if(index == size){
            tail = pre.next;
        }
        size++;
    }

    public boolean contains(E e){
        if (isEmpty())
            return false;
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e == e)
                return true;
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index){
        if(index < -1 || index >= size)
            return null;
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node<E> delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;

        if (size == index + 1){
            tail = pre;
        }
        if(index == 0){
            tail = null;
        }
        size--;
        return delNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    public E get(int index){
        if(index < -1 || index >= size)
            throw new IllegalArgumentException("index illegal");
        Node<E> cur = dummyHead;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public void set(int index,E e){
        if(index < -1 || index >= size)
            throw new IllegalArgumentException("index illegal");
        Node<E> cur = dummyHead;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public LinkedList reverse(){
        LinkedList linkedList1 = new LinkedList();

        Node<E> cur = dummyHead.next;
        while(cur != null){
            linkedList1.addFirst(cur.e);
            cur = cur.next;
        }
       /* while (true){
            E e = this.removeFirst();
            if (e != null){
                linkedList1.addFirst(e);
            }else {
                break;
            }
        }*/
         return linkedList1;
    }

    public LinkedList reverse1(){
        LinkedList linkedList1 = new LinkedList();

        while (true){
            E e = this.removeFirst();
            if (e != null){
                linkedList1.addFirst(e);
            }else {
                break;
            }
        }
        return linkedList1;
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("linkedList : size :["+getSize()+"]\n");
        stringBuilder.append("dummyHead [");
        Node cur = dummyHead;
        while (cur.next != null){
            cur = cur.next;
            stringBuilder.append(cur.e);
            if (cur.next != null){
                stringBuilder.append("-->");
            }
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }
}
