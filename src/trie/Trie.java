package trie;

import java.util.TreeMap;

/**
 * 字典树基本实现
 */
public class Trie {
    public class Node {
        boolean isWord; // 当前节点是否是一个单词

        //数据量级较小可以是使用数组，HashMap会更快速
        TreeMap<Character,Node> next; // 当前节点为前缀的下一层节点映射

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private int size;
    private Node root;

    public Trie() {
        this.size = 0;
        this.root = new Node();
    }

    public int getSize(){
        return size;
    }

    /**
     * 向trie树中添加新的单词
     * O(n)
     * @param word
     */
    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
        if(!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    /**
     * 递归向trie 树中添加 一个单词
     * @param word
     */
    public void addRecur(String word){
        add(root, word, 0);
    }

    private void add(Node node, String word, int index){
        //终止条件
        if(index == word.length()){
            if (!node.isWord){
                node.isWord = true;
                size ++;
            }
            return;
        }
        //处理当前层逻辑
        char c = word.charAt(index);
        if (!node.next.containsKey(word.charAt(index)))
            node.next.put(c, new Node());
        // 进入下一层处理
        add(node.next.get(c), word, index + 1);
    }

    /**
     * 是否包含一个单词
     * @param word
     * @return
     */
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * 是否包含以prefix为前缀的字符
     * @param prefix
     * @return
     */
    public boolean isPrefix(String prefix){
        return isPrefix(root, prefix, 0);
    }

    /**
     * 查询在以Node为根的Trie中是否有单词以prefix为前缀, 递归算法
     * @param node
     * @param word
     * @param index
     * @return
     */
    private boolean isPrefix(Node node, String word, int index){
        if (index == word.length())
            return true;
        char c = word.charAt(index);
        if (node.next.get(c) == null)
            return false;
        return isPrefix(node.next.get(c), word, index + 1);
    }

    /**
     * 移除一个单词
     * @param word
     */
    public void remove(String word){
        remove(root, word, 0);
    }

    /**
     * 在以Node为根的Trie中删除单词word
     * @param node
     * @param word
     * @param index
     * @return
     */
    private boolean remove(Node node, String word, int index){
        if(index == word.length()){
            if(!node.isWord )
                return false;
            node.isWord = false;
            size --;
            return true;
        }
        char c = word.charAt(index);
        if(!node.next.containsKey(c)){
            return false;
        }
        // 自底向上删除，所以优先处理下层逻辑，
        boolean result = remove(node.next.get(c), word, index + 1);

        // 递归向下逻辑处理结束后，处理当前层遗留逻辑。
        Node nextNode = node.next.get(c); // c节点对应的下一级引用
        if (!node.isWord && nextNode.next.size() == 0){ // c节点无孩子节点且自身不是单词。将c从上级节点next映射中删除
            node.next.remove(word.charAt(c));
        }
        return result;
    }
}
