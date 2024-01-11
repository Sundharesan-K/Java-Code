package data_structure;

import java.security.Permission;
import java.util.PriorityQueue;
import java.util.Queue;

public class Queue_DS {
    public static void main(String[] args) {
        Queue<Double> queue = new PriorityQueue<>();
        queue.offer(3.0);
        queue.offer(1.0);
        queue.offer(5.5);
        queue.offer(1.5);
        queue.offer(4.3);
        System.out.println(queue.poll());
        while (!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
