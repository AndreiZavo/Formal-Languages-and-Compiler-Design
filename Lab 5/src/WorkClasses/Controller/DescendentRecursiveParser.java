package WorkClasses.Controller;

import WorkClasses.Domain.DescendantConfig;
import WorkClasses.Domain.Grammar;
import WorkClasses.Domain.StateOfParsing;

import java.util.List;
import java.util.Stack;

public class DescendentRecursiveParser {
    DescendantConfig descendantConfig;
    Grammar grammarModel;
    List<String> inputSequence;

    public DescendentRecursiveParser(Grammar grammar, List<String> inputSequence) {
        this.grammarModel = grammar;
        this.descendantConfig = new DescendantConfig(StateOfParsing.NORMAL_STATE, 0, new Stack<>(), new Stack<>());
        this.descendantConfig.getInputStack().push(grammar.getInitialNonTerminal());
        this.inputSequence = inputSequence;
    }

    public void runParser() throws Exception {
        StateOfParsing stateOfParsing = this.descendantConfig.getParsingState();
        while(stateOfParsing != StateOfParsing.FINAL_STATE && stateOfParsing != StateOfParsing.ERROR_STATE){
            if(stateOfParsing == StateOfParsing.NORMAL_STATE){
                if(Operations.OperationChecker.canSuccess(this.descendantConfig, this.inputSequence))
                    Operations.success(this.descendantConfig);
                else{
                    if(Operations.OperationChecker.canExpand(this.descendantConfig, this.grammarModel))
                        Operations.expand(this.descendantConfig, this.grammarModel);
                    else{
                        if(Operations.OperationChecker.canAdvance(descendantConfig, this.inputSequence))
                            Operations.advance(this.descendantConfig);
                        else Operations.momentaryInsuccess(this.descendantConfig);
                    }
                }
            }

            if(stateOfParsing == StateOfParsing.BACK_STATE){
                if(Operations.OperationChecker.canGoBack(this.descendantConfig))
                    Operations.goBack(this.descendantConfig);
                else Operations.anotherTry(this.descendantConfig, this.grammarModel);
            }

            stateOfParsing = this.descendantConfig.getParsingState();
        }

        if(this.descendantConfig.getParsingState() == StateOfParsing.ERROR_STATE)
            throw new Exception("Sequence cannot be parsed");
        System.out.println("Sequence accepted!");

        TableBuilder tableBuilder = new TableBuilder(this.grammarModel, this.descendantConfig.getWorkingStack());
        tableBuilder.buildTable();
    }
}