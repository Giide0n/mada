package fhnw.mada.rsa;

import java.math.BigInteger;
import junit.framework.TestCase;

public class RSAKeyGenTest extends TestCase {

  public void testFindD1() {
    BigInteger expected = BigInteger.valueOf(-1);
    BigInteger actual = RSAKeyGen.findD(
        BigInteger.valueOf(111),
        BigInteger.valueOf(300)
    );
    assertEquals(expected, actual);
  }

  public void testFindD2() {
    BigInteger expected = BigInteger.valueOf(91);
    BigInteger actual = RSAKeyGen.findD(
        BigInteger.valueOf(19),
        BigInteger.valueOf(144)
    );
    assertEquals(expected, actual);
  }

  public void testFindD3() {
    BigInteger expected = BigInteger.valueOf(39);
    BigInteger actual = RSAKeyGen.findD(
        BigInteger.valueOf(20),
        BigInteger.valueOf(41)
    );
    assertEquals(expected, actual);
  }

  public void testFindD4() {
    BigInteger expected = BigInteger.valueOf(2);
    BigInteger actual = RSAKeyGen.findD(
        BigInteger.valueOf(20),
        BigInteger.valueOf(39)
    );
    assertEquals(expected, actual);
  }
}
