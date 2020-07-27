package joey.DataStructures;

public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
        }
    }

    public int find(int i) throws IndexOutOfBoundsException {
        if(i < 0 || i >= this.parent.length) {
            throw new IndexOutOfBoundsException("No such element exists.");
        }

//        if(i != this.parent[i]) this.parent[i] = this.find(parent[i]);
//        return this.parent[i];

        while (i != this.parent[i]) {
            this.parent[i] = this.parent[this.parent[i]];
            i = this.parent[i];
        }
        return i;
    }

    public boolean isConnected(int i, int j) throws IndexOutOfBoundsException {
        return this.find(i) == this.find(j);
    }

    public void union(int i, int j) throws IndexOutOfBoundsException {
        int iRoot = this.find(i);
        int jRoot = this.find(j);
        if(iRoot == jRoot) {
            return;
        }

        if(this.rank[iRoot] < this.rank[jRoot]) {
            this.parent[iRoot] = j;
        } else if(this.rank[jRoot] < this.rank[iRoot]) {
            this.parent[jRoot] = i;
        } else {
            this.parent[iRoot] = j;
            this.rank[jRoot]++;
        }
    }
}
