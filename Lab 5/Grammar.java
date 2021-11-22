import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Grammar {

    private List<String> terminals;
    private List<String> nonTerminals;
    private String startingSymbol;
    private Map<String, List<String>> productions;

    public Grammar(List<String> fileLines) {
        this.nonTerminals = takeDataFromFileLine(fileLines.get(0));
        this.terminals = takeDataFromFileLine(fileLines.get(1));
        this.startingSymbol = takeDataFromFileLine(fileLines.get(2)).get(0);
        takeProductionsFromFile(fileLines);
    }

    private List<String> takeDataFromFileLine(String line){
        return List.of(List.of(line.split(":")).get(1).split(" "));
    }

    private void takeProductionsFromFile(List<String> fileLines){
        for (int i = 5; i < fileLines.size(); i++) {
            if(Objects.equals(fileLines.get(i), "")){
                break;
            }
            List<String> line = List.of(fileLines.get(i).split("-"));
            String key = line.get(0).trim();
            List<String> values = new ArrayList<>(List.of(line.get(1).split(" ")));
            values.removeIf(el -> (Objects.equals(el, "|")));
            productions.put(key, values);
        }
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public Map<String, List<String>> getProductions() {
        return productions;
    }

    public void checkIfStartingSymbol() throws Exception {
        if(!nonTerminals.contains(startingSymbol)){
            throw new Exception("Starting symbol is not valid!");
        }
    }

    public void checkIfProduction() throws Exception {
        for (int i = 0; i < productions.size(); i++) {
            for (String nonTerminal : nonTerminals) {
                if (!productions.containsKey(nonTerminal)) {
                    throw new Exception("Invalid production!");
                }
            }
        }
    }

    public List<String> getForSpecificNonTerminal(String nonTerminal) {
        if (nonTerminals.contains(nonTerminal)) {
            return productions.get(nonTerminal);
        }
        return List.of();
    }

    public void CFG(){

    }
}