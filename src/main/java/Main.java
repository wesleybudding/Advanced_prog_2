import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;


// Create a scanner on System.in

// While there is input, read line and parse it.
// Lucas: LIST & SET operation afmaken.
// Wesley: Identifier, EBNF method, hashmap

public class Main {

    char[] letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    char[] number = "0123456789".toCharArray();
    HashMap<Identifier, String> storageIdentifiers = new HashMap<Identifier, String>();

    void storeIdenfifier(String input){
        Identifier store = new Identifier();
        store.addFirst(input.charAt(0));
        for(int i=1; i<input.length();i++){
            if(isLetter(input.charAt(i))||isNumber(input.charAt(i))){
                store.add(input.charAt(i));
            }else{
                System.out.println("not expected character");
                System.exit(1);
            }

        }
        System.out.println("stored: " + input );
    }


    void readStatement(String input) {
        Scanner line = new Scanner(input);
        storeIdenfifier(line.next());

        if (line.hasNext()&&line.next().equals("=")) {
            while (line.hasNext()) {
                String check = line.next();
                if (check.charAt(0) == '{' && input.charAt(input.length() - 1) == '}') {
                    System.out.println("here come the numbers:");
                    String printtest = line.nextLine();
                    System.out.println(printtest);
                }
                else if (isLetter(check.charAt(0))){
                    System.out.println("we should have a stored list for: " + check);
                }
                else if (check.charAt(0)=='-'||check.charAt(0)=='+'||check.charAt(0)=='*'||check.charAt(0)=='|'){
                    System.out.println("calculate operator: " + check);
                }
            }
        }else {
            System.out.println("Wrong argument");
            System.exit(1);
        }
    }

    boolean space(char x){
        if(x==' '){
            return true;
        }
        return false;
    }

    void returnResults(String input){

    }

    void processLine(String input){
        Scanner line = new Scanner(input);
        char start = line.next().charAt(0);
        if(start=='/'){
            System.out.println("comment!");
        }else if(start=='?'){
            System.out.println("statement");
        }else if(isLetter(start)){
            readStatement(input);
        }else{
            System.out.println("invalid!");
        }

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

    void start() {
        System.out.println("enter data:");
        Scanner data = new Scanner(System.in);
        while(data.hasNextLine()){
            processLine(data.nextLine());
        }

        }


    public static void main(String[] argv) {
        new Main().start();
    }
}
