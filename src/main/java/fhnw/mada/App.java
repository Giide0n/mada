package fhnw.mada;

import fhnw.mada.other.IO;
import fhnw.mada.rsa.RSAKeyGen;

public class App {

  public static void main(String[] args) {
    RSAKeyGen.generateKeyPair();

    System.out.println(IO.readFile("text.txt"));
  }
}
