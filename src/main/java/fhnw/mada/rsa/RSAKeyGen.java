package fhnw.mada.rsa;

import fhnw.mada.other.IO;
import java.math.BigInteger;
import java.util.Random;

public final class RSAKeyGen {

  private static final BigInteger ZERO = BigInteger.ZERO;
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
      d = RSAKeyGen.findD(e, phiN);
    }

    IO.writeFile("sk.txt", "(" + n + "," + d + ")");
    IO.writeFile("pk.txt", "(" + n + "," + e + ")");
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
