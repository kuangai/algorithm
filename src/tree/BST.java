package tree;

public class BST<E extends Comparable<E>> {

    public class Node{
        private E e;
        private Node left, right;

        public Node() {
        }

        public Node(E e) {
            this.e = e;
            left = new Node();
            right = new Node();
        }
    }

    private Node root;
    private int size;

    public BST() {
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
    public void add(E e){
        add(root, e);
    }

    /**
     * 递归向树中添加元素
     * @param node
     * @param e
     */
    private void add(Node node, E e){
        if (node == null){
            node = new Node(e);
            size ++;
        }
        if (node.e.compareTo(e) > 0){
            add(node.left,e);
        }else if(node.e.compareTo(e) < 0){
            add(node.right,e);
        }
    }

    /**
     * 判断树中是否包含e
     * @param e
     * @return
     */
    public boolean contains(E e){
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
            return contains(node.left,e);
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
     * 删除以node为根的树中值为e的节点，并返回删除后的树根节点
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node,E e){
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
     * 删除并返回树中最小元素
     * @return
     */
    public E removeMin(){
        E min = getMin();
        root = removeMin(root);
        return min;
    }
    /**
     * 删除以node为节点的树中的最小值对应节点，返回删除后树的根节点
     * @param node
     * @return
     */
    public Node removeMin(Node node){
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
     * 删除并返回树中最大元素
     * @return
     */
    public E removeMax(){
        E max = getMax();
        root = removeMax(root);
        return max;
    }

    /**
     * 删除以node为节点的树中的最大值对应节点，返回删除后树的根节点
     * @param node
     * @return
     */
    public Node removeMax(Node node){
        if (node == null)
            return null;
        if (node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        return removeMax(node.right);
    }

    /**
     * 递归查询以node为根节点的树中最大值对应节点
     * @param node
     * @return
     */
    public Node getMaxNode(Node node){
        if (node == null)
            return null;
        if (node.right == null)
            return node;
        else
            return getMaxNode(node.right);
    }

    /**
     * 查询最大值
     * @return
     */
    public E getMax(){
        if (size == 0){
            return null;
        }
        return getMaxNode(root).e;
    }

    /**
     * 递归查询以node为根节点的树中最小值对应节点
     * @param node
     * @return
     */
    public Node getMinNode(Node node){
        if (node == null)
            return null;
        if (node.left == null)
            return node;
        else
            return getMaxNode(node.left);
    }

    /**
     * 查询最小值
     * @return
     */
    public E getMin(){
        if (size == 0){
            return null;
        }
        return getMinNode(root).e;
    }

}
