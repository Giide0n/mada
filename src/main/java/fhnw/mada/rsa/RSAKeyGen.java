package fhnw.mada.rsa;

import fhnw.mada.other.Exp;
import fhnw.mada.other.IO;
import java.math.BigInteger;
import java.util.Random;

public final class RSAKeyGen {

    private static final BigInteger ONE = BigInteger.ONE;

    private RSAKeyGen() {
    }

    public static void generateKeyPair() {
        Random rand = new Random();
        BigInteger p = BigInteger.probablePrime(2048, rand);
        BigInteger q = BigInteger.probablePrime(2048, rand);

        BigInteger n = p.multiply(q);
        BigInteger phiN = p.subtract(ONE).multiply(q.subtract(ONE));

        BigInteger e = null, d = null;
        while (d == null || BigInteger.valueOf(-1).equals(d)) {
            e = BigInteger.probablePrime(1024, rand);
            d = Exp.findD(e, phiN);
        }

        IO.writeFile("rsa/sk.txt", "(" + n + "," + d + ")");
        IO.writeFile("rsa/pk.txt", "(" + n + "," + e + ")");
    }

}
