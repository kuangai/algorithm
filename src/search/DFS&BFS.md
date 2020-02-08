##dfs
深度优先搜索，类似树的前序遍历
> 可使用递归实现，或自己维护一个栈实现

模板
> visited = set();
dfs(Node node){
    if node in visited;
        return 
    visited.add(node);
    dfs(node.left);
    dfs(node.right);
    for(node : node.next){
        dfs(node)
    }
}
 
##bfs
广度优先搜索，类似树的层序遍历 可使用队列实现
> 文件夹的遍历，最短路径，就是常见的的bfs

模板
> bfs(Node node){
    queue = new queue(node);
    while(queue){
        first = queue.removeFirst();
        process(first);
        queue.addLast(first.next);
    }
}

