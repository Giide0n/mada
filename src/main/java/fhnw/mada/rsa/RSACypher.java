package fhnw.mada.rsa;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

public final class RSACypher {

  private static final BigInteger ZERO = BigInteger.ZERO;
  private static final BigInteger ONE = BigInteger.ONE;

  private RSACypher() {
  }

  public static String encrypt(String key, String text) {
    BigInteger n = new BigInteger(key.substring(1, key.indexOf(',')));
    BigInteger e = new BigInteger(key.substring(key.indexOf(',') + 1, key.indexOf(')')));

    char[] chars = text.toCharArray();
    return IntStream.range(0, chars.length)
        .mapToObj(i -> BigInteger.valueOf(chars[i]))
        .map(x -> fastExponentiation(x, e, n))
        .map(BigInteger::toString)
        .reduce((x, y) -> x + "," + y)
        .orElse("");
  }

  public static String decrypt(String key, String text) {
    BigInteger n = new BigInteger(key.substring(1, key.indexOf(',')));
    BigInteger d = new BigInteger(key.substring(key.indexOf(',') + 1, key.indexOf(')')));

    return Arrays.stream(text.replaceAll("\n", "").split(","))
        .map(BigInteger::new)
        .map(x -> fastExponentiation(x, d, n))
        .map(x -> String.valueOf((char) x.intValue()))
        .reduce((x, y) -> x + y)
        .orElse("");
  }

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
