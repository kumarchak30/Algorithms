import java.util.*;

public class princesses {

    static class Node implements Comparable<Node> {
        int index;
        int distance;

        Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    static List<List<Node>> graph;
    static int[] distances;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            int u = input.nextInt();
            int v = input.nextInt();
            int w = input.nextInt();
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        distances = new int[n+1];
        Arrays.fill(distances, -1);
        dijkstra(1);
        for (int i = 2; i <= n; i++) {
            int distance = distances[i];
            if (distance == -1) {
                System.out.println(distance);
            } else {
                int reversedIndex = n - i + 1;
                int reversedDistance = distances[reversedIndex];
                if (reversedDistance == -1) {
                    System.out.println(distance);
                } else {
                    System.out.println(Math.min(distance, reversedDistance));
                }
            }
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int index = node.index;
            int distance = node.distance;
            if (distances[index] != -1) {
                continue;
            }
            distances[index] = distance;
            for (Node neighbor : graph.get(index)) {
                int neighborIndex = neighbor.index;
                int neighborDistance = neighbor.distance;
                if (distances[neighborIndex] == -1) {
                    queue.offer(new Node(neighborIndex, distance + neighborDistance));
                }
            }
        }
    }
}