package code.Table;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class SymbolTable {

    private final HashMap<Integer, List<String>> symbolTable;
    private final int size;

    public SymbolTable(int size) {
        this.size = size;
        this.symbolTable = new HashMap<>();
        initializeSymbolTable(this.symbolTable, this.size);
    }

    private void initializeSymbolTable(HashMap<Integer, List<String>> symbolTable , int counter){
        for(int i = 0; i < counter; i++){
            symbolTable.put(i, new ArrayList<>());
        }
    }

    public int hashFunction(@NotNull String s) {
        int sum = IntStream.range(0, s.length()).map(s::charAt).sum();
        return sum % this.size;
    }

    public int find(String element){
        int pos = hashFunction(element);
        List<String> list = this.symbolTable.get(pos);
        int bound = list.size();
        return IntStream.range(0, bound).filter(i -> list.get(i).equals(element)).findFirst().orElse(-1);
    }

    public int add(String s) {
        int pos = hashFunction(s);
        if (find(s) != -1) {
            return pos;
        }
        if (this.symbolTable.containsKey(pos)) {
            this.symbolTable.get(pos).add(s);
        }
        return pos;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Symbol code.Table:\n");
        for(Map.Entry<Integer, List<String>> entry : this.symbolTable.entrySet()){
            stringBuilder.append(entry.getKey()).append("  ==>  ").append(entry.getValue()).append("\n");
        }
        stringBuilder.append("\t--------\n");
        return stringBuilder.toString();
    }
}
