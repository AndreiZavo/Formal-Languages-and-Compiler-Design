package WorkClasses.Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DescendantConfig {
    private StateOfParsing stateOfParsing;
    private Integer inputIndex;
    private Stack<Object> workingStack;
    private Stack<String> inputStack;


    public DescendantConfig() {
        this.stateOfParsing = StateOfParsing.NONE;
        this.inputIndex = -1;
        this.workingStack = new Stack<>();
        this.inputStack = new Stack<>();
    }

    public DescendantConfig(StateOfParsing stateOfParsing, Integer inputIndex, Stack<Object> workingStack, Stack<String> inputStack) {
        this.stateOfParsing = stateOfParsing;
        this.inputIndex = inputIndex;
        this.workingStack = workingStack;
        this.inputStack = inputStack;
    }

    public StateOfParsing getParsingState() {
        return stateOfParsing;
    }

    public void setParsingState(StateOfParsing parsingState) {
        this.stateOfParsing = parsingState;
    }

    public Integer getInputIndex() {
        return inputIndex;
    }

    public void setInputIndex(Integer inputIndex) {
        this.inputIndex = inputIndex;
    }

    public Stack<Object> getWorkingStack() {
        return workingStack;
    }

    public void setWorkingStack(Stack<Object> workingStack) {
        this.workingStack = workingStack;
    }


    public Stack<String> getInputStack() {
        return inputStack;
    }

    public void setInputStack(Stack<String> inputStack) {
        this.inputStack = inputStack;
    }



    @Override
    public String toString() {
        String[] toBePrinted = new String[1];

        List<Object> workingStackCopy = new ArrayList<>(this.getWorkingStack());
        List<Object> inputStackCopy = new ArrayList<>(this.getInputStack());

        toBePrinted[0] = "(" + this.getParsingState() + ", " + this.getInputIndex() + ", ";
        workingStackCopy.forEach(elements -> toBePrinted[0] += elements);
        toBePrinted[0] += ", ";
        for(int index = inputStackCopy.size()-1; index >= 0; index--)
            toBePrinted[0] += inputStackCopy.get(index);
        toBePrinted[0] += " )";


        return toBePrinted[0];
    }
}