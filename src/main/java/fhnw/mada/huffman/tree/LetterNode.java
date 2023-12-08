package fhnw.mada.huffman.tree;

public class LetterNode extends BasicNode {

    private final Integer letterCodePoint;
    private final Integer weight;

    public LetterNode(Integer letterCodePoint, Integer weight) {
        this.letterCodePoint = letterCodePoint;
        this.weight = weight;
    }

    @Override
    public boolean containsLetter(Integer letterCodePoint) {
        return this.letterCodePoint.equals(letterCodePoint);
    }

    @Override
    public String constructCode(Integer letterCodePoint) {
        return "";
    }

    @Override
    public Integer getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LetterNode)) {
            return false;
        }
        LetterNode node = (LetterNode) obj;

        return letterCodePoint.equals(node.letterCodePoint);
    }

    @Override
    public String toString() {
        return letterCodePoint + ":" + getTopNode().constructCode(letterCodePoint);
    }
}
