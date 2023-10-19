package fhnw.mada;

import fhnw.mada.other.IO;
import fhnw.mada.rsa.RSACypher;
import fhnw.mada.rsa.RSAKeyGen;

public class App {

  public static void main(String[] args) {
    // Generate a valid RSA Key Pair and save them into pk.txt and sk.txt
    RSAKeyGen.generateKeyPair();

    // Encrypt the file text.txt using the pk.txt key and save the result to chiffre.txt
    String encryptedText = RSACypher.encrypt(IO.readFile("pk.txt"), IO.readFile("text.txt"));
    IO.writeFile("chiffre.txt", encryptedText);

    // Decrypt the file chiffre.txt using the sk.txt and save the result to text-d.txt
    // text-d.txt should have the same content as text.txt if everything worked correctly
    String decryptedText = RSACypher.decrypt(IO.readFile("sk.txt"), encryptedText);
    IO.writeFile("text-d.txt", decryptedText);

    // Decrypt the provided file with the provided key and save the result to the file solution.txt
    String decryptedProvidedText = RSACypher.decrypt(
        IO.readFile("provided-sk.txt"),
        IO.readFile("provided-chiffre.txt")
    );
    IO.writeFile("solution.txt", decryptedProvidedText);
  }
}
