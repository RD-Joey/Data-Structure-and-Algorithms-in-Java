package joey.Algorithms.Graph;

import joey.DataStructures.Graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSPath {
    private final String VERTEX_NOT_IN_GRAPH = "No such a vertex exists. ";
    private Graph graph;
    private int source;
    private boolean[] visited;
    private int[] from;

    public DFSPath(Graph graph, int source) {
        if (source < 0 || source >= graph.vertices()) {
            throw new IllegalArgumentException(VERTEX_NOT_IN_GRAPH);
        }
        this.graph = graph;
        this.source = source;
        this.visited = new boolean[graph.vertices()];
        this.from = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            from[i] = -1;
        }
        dfs(source);
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

    private void dfs(int vertex) {
        this.visited[vertex] = true;
        for (int nextVertex :
                this.graph.adj(vertex)) {
            if (!visited[nextVertex]) {
                this.from[nextVertex] = vertex;
                this.dfs(nextVertex);
            }
        }
    }

    public int getSource() {
        return source;
    }
}
