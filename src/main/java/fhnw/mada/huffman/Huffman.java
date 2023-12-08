package fhnw.mada.huffman;

import fhnw.mada.huffman.tree.ConnectionNode;
import fhnw.mada.huffman.tree.LetterNode;
import fhnw.mada.huffman.tree.Node;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Huffman {

    private Huffman() {
    }

    public static String generateCodeTable(String text) {
        Map<Integer, Long> table = text.chars()
            .boxed()
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        List<Node> nodes = table.keySet().stream()
            .map(l -> new LetterNode(l, table.get(l).intValue()))
            .collect(Collectors.toList());

        while (nodes.size() > 1) {
            Node child0 = findAndPopMin(nodes);
            Node child1 = findAndPopMin(nodes);
            Node parent = new ConnectionNode(child0, child1);
            nodes.add(parent);
            child0.setParent(parent);
            child1.setParent(parent);
        }

        return nodes.get(0).toString();
    }

    private static Node findAndPopMin(List<Node> nodes) {
        Node min = nodes.stream().min(Comparator.comparing(Node::getWeight))
            .orElseThrow(RuntimeException::new);

        nodes.remove(min);
        return min;
    }
}
