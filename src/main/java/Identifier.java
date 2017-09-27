/**
 * Created by wesleybudding on 20/09/2017.
 */
public class Identifier {
    // make interface
    // identifier - add

    char[] nonZeroNumber = "123456789".toCharArray();
    char[] letter = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

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
