package fhnw.mada.other;

import fhnw.mada.App;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class IO {

  private IO() {
  }

  public static void writeFile(String fileName, String content) {
    File file = new File(App.class.getResource("").getPath(), fileName);

    try {
      FileWriter writer = new FileWriter(file);
      writer.write(content);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String readFile(String fileName) {
    File file = new File(App.class.getResource("").getPath(), fileName);
    StringBuilder content = new StringBuilder();

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = br.readLine()) != null) {
        content.append(line).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return content.toString();
  }
}
