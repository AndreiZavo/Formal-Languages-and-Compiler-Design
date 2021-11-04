package code.Scanner;

import code.FileFunction.Readwrite;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Validation {

    private final static List<String> RESERVED_WORDS_AND_CHARACTERS = Readwrite.getContent("src/assets/token.in");

    public boolean checkIfIdentifier(String token) {
        return !RESERVED_WORDS_AND_CHARACTERS.contains(token) && checkIfIdentifierIsCorrectFormatted(token);
    }

    public boolean checkIfConstant(String token) {
        return !RESERVED_WORDS_AND_CHARACTERS.contains(token) && (Objects.equals(token, "true") || Objects.equals(token, "false")) || checkIfString(token)
                || checkIfNumber(token) || checkIfChar(token);
    }

    public boolean checkIfReservedWord(String token) {
        return RESERVED_WORDS_AND_CHARACTERS.contains(token);
    }

    private boolean checkIfIdentifierIsCorrectFormatted(String identifier) {
        String regex = "^([a-zA-Z][a-zA-Z0-9]*$)";
        return Pattern.matches(regex, identifier);
    }

    private boolean checkIfNumber(String constant) {
        String regex = "^([0-9]*)$";
        return Pattern.matches(regex, constant);
    }

    private boolean checkIfString(String token) {
        char firstCharacter = token.charAt(0);
        char lastCharacter = token.charAt(token.length() - 1);

        if (token.length() >= 3) {
            String finalToken = token.substring(1, token.length() - 1);
            return firstCharacter == '"' && lastCharacter == '"' && !finalToken.contains("\"");
        }
        return false;
    }

    private boolean checkIfChar(String token) {
        if (token.length() == 3) {
            String firstCharacter = String.valueOf(token.charAt(0));
            String middleCharacter = String.valueOf(token.charAt(1));
            String lastCharacter = String.valueOf(token.charAt(2));
            return firstCharacter.equals("'") && lastCharacter.equals("'") && !middleCharacter.contains("'");
        }
        return false;
    }
}
