import java.util.Objects;

public class Identifier implements IdentifierInterface {

    private String identifierString;

    @Override
    public void addFirst(char charToStore) {
        identifierString += String.valueOf(charToStore);
    }

    @Override
    public void add(char charToStore) {
        identifierString += String.valueOf(charToStore);
    }

    @Override
    public char get(char p) {
        return p;
    }

    public String getString(){
        return identifierString;
    }

    @Override
    public int size() {
        return identifierString.length();
    }

    @Override public int hashCode(){
        return Objects.hash(identifierString);
    }
}