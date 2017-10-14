import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;


// Lucas: LIST & SET operation afmaken.
// Wesley: Identifier, EBNF method, hashmap

// Parser/Interpreter afmaken:
// - Support voor () toevoegen
// - Zorgen dat Hashmap ook iets kan vinden -> hashcode class in Identifier?
// - Exceptions adden waar nodig
// - Cleanup

public class Main {
    private char[] letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private char[] number = "0123456789".toCharArray();
    private HashMap<Identifier,Set> storage = new HashMap<>();

    private Identifier storeIdenfifier(String input) throws APException{
        Identifier store = new Identifier();
        store.addFirst(input.charAt(0));

        for(int i=1; i<input.length();i++){
            if(isLetter(input.charAt(i))||isNumber(input.charAt(i))) {
                store.add(input.charAt(i));
            } else{
                throw new APException("Not expected character. Identifier should start with a letter, and contain only letters and numbers.");
            }
        }
        System.out.println("stored: " + input );
        return store;
    }

    private void processLine(String in) throws APException {
        if(in.charAt(0) == '/'){
            System.out.println("comment!");
        } else if (isLetter(in.charAt(0))){
            System.out.println("Assignment statement!");
            processAssignment(in);
        } else if (in.charAt(0) == '?'){
            System.out.print("Print statement!");
            processPrint(in);
        } else{
            throw new APException("Incorrect input. Input should start with letter, slash, or question mark.");
        }
    }

    private void processPrint(String in) throws APException{
        Set s = processExpression(in.substring(1));
        System.out.println(s);
    }

    private void processAssignment(String in) throws APException{
        int i = 0;
        while(!(in.charAt(i) == '=') && !(in.charAt(i) == ' ')){
            i += 1;
        }

        Identifier name = storeIdenfifier(in.substring(0,i));

        while(!(in.charAt(i) == '=')){
            i+=1;
        }
        String equals = in.substring(i,i+1);
        System.out.println(equals);

        Set s = processExpression(in.substring(i+1,in.length()));
        storage.put(name,s);
    }

    private Set processExpression(String in) throws APException{
        if(in.length() <= 1){
            throw new APException("Invalid input after '=' sign.");
        }

        int i = 0;

        while(!(i == in.length())){

            if((in.charAt(i) == '+') || (in.charAt(i) == '-') || (in.charAt(i) == '|')){
                String term = in.substring(0,i);
                String operator = in.substring(i,i+1);
                System.out.println("Operator is: " + operator);
                Set set1 = processTerm(term);
                Set set2 = processExpression(in.substring(i+1));

                if(in.charAt(i) == '+') return set1.union(set2);
                else if (in.charAt(i) == '-') return set1.complement(set2);
                else if (in.charAt(i) == '|') return set1.indifference(set2);
            }
            i +=1;
        }

        System.out.println("Term is: " + in);
        return processTerm(in);
    }

    private Set processTerm(String in) throws APException{
        System.out.println("We will process the term: " + in);
        int i = 0;
        while (!(i == in.length())){
            if(in.charAt(i) == '('){
                System.out.println("Complex factor");
            }

            if(in.charAt(i) == '*'){
                String factor = in.substring(0,i);
                String operator = in.substring(i,i+1);
                System.out.println("Multiplicative Operator: " + operator);
                Set firstSet = processFactor(factor);
                Set secondSet = processTerm(in.substring(i+1));
                return firstSet.intersection(secondSet);
            }
            i +=1;
        }
        return processFactor(in);
    }

    private Set processFactor(String in) throws APException{
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

    private Set processSet(Set s, String in){
        if(in.charAt(0) == '{' && (in.charAt(1) == '}' || (!isNumber(in.charAt(1)) && (in.charAt(2) == '}')))){
            System.out.println("Empty set!");
            return s;
        }

        if((in.charAt(0) == '{') && ((in.charAt(in.length()-1) == '}') || (in.charAt(in.length() -2) == '}') )){
            int i = 0;
            while(!(i == in.length())){
                if(isNumber(in.charAt(i))){
                    StringBuilder number = new StringBuilder(String.valueOf(in.charAt(i)));
                    int j = i+1;
                    while(!(in.charAt(j) == ',') && !(in.charAt(j) == '}')){
                        number.append(String.valueOf(in.charAt(j)));
                        j+=1;
                    }
                    i = j;
                    s.add(new BigInteger(number.toString()));
                }
                i +=1;
            }
        }
        return s;
    }

    private boolean isLetter(char x){
        Character check = x;
        for(int i = 0; i<51; i++){
            if(check.equals(letter[i])){
                return true;
            }
        }
        return false;
    }

    private boolean isNumber(char x){
        Character check = x;
        for(int i = 0; i<9; i++){
            if(check.equals(number[i])){
                return true;
            }
        }
        return false;
    }

    public void start() throws APException{
        System.out.println("Enter data:");
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String line = in.nextLine();
            processLine(line);
        }
    }

    public static void main(String[] argv) {
        try {
            new Main().start();
        } catch (APException e) {
            e.printStackTrace();
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