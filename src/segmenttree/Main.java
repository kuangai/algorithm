package segmenttree;

public class Main {
    public static void main(String[] args) {
        Integer [] test = {1,2,3,4,5};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(test, (a,b) -> a + b);

        System.out.println(segmentTree.getSize());
        System.out.println(segmentTree.toString());
    }
}
