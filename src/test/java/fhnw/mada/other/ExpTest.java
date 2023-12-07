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

    public void testFindD1() {
        BigInteger expected = BigInteger.valueOf(-1);
        BigInteger actual = Exp.findD(
            BigInteger.valueOf(111),
            BigInteger.valueOf(300)
        );
        assertEquals(expected, actual);
    }

    public void testFindD2() {
        BigInteger expected = BigInteger.valueOf(91);
        BigInteger actual = Exp.findD(
            BigInteger.valueOf(19),
            BigInteger.valueOf(144)
        );
        assertEquals(expected, actual);
    }

    public void testFindD3() {
        BigInteger expected = BigInteger.valueOf(39);
        BigInteger actual = Exp.findD(
            BigInteger.valueOf(20),
            BigInteger.valueOf(41)
        );
        assertEquals(expected, actual);
    }

    public void testFindD4() {
        BigInteger expected = BigInteger.valueOf(2);
        BigInteger actual = Exp.findD(
            BigInteger.valueOf(20),
            BigInteger.valueOf(39)
        );
        assertEquals(expected, actual);
    }
}
