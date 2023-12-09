package fhnw.mada.other;

import fhnw.mada.App;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    public static void writeByteArray(String fileName, Byte[] bytes) {
        String file = new File(App.class.getResource("").getPath(), fileName).getAbsolutePath();

        byte[] primitiveArray = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            primitiveArray[i] = bytes[i];
        }

        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(primitiveArray);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Byte[] readByteArray(String fileName) {
        File file = new File(App.class.getResource("").getPath(), fileName);
        byte[] bFile = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(bFile);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Byte[] byteObjects = new Byte[bFile.length];
        int i = 0;
        for (byte b : bFile) {
            byteObjects[i++] = b;
        }

        return byteObjects;
    }
}
