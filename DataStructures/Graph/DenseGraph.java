package joey.DataStructures.Graph;

import java.util.ArrayList;
import java.util.List;

public class DenseGraph implements Graph {
    private int vertices;
    private int edges;
    private boolean directed;
    private boolean[][] graph;

    public DenseGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        this.graph = new boolean[vertices][vertices];
    }

    @Override
    public int vertices() {
        return this.vertices;
    }

    @Override
    public int edges() {
        return this.edges;
    }

    @Override
    public void addEdge(int v, int w) throws IndexOutOfBoundsException {
        if (hasEdge(v, w)) {
            return;
        }
        this.graph[v][w] = true;
        if (!this.directed) {
            this.graph[w][v] = true;
        }
        this.edges++;
    }

    @Override
    public boolean hasEdge(int v, int w) throws IndexOutOfBoundsException {
        if ((v < 0 && v >= this.vertices) || (w < 0 && w >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        return this.graph[v][w];
    }

    @Override
    public Iterable<Integer> adj(int vertex) throws IndexOutOfBoundsException {
        if ((vertex < 0 && vertex >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        List<Integer> adjV = new ArrayList<>();
        for (int i = 0; i < this.vertices; i++) {
            if (this.graph[vertex][i]) {
                adjV.add(i);
            }
        }
        return adjV;
    }
}
