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
}
