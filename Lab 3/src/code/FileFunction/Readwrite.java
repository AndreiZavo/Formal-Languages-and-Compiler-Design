package code.FileFunction;

import code.Table.PIF;
import code.Table.SymbolTable;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Readwrite {

    public static List<String> getContent(String filename) {
        String path = Paths.get(filename).toString();
        try {
            return Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    public static void writeSTToFile(String filename, SymbolTable symbolTable) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(symbolTable.toString());
        writer.close();
    }

    public static void writePIFToFile(String filename, PIF programInternalForm) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(programInternalForm.toString());
        writer.close();
    }


}
