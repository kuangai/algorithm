package unionfind;

public class UnionFind implements UF{

    private int [] parent;
    private int size;
    private int [] rank;

    public UnionFind(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        if (isConnected(p,q))
            return ;

        int qRoot = find(q);
        int pRoot = find(p);
        if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }else if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }

    /**
     * 查询 p 对应的集合的根，
     * @param p
     * @return
     */
    private int find(int p){
        if (p >= parent.length || p == 0){
            throw new IllegalArgumentException("p is out of size");
        }
        while (parent[p] != p){
            parent[p] = parent[parent[p]];// 路径压缩，加速寻找根元素
            p = parent[p];
        }
        return p;
    }

    /**
     * 递归寻找p 所在集合的根，并将树高度一次性压缩为2
     * @param p
     * @return
     */
    private int findRecur(int p){
        if (p >= parent.length || p == 0){
            throw new IllegalArgumentException("p is out of size");
        }

        if (p != parent[p])
            parent[p] = find(parent[p]);// 一次性将树压缩为高度为 2
        return parent[p];
    }
}
