package kr.oso;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

final class MyFile {
    static File myFile = new File("myFile.txt");

    static void writeToFile (String product) {
        try (FileWriter writer = new FileWriter(myFile, true)) {
            if (product.matches("\\S+")) {
                writer.write(product + "\n");
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    private static void writeToFile (String[] products) {
        try (PrintWriter printWriter = new PrintWriter(myFile)) {
            for (String product: products) {
                if (product.matches("\\S+")) {
                    printWriter.println(product);
                }
            }
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    static String readFromFile () {
        try {
            String[] dataFile = readFileAsString(myFile.getPath()).trim().split("\\s+");
            writeToFile(Arrays.copyOfRange(dataFile, 1, dataFile.length));
            return dataFile[0];
        } catch (IOException e) {
            System.out.println("Cannot read file: " + e.getMessage());
        }
        return null;
    }

    private static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}
