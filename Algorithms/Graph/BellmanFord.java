package joey.Algorithms.Graph;

import joey.DataStructures.Graph.WeightedGraph;

import java.util.LinkedList;
import java.util.Stack;

public class BellmanFord<Weight extends Number & Comparable<Weight>> {
    private final String VERTEX_NOT_IN_GRAPH = "No such a vertex exists. ";
    private WeightedGraph graph;
    private int source;
    private Number[] distTo;
    private boolean hasNegativeCycle;
    private WeightedGraph.Edge<Weight>[] from;

    public BellmanFord(WeightedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.distTo = new Number[graph.vertices()];
        this.from = new WeightedGraph.Edge[graph.vertices()];

        this.distTo[source] = 0.0;
        this.from[source] = new WeightedGraph.Edge<Weight>(source, source, (Weight)(Number)(0.0));
        for (int step = 1; step < graph.vertices(); step++) {
            for (int vertex = 0; vertex < graph.vertices(); vertex++) {
                if (this.from[vertex] == null) {
                    continue;
                }
                for (Object item :
                        graph.adj(vertex)) {
                    WeightedGraph.Edge<Weight> edge = (WeightedGraph.Edge<Weight>) item;
                    if (this.from[edge.getW()] == null
                            || this.distTo[edge.getV()].doubleValue() + edge.getWeight().doubleValue()
                            < this.distTo[edge.getW()].doubleValue()) {
                        this.distTo[edge.getW()] = this.distTo[edge.getV()].doubleValue()
                                + edge.getWeight().doubleValue();
                        this.from[edge.getW()] = edge;
                    }
                }
            }
        }
        this.hasNegativeCycle = this.detectNegativeCycle();
    }

    public boolean hasNegativeCycle() {
        return this.hasNegativeCycle;
    }

    public Number minimumWeightTo(int destination) {
        if (!this.hasPathTo(destination)) {
            throw new IllegalArgumentException("No path from source to destination. ");
        }
        if (this.hasNegativeCycle) {
            throw new IllegalStateException("The minimum weight is negative infinite due to negative cycles. ");
        }
        return this.distTo[destination];
    }

    public boolean hasPathTo(int destination) {
        if (destination < 0 || destination >= this.graph.vertices()) {
            throw new IllegalArgumentException(VERTEX_NOT_IN_GRAPH);
        }
        return this.from[destination] != null;
    }

    public LinkedList<WeightedGraph.Edge<Weight>> getShortestPathTo(int destination) {
        if (!this.hasPathTo(destination)) {
            throw new IllegalArgumentException("No path from source to destination. ");
        }
        if (this.hasNegativeCycle) {
            throw new IllegalStateException("The minimum weight is negative infinite due to negative cycles. ");
        }
        Stack<WeightedGraph.Edge<Weight>> stack = new Stack<>();
        WeightedGraph.Edge<Weight> edge = this.from[destination];
        while (edge.getW() != this.source) {
            stack.push(edge);
            edge = this.from[edge.getV()];
        }

        LinkedList<WeightedGraph.Edge<Weight>> path = new LinkedList<>();
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }
        return path;
    }

    public void showPathTo(int destination) {
        LinkedList<WeightedGraph.Edge<Weight>> path = this.getShortestPathTo(destination);
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i).getV() + " -> ");
            if (i == path.size() - 1) {
                System.out.println(path.getLast().getW());
            }
        }
    }

    private boolean detectNegativeCycle() {
        for (int vertex = 0; vertex < this.graph.vertices(); vertex++) {
            if (this.from[vertex] == null) {
                continue;
            }
            for (Object item :
                    this.graph.adj(vertex)) {
                WeightedGraph.Edge<Weight> edge = (WeightedGraph.Edge<Weight>) item;
                if (this.distTo[edge.getV()].doubleValue() + edge.getWeight().doubleValue()
                        < this.distTo[edge.getW()].doubleValue()) {
                    return true;
                }
            }
        }
        return false;
    }
}
