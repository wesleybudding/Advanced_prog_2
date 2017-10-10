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
// List: What should the ListInterface<E> methods return? A list object? Pointer to the current node? list
// Ik heb de SetInterface nog wat aangepast, of ze daar misschien nog naar kan kijken.
// List should order numbers.
// list can contain duplicate numbers, but set should be unique
// Should the set order the input numbers, or should main do this? main
// what do step 6 and 9 mean?
// parser in main should arange everything.
// 6 is the ebnf interperter = parser of lines


//BigInteger: String as input
// BigInteger bigInt = new BigInteger("40000");

// Hashmap h = new Hashmap();
// h.put(key,value)


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
        Identifier identifier = new Identifier();
        System.out.println("Test input:");
        String line = data.nextLine();
        identifier.identifier(line);
        }






    public static void main(String[] argv) {
        new Main().start();
    }
}
