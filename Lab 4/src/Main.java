import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<String> fileLines = Readwrite.getContent("src/example.in");
        FiniteAutomata fa = new FiniteAutomata(fileLines);
        String menu = """
                1. Show set of states
                2. Show alphabet
                3. Show transitions
                4. Show set of final states
                5. Check if sequence is accepted by the FA""";
        System.out.println(menu);
        Scanner sc = new Scanner(System.in);
        int command;
       do {
           System.out.print("\nEnter command: ");
           command = sc.nextInt();
            switch (command){
                case 1 -> {
                    System.out.println("The set of states is: ");
                    System.out.println(fa.getSetOfStates());
                }
                case 2 -> {
                    System.out.println("The alphabet is: ");
                    System.out.println(fa.getAlphabet());
                }
                case 3 -> {
                    System.out.println("The transitions are: ");
                    System.out.println(fa.getTransitions());
                }
                case 4 -> {
                    System.out.println("The final states are: ");
                    System.out.println(fa.getFinalStates());
                }
                case 5 -> {
                    String input = "";
                    do{
                        System.out.print("\nInsert the finite automata: ");
                        input = sc.next();
                        if(fa.checkIfFAIsValid(input)){
                            System.out.println("The finite automata is valid!");
                        }
                        else{
                            System.out.println("The finite automata is not valid!");
                        }
                    }while(!input.equals("quit"));
                }
                default -> System.out.println("This command is not recognise");
            }
        } while(command != 0);
    }
}
