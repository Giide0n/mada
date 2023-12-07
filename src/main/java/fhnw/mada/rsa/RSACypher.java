package fhnw.mada.rsa;

import fhnw.mada.other.Exp;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

public final class RSACypher {

    private RSACypher() {
    }

    public static String encrypt(String key, String text) {
        BigInteger n = new BigInteger(key.substring(1, key.indexOf(',')));
        BigInteger e = new BigInteger(key.substring(key.indexOf(',') + 1, key.indexOf(')')));

        char[] chars = text.toCharArray();
        return IntStream.range(0, chars.length)
            .mapToObj(i -> BigInteger.valueOf(chars[i]))
            .map(x -> Exp.fastExponentiation(x, e, n))
            .map(BigInteger::toString)
            .reduce((x, y) -> x + "," + y)
            .orElse("");
    }

    public static String decrypt(String key, String text) {
        BigInteger n = new BigInteger(key.substring(1, key.indexOf(',')));
        BigInteger d = new BigInteger(key.substring(key.indexOf(',') + 1, key.indexOf(')')));

        return Arrays.stream(text.replaceAll("\n", "").split(","))
            .map(BigInteger::new)
            .map(x -> Exp.fastExponentiation(x, d, n))
            .map(x -> String.valueOf((char) x.intValue()))
            .reduce((x, y) -> x + y)
            .orElse("");
    }

}
