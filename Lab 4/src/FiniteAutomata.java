import java.util.*;

public class FiniteAutomata {

    private final List<String> setOfStates;
    private final List<String> alphabet;
    private final String initialState;
    private final List<String> finalStates;
    private final Map<String, String> transitions = new HashMap<>();

    public FiniteAutomata(List<String> fileLines) {
        this.setOfStates = takeDataFromFileLine(fileLines.get(0));
        this.alphabet = takeDataFromFileLine(fileLines.get(1));
        this.initialState = takeDataFromFileLine(fileLines.get(2)).get(0);
        this.finalStates = takeDataFromFileLine(fileLines.get(3));
        takeTransitionsFromFile(fileLines);
    }

    public List<String> getSetOfStates() {
        return setOfStates;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public Map<String, String> getTransitions() {
        return transitions;
    }

    private List<String> takeDataFromFileLine(String line){
        return List.of(List.of(line.split(":")).get(1).split(" "));
    }

    private void takeTransitionsFromFile(List<String> fileLines){
        for (int i = 5; i < fileLines.size(); i++) {
            if(Objects.equals(fileLines.get(i), "")){
                break;
            }
            List<String> line = List.of(fileLines.get(i).split("=>"));
            String key = line.get(0).trim();
            String value = line.get(1).trim();
            transitions.put(key, value);
        }
    }

    private boolean checkIfDFA() {
        for (String key : transitions.keySet()) {
            if (transitions.get(key) == null) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIfFAIsValid(String input) {
        if(checkIfDFA()){
            String state = this.initialState;
            for (int i = 0; i < input.length(); i++) {
                char command = input.charAt(i);
                String keyToFind = "(" + state + "," + command + ")";

                if(transitions.containsKey(keyToFind)){
                    state = transitions.get(keyToFind);
                }
                else{
                    return false;
                }
            }
            return checkIfFinalState(state);
        }
        return false;
    }

    private boolean checkIfFinalState(String stateToTest) {
        for (String state: finalStates) {
            if(state.equals(stateToTest)){
                return true;
            }
        }
        return false;
    }
}
