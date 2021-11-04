package code.Scanner;

import code.FileFunction.Readwrite;
import code.Table.PIF;
import code.Table.SymbolTable;

import java.io.IOException;
import java.util.List;

public class Scanner {

    private final SymbolTable symbolTable;
    private final Validation validation;

    public Scanner(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
        this.validation = new Validation();
    }

    public String verifyAndAddTokensInSTAndPIF(List<String> miniProgram, String ST_filename, String PIF_filename) throws IOException {
        PIF pif = new PIF(symbolTable);
        boolean flag = true;
        for (int i = 0; i < miniProgram.size(); i++) {
            List<String> tokens = FormattingLine.splitLine(miniProgram.get(i));
            int j = 0;
            try {
                for (String token : tokens) {
                    if (validation.checkIfConstant(token)) {
                        symbolTable.add(token);
                    } else if (validation.checkIfIdentifier(token)) {
                        symbolTable.add(token);
                    } else if (!validation.checkIfReservedWord(token)) {
                        throw new RuntimeException("Error");
                    }
                    pif.add(token);
                    j += 1;
                }
            } catch (RuntimeException e) {
                flag = false;
                int counterLine = i + 1;
                System.err.println("Error on line " + counterLine + " -> " + tokens.get(j));
            }
        }
        Readwrite.writeSTToFile(ST_filename, symbolTable);
        Readwrite.writePIFToFile(PIF_filename, pif);
        if (flag) {
            return "Lexical correct!";
        }
        return "Lexical incorrect!";
    }


}
