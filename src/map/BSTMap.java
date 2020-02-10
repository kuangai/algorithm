package map;

import javax.management.NotificationEmitter;

public class BSTMap<K extends Comparable<K>, V> {

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

    public BSTMap() {
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
    private void add(K k, V v){
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
            node.right = add(node.right,k,v);
        }
        return node;
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
            return node;
        if (node.k.compareTo(k) == 0){
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
        if (null == node)
            return 0;
        return Math.max(getHeight(node.right ), getHeight(node.left));
    }

    public void put(K k, V v){
        Node node = getNode(root, k);
        if (node == null) add(k, v);
        else node.v = v;
    }

    public V get(K k){
        Node node = getNode(root, k);
        if (node == null) return null;
        return node.v;
    }
}
