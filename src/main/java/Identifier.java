import java.util.Objects;

public class Identifier implements IdentifierInterface {
    private static int MAX_CHAR=100;
    private String identifierString;
    char[] storedChar = new char[MAX_CHAR];
    int charCount;


    @Override
    public void addFirst(char charToStore) {
        charCount=0;
        storedChar[charCount]=charToStore;
        charCount++;
    }

    @Override
    public void add(char charToStore) {
        storedChar[charCount]=charToStore;
        charCount++;
        identifierString += String.valueOf(charToStore);
    }

    @Override
    public char get(char p) {
        return p;
    }

    public String getString(){
        identifierString = "";
        for(int i=0; i < charCount; i++){
            identifierString += storedChar[i];
        }
        return identifierString;
    }

    @Override
    public int size() {
        return charCount;
    }

    @Override public int hashCode(){
        return Objects.hash(identifierString);
    }

    @Override public boolean equals(final Object obj){
        Identifier object = (Identifier) obj;
        if(this.getString().equals(object.getString())){
            return true;
        }
        return false;
    }
}