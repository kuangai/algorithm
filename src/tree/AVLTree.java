package tree;

import java.util.ArrayList;
import java.util.List;

public class AVLTree <E extends Comparable<E>> {

    public class Node{
        private E e;
        private Node left, right;
        private int height;

        public Node() {
        }

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return getSize() == 0;
    }

    /**
     * 添加
     * @param e
     */
    private void add(E e){
        root = add(root, e);
    }

    /**
     * 递归向树中添加元素
     * @param node
     * @param e
     */
    private Node add(Node node, E e ){
        if (node == null){
            size ++;
            return new Node(e);
        }
        if (node.e.compareTo(e) > 0){
            node.left = add(node.left, e);
        }else if(node.e.compareTo(e) < 0){
            node.right = add(node.right, e);
        }

        if (node.e.compareTo(e) != 0)
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        System.out.println("balanceFactor: " + getBalanceFactor(node));
        return node;
    }

    /**
     * 判断树中是否包含k
     * @param e
     * @return
     */
    public boolean containsKey(E e){
        return contains(root, e);
    }

    /**
     * 递归判断以node为根的树中是否包含元素e
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node node, E e){
        if (node == null)
            return false;
        if (node.e.compareTo(e) == 0)
            return true;
        if (node.e.compareTo(e) > 0){
            return contains(node.left, e);
        }else {
            return contains(node.right, e);
        }
    }

    /**
     * 删除元素e对应的节点
     * @param e
     */
    public void remove(E e){
        root = remove(root, e);
    }

    /**
     * 查询k 所在节点
     * @param node
     * @param e
     * @return
     */
    private Node getNode(Node node, E e) {
        if (node == null)
            return null;

        if (node.e.equals(e))
            return node;
        else if (node.e.compareTo(e) > 0)
            return getNode(node.left, e);
        else
            return getNode(node.right, e);
    }

    /**
     * 删除以node为根的树中值为k的节点，并返回删除后的树根节点
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e){
        if (node == null)
            return node;
        if (node.e.compareTo(e) == 0){
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                return leftNode;
            }
            if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                return rightNode;
            }

            //使用右子树中的最小值对应节点替换被删节点
            Node minOfRight = getMinNode(node);//查询node右孩子中的最小值对应的节点。
            minOfRight.right = removeMin(node.right);//删除node右孩子中最小节点并将删除后的子树挂接到被删除节点的右侧
            minOfRight.left = node.left;
            return minOfRight;
        }else if(node.e.compareTo(e) > 0){
            return remove(node.left, e);
        }else {
            return remove(node.right, e);
        }
    }

    /**
     * 删除以node为节点的树中的最小值对应节点，返回删除后树的根节点
     * @param node
     * @return
     */
    private Node removeMin(Node node){
        if (node == null)
            return null;
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }
        return removeMin(node.left);
    }

    /**
     * 递归查询以node为根节点的树中最小值对应节点
     * @param node
     * @return
     */
    private Node getMinNode(Node node){
        if (node == null)
            return null;
        if (node.left == null)
            return node;
        return getMinNode(node.left);
    }

    /**
     * 查询节点高度
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if (null == node)
            return 0;
        return Math.max(getHeight(node.right ), getHeight(node.left));
    }

    // 查询节点平衡因子（左右子树高度差）
    private int getBalanceFactor(Node node){
        if (null == node)
            return 0;
        return Math.abs(getHeight(node.left) - getHeight(node.right));
    }

    /**
     * 验证是不是二分搜索树
     * @return
     */
    public boolean isBST(){
        List<E> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i).compareTo(keys.get(i - 1)) < 0)
                return false;
        }
        return true;
    }

    public boolean isBalance(){
        return isBalance(root);
    }

    /**
     * node节点及其子树是否平衡
     * @param node
     * @return
     */
    private boolean isBalance(Node node){
        if (node == null) return true;
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1)
            return false;
        return isBalance(node.left) && isBalance(node.right);
    }

    /**
     * 中序遍历
     * @param node
     * @param keys
     */
    private void inOrder(Node node, List<E> keys){
        if (null == node)
            return;
        inOrder(node.left,keys);
        keys.add(node.e);
        inOrder(node.right, keys);
    }

}
