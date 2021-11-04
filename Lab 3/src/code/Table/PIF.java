package code.Table;

import code.UtilityClasses.Pair;
import java.util.ArrayList;
import java.util.List;

public class PIF {

    private final List<Pair<String, Pair<Integer, Integer>>> pif;
    private final SymbolTable symbolTable;

    public PIF(SymbolTable symbolTable) {
        this.pif = new ArrayList<>();
        this.symbolTable = symbolTable;
    }

    public void add(String token){
        int positionOfTokenInSymbolTable = symbolTable.find(token);
        if(positionOfTokenInSymbolTable != -1){
            pif.add(Pair.of(token, Pair.of(symbolTable.hashFunction(token) ,positionOfTokenInSymbolTable)));
        }
        else{
            pif.add(Pair.of(token, null));
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PIF: \n");
        for(Pair<String, Pair<Integer, Integer>> stringPairPair : this.pif){
            stringBuilder.append(stringPairPair.getFirst())
                    .append(" ==> ");
            if(stringPairPair.getSecond() == null){
                stringBuilder.append("-1");
            }
            else{
                stringBuilder.append(stringPairPair.getSecond().toString());
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}




