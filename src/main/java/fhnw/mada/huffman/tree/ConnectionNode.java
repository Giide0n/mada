package fhnw.mada.huffman.tree;

public class ConnectionNode extends BasicNode {

    private final Node child0;
    private final Node child1;

    public ConnectionNode(Node child0, Node child1) {
        this.child0 = child0;
        this.child1 = child1;
    }

    @Override
    public boolean containsLetter(Integer letterCodePoint) {
        return child0.containsLetter(letterCodePoint) || child1.containsLetter(letterCodePoint);
    }

    @Override
    public String constructCode(Integer letterCodePoint) {
        if (child0.containsLetter(letterCodePoint)) {
            return "0" + child0.constructCode(letterCodePoint);
        }

        if (child1.containsLetter(letterCodePoint)) {
            return "1" + child1.constructCode(letterCodePoint);
        }

        throw new LetterNotFoundException();
    }

    @Override
    public Integer getWeight() {
        return child0.getWeight() + child1.getWeight();
    }

    @Override
    public String toString() {
        return child0.toString() + "-" + child1.toString();
    }
}
