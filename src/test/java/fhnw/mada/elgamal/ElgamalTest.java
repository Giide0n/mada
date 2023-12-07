package fhnw.mada.elgamal;

import java.math.BigInteger;
import junit.framework.TestCase;

public class ElgamalTest extends TestCase {

    public void testGeneratePublicKey1() {
        BigInteger b = BigInteger.valueOf(2);

        BigInteger expected = BigInteger.valueOf(4);
        BigInteger actual = Elgamal.calculateExp(b);

        assertEquals(expected, actual);
    }

    public void testDecryptLetter() {
        String chiffre = "(10, 6)";
        BigInteger privateKey = BigInteger.valueOf(3);
        BigInteger m = BigInteger.valueOf(13);

        BigInteger expected = BigInteger.valueOf(7);
        BigInteger actual = Elgamal.decryptLetter(chiffre, privateKey, m);

        assertEquals(expected, actual);
    }
}
