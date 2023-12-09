package fhnw.mada.huffman;

import fhnw.mada.huffman.tree.ConnectionNode;
import fhnw.mada.huffman.tree.LetterNode;
import fhnw.mada.huffman.tree.Node;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static Byte[] encode(String text, String codeTable) {
        Map<Integer, String> table = Arrays.stream(codeTable.split("-"))
            .map(s -> s.split(":"))
            .collect(Collectors.toMap(a -> Integer.parseInt(a[0]), a -> a[1]));

        String bitString = text.chars()
            .boxed()
            .map(table::get)
            .collect(Collectors.joining());

        String extendedBitString = bitString + "1" + "0".repeat(8 - 1 - (bitString.length() % 8));
        Byte temp = (byte) Integer.parseInt(extendedBitString.split("(?<=\\G.{8})")[0], 2);

        return Arrays.stream(extendedBitString.split("(?<=\\G.{8})"))
            .map(s -> (byte) Integer.parseInt(s, 2))
            .toArray(Byte[]::new);
    }

    public static String decode(Byte[] encodedText, String codeTable) {
        Map<String, Integer> table = Arrays.stream(codeTable.split("-"))
            .map(s -> s.split(":"))
            .collect(Collectors.toMap(a -> a[1], a -> Integer.parseInt(a[0])));

        String extendedBitString = Arrays.stream(encodedText)
            .map(b -> String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'))
            .collect(Collectors.joining());

        String bitString = extendedBitString.substring(0, extendedBitString.lastIndexOf("1"));

        int i = 0;
        int j = 1;
        List<Integer> codePoints = new ArrayList<>();
        while (i < bitString.length() && j <= bitString.length()) {
            String possibleCode = bitString.substring(i, j);
            if (table.containsKey(possibleCode)) {
                codePoints.add(table.get(possibleCode));
                i = j;
            }
            j++;
        }

        return codePoints.stream()
            .map(Character::toString)
            .collect(Collectors.joining());
    }

    private static Node findAndPopMin(List<Node> nodes) {
        Node min = nodes.stream().min(Comparator.comparing(Node::getWeight))
            .orElseThrow(RuntimeException::new);

        nodes.remove(min);
        return min;
    }
}
