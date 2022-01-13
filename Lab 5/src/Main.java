import WorkClasses.Controller.DescendentRecursiveParser;
import WorkClasses.Controller.GrammarReader;
import WorkClasses.Controller.Menu;
import WorkClasses.Domain.Grammar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        final String GRAMMAR_FILE_PATH = "src/assets/g1.txt";

        GrammarReader grammarReader = new GrammarReader(GRAMMAR_FILE_PATH);
        Grammar grammar = grammarReader.readGrammar();

        Menu menu = new Menu(grammar);
        menu.run();
        List<String> inputSequence = new ArrayList<>(Arrays.asList("a", "a", "c", "b", "c"));
        DescendentRecursiveParser descendentRecursiveParser = new DescendentRecursiveParser(grammar, inputSequence);
        descendentRecursiveParser.runParser();

        System.out.println("~end of program~");
    }
}















//        List<String> fileLines = Readwrite.getContent("src/g1.txt");
//        Grammar grammar = new Grammar(fileLines);
//        try{
//            grammar.checkIfStartingSymbol();
//            grammar.cfgCheck();
//
//            String menu = """
//                    1. Show non-terminals
//                    2. Show terminals
//                    3. Show starting symbol
//                    4. Show production
//                    5. Show specific production for a non-terminal""";
//            System.out.println(menu);
//            Scanner sc = new Scanner(System.in);
//            int command;
//            do{
//                System.out.print("\nEnter command: ");
//                command = sc.nextInt();
//                switch (command){
//                    case 1 -> {
//                        System.out.println("The non-terminals are: ");
//                        System.out.println(grammar.getNonTerminals());
//                    }
//                    case 2 -> {
//                        System.out.println("The terminals are: ");
//                        System.out.println(grammar.getTerminals());
//                    }
//                    case 3 -> {
//                        System.out.println("The starting symbol is: ");
//                        System.out.println(grammar.getStartingSymbol());
//                    }
//                    case 4 -> {
//                        System.out.println("The productions are: ");
//                        System.out.println(grammar.getProductions());
//                    }
//                    case 5 -> {
//                        String input;
//                        do{
//                            System.out.print("\nInsert the non-terminal: ");
//                            input = sc.next();
//                            System.out.println(grammar.getForSpecificNonTerminal(input));
//                        }while(!input.equals("quit"));
//                    }
//                    default -> System.out.println("This command is not recognised");
//                }
//            }while(command != 0);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}