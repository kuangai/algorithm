package trie;

import java.util.ArrayList;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        System.out.println("pride and prejudice.txt");

        ArrayList<String> list = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt",list)){
            long start = System.nanoTime();

            TreeSet<String> treeSet = new TreeSet<>();
            for (String s : list)
                treeSet.add(s);

            for (String s : list)
                treeSet.contains(s);

            long end = System.nanoTime();
            System.out.println((end - start)/1000000000.0 + "s") ;

            start = System.nanoTime();

             Trie trie = new Trie();
            for (String s : list)
                trie.add(s);

            for (String s : list)
                trie.contains(s);

            end = System.nanoTime();
            System.out.println((end - start)/1000000000.0 + "s") ;
        }
    }
}
