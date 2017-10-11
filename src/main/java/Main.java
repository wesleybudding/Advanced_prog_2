import java.util.Scanner;
import java.math.BigInteger;
import java.util.HashMap;


// Create a scanner on System.in

// While there is input, read line and parse it.
// Lucas: LIST & SET operation afmaken.
// Wesley: Identifier, EBNF method, hashmap 



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
