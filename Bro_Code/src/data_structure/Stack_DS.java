package data_structure;

import java.util.Optional;
import java.util.Stack;

public class Stack_DS {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("Krishnan");
        stack.push("Suguna");
        stack.push("Surya");

        System.out.println(stack.search(stack.push("Sundhar")));

        stack.push("Kannathal");

        System.out.println(stack.peek());

        System.out.println(stack.size());

        System.out.println(stack);
        final int size = stack.size();

        for (int i = 0; i < size; i++) {
            System.out.println(stack.pop());
            if (stack.empty()){
                System.out.println(stack.push("Sundharesan"));
            }
        }
        System.out.println(stack);
    }
}
