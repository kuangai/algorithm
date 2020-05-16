package map;

import trie.FileOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

    private int a;

    class sss{
        private int a;


    }
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt",strings)){
           /*test1(strings);
           test2(strings);
           test3(strings);
           test4(strings);*/
           test5(strings);
        }
    }

    public static void test1(ArrayList<String> strings){
        long s1 = System.nanoTime();

        AVLTreeMap<String,Integer> avlTree = new AVLTreeMap<>();
        for (String s : strings){
            avlTree.put(s,avlTree.containsKey(s) ? avlTree.get(s) + 1: 1);
        }
        long s2 = System.nanoTime();
        System.out.println("avlTree:"+(s2 - s1) / 1000000000.0);
        System.out.println("avlTree size:"+ avlTree.getSize());
        System.out.println("is balance:" + avlTree.isBalance());
        System.out.println("is bst:" + avlTree.isBST());
    }

    public static void test2(ArrayList<String> strings){
        long s1 = System.nanoTime();

        HashMap<String,Integer> hashMap = new HashMap<>();
        for (String s : strings){
            hashMap.put(s,hashMap.containsKey(s) ? hashMap.get(s) + 1: 1);
        }
        long s2 = System.nanoTime();
        System.out.println("hashmap: "+(s2 - s1) / 1000000000.0);
        System.out.println("hashmap size: "+ hashMap.size());
    }



    public static void test3(ArrayList<String> strings){
        long s1 = System.nanoTime();

        TreeMap<String,Integer> treeMap = new TreeMap<>();
        for (String s : strings){
            treeMap.put(s,treeMap.containsKey(s) ? treeMap.get(s) + 1: 1);
        }
        long s2 = System.nanoTime();
        System.out.println("treeMap: "+(s2 - s1) / 1000000000.0);
        System.out.println("treeMap size: "+ treeMap.size());

    }

    public static void test4(ArrayList<String> strings){
        long s1 = System.nanoTime();

        BSTMap<String,Integer> bstMap = new BSTMap<>();
        for (String s : strings){
            bstMap.put(s,bstMap.containsKey(s) ? bstMap.get(s) + 1: 1);
        }
        long s2 = System.nanoTime();
        System.out.println("bstMap: "+(s2 - s1) / 1000000000.0);
        System.out.println("bstMap size: "+ bstMap.getSize());

    }

    public static void test5(ArrayList<String> strings){
        long s1 = System.nanoTime();

        AVLTreeMap<String,Integer> avlTree1 = new AVLTreeMap<>();
        avlTree1.put("1",1);
        System.out.println("--------------------"+avlTree1.getSize());
        avlTree1.remove("1");
        System.out.println("--------------------"+avlTree1.getSize());
    }



}
