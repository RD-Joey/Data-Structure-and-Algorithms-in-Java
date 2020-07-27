package joey.DataStructures.Graph;

import java.util.LinkedList;

public class SparseWeightedGraph<Weight extends Number & Comparable<Weight>> implements WeightedGraph<Weight> {
    private int vertices;
    private int edges;
    private boolean directed;
    private LinkedList<Edge<Weight>>[] graph;

    public SparseWeightedGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.directed = directed;
        this.graph = new LinkedList[vertices];
        for (int i = 0; i < graph.length; i++) {
            this.graph[i] = new LinkedList<Edge<Weight>>();
        }
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
    public void addEdge(int v, int w, Weight weight) throws IndexOutOfBoundsException {
        if ((v < 0 && v >= this.vertices) || (w < 0 && w >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        this.graph[v].add(new Edge<>(v, w, weight));
        this.edges++;
        if (v == w && directed) {
            return;
        }
        this.graph[w].add(new Edge<>(w, v, weight));
    }

    @Override
    public boolean hasEdge(int v, int w) throws IndexOutOfBoundsException {
        if ((v < 0 && v >= this.vertices) || (w < 0 && w >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        for (int i = 0; i < this.graph[v].size(); i++) {
            if (w == this.graph[v].get(i).other(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<Edge<Weight>> adj(int vertex) {
        if ((vertex < 0 && vertex >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        return this.graph[vertex];
    }
}
