package joey.Algorithms.Graph;

import joey.DataStructures.Graph.Graph;

import java.util.Iterator;

public class Component {
    private final String VERTEX_NOT_IN_GRAPH = "No such a vertex exists. ";
    private Graph graph;
    private boolean visited[];
    private int componentCount;
    private int[] id;


    public Component(Graph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.vertices()];
        this.componentCount = 0;
        this.id = new int[graph.vertices()];
        for (int i = 0; i < graph.vertices(); i++) {
            if (!this.visited[i]) {
                this.componentCount++;
                this.dfs(i);
            }
        }
    }

    public int getComponentCount() {
        return componentCount;
    }

    public boolean isConnected(int v, int w) {
        if (v < 0 || v >= this.graph.vertices() || w < 0 || w >= this.graph.vertices()) {
            throw new IllegalArgumentException(VERTEX_NOT_IN_GRAPH);
        }
        return id[v] == id[w];
    }

    private void dfs(int vertex) {
        this.visited[vertex] = true;
        this.id[vertex] = this.componentCount;
        Iterator<Integer> iterator = this.graph.adj(vertex).iterator();
        while (iterator.hasNext()) {
            int nextVertex = iterator.next();
            if (!visited[nextVertex]) {
                dfs(nextVertex);
            }
        }
    }
}
