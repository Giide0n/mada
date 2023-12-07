package fhnw.mada.other;

import java.math.BigInteger;

public class Exp {

    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger ZERO = BigInteger.ZERO;

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

    public static BigInteger findD(BigInteger e, BigInteger m) {
        BigInteger a = e, b = m;
        BigInteger x0 = ONE, y0 = ZERO, x1 = ZERO, y1 = ONE;
        BigInteger q, r;

        while (ZERO.compareTo(b) < 0) {
            q = a.divide(b);
            r = a.mod(b);

            BigInteger tx0 = x0, ty0 = y0;
            x0 = x1;
            y0 = y1;
            x1 = tx0.subtract(q.multiply(x1));
            y1 = ty0.subtract(q.multiply(y1));

            a = b;
            b = r;
        }

        if (ZERO.compareTo(x0) > 0) {
            x0 = x0.add(m);
        }
        return ONE.equals(a) ? x0 : BigInteger.valueOf(-1);
    }
}
