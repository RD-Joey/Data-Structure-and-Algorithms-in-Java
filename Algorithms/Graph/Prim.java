package joey.Algorithms.Graph;

import joey.DataStructures.Heap.IndexMinHeap;
import joey.DataStructures.Graph.WeightedGraph;

import java.util.Iterator;
import java.util.LinkedList;

public class Prim<Weight extends Number & Comparable<Weight>> {
    private WeightedGraph graph;
    private IndexMinHeap<Weight> ipq;
    private WeightedGraph.Edge<Weight>[] edgeTo;
    private boolean[] marked;
    private LinkedList<WeightedGraph.Edge<Weight>> minimumSpanningTree;
    private Number treeWeight;

    public Prim(WeightedGraph graph) {
        this.graph = graph;
        this.ipq = new IndexMinHeap<Weight>(graph.vertices());
        this.edgeTo = new WeightedGraph.Edge[graph.vertices()];
        this.marked = new boolean[graph.vertices()];
        this.minimumSpanningTree = new LinkedList<>();

        this.visit(0);
        while (!ipq.isEmpty()) {
            int vertex = ipq.extractMinIndex();
            if (edgeTo[vertex] == null) {
                throw new NullPointerException("No edge to this vertex. ");
            }
            this.minimumSpanningTree.add(edgeTo[vertex]);
            this.visit(vertex);
        }
        this.treeWeight = minimumSpanningTree.get(0).getWeight();
        for (int i = 1; i < minimumSpanningTree.size(); i++) {
            this.treeWeight = this.treeWeight.doubleValue() + this.minimumSpanningTree.get(i).getWeight().doubleValue();
        }
    }
    public LinkedList<WeightedGraph.Edge<Weight>> getMinimumSpanningTree() {
        return minimumSpanningTree;
    }

    public Number getTreeWeight() {
        return treeWeight;
    }

    private void visit(int vertex) {
        if (this.marked[vertex]) {
            throw new IllegalArgumentException("This vertex has been marked. ");
        }
        this.marked[vertex] = true;

        Iterator iterator = this.graph.adj(vertex).iterator();
        while (iterator.hasNext()) {
            WeightedGraph.Edge<Weight> edge = (WeightedGraph.Edge<Weight>)iterator.next();
            int other = edge.other(vertex);
            if (!marked[other]) {
                if (edgeTo[other] == null) {
                    ipq.insert(other, edge.getWeight());
                    edgeTo[other] = edge;
                } else if (edge.getWeight().compareTo(edgeTo[other].getWeight()) < 0) {
                    edgeTo[other] = edge;
                    ipq.update(other, edge.getWeight());
                }
            }

        }
    }
}
