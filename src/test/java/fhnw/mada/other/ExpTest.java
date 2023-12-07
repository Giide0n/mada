package fhnw.mada.other;

import java.math.BigInteger;
import junit.framework.TestCase;

public class ExpTest extends TestCase {

    public void testFastExponentiation1() {
        BigInteger expected = BigInteger.ONE;
        BigInteger actual = Exp.fastExponentiation(
            BigInteger.valueOf(9),
            BigInteger.valueOf(25),
            BigInteger.valueOf(11)
        );
        assertEquals(expected, actual);
    }

    public void testFastExponentiation2() {
        BigInteger expected = BigInteger.valueOf(5);
        BigInteger actual = Exp.fastExponentiation(
            BigInteger.valueOf(5),
            BigInteger.valueOf(36),
            BigInteger.valueOf(11)
        );
        assertEquals(expected, actual);
    }

    public void testFastExponentiation3() {
        BigInteger expected = BigInteger.valueOf(16);
        BigInteger actual = Exp.fastExponentiation(
            BigInteger.valueOf(8),
            BigInteger.valueOf(20),
            BigInteger.valueOf(30)
        );
        assertEquals(expected, actual);
    }

}
