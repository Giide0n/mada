package fhnw.mada.elgamal;

import fhnw.mada.other.Exp;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public final class Elgamal {

    private static final BigInteger ONE = BigInteger.ONE;

    private static final BigInteger n = new BigInteger(
        "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1"
            + "29024E088A67CC74020BBEA63B139B22514A08798E3404DD"
            + "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245"
            + "E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED"
            + "EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3D"
            + "C2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F"
            + "83655D23DCA3AD961C62F356208552BB9ED529077096966D"
            + "670C354E4ABC9804F1746C08CA18217C32905E462E36CE3B"
            + "E39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9"
            + "DE2BCBF6955817183995497CEA956AE515D2261898FA0510"
            + "15728E5A8AACAA68FFFFFFFFFFFFFFFF", 16);

    private static final BigInteger g = BigInteger.valueOf(2);

    private Elgamal() {
    }

    public static BigInteger generateAorB() {
        BigInteger max = n.subtract(ONE);

        Random random = new Random();
        BigInteger result;
        do {
            result = new BigInteger(max.bitLength(), random);
        } while (result.compareTo(max) >= 0);

        return result;
    }

    public static BigInteger calculateExp(BigInteger aOrB) {
        return Exp.fastExponentiation(g, aOrB, n);
    }

    public static String encrypt(String text, BigInteger publicKey) {
        return text.chars().mapToObj(BigInteger::valueOf).map(l -> encryptLetter(l, publicKey))
            .reduce((a, b) -> a + ";" + b).orElse("");
    }

    private static String encryptLetter(BigInteger letter, BigInteger publicKey) {
        BigInteger a = generateAorB();
        BigInteger y1 = calculateExp(a);
        BigInteger y2 = Exp.fastExponentiation(publicKey, a, n).multiply(letter).mod(n);

        return "(" + y1 + "," + y2 + ")";
    }

    public static String decrypt(String chiffre, BigInteger privateKey) {
        return Arrays.stream(chiffre.split(";"))
            .map(l -> decryptLetter(l, privateKey, n))
            .map(x -> String.valueOf((char) x.intValue()))
            .reduce((a, b) -> a + b)
            .orElse("");
    }

    public static BigInteger decryptLetter(String encryptedLetter, BigInteger privateKey,
        BigInteger m) {
        String input = encryptedLetter.replaceAll("[()]", "");
        String[] numbers = input.split(",");

        BigInteger y1 = new BigInteger(numbers[0].trim());
        BigInteger y2 = new BigInteger(numbers[1].trim());

        return y2.multiply(Exp.findD(Exp.fastExponentiation(y1, privateKey, m), m)).mod(m);
    }
}
