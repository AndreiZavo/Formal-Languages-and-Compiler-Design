package WorkClasses.Domain;

import java.util.ArrayList;
import java.util.List;

public class Grammar {
    private String initialNonTerminal;
    private List<String> nonTerminals;
    private List<String> terminals;
    private List<Production> productions;

    public Grammar() {
    }

    public String getInitialNonTerminal() {
        return initialNonTerminal;
    }

    public void setInitialNonTerminal(String initialNonTerminal) {
        this.initialNonTerminal = initialNonTerminal;
    }

    public List<String> getNonTerminals() {
        return nonTerminals;
    }

    public void setNonTerminals(List<String> nonTerminals) {
        this.nonTerminals = nonTerminals;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    public List<String> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<String> terminals) {
        this.terminals = terminals;
    }

    public List<Production> getProductionsForNonterminal(String nonTerminal) throws Exception {
        List<Production> productionList = new ArrayList<>();

        boolean validNonTerminal = this.getNonTerminals().contains(nonTerminal);
        if(!validNonTerminal){
            throw new Exception("The terminal you have inserted does not exist in the grammar!\n");
        }

        this.getProductions().forEach(
                production -> {
                    if(production.getNonTerminal().equals(nonTerminal)) productionList.add(production);
                }
        );

        return productionList;
    }
}


//public class Grammar {
//
//    private final List<String> terminals;
//    private final List<String> nonTerminals;
//    private final String startingSymbol;
//    private final Map<String, List<String>> productions = new HashMap<>();
//
//    public Grammar(List<String> fileLines) {
//        this.nonTerminals = takeDataFromFileLine(fileLines.get(0));
//        this.terminals = takeDataFromFileLine(fileLines.get(1));
//        this.startingSymbol = takeDataFromFileLine(fileLines.get(2)).get(0);
//        takeProductionsFromFile(fileLines);
//    }
//
//    private List<String> takeDataFromFileLine(String line){
//        return List.of(List.of(line.split(":")).get(1).split(" "));
//    }
//
//    private void takeProductionsFromFile(List<String> fileLines){
//        for (int i = 4; i < fileLines.size(); i++) {
//            if(Objects.equals(fileLines.get(i), "")){
//                break;
//            }
//            List<String> line = List.of(fileLines.get(i).split("-"));
//            String key = line.get(0).trim();
//            List<String> values = new ArrayList<>(List.of(line.get(1).split(" ")));
//            values.removeIf(el -> (Objects.equals(el, "|")));
//            productions.put(key, values);
//        }
//    }
//
//    public List<String> getTerminals() {
//        return terminals;
//    }
//
//    public List<String> getNonTerminals() {
//        return nonTerminals;
//    }
//
//    public String getStartingSymbol() {
//        return startingSymbol;
//    }
//
//    public Map<String, List<String>> getProductions() {
//        return productions;
//    }
//
//    public List<String> getForSpecificNonTerminal(String nonTerminal) {
//        if (nonTerminals.contains(nonTerminal)) {
//            return productions.get(nonTerminal);
//        }
//        return List.of();
//    }
//    public void checkIfStartingSymbol() throws Exception {
//        if(!nonTerminals.contains(startingSymbol)){
//            throw new Exception("Starting symbol is not valid!");
//        }
//    }
//    public void cfgCheck() throws Exception {
//        for(Map.Entry<String, List<String>> entry : productions.entrySet()){
//            if(!nonTerminals.contains(entry.getKey())){
//                throw new Exception("CFG check not passed on productions keys!");
//            }
//            for (String val:entry.getValue()) {
//                if(val.length() > 1){
//                    String firstChar = String.valueOf(val.charAt(0));
//                    String secondChar = String.valueOf(val.charAt(1));
//                    if( !nonTerminals.contains(firstChar) && !terminals.contains(firstChar) &&
//                            !nonTerminals.contains(secondChar) && !terminals.contains(secondChar)) {
//                        throw new Exception("CFG check not passed on production values!");
//                    }
//                }
//                else {
//                    if (!nonTerminals.contains(val) && !terminals.contains(val)) {
//                        throw new Exception("CFG check not passed on production values!");
//                    }
//                }
//            }
//        }
//    }
//}