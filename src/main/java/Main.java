import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;


// Create a scanner on System.in

// While there is input, read line and parse it.

// Lucas: maakt Set <E> ADT
// Lucas: maakt punt 3 list
// Wesley: maakt API big integer & hasmap
// Wesley: maakt identifier ADT
// https://www.javaworld.com/article/2076974/learn-java/build-an-interpreter-in-java----implement-the-execution-engine.html
// https://ruslanspivak.com/lsbasi-part1/
// https://en.wikipedia.org/wiki/Extended_Backus–Naur_form
// example string that needs to be interpertated ?
// identifier for math operations seperate or just in reader ?
// 12 as two sepperate or regular numbers


// test
// Betere test

// Vragen voor TA:
// List: Should there be a pointer to the first element ("head"), the last element, and the 'current element?
// List: What should the ListInterface<E> methods return? A list object? Pointer to the current node?
// List: What is the function of init() in comparison to constructor List() -> Should it just create a node that has as data 'null'?
// Ik heb de SetInterface nog wat aangepast, of ze daar misschien nog naar kan kijken.


public class Main {

    int positionNumberLoop = 0;
    Identifier iden = new Identifier();

     void numberLoop(String x, int n){
        int positionNumberLoop = n;
        System.out.print(x.charAt(n));
        if(iden.number(x.charAt(n+1))){
            System.out.print(x.charAt(n));
            positionNumberLoop++;
            numberLoop(x, positionNumberLoop);
        }else if(iden.zero(x.charAt(n+1))){
            positionNumberLoop++;
            numberLoop(x, positionNumberLoop);
        }else{
            positionNumberLoop++;
        }


    }


    private void start() {
        Scanner data = new Scanner(System.in);


        System.out.println("Test input:");

        String file = data.nextLine();
        for(int i = 0 ; i<file.length();i++){
            System.out.println("testing: " + file.charAt(i));
            if(iden.number(file.charAt(i))){
                numberLoop(file , i);
                i=positionNumberLoop;
            }else if(iden.zero(file.charAt(i))){
                System.out.println("zero");
            }else if(iden.letter(file.charAt(i))){
                System.out.println("letter");
            }else if(iden.space(file.charAt(i))){
                System.out.println("space");
            }else{
                System.out.println("invallid ");
            }

        }


    }



    public static void main(String[] argv) {
        new Main().start();
    }
}
