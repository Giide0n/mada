package fhnw.mada.huffman.tree;

public interface Node {

    boolean containsLetter(Integer letterCodePoint);

    String constructCode(Integer letterCodePoint);

    Integer getWeight();

    Node getTopNode();

    void setParent(Node node);
}
