/**
 * Created by wesleybudding on 20/09/2017.
 */
public class Identifier {

    //char[] nonZeroNumber =  {"1","2","3","4","5","6","7","8","9" };
    char[] nonZeroNumber = "123456789".toCharArray();
    char[] letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    //char[] letter =  { "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
    //                    "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z" };


    boolean number(char x){
        for(int i = 0; i<9; i++){
            Character check = new Character(x);
            if(check.equals(nonZeroNumber[i])){
                return true;
                //System.out.println("true");
            }
        }
            return false;
    }

    boolean zero(char x){
            if(x==0){
                return true;
            }

        return false;
    }

    boolean letter(char x){
        Character check = new Character(x);
        for(int i = 0; i<51; i++){
            if(check.equals(letter[i])){
                return true;
            }
        }
        return false;
    }

    boolean space(char x){
        Character check = new Character(x);
        if(check.equals(" ")){
            return true;
        }
        return false;
    }


}
