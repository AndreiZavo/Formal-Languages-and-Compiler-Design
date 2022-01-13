package WorkClasses.Controller;

import WorkClasses.Domain.*;

import java.util.List;

public class Operations {
    public static boolean STATIC_LOGGER = false;

    public static void expand(DescendantConfig descendantConfig, Grammar grammar) throws Exception {
        String nextNonTerminal = descendantConfig.getInputStack().pop();

        NonterminalAndProduction nonterminalAndProduction
                = new NonterminalAndProduction(nextNonTerminal, 0);

        Production toBeExpanded = grammar.getProductionsForNonterminal(nextNonTerminal).get(0);
        for(int index = toBeExpanded.getProductionRule().size() - 1; index >= 0; index--){
            String nextElement = toBeExpanded.getProductionRule().get(index);
            descendantConfig.getInputStack().push(nextElement);
        }

        descendantConfig.getWorkingStack().push(nonterminalAndProduction);
    }

    public static void advance(DescendantConfig descendantConfiguration){
        String nextNonTerminal = descendantConfiguration.getInputStack().pop();

        descendantConfiguration.getWorkingStack().push(nextNonTerminal);
        descendantConfiguration.setInputIndex(descendantConfiguration.getInputIndex() + 1);
    }

    public static void momentaryInsuccess(DescendantConfig descendantConfiguration){
        descendantConfiguration.setParsingState(StateOfParsing.BACK_STATE);
    }

    public static void goBack(DescendantConfig descendantConfiguration){
        String badTerminal = (String) descendantConfiguration.getWorkingStack().pop();

        descendantConfiguration.setInputIndex(descendantConfiguration.getInputIndex() - 1);
        descendantConfiguration.getInputStack().push(badTerminal);
    }

    public static void success(DescendantConfig descendantConfiguration){
        descendantConfiguration.setParsingState(StateOfParsing.FINAL_STATE);
    }

    public static void anotherTry(DescendantConfig descendantConfiguration, Grammar grammar) throws Exception {
        NonterminalAndProduction nonterminalAndProduction
                = (NonterminalAndProduction) descendantConfiguration.getWorkingStack().peek();

        int currentProductionIndex = nonterminalAndProduction.getProductionIndex();
        String nonTerminal = nonterminalAndProduction.getNonTerminal();

        List<Production> productionsForNonterminal = grammar.getProductionsForNonterminal(nonTerminal);
        Production currentProduction = productionsForNonterminal.get(currentProductionIndex);

        if(currentProductionIndex + 1 < productionsForNonterminal.size()){
            Production nextProduction = productionsForNonterminal.get(currentProductionIndex + 1);

            descendantConfiguration.getWorkingStack().pop();
            currentProduction.getProductionRule().forEach(production
                    -> descendantConfiguration.getInputStack().pop());

            descendantConfiguration.getWorkingStack().push(
                    new NonterminalAndProduction(nonTerminal, currentProductionIndex+1)
            );
            for(int index = nextProduction.getProductionRule().size() - 1; index >= 0; index--){
                String currentTerminal = nextProduction.getProductionRule().get(index);
                descendantConfiguration.getInputStack().push(currentTerminal);
            }

            descendantConfiguration.setParsingState(StateOfParsing.NORMAL_STATE);
        }else{
            currentProduction.getProductionRule().forEach(production
                    -> descendantConfiguration.getInputStack().pop());
            descendantConfiguration.getInputStack().push(nonTerminal);
            descendantConfiguration.getWorkingStack().pop();
            descendantConfiguration.setParsingState(StateOfParsing.BACK_STATE);

            if(
                    descendantConfiguration.getInputStack().peek().equals(grammar.getInitialNonTerminal())
                            && descendantConfiguration.getInputIndex() == 0
            ){
                descendantConfiguration.setParsingState(StateOfParsing.ERROR_STATE);
                descendantConfiguration.getInputStack().pop();
            }
        }

    }



    public static class OperationChecker{
        public static boolean canAdvance(DescendantConfig descendantConfiguration,
                                         List<String> inputSequence){
            if(descendantConfiguration.getInputIndex() <= inputSequence.size() - 1){
                String nextNonTerminal = inputSequence.get(descendantConfiguration.getInputIndex());

                return descendantConfiguration.getInputStack().peek().equals(nextNonTerminal);
            }
            return false;
        }

        public static boolean canExpand(DescendantConfig descendantConfiguration, Grammar grammarModel){
            String nextInInputStack = descendantConfiguration.getInputStack().peek();

            return grammarModel.getNonTerminals().contains(nextInInputStack);
        }

        public static boolean canGoBack(DescendantConfig descendantConfiguration){
            Object lastElementWorkingStack = descendantConfiguration.getWorkingStack().peek();

            return lastElementWorkingStack instanceof String;
        }

        public static boolean canSuccess(DescendantConfig descendantConfiguration, List<String> inputSequnce){
            return descendantConfiguration.getInputStack().empty()
                    && descendantConfiguration.getInputIndex() == inputSequnce.size();
        }
    }

}
