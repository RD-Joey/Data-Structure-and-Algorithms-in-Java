package joey.Algorithms.Graph;

import joey.DataStructures.Graph.Graph;

import java.util.*;

public class BFSPath {
    private final String VERTEX_NOT_IN_GRAPH = "No such a vertex exists. ";
    private Graph graph;
    private int source;
    private int[] from;
    private boolean[] visited;
    private int[] order;

    public BFSPath(Graph graph, int source) {
        if (source < 0 || source >= graph.vertices()) {
            throw new IllegalArgumentException(VERTEX_NOT_IN_GRAPH);
        }
        this.graph = graph;
        this.source = source;
        this.from = new int[graph.vertices()];
        this.visited = new boolean[graph.vertices()];
        this.order = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            this.from[i] = -1;
            this.order[i] = -1;
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(source);
        this.visited[source] = true;
        this.order[source] = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int nextVertex :
                    graph.adj(vertex)) {
                if (!this.visited[nextVertex]) {
                    queue.add(nextVertex);
                    this.visited[nextVertex] = true;
                    this.order[nextVertex] = this.order[vertex] + 1;
                    this.from[nextVertex] = vertex;
                }
            }
        }
    }

    public boolean hasPath(int destination) {
        if (destination < 0 || destination >= this.graph.vertices()) {
            throw new IllegalArgumentException(VERTEX_NOT_IN_GRAPH);
        }
        return visited[destination];
    }

    public List<Integer> path(int destination) {
        if (destination < 0 || destination >= this.graph.vertices()) {
            throw new IllegalArgumentException(VERTEX_NOT_IN_GRAPH);
        }
        Stack<Integer> stack = new Stack<Integer>();
        int backTrace = destination;
        while (backTrace != -1) {
            stack.push(backTrace);
            backTrace = this.from[backTrace];
        }
        List<Integer> ret = new ArrayList<Integer>(stack.size());
        while (!stack.isEmpty()) {
            ret.add(stack.pop());
        }
        return ret;
    }

    public void showPath(int destination) {
        List<Integer> path = this.path(destination);
        for (int vertex = 0; vertex < path.size(); vertex++) {
            System.out.print(path.get(vertex));
            if (vertex == path.size() - 1) {
                System.out.println();
            } else {
                System.out.print(" -> ");
            }
        }
    }

    public int length(int destination) {
        if (destination < 0 || destination >= this.graph.vertices()) {
            throw new IllegalArgumentException(VERTEX_NOT_IN_GRAPH);
        }
        return this.order[destination];
    }

    public int getSource() {
        return source;
    }
}
