package joey.Algorithms.Graph;

import joey.DataStructures.Heap.IndexMinHeap;
import joey.DataStructures.Graph.WeightedGraph;

import java.util.LinkedList;
import java.util.Stack;

public class Dijkstra<Weight extends Number & Comparable<Weight>> {
    private final String VERTEX_NOT_IN_GRAPH = "No such a vertex exists. ";
    private WeightedGraph graph;
    private int source;
    private Number[] distTo;
    private boolean[] marked;
    private WeightedGraph.Edge<Weight>[] from;

    public Dijkstra(WeightedGraph graph, int source) {
        this.graph = graph;
        this.source = source;
        this.distTo = new Number[graph.vertices()];
        this.marked = new boolean[graph.vertices()];
        this.from = new WeightedGraph.Edge[graph.vertices()];

        IndexMinHeap<Weight> ipq = new IndexMinHeap<>(graph.vertices());
        this.distTo[source] = 0.0;
        from[source] = new WeightedGraph.Edge<>(source, source, (Weight)(Number)(0.0));
        ipq.insert(source, (Weight)this.distTo[source]);
        this.marked[source] = true;
        while (!ipq.isEmpty()) {
            int vertex = ipq.extractMinIndex();
            this.marked[vertex] = true;
            for (Object item :
                    graph.adj(vertex)) {
                WeightedGraph.Edge<Weight> edge = (WeightedGraph.Edge<Weight>) item;
                int other = edge.other(vertex);
                if (!this.marked[other]) {
                    if( from[other] == null
                            || distTo[vertex].doubleValue() + edge.getWeight().doubleValue() < distTo[other].doubleValue() ){
                        distTo[other] = distTo[vertex].doubleValue() + edge.getWeight().doubleValue();
                        from[other] = edge;
                        if( ipq.contains(other) )
                            ipq.update(other, (Weight)distTo[other] );
                        else
                            ipq.insert(other, (Weight)distTo[other] );
                    }
                }
            }
        }
    }

    public Number minimumWeightTo(int destination) {
        if (!hasPathTo(destination)) {
            throw new IllegalArgumentException("No path from source to destination. ");
        }
        return distTo[destination];
    }

    public boolean hasPathTo(int destination) {
        if (destination < 0 || destination >= this.graph.vertices()) {
            throw new IllegalArgumentException(VERTEX_NOT_IN_GRAPH);
        }
        return marked[destination];
    }

    public LinkedList<WeightedGraph.Edge<Weight>> getShortestPathTo(int destination) {
        if (!this.hasPathTo(destination)) {
            throw new IllegalArgumentException("No path from source to destination. ");
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
}
