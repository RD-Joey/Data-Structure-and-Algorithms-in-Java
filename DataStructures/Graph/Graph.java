package joey.DataStructures.Graph;

public interface Graph {
    public int vertices();

    public int edges();

    public void addEdge(int v, int w);

    public boolean hasEdge(int v, int w);

    public Iterable<Integer> adj(int vertex);
}
