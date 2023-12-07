package fhnw.mada.other;

import java.math.BigInteger;

public class Exp {

    private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;

    public static BigInteger fastExponentiation(BigInteger x, BigInteger e, BigInteger m) {
        int i = 0;
        BigInteger h = ONE, k = x;
        while (i < e.bitLength()) {
            if (e.testBit(i)) {
                h = h.multiply(k).mod(m);
            }
            k = k.pow(2).mod(m);
            i++;
        }
        return h;
    }
}
