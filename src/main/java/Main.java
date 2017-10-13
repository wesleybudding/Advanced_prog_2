import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;

// Create a scanner on System.in

// While there is input, read line and parse it.
// Lucas: LIST & SET operation afmaken.
// Wesley: Identifier, EBNF method, hashmap

public class Main {

    //    char[] letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
//    char[] number = "0123456789".toCharArray();
//    //HashMap<Identifier, String> storageIdentifiers = new HashMap<Identifier, String>();
//
////    void storeIdenfifier(String input){
////        Identifier store = new Identifier();
////        store.addFirst(input.charAt(0));
////
////        for(int i=1; i<input.length();i++){
////            if(isLetter(input.charAt(i))||isNumber(input.charAt(i))){
////                store.add(input.charAt(i));
////            }else{
////                System.out.println("not expected character");
////                System.exit(1);
////            }
////
////        }
////        System.out.println("stored: " + input );
////    }
//
//    void processPrintStatement(String input){
//        // print
//        processAssignmentStatement(input);
//    }
//
//    void processAssignmentStatement(String input){
//        Scanner line = new Scanner(input);
//        System.out.println("Identifier = " + line.next()); // Identifier:
//        String equals = line.next();
//
//        if((line.hasNext()) && (equals.equals("="))){
//            while (line.hasNext()){
//                processExpression(line.nextLine());
//            }
//        } else {
//            System.out.println("Wrong argument");
//            System.exit(1);
//        }
//    }
//
//    void processExpression(String input){
//        Scanner line = new Scanner(input);
//        String check = line.next();
//
//        if ((check.charAt(0) == '-') || (check.charAt(0) == '+') || (check.charAt(0) == '|')){
//            System.out.println("Calculate operator:" + check);
//        }
//
//        if(line.hasNext()){
//        processTerm(check + line.nextLine());
//        }  else{
//            processTerm(check);
//        }
//    }
//
//    void processTerm(String input){
//        Scanner line = new Scanner(input);
//        String check = line.next();
//
//        if((check.charAt(0) == '*')){
//            processFactor(line.nextLine());
//
//        } else {
//            if(line.hasNext()){
//                processFactor(check + line.nextLine());
//            }
//            else{
//                processFactor(check);
//            }
//        }
//    }
//
//    void processFactor(String input){
//        Scanner line = new Scanner(input);
//        String check = line.nextLine();
//
//        if(isLetter(check.charAt(0))){
//            System.out.println("Identifier: " + check);
//        } else if (check.charAt(0) == '('){
//            String expression = extractExpressionFromBraces(check,0);
//            System.out.println("Complex factor" + check);
//            processExpression(expression);
//
//        } else if((check.charAt(0) == '{') && (input.charAt(input.length() - 1) == '}')){
//            System.out.println("New set: " + check);
//        }
//    }
//
//    boolean space(char x){
//        if(x==' '){
//            return true;
//        }
//        return false;
//    }
//
//    void returnResults(String input){
//
//    }
//
//    void processLine(String input){
//        Scanner line = new Scanner(input);
//        char start = line.next().charAt(0);
//
//        if(start=='/'){
//            System.out.println("Comment!");
//        } else if(start=='?'){
//            System.out.println("Print statement");
//            processPrintStatement(input);
//        } else if(isLetter(start)){
//            System.out.println("Assignment statement");
//            processAssignmentStatement(input);
//            //readStatement(input);
//        } else{
//            System.out.println("invalid!");
//        }
//
//    }
//
//    boolean isLetter(char x){
//        Character check = new Character(x);
//        for(int i = 0; i<51; i++){
//            if(check.equals(letter[i])){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    boolean isNumber(char x){
//        Character check = new Character(x);
//        for(int i = 0; i<9; i++){
//            if(check.equals(number[i])){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    void start() {
//        System.out.println("Enter data:");
//        Scanner data = new Scanner(System.in);
//        while(data.hasNextLine()){
//            processLine(data.nextLine());
//        }
//    }
//
//    private static String extractExpressionFromBraces(String expression, int startposition) {
//        int braceDepth = 1;
//        String subexp="";
//
//        for (int i = startposition+1; i < expression.length(); i++) {
//            switch (expression.charAt(i)) {
//                case '(':
//                    braceDepth++;
//                    subexp += "(";
//                    break;
//                case ')':
//                    braceDepth--;
//                    if (braceDepth != 0) subexp += ")";
//                    break;
//                default:
//                    if (braceDepth > 0) subexp += expression.charAt(i);
//
//            }
//            if (braceDepth == 0 && !subexp.equals("")) return subexp;
//        }
//        return "Failure!";
//    }
    char[] letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    char[] number = "0123456789".toCharArray();
    HashMap<Identifier,Set> storage = new HashMap<>();

    Identifier storeIdenfifier(String input){
        Identifier store = new Identifier();
        store.addFirst(input.charAt(0));

        for(int i=1; i<input.length();i++){
            if(isLetter(input.charAt(i))||isNumber(input.charAt(i))){
                store.add(input.charAt(i));
            } else if (space(input.charAt(i))){
                // do nothing
            }

            else{
                System.out.println("not expected character");
                System.exit(1);
            }

        }
        System.out.println("stored: " + input );
        return store;
    }

    boolean space(char x){
        if(x==' '){
            return true;
        }
        return false;
    }

    void processLine(String in){
        if(in.charAt(0) == '/'){
            System.out.println("comment!");
        } else if (isLetter(in.charAt(0))){
            System.out.println("Assignment statement!");
            processAssignment(in);
        } else if (in.charAt(0) == '?'){
            System.out.print("Print statement!");
            processPrint(in);
        } else{
            System.out.println("Wrong argument!");
        }
    }

    void processPrint(String in){
        Identifier identifier;
        if(in.charAt(1) == ' '){
            identifier = storeIdenfifier(in.substring(2));
        } else {
            identifier = storeIdenfifier(in.substring(1));
        }
        Set s = storage.get(identifier);
        System.out.println(s);
    }

    void processAssignment(String in){
        int i = 0;
        while(!(in.charAt(i) == '=') && !(in.charAt(i) == ' ')){
            i += 1;
        }

        Identifier name = storeIdenfifier(in.substring(0,i));

        while(!(in.charAt(i) == '=')){
            i+=1;
        }
        String equals = in.substring(i,i+1);

        Set s = processExpression(in.substring(i+1,in.length()));
        storage.put(name,s);
    }

    Set processExpression(String in){
        int i = 0;

        while(!(i == in.length())){
            if((in.charAt(i) == '+') || (in.charAt(i) == '-') || (in.charAt(i) == '|')){
                String term = in.substring(0,i);
                String operator = in.substring(i,i+1);
                System.out.println("Operator is: " + operator);
                Set set2 = processExpression(in.substring(i+1));
                Set set1 = processTerm(term);

                if(in.charAt(i) == '+') return set1.union(set2);
                else if (in.charAt(i) == '-') return set1.complement(set2);
                else if (in.charAt(i) == '|') return set1.indifference(set2);
            }
            i +=1;
        }

        System.out.println("Term is: " + in);
        return processTerm(in);
    }

    Set processTerm(String in){
        System.out.println("We will process the term: " + in);
        int i = 0;
        while (!(i == in.length())){
            if(in.charAt(i) == '('){
                System.out.println("Complex factor");
            }

            if(in.charAt(i) == '*'){
                String factor = in.substring(0,i);
                String operator = in.substring(i,i+1);
                System.out.println("MOp: " + operator);
                Set firstSet = processFactor(factor);
                Set secondSet = processTerm(in.substring(i+1));
                return firstSet.intersection(secondSet);
            }
            i +=1;
        }
        return processFactor(in);
    }

    Set processFactor(String in){
        System.out.println("We will process factor:" + in);
        int i = 0;
        while(!(i == in.length())){
            if(isLetter(in.charAt(i))){
                Identifier name = storeIdenfifier(in.substring(i));
                System.out.println("Identifier! The name is:" + name);
                return(storage.get(name));
            } else if(in.charAt(i) == '{'){
                String set = in.substring(i);
                System.out.println("SET! The set is:" + set);
                Set s = new Set<BigInteger>();
                return(processSet(s,set));
            } else if(in.charAt(i) == '('){
                String complexFactor = in.substring(i);
                System.out.println("COMPLEX FACTOR!! The complex factor is: " + complexFactor);
            }
            i+=1;
        }
        return null;

    }

    Set processSet(Set s, String in){
        if(in.charAt(0) == '{' && (in.charAt(1) == '}' || (!isNumber(in.charAt(1)) && (in.charAt(2) == '}')))){
            System.out.println("Empty set!");
            return s;
        }

        if((in.charAt(0) == '{') && ((in.charAt(in.length()-1) == '}') || (in.charAt(in.length() -2) == '}') )){
            int i = 0;
            while(!(i == in.length())){
                if(isNumber(in.charAt(i))){
                    String number = String.valueOf(in.charAt(i));
                    int j = i+1;
                    while(!(in.charAt(j) == ',') && !(in.charAt(j) == '}')){
                        number += String.valueOf(in.charAt(j));
                        j+=1;
                    }
                    i = j;
                    s.add(new BigInteger(number));
                }
                i +=1;
            }
        }
        return s;
    }

    boolean isLetter(char x){
        Character check = new Character(x);
        for(int i = 0; i<51; i++){
            if(check.equals(letter[i])){
                return true;
            }
        }
        return false;
    }

    boolean isNumber(char x){
        Character check = new Character(x);
        for(int i = 0; i<9; i++){
            if(check.equals(number[i])){
                return true;
            }
        }
        return false;
    }

    public void start(){
        System.out.println("Enter data:");
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String line = in.nextLine();
            processLine(line);
        }
    }

    public static void main(String[] argv) {
        new Main().start();
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


