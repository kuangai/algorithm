package map;

import java.util.ArrayList;
import java.util.List;

public class AVLTreeMap <K extends Comparable<K>, V> {

    public class Node{
        private K k;
        private V v;
        private Node left, right;
        private int height;

        public Node() {
        }

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTreeMap() {
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
     * @param k
     */
    public void put(K k, V v){
        root = add(root, k, v);
    }

    /**
     * 递归向树中添加元素
     * @param node
     * @param k
     */
    private Node add(Node node, K k, V v ){
        if (node == null){
            size ++;
            return new Node(k, v);
        }
        if (node.k.compareTo(k) > 0){
            node.left = add(node.left, k, v);
        }else if(node.k.compareTo(k) < 0){
            node.right = add(node.right,k, v);
        }else {
            node.v = v;
        }

        //从下一层返回到当前层，更新当前层 节点高度
        if (node.k.compareTo(k) == 0)
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //维持平衡
        node = balance(node);
        return node;
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node node){
        Node x = node.left;
        Node t3 = x.right;

        x.right = node;
        node.left = t3;

        // 更新节点高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node node){
        Node x = node.right;
        Node t2 = x.left;

        x.left = node;
        node.right = t2;

        // 更新节点高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }
    /**
     * 判断树中是否包含k
     * @param k
     * @return
     */
    public boolean containsKey(K k){
        return contains(root, k);
    }

    /**
     * 递归判断以node为根的树中是否包含元素e
     * @param node
     * @param k
     * @return
     */
    private boolean contains(Node node, K k){
        if (node == null)
            return false;
        if (node.k.compareTo(k) == 0)
            return true;
        if (node.k.compareTo(k) > 0){
            return contains(node.left,k);
        }else {
            return contains(node.right, k);
        }
    }

    /**
     * 删除元素e对应的节点
     * @param k
     */
    public V remove(K k){
        Node node = getNode(root, k);
        if (node == null)
            return null;
        root = remove(root, k);
        return node.v;
    }

    /**
     * 查询k 所在节点
     * @param node
     * @param k
     * @return
     */
    private Node getNode(Node node, K k) {
        if (node == null)
            return null;

        if (node.k .equals(k))
            return node;
        else if (node.k.compareTo(k) > 0)
            return getNode(node.left, k);
        else
            return getNode(node.right, k);
    }

    /**
     * 删除以node为根的树中值为k的节点，并返回删除后的树根节点
     * @param node
     * @param k
     * @return
     */
    private Node remove(Node node,K k){
        if (node == null)
            return null;
        if (node.k.compareTo(k) == 0){
            Node retNode;
            if (node.right == null){
                Node leftNode = node.left;
                node.left = null;
                retNode = leftNode;
            }else if (node.left == null){
                Node rightNode = node.right;
                node.right = null;
                retNode = rightNode;
            }else {
                //使用右子树中的最小值对应节点替换被删节点
                Node minOfRight = getMinNode(node);//查询node右孩子中的最小值对应的节点。
                minOfRight.right = remove(node.right, minOfRight.k);//删除node右孩子中最小节点并将删除后的子树挂接到被删除节点的右侧
                minOfRight.left = node.left;
                node.left = node.right = null;
                retNode = minOfRight;
            }
            size --;
            return balance(retNode);
        }else if(node.k.compareTo(k) > 0){
            return remove(node.left, k);
        }else {
            return remove(node.right, k);
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
        return null == node ? 0 : node.height;
    }

    // 查询节点平衡因子（左右子树高度差）
    private int getBalanceFactor(Node node){
        if (null == node)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 验证是不是二分搜索树
     * @return
     */
    public boolean isBST(){
        List<K> keys = new ArrayList<>();
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
    private void inOrder(Node node, List<K> keys){
        if (null == node)
            return;
        inOrder(node.left,keys);
        keys.add(node.k);
        inOrder(node.right, keys);
    }

    public V get(K k){
        Node node = getNode(root, k);
        if (node == null) return null;
        return node.v;
    }

    /**
     * 维持节点平衡
     * @param node
     * @return
     */
    private Node balance(Node node){
        //从下一层返回到当前层，更新当前层 节点高度
        int balanceFactor = getBalanceFactor(node);
        //维持平衡，四种情况 ll rr lr rl
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0) //ll
            return rightRotate(node);
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0) { //lr
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {//rl
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)// rr
            return leftRotate(node);
        return node;
    }
}
