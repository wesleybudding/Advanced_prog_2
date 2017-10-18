import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;

// Zorgen dat sets geen - nummers hebben
// Zorgen dat niet twee sets achter elkaar krijgt ({}({4}))
// Kijken naar set: Geen lege plekken, geen spaties tussen getallen
// Aanpassen: eerste haakje sluit } vinden
// Equals sign bij assignment
// Exceptions throwend


public class Main {

    private char[] letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private char[] number = "123456789".toCharArray();
    private HashMap<String,Set> storage = new HashMap<>();

    private Identifier storeIdenfifier(String input) throws APException{
        Identifier store = new Identifier();
        store.addFirst(input.charAt(0));

        for(int i=1; i<input.length();i++){
            if(isLetter(input.charAt(i))||isNumber(input.charAt(i))) {
                store.add(input.charAt(i));
            } else if(input.charAt(i) == ' '){
                // Do nothing
            } else{
                throw new APException("Not expected character. Identifier should start with a letter, and contain only letters and numbers.");
            }
        }
        return store;
    }

    public void printSet(Set<BigInteger> s){
        if(s.isEmpty()){

        } else{
            for(int i = 1; i <= s.cardinality(); i++){
                System.out.print(s.retrieve(i) + " ");
            }
        }
        System.out.println();
    }

    private void processLine(String in) throws APException {
        if(!countParentheses(in)){
            throw new APException("amount of parentheses is not correct!");
        }else if(!countBrackets(in)){
            throw new APException("amount of brackets is not correct!");
        }else if(!setsComparedToOperations(in)){
            throw new APException("Amount operations missing for amount sets");
        }
        else if(in.charAt(0) == '/'){
            //System.out.println("comment! not processing this line:" + in);
        } else if (isLetter(in.charAt(0))){

            processAssignment(in);
        } else if (in.charAt(0) == '?'){
            processPrint(in);
        } else{
            throw new APException("Incorrect input. Input should start with letter, slash, or question mark.");
        }
    }

    private void processPrint(String in) throws APException{
        Set<BigInteger> s = processExpression(in.substring(1));
        printSet(s);
    }

    private void processAssignment(String in) throws APException{
        int i = 0;
        while(!(in.charAt(i) == '=') && !(in.charAt(i) == ' ')){
            i += 1;
        }

        Identifier identfier = storeIdenfifier(in.substring(0,i));

        while(!(in.charAt(i) == '=')){
            i+=1;
        }
        String equals = in.substring(i,i+1);

        Set<BigInteger> s = processExpression(in.substring(i+1,in.length()));
        String name = identfier.getString();
        storage.put(name,s);
    }

    private Set<BigInteger> processComplexFactor(String in, int startPosition) throws APException{

        int endBracket =  FindClosingParentheses(in,startPosition+1);
        String term1 = in.substring(startPosition+1,endBracket);

        if(endBracket +2 < in.length()){
            String rest = in.substring(endBracket+1);


            for(int j = 0; j < rest.length(); j++){
                if((rest.charAt(j) == '+')){
                    Set<BigInteger> set1 = processExpression(term1);
                    Set<BigInteger> set2 = processExpression(rest.substring(j+1));
                    return set1.union(set2);
                }
                if((rest.charAt(j) == '-')){
                    Set<BigInteger> set1 = processExpression(term1);
                    Set<BigInteger> set2 = processExpression(rest.substring(j+1));
                    return set1.complement(set2);
                }
                if((rest.charAt(j) == '|')){
                    Set<BigInteger> set1 = processExpression(term1);
                    Set<BigInteger> set2 = processExpression(rest.substring(j+1));
                    return set1.indifference(set2);
                }
                if((rest.charAt(j) == '*')){
                    Set<BigInteger>set1 = processExpression(term1);
                    Set<BigInteger> set2 = processExpression(rest.substring(j+1));
                    return set1.intersection(set2);
                }
            }
        }
        return processExpression(term1);
    }

    private Set<BigInteger> processExpression(String in) throws APException{

        if(in.length() < 1){
            throw new APException("Invalid input after '=' sign.");
        }

        for(int i = 0; i < in.length(); i++){
            if (in.charAt(i) == '(') {
                return processComplexFactor(in,i);
            }


            if((in.charAt(i) == '+') || (in.charAt(i) == '-') || (in.charAt(i) == '|')){
                String term = in.substring(0,i);
                String operator = in.substring(i,i+1);

                Set<BigInteger> set1 = processTerm(term);
                Set<BigInteger> set2 = processExpression(in.substring(i+1));
                if(in.charAt(i) == '+') return set1.union(set2);
                else if (in.charAt(i) == '-') return set1.complement(set2);
                else if (in.charAt(i) == '|') return set1.indifference(set2);
            }

        }
        return processTerm(in);
    }

    private Set<BigInteger> processTerm(String in) throws APException{

        for (int i = 0; i < in.length(); i++){

            if(in.charAt(i) == '*'){
                String factor = in.substring(0,i);
                String operator = in.substring(i,i+1);
                Set<BigInteger> firstSet = processFactor(factor);
                Set<BigInteger> secondSet = processTerm(in.substring(i+1));
                return firstSet.intersection(secondSet);
            }
        }
        return processFactor(in);
    }

    private Set<BigInteger> processFactor(String in) throws APException{


        for(int i = 0; i < in.length(); i++) {
            if (isLetter(in.charAt(i))) {
                Identifier name = storeIdenfifier(in.substring(i));
                Set<BigInteger> s = storage.get(name.getString());
                if(s == null){
                    throw new APException("Set not found.");
                } else{
                    return s;
                }
            } else if (in.charAt(i) == '{') {
                String set = in.substring(i);
                return (processSet(set));
            }
        }
        throw new APException("Input not valid.");
    }

    private Set<BigInteger> processSet(String in) throws APException{
        Set<BigInteger> s = new Set<>();


        if(in.charAt(0) == '{' && (in.charAt(1) == '}' || (!isNumber(in.charAt(1)) && (in.charAt(2) == '}')))){
            return s;
        }

        if((in.charAt(0) == '{') && ((in.charAt(in.length()-1) == '}') || (in.charAt(in.length() -2) == '}') )){

            return processRowNaturalNumbers(in.substring(1,in.length()-1), s);

        } else {
            throw new APException("Set not valid.");
        }
    }

    private Set<BigInteger> processRowNaturalNumbers(String in, Set s) throws APException{
        for(int i = 0; i < in.length(); i++){
            if(isNumber(in.charAt(i)) || in.charAt(i) == '0'){
                int j = i;
                while((j < in.length()) && !(in.charAt(j) == ',') && !(in.charAt(j) == '}') && !(in.charAt(j) == ' ')){
                    j++;
                }
                s.add(processNaturalNumber(in.substring(i,j)));
                i = j;
            }
        }
        return s;
    }

    private BigInteger processNaturalNumber(String in) throws APException{

        if(in.charAt(0) == '0' && (in.length() == 1)){
        } else if (isNumber(in.charAt(0))){
        } else {
            throw new APException("Number not allowed");
        }

        return new BigInteger(in);
    }

    private int FindClosingParentheses(String in, int startPosition){
        int i = startPosition;

        int counter = 1;
        while(counter > 0){
            if(in.charAt(i) == '('){
                counter++;
            }
            if(in.charAt(i) == ')'){
                counter--;
            }
            i++;
        }
        return i-1;
    }

    private boolean isLetter(char x){
        Character check = x;
        for(int i = 0; i<=51; i++){
            if(check.equals(letter[i])){
                return true;
            }
        }
        return false;
    }

    private boolean isNumber(char x){
        Character check = x;
        for(int i = 0; i<=8; i++){
            if(check.equals(number[i])){
                return true;
            }
        }
        return false;
    }

    private boolean countParentheses(String in){
        int i = 0;
        int leftParentheses = 0;
        int rightParentheses = 0;
        while(i < in.length()){
            if(in.charAt(i) == '('){
                leftParentheses++;

            } else if(in.charAt(i) == ')'){
                rightParentheses++;

            }
            i++;
        }
        return leftParentheses == rightParentheses;
    }

    private boolean countBrackets(String in){
        int i = 0;
        int leftBrackets = 0;
        int rightBrackets = 0;
        while(i < in.length()){
            if(in.charAt(i) == '{'){
                leftBrackets++;

            } else if(in.charAt(i) == '}'){
                rightBrackets++;

            }
            i++;
        }
        return leftBrackets == rightBrackets;
    }

    private boolean setsComparedToOperations(String in){
        int i = 0;
        int leftBrackets = 0;
        int rightBrackets = 0;
        int operations=1;
        while(i < in.length()){
            if(in.charAt(i) == '+'||in.charAt(i) == '-'||in.charAt(i) == '*'||in.charAt(i) == '|'){
                operations++;
            }
            else if(in.charAt(i) == '{'){
                leftBrackets++;

            } else if(in.charAt(i) == '}'){
                rightBrackets++;

            }
            i++;
        }
        return ((leftBrackets+rightBrackets)/4) < operations;
    }


    public void start() throws APException{

        System.out.println("Enter data: (q to quit)");
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line.equals("q")){
                System.out.println("bye i'm quiting");
                System.exit(1);
            }else if(!line.equals("")){
                processLine(line);
            }else{
                System.out.println("Enter data: (q to quit)");
            }
        }
    }

    public static void main(String[] argv) {
        try {
            new Main().start();
        } catch (APException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

// ProcessExpression
// ProcessTerm

/*
Input -> Process line:

Assignment:
- Identifier
- '='
- Expression:
    - Term:
        - Factor:
            - Identifier
            - Expression between brackets
            - Set
        - '*'
        - 0 or more factors
    - '+ , -, / , *'
    - Another term

Print Statement:
- '?'
- Expression:
    - Term:
            - Factor:
                - Identifier
                - Expression between brackets
                - Set
            - '*'
            - 0 or more factors
        - '+ , -, /'
        - Another term

Comment:
- '/'
- Line of text
rein = {1, 2, 5, 16} | {2, 3, 4, 5}
 */