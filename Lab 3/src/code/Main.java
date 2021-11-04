package code;

import code.FileFunction.Readwrite;
import code.Scanner.Scanner;
import code.Table.SymbolTable;

import java.io.IOException;
import java.util.List;

public class Main {

    public static final String FILE_PATH = "src/assets/resources";

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 4; i++) {
            SymbolTable symbolTable = new SymbolTable(50);
            Scanner scanner = new Scanner(symbolTable);
            i++;
            List<String> miniProgram = Readwrite.getContent(FILE_PATH + "/p" + i + ".txt");
            String result = scanner.verifyAndAddTokensInSTAndPIF(miniProgram, "ST_" + i, "PIF_" + i);
            System.out.println("Problem " + i + " is: " + result);
            i--;
        }
    }
}
