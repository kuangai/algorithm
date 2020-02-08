package segmenttree;

import java.util.ArrayList;
import java.util.List;

public class SegmentTree<E> {

    private E [] date;
    private E [] tree;
    private Merger<E> merger;

    /**
     * 使用给定数组与合并器，构建线段树
     * 时间复杂度为O(logn)
     * @param arr
     * @param merger
     */
    public SegmentTree(E [] arr, Merger<E> merger) {
        this.merger = merger;
        this.date = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            date[i] = arr[i];

        this.tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, date.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int left, int right) {
        if (left == right){
            tree[treeIndex] = date[left];
            return;
        }

        int leftChildIndex = getLeftChildIndex(treeIndex);
        int rightChildIndex = getRightChildIndex(treeIndex);
        int middle = left + (right - left) / 2;
        buildSegmentTree(leftChildIndex, left, middle);
        buildSegmentTree(rightChildIndex, middle + 1, right);
        tree[treeIndex] = merger.merge(tree[leftChildIndex],tree[rightChildIndex]);
    }

    public int getSize(){
        return date.length;
    }

    /**
     * 查询左孩子索引
     * @param treeIndex
     * @return
     */
    private int getLeftChildIndex(int treeIndex){
        return treeIndex * 2 + 1;
    }

    /**
     * 查询右孩子索引
     * @param treeIndex
     * @return
     */
    private int getRightChildIndex(int treeIndex){
        return treeIndex * 2 + 2;
    }

    /**
     * 查询 区间[left,right]存储的值
     * @param left
     * @param right
     * @return
     */
    public E query(int left, int right){
        if (left > right || left < 0 || right < 0 || left >= date.length || right >= date.length)
            throw new IllegalArgumentException("参数非法");
        return query(0, 0, date.length -1, left, right);
    }

    /**
     * 在以treeIndex为根的树 （区间[l，r]）中查询 [left，right]区间的值。
     * @param treeIndex
     * @param l 查询范围区间左边界
     * @param r 查询范围区间右边界
     * @param left 目标区间左边界
     * @param right 目标区间右边界
     * @return
     */
    private E query(int treeIndex, int l, int r, int left, int right){
        if (l == r)
            return tree[r];

        int leftChildIndex = getLeftChildIndex(treeIndex);
        int rightChildIndex = getRightChildIndex(treeIndex);
        int middle = l + (r - l) / 2;
        if (left >= middle + 1)
            return query(rightChildIndex,middle + 1, r, left, right);
        else if (right <= middle)
            return query(leftChildIndex,l, middle, left, right);

        E leftE = query(leftChildIndex, l, middle, left, middle);
        E rightE = query(rightChildIndex,middle + 1, r, middle + 1, right);
        return merger.merge(leftE, rightE);
    }

    /**
     * 更新date[] 中下标为index 的值 为e，并更新树
     * @param index
     * @param e
     */
    public void set(int index, E e){
        if (index < 0 || index > date.length - 1)
            throw new IllegalArgumentException("参数非法 : index");
        date[index] = e;
        set(0, 0, date.length, index, e);
    }

    /**
     * 在以treeIndex [left,right]为根的树中更新index的值为e
     * left ，right 为 treeIndex的左右边界， 即 date[] 的下标[left，right]之间元素总和
     * @param treeIndex 树对应数组的下标
     * @param left 左边界
     * @param right 右边界
     * @param index date[]中待更新的索引
     * @param e
     */
    private void set(int treeIndex, int left, int right, int index, E e){
        if (left == right)
            tree[treeIndex] = e;

        int middle = left + (right - left) / 2;
        int leftChildIndex = getLeftChildIndex(treeIndex);
        int rightChildIndex = getRightChildIndex(treeIndex);

        if (index >= middle + 1)
            set(rightChildIndex, middle + 1, index, index, e );
        else // if (index <= middle)
            set(leftChildIndex, left, middle, index, e);

        merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    /**
     * 以树的样式打印线段树
     * 每一行打印树的一层，方便直观看出树的结构
     * @return
     */
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        List<E> temp = new ArrayList<>();
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                temp.add(tree[i]);
            else
                temp.add(null);
        }
        for (int i = 0; i < Math.log(tree.length/ Math.log(2)); i++) {
            StringBuilder tempStr = new StringBuilder();
            tempStr.append("[");
            for (int j = (int)Math.pow(2, i) - 1; j < Math.pow(2,i + 1) - 1; j++) {
                tempStr.append(temp.get(j));
                if (j != Math.pow(2,i + 1) - 2)
                    tempStr.append(",");
            }
            tempStr.append("] \n");
            stringBuilder.append(tempStr.toString());
        }
        
        return stringBuilder.toString();
    }

    // todo 区域更新
}
