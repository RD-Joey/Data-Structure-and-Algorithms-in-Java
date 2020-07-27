package joey.DataStructures.Graph;

import java.util.ArrayList;
import java.util.List;

public class DenseWeightedGraph<Weight extends Number & Comparable<Weight>> implements WeightedGraph<Weight> {
    private int vertices;
    private int edges;
    private boolean directed;
    private Edge<Weight>[][] graph;

    public DenseWeightedGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.edges = 0;
        this.directed = directed;
        this.graph = new Edge[vertices][vertices];
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
    public void addEdge(int v, int w, Weight weight) {
        if (hasEdge(v, w)) {
            this.edges--;
        }
        this.graph[v][w] = new Edge<Weight>(v, w, weight);
        if (v != w && !this.directed) {
            this.graph[w][v] = new Edge<Weight>(w, v, weight);
        }
        this.edges++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if ((v < 0 && v >= this.vertices) || (w < 0 && w >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        return this.graph[v][w] != null;
    }

    @Override
    public Iterable<Edge<Weight>> adj(int vertex) {
        if ((vertex < 0 && vertex >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        List<Edge<Weight>> adjV = new ArrayList<Edge<Weight>>();
        for (int i = 0; i < this.vertices; i++) {
            if (this.graph[vertex][i] != null) {
                adjV.add(this.graph[vertex][i]);
            }
        }
        return adjV;
    }
}
