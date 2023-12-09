package fhnw.mada.huffman.tree;

public abstract class BasicNode implements Node {

    protected Node parent;

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getTopNode() {
        return parent != null ? parent.getTopNode() : this;
    }
}
