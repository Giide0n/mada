package fhnw.mada.rsa;

import java.math.BigInteger;
import junit.framework.TestCase;

public class RSACypherTest extends TestCase {

  public void testFastExponentiation1() {
    BigInteger expected = BigInteger.ONE;
    BigInteger actual = RSACypher.fastExponentiation(
        BigInteger.valueOf(9),
        BigInteger.valueOf(25),
        BigInteger.valueOf(11)
    );
    assertEquals(expected, actual);
  }

  public void testFastExponentiation2() {
    BigInteger expected = BigInteger.valueOf(5);
    BigInteger actual = RSACypher.fastExponentiation(
        BigInteger.valueOf(5),
        BigInteger.valueOf(36),
        BigInteger.valueOf(11)
    );
    assertEquals(expected, actual);
  }

  public void testFastExponentiation3() {
    BigInteger expected = BigInteger.valueOf(16);
    BigInteger actual = RSACypher.fastExponentiation(
        BigInteger.valueOf(8),
        BigInteger.valueOf(20),
        BigInteger.valueOf(30)
    );
    assertEquals(expected, actual);
  }

  public void testEncrypt1() {
    String expected = "62";
    String actual = RSACypher.encrypt("(77,13)", "\006");
    assertEquals(expected, actual);
  }

  public void testEncrypt2() {
    String expected = "32";
    String actual = RSACypher.encrypt("(77,23)", "A");
    assertEquals(expected, actual);
  }

  public void testEncrypt3() {
    String expected = "18,32,76,76";
    String actual = RSACypher.encrypt("(77,23)", "HALL");
    assertEquals(expected, actual);
  }

  public void testDecrypt1() {
    String expected = "A";
    String actual = RSACypher.decrypt("(77,47)", "32");
    assertEquals(expected, actual);
  }

  public void testDecrypt2() {
    String expected = "HALL";
    String actual = RSACypher.decrypt("(77,47)", "18,32,76,76");
    assertEquals(expected, actual);
  }
}
