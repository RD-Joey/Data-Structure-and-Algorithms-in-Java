package joey.Algorithms.Graph;

import joey.DataStructures.Heap.MinHeap;
import joey.DataStructures.UnionFind;
import joey.DataStructures.Graph.WeightedGraph;

import java.util.LinkedList;

public class Kruskal<Weight extends Number & Comparable<Weight>> {
    private LinkedList<WeightedGraph.Edge<Weight>> minimumSpanningTree;
    private Number treeWeight;

    public Kruskal(WeightedGraph graph) {
        this.minimumSpanningTree = new LinkedList<>();
        MinHeap<WeightedGraph.Edge<Weight>> pq = new MinHeap<>(graph.edges());
        for (int vertex = 0; vertex < graph.vertices(); vertex++) {
            for (Object item :
                    graph.adj(vertex)) {
                WeightedGraph.Edge<Weight> edge = (WeightedGraph.Edge<Weight>) item;
                if (edge.getV() < edge.getW()) {
                    pq.insert(edge);
                }
            }
        }
        UnionFind unionFind = new UnionFind(graph.vertices());
        while (!pq.isEmpty()) {
            WeightedGraph.Edge<Weight> edge = pq.pop();
            if (unionFind.isConnected(edge.getV(), edge.getW())) {
                continue;
            }
            this.minimumSpanningTree.add(edge);
            unionFind.union(edge.getV(), edge.getW());
        }
        this.treeWeight = this.minimumSpanningTree.get(0).getWeight();
        for (int i = 0; i < this.minimumSpanningTree.size(); i++) {
            treeWeight = this.treeWeight.doubleValue() + this.minimumSpanningTree.get(i).getWeight().doubleValue();
        }
    }

    public LinkedList<WeightedGraph.Edge<Weight>> getMinimumSpanningTree() {
        return minimumSpanningTree;
    }

    public Number getTreeWeight() {
        return treeWeight;
    }
}
