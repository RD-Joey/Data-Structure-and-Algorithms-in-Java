package joey.DataStructures;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private int count;

    BinarySearchTree() {
    }

    public int size() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public void insert(Key key, Value value) {
        this.root = this.insert(this.root, key, value);
    }

    public boolean contains(Key key) {
        return this.contains(this.root, key);
    }

    public Value search(Key key) {
        return this.search(this.root, key);
    }

    public Key minimum() {
        if (this.count < 1) {
            throw new IndexOutOfBoundsException("NoElementInTheTree.");
        }
        return this.minimum(this.root).key;
    }

    public Key maximum() {
        if (this.count < 1) {
            throw new IndexOutOfBoundsException("NoElementInTheTree.");
        }
        return this.maximum(this.root).key;
    }

    public void removeMin() {
        if (this.root != null) {
            this.root = this.removeMin(root);
        }
    }

    public void removeMax() {
        if (this.root != null) {
            this.root = this.removeMax(root);
        }
    }

    public void remove(Key key) {
        this.root = this.remove(this.root, key);
    }

    private Node insert(Node root, Key key, Value value) {
        if (root == null) {
            this.count++;
            return new Node(key, value);
        }
        if (root.key.compareTo(key) < 0) {
            root.right = this.insert(root.right, key, value);
        } else if (root.key.compareTo(key) > 0) {
            root.left = this.insert(root.left, key, value);
        } else {
            root.value = value;
        }
        return root;
    }

    private boolean contains(Node root, Key key) {
        if (root == null) {
            return false;
        }
        if (root.key.compareTo(key) < 0) {
            return this.contains(root.right, key);
        } else if (root.key.compareTo(key) > 0) {
            return this.contains(root.left, key);
        } else {
            return true;
        }
    }

    private Value search(Node root, Key key) {
        if (root == null) {
            return null;
        }
        if (root.key.compareTo(key) < 0) {
            return this.search(root.right, key);
        } else if (root.key.compareTo(key) > 0) {
            return this.search(root.left, key);
        } else {
            return root.value;
        }
    }

    private Node minimum(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    private Node maximum(Node root) {
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }

    private Node removeMin(Node root) {
        if (root.left == null) {
            this.count--;
            return root.right;
        } else {
            root.left = removeMin(root.left);
            return root;
        }
    }

    private Node removeMax(Node root) {
        if (root.right == null) {
            this.count--;
            return root.left;
        } else {
            root.right = removeMin(root.right);
            return root;
        }
    }

    private Node remove(Node root, Key key) {
        if (root == null) {
            return null;
        }
        if (root.key.compareTo(key) < 0) {
            root.right = this.remove(root.right, key);
            return root;
        } else if (root.key.compareTo(key) > 0) {
            root.left = this.remove(root.left, key);
            return root;
        } else {
            if (root.left == null) {
                this.count--;
                return root.right;
            } else if (root.right == null) {
                this.count--;
                return root.left;
            } else {
                Node successor = this.minimum(root.right);
                successor.right = this.removeMin(root.right);
                successor.left = root.left;
                return successor;
            }
        }
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

    }
}
