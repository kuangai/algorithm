package linkedlist;

public class Main {
    public static void main(String[] args) {
        int opcount = 100000;
        System.out.println("add spent :"+ testLinkedListAdd(opcount) + " ms");// O(n)
        System.out.println("addfirst spent :"+ testLinkedListAddFirst(opcount)+" ms");//O(1)
        System.out.println("addlast spent :"+ testLinkedListAddLast(opcount)+ " ms");//O(1)

        /*LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i <10; i++) {
            linkedList.addLast(i);
            System.out.println(linkedList);
        }*/

    }
    static long testLinkedListAdd( int opCount){
        LinkedList<Integer> linkedList = new LinkedList<>();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < opCount; i++) {
            linkedList.add(i,i);
        }
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }

    static long testLinkedListAddFirst( int opCount){
        LinkedList<Integer> linkedList = new LinkedList<>();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < opCount; i++) {
            linkedList.addFirst(i);
        }
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }

    static long testLinkedListAddLast(int opCount){
        LinkedList<Integer> linkedList = new LinkedList<>();
        long t1 = System.currentTimeMillis();
        for (int i = 0; i < opCount; i++) {
            linkedList.addLast(i);
        }
        long t2 = System.currentTimeMillis();
        return t2-t1;
    }
}
