import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;

public class Main {

    private char[] letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private char[] number = "123456789".toCharArray();
    private HashMap<String, Set> storage = new HashMap<>();

    private Identifier storeIdenfifier(String input) throws APException {
        Identifier store = new Identifier();
        store.addFirst(input.charAt(0));

        for (int i = 1; i < input.length(); i++) {
            if (isLetter(input.charAt(i)) || isNumber(input.charAt(i))) {
                store.add(input.charAt(i));

            } else if (input.charAt(i) == ' ' && i == input.length() - 1) {
                return store;
            } else if ((input.charAt(i) == ' ') && ((isNumber(input.charAt(i + 1))) || (isLetter(input.charAt(i + 1))))) {
                throw new APException("No spaces allowed in Identifier");
            } else if (input.charAt(i) == ' '){
                // Do nothing
            }
            else {
                throw new APException("Not expected character. Identifier should start with a letter, and contain only letters and numbers.");
            }
        }
        return store;
    }

    private void printSet(Set<BigInteger> s) {
        if (s.isEmpty()) {

        } else {
            for (int i = 1; i <= s.cardinality(); i++) {
                System.out.print(s.retrieve(i) + " ");
            }
        }
        System.out.println();
    }

    private void processLine(String in) throws APException {
        if (in.charAt(0) == '/') {
            //Do nothing
        } else if (!countParentheses(in)) {
            throw new APException("Amount of parentheses is not correct!");
        } else if (!countBrackets(in)) {
            throw new APException("Amount of brackets is not correct!");
        }  else if (!countEqualssigns(in)) {
            throw new APException("Too many equal signs");
        } else if (!setsComparedToOperations(in)) {
            throw new APException("Amount operations missing for amount sets");
        }  else if (isLetter(in.charAt(0))) {
            processAssignment(in);
        } else if (in.charAt(0) == '?') {
            processPrint(in);
        } else {
            throw new APException("Incorrect input. Input should start with letter, slash, or question mark.");
        }
    }

    private void processPrint(String in) throws APException {
        Set<BigInteger> s = processExpression(in.substring(1));
        printSet(s);
    }

    private void processAssignment(String in) throws APException {
        int i = 0;
        while (i<in.length()-1 && !(in.charAt(i) == '=') && !(in.charAt(i) == ' ') ) {
            i += 1;
        }

        Identifier identfier = storeIdenfifier(in.substring(0, i));

        while (!(in.charAt(i) == '=')) {
            if (i == in.length() - 1) {
                throw new APException("No equals sign found.");
            } else {
                i += 1;
            }
        }

        Set<BigInteger> s = processExpression(in.substring(i + 1, in.length()));
        String name = identfier.getString();
        storage.put(name, s);
    }

    private Set<BigInteger> processComplexFactor(String in, int startPosition) throws APException {

        int endBracket = findClosingParentheses(in, startPosition + 1);
        String term1 = in.substring(startPosition + 1, endBracket);

        if (endBracket + 2 < in.length()) {
            String rest = in.substring(endBracket + 1);


            for (int j = 0; j < rest.length(); j++) {
                if ((rest.charAt(j) == '+')) {
                    Set<BigInteger> set1 = processExpression(term1);
                    Set<BigInteger> set2 = processExpression(rest.substring(j + 1));
                    return set1.union(set2);
                }
                if ((rest.charAt(j) == '-')) {
                    Set<BigInteger> set1 = processExpression(term1);
                    Set<BigInteger> set2 = processExpression(rest.substring(j + 1));
                    return set1.complement(set2);
                }
                if ((rest.charAt(j) == '|')) {
                    Set<BigInteger> set1 = processExpression(term1);
                    Set<BigInteger> set2 = processExpression(rest.substring(j + 1));
                    return set1.indifference(set2);
                }
                if ((rest.charAt(j) == '*')) {
                    Set<BigInteger> set1 = processExpression(term1);
                    Set<BigInteger> set2 = processExpression(rest.substring(j + 1));
                    return set1.intersection(set2);
                }
            }
        }
        return processExpression(term1);
    }

    private Set<BigInteger> processExpression(String in) throws APException {

        Set[] setArray = new Set[10000];
        char[] operators = new char[10000];
        int lastterm = 0;
        int index = 0;

        if (in.length() < 1) {
            throw new APException("Invalid input after '=' sign.");
        }

        for(int i = 0; i < in.length(); i++){
            if (in.charAt(i) == '(') {
                i = findClosingParentheses(in,i+1);
            }

            if((in.charAt(i) == '+') || (in.charAt(i) == '-') || (in.charAt(i) == '|')){
                String term = in.substring(lastterm,i);
                lastterm = i+1;
                Set s = processTerm(term);
                setArray[index] = s;
                operators[index] = in.charAt(i);
                index++;
            }

            if(i == in.length() - 1 && (index > 0)){
                String finalTerm = in.substring(lastterm);
                Set finalSet = processTerm(finalTerm);
                setArray[index] = finalSet;
                index++;
            }
        }

        if(index > 0){
            Set result = new Set<>();
            int i = 0;

            while(i < index-1){
                char operator = operators[i];
                Set s1 = setArray[i];
                Set s2 = setArray[++i];
                if(operator == '+'){
                    result = s1.union(s2);
                } else if (operator == '-'){
                    result = s1.complement(s2);
                } else if (operator == '|'){
                    result = s1.indifference(s2);
                }
                setArray[i] = result;
            }
            return result;
        } else {
            return processTerm(in);
        }
    }

    private Set<BigInteger> processTerm(String in) throws APException {
        for (int i = 0; i < in.length(); i++) {
            if(in.charAt(i) == '('){
                return processComplexFactor(in,i);
            }
            else if (in.charAt(i) == '*') {
                String factor = in.substring(0, i);
                Set<BigInteger> firstSet = processFactor(factor);
                Set<BigInteger> secondSet = processTerm(in.substring(i + 1));
                return firstSet.intersection(secondSet);
            }
        }
        return processFactor(in);
    }

    private Set<BigInteger> processFactor(String in) throws APException {



        for (int i = 0; i < in.length(); i++) {
            if (isLetter(in.charAt(i))) {
                Identifier name = storeIdenfifier(in.substring(i));
                Set<BigInteger> s = storage.get(name.getString());
                if (s == null) {
                    throw new APException("Set not found.");
                } else {
                    return s;
                }
            } else if (in.charAt(i) == '{') {
                String set = in.substring(i);
                return (processSet(set));
            }
        }
        throw new APException("Input not valid.");
    }

    private Set<BigInteger> processSet(String in) throws APException {
        Set<BigInteger> s = new Set<>();
        int startSet = 0, endSet = 0;

        for(int i = 0; i<in.length();i++){
            if (in.charAt(i) == '{'){
                startSet = i;
            }else if(in.charAt(i) == '}'){
                endSet = i;
            }
        }

        if(endSet == in.length() -1 ){
            String valuesInSet = in.substring(startSet+1,endSet);
            checkValuesInString(valuesInSet);
            checkDoublevalues(valuesInSet);
        } else if (in.charAt(in.length() -1 ) != ' '){
            throw new APException("Set not valid");
        }

        return processRowNaturalNumbers(in.substring(startSet+1,endSet), s);
    }

    private Set<BigInteger> processRowNaturalNumbers(String in, Set s) throws APException {
        for (int i = 0; i < in.length(); i++) {
            if (isNumber(in.charAt(i)) || in.charAt(i) == '0') {
                int j = i;
                while ((j < in.length()) && !(in.charAt(j) == ',') && !(in.charAt(j) == '}') && !(in.charAt(j) == ' ')) {
                    j++;
                }
                s.add(processNaturalNumber(in.substring(i, j)));
                i = j;
            }
        }
        return s;
    }

    private BigInteger processNaturalNumber(String in) throws APException {

        if (in.charAt(0) == '0' && (in.length() == 1)) {
        } else if (isNumber(in.charAt(0))) {
        } else {
            throw new APException("Number not allowed");
        }

        return new BigInteger(in);
    }

    private int findClosingParentheses(String in, int startPosition) {
        int i = startPosition;

        int counter = 1;
        while (counter > 0) {
            if (in.charAt(i) == '(') {
                counter++;
            }
            if (in.charAt(i) == ')') {
                counter--;
            }
            i++;
        }
        return i - 1;
    }

    private boolean isLetter(char x) {
        Character check = x;
        for (int i = 0; i <= 51; i++) {
            if (check.equals(letter[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean isNumber(char x) {
        Character check = x;
        for (int i = 0; i <= 8; i++) {
            if (check.equals(number[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmpty(String in) throws APException{
        for(int i=0; i<in.length();i++){
            if(in.charAt(i) == ' '){
                //check
            }
            else {
                return false;
            }
        }
        return true;
    }

    private boolean checkValuesInString(String in) throws APException{
        for(int i=0; i<in.length();i++){
            if(isNumber(in.charAt(i)) || in.charAt(i) == ',' || in.charAt(i) == '0' || in.charAt(i) == ' '){
                // check
            }else {
                throw new APException("Invalid data in set");
            }
        }
        return true;
    }

    private boolean checkDoublevalues(String in) throws APException{

        Scanner setIn = new Scanner(in);
        setIn.useDelimiter(",");
        int counterComma=0;

        for(int i=1;i<in.length();i++){
            if(in.charAt(i)==','){
                counterComma++;
            }
        }

        while(setIn.hasNext()){
            String test = setIn.next();

            if(test.length()==0) {
                throw new APException("invalid data in set length");
            }
            if(isEmpty(test)&&counterComma>=1){
                throw new APException("invalid data in set empty");
            }
            Scanner checkNumber = new Scanner(test);
            if(checkNumber.hasNext()) {
                String cn = checkNumber.next();
                if(isEmpty(cn)){
                    throw new APException("invalid data in set empty 2");
                }
                if (checkNumber.hasNext()) {
                    throw new APException("invalid data in set has next");
                }
            }
        }
        return true;
    }

    private boolean countParentheses(String in) {
        int i = 0;
        int leftParentheses = 0;
        int rightParentheses = 0;
        while (i < in.length()) {
            if (in.charAt(i) == '(') {
                leftParentheses++;

            } else if (in.charAt(i) == ')') {
                rightParentheses++;

            }
            i++;
        }
        return leftParentheses == rightParentheses;
    }

    private boolean countBrackets(String in) {
        int i = 0;
        int leftBrackets = 0;
        int rightBrackets = 0;
        while (i < in.length()) {
            if (in.charAt(i) == '{') {
                leftBrackets++;

            } else if (in.charAt(i) == '}') {
                rightBrackets++;

            }
            i++;
        }
        return leftBrackets == rightBrackets;
    }

    private boolean countEqualssigns(String in){
        int countEquals=0;
        for(int i=0;i<in.length();i++){
            if(in.charAt(i) == '='){
                countEquals++;
            }
        }
        if(countEquals<=1){
            return true;
        }else{
            return false;
        }
    }

    private boolean setsComparedToOperations(String in) {
        int i = 0;
        int leftBrackets = 0;
        int rightBrackets = 0;
        int operations = 1;
        while (i < in.length()) {
            if (in.charAt(i) == '+' || in.charAt(i) == '-' || in.charAt(i) == '*' || in.charAt(i) == '|') {
                operations++;
            } else if (in.charAt(i) == '{') {
                leftBrackets++;

            } else if (in.charAt(i) == '}') {
                rightBrackets++;

            }
            i++;
        }
        return ((leftBrackets + rightBrackets) / 4) < operations;
    }

    private void start() throws APException {

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (!line.equals("")) {
                processLine(line);
            } else {
                throw new APException("No data");
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