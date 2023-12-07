package fhnw.mada;

import fhnw.mada.elgamal.Elgamal;
import fhnw.mada.other.IO;
import fhnw.mada.rsa.RSACypher;
import fhnw.mada.rsa.RSAKeyGen;
import java.math.BigInteger;

public class App {

    public static void main(String[] args) {
        // startRSA();
        startElgamal();
    }

    private static void startRSA() {
        // Generate a valid RSA Key Pair and save them into pk.txt and sk.txt
        RSAKeyGen.generateKeyPair();

        // Encrypt the file text.txt using the pk.txt key and save the result to chiffre.txt
        String encryptedText = RSACypher.encrypt(IO.readFile("rsa/pk.txt"),
            IO.readFile("rsa/text.txt"));
        IO.writeFile("rsa/chiffre.txt", encryptedText);

        // Decrypt the file chiffre.txt using the sk.txt and save the result to text-d.txt
        // text-d.txt should have the same content as text.txt if everything worked correctly
        String decryptedText = RSACypher.decrypt(IO.readFile("rsa/sk.txt"), encryptedText);
        IO.writeFile("rsa/text-d.txt", decryptedText);

        // Decrypt the provided file with the provided key and save the result to the file solution.txt
        String decryptedProvidedText = RSACypher.decrypt(
            IO.readFile("rsa/provided-sk.txt"),
            IO.readFile("rsa/provided-chiffre.txt")
        );
        IO.writeFile("rsa/rsa-solution.txt", decryptedProvidedText);
    }

    private static void startElgamal() {
        BigInteger b = Elgamal.generateAorB();
        BigInteger gb = Elgamal.calculateExp(b);

        String encryptedText = Elgamal.encrypt(IO.readFile("elgamal/text.txt"), gb);
        IO.writeFile("elgamal/chiffre.txt", encryptedText);

        String decryptedText = Elgamal.decrypt(encryptedText, b);
        IO.writeFile("elgamal/text-d.txt", decryptedText);

        /*
        String decryptedProvidedText = Elgamal.decrypt(
            IO.readFile("elgamal/provided-chiffre.txt"),
            new BigInteger(IO.readFile("elgamal/provided-sk.txt"))
        );
         */
    }
}
