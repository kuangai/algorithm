package stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack();
        for (int i = 0; i < 10; i++)
            stack.push(i);
        System.out.println(stack);
        System.out.println();
        int pop = stack.pop();
        System.out.println(pop);
        System.out.println(stack);
        int see = stack.see();
        System.out.println(see);
        System.out.println(stack);

    }
}
