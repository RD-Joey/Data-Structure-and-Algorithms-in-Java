package joey.DataStructures.Graph;

import org.jetbrains.annotations.NotNull;

public interface WeightedGraph<Weight extends Number & Comparable<Weight>> {
    public int vertices();

    public int edges();

    public void addEdge(int v, int w, Weight weight);

    public boolean hasEdge(int v, int w);

    public Iterable<Edge<Weight>> adj(int vertex);

    class Edge<Weight extends Comparable<Weight>> implements Comparable<Edge<Weight>> {
        private int v, w;
        private Weight weight;

        public Edge(Edge<Weight> edge) {
            this.v = edge.v;
            this.w = edge.w;
            this.weight = edge.weight;
        }

        public Edge(int v, int w, Weight weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int getV() {
            return v;
        }

        public int getW() {
            return w;
        }

        public Weight getWeight() {
            return weight;
        }

        public int other(int x) {
            if (x != this.v && x != this.w) {
                throw new IllegalArgumentException("The vertex is not in this edge. ");
            }
            return x == this.v ? this.w : this.v;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v=" + v +
                    ", w=" + w +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public int compareTo(@NotNull WeightedGraph.Edge<Weight> o) {
            return this.weight.compareTo(o.weight);
        }
    }
}
