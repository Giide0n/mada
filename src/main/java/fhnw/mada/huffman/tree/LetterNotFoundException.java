package fhnw.mada.huffman.tree;

public final class LetterNotFoundException extends RuntimeException {

    public LetterNotFoundException() {
        super("The provided Letter is not part of this tree.");
    }
}
