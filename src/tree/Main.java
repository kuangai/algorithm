package tree;

import map.BSTMap;
import trie.FileOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt",strings)){
            AVLTree<String,Integer> avlTree = new AVLTree<>();
            Map<String,Integer>  hashMap = new HashMap<>();
            for (String s : strings){
                hashMap.put(s,hashMap.containsKey(s) ? hashMap.get(s) + 1: 1);
                avlTree.put(s,avlTree.containsKey(s) ? avlTree.get(s) + 1: 1);
            }
            System.out.println("avl:" + avlTree.getSize());
            System.out.println("hashmap:" + hashMap.size());
            System.out.println("isbalance:" + avlTree.isBalance());
            System.out.println("isBST:" + avlTree.isBST());




        }
    }
}
