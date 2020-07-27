package joey.DataStructures.Graph;

import joey.DataStructures.Graph.Graph;

import java.util.LinkedList;

public class SparseGraph implements Graph {
    private int vertices;
    private int edges;
    private boolean directed;
    private LinkedList<Integer>[] graph;

    public SparseGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.directed = directed;
        this.graph = new LinkedList[vertices];
        for (int i = 0; i < graph.length; i++) {
            this.graph[i] = new LinkedList<Integer>();
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
    public void addEdge(int v, int w) throws IndexOutOfBoundsException {
        if ((v < 0 && v >= this.vertices) || (w < 0 && w >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        this.graph[v].add(w);
        this.edges++;
        if (v == w && directed) {
            return;
        }
        this.graph[w].add(v);
    }

    @Override
    public boolean hasEdge(int v, int w) throws IndexOutOfBoundsException {
        if ((v < 0 && v >= this.vertices) || (w < 0 && w >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        for (int i = 0; i < this.graph[v].size(); i++) {
            if (w == this.graph[v].get(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterable<Integer> adj(int vertex) {
        if ((vertex < 0 && vertex >= this.vertices)) {
            throw new IndexOutOfBoundsException("No such vertex exists. ");
        }
        return this.graph[vertex];
    }
}
