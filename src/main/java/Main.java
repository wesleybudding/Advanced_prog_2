import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

// Create a scanner on System.in

// While there is input, read line and parse it.

// Lucas: maakt Set <E> ADT
// Lucas: maakt punt 3 list
// Wesley: maakt API big integer & hasmap
// Wesley: maakt identifier ADT
// https://www.javaworld.com/article/2076974/learn-java/build-an-interpreter-in-java----implement-the-execution-engine.html
// https://ruslanspivak.com/lsbasi-part1/
// https://en.wikipedia.org/wiki/Extended_Backus–Naur_form


public class Main {

    private void start() {
        Scanner data = new Scanner(System.in);
        Identifier iden = new Identifier();

        System.out.println("Test input:");

        String file = data.nextLine();
        for(int i = 0 ; i<file.length();i++){
            System.out.println("testing: " + file.charAt(i));
            if(iden.number(file.charAt(i))){
                System.out.println("number");
            }else if(iden.zero(file.charAt(i))){
                System.out.println("zero");
            }else if(iden.letter(file.charAt(i))){
                System.out.println("letter");
            }else if(iden.space(file.charAt(i))){
                System.out.println("letter");
            }else{
                System.out.println("invallid ");
            }

        }


    }



    public static void main(String[] argv) {
        new Main().start();
    }
}
