package fhnw.mada.huffman.tree;

public abstract class BasicNode implements Node {

    private Node parent;

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getTopNode() {
        return parent != null ? parent.getTopNode() : this;
    }
}
