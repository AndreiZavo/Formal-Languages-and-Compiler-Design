package code.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormattingLine {

    public static final String SQUARE_BRACKETS = "[\\[\\]]";

    public static List<String> splitLine(String line) {
        List<String> finalListOfTokens = new ArrayList<>();
        List<String> newList = combineElements(line);

        for (String value : newList) {
            String[] tokensSplitBySquareBrackets = value.split(SQUARE_BRACKETS);
            finalListOfTokens.addAll(Arrays.asList(tokensSplitBySquareBrackets));
        }
        return eraseEmptyValuesFromList(finalListOfTokens);
    }

    private static List<String> eraseEmptyValuesFromList(List<String> tokens) {
        tokens.removeIf(String::isEmpty);
        return tokens;
    }

    private static List<String> combineElements(String line) {
        int index = 0;
        StringBuilder token = new StringBuilder();
        List<String> tokens = new ArrayList<>();
        boolean quote = false;
        while(index < line.length()){
            char character = line.charAt(index);
            if( (character == ' ' || character == ',') && !quote){
                tokens.add(token.toString());
                token = new StringBuilder();
                index++;
                continue;
            }
            if(character == '\"'){
                if(quote){
                    token.append(character);
                    tokens.add(token.toString());
                    token = new StringBuilder();
                    index += 2;
                    quote = false;
                    continue;
                }else{
                    quote = true;
                }
            }
            token.append(character);
            index++;
        }
        tokens.add(token.toString());
        return tokens;
    }

}
