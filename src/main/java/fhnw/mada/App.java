package fhnw.mada;

import fhnw.mada.elgamal.Elgamal;
import fhnw.mada.huffman.Huffman;
import fhnw.mada.other.IO;
import fhnw.mada.rsa.RSACypher;
import fhnw.mada.rsa.RSAKeyGen;
import java.math.BigInteger;

public class App {

    public static void main(String[] args) {
        startRSA();
        startElgamal();
        startHuffman();
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

        // Decrypt the provided file with the provided key and save the result to the file rsa-solution.txt
        String decryptedProvidedText = RSACypher.decrypt(
            IO.readFile("rsa/provided-sk.txt"),
            IO.readFile("rsa/provided-chiffre.txt")
        );
        IO.writeFile("rsa/rsa-solution.txt", decryptedProvidedText);
    }

    private static void startElgamal() {
        // Generate a random b and calculate g to the power of b
        BigInteger b = Elgamal.generateAorB();
        BigInteger gb = Elgamal.calculateExp(b);

        // Encrypt the file text.txt using the generated public key gb and save the result to chiffre.txt
        String encryptedText = Elgamal.encrypt(IO.readFile("elgamal/text.txt"), gb);
        IO.writeFile("elgamal/chiffre.txt", encryptedText);

        // Decrypt the file chiffre.txt using the generated private key b and save the result to text-d.txt
        // text-d.txt should have the same content as text.txt if everything worked correctly
        String decryptedText = Elgamal.decrypt(encryptedText, b);
        IO.writeFile("elgamal/text-d.txt", decryptedText);

        // Decrypt the provided file with the provided key and save the result to the file elgamal-solution.txt
        BigInteger providedSk = new BigInteger(IO.readFile("elgamal/provided-sk.txt").trim());
        String decryptedProvidedText = Elgamal.decrypt(
            IO.readFile("elgamal/provided-chiffre.txt"),
            providedSk
        );
        IO.writeFile("elgamal/elgamal-solution.txt", decryptedProvidedText);
    }

    private static void startHuffman() {
        // Read a text and generate a matching code table
        String text = IO.readFile("huffman/text.txt");
        String codeTable = Huffman.generateCodeTable(text);
        IO.writeFile("huffman/dec_tab.txt", codeTable);

        // encode the text using the generated code table
        Byte[] encodedText = Huffman.encode(text, codeTable);
        IO.writeByteArray("huffman/output.dat", encodedText);

        // decode the encoded text
        // the result should match the input file if everything went right
        String decryptedText = Huffman.decode(encodedText, codeTable);
        IO.writeFile("huffman/dec_text.txt", decryptedText);

        // decode the provided data using the provided code table
        String providedCodeTable = IO.readFile("huffman/provided_dec_tab-mada.txt");
        Byte[] providedEncodedText = IO.readByteArray("huffman/provided-output-mada.dat");
        String decodedProvidedText = Huffman.decode(providedEncodedText, providedCodeTable);
        IO.writeFile("huffman/huffman-solution.txt", decodedProvidedText);
    }
}
