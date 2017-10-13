public class Identifier implements IdentifierInterface{

    public static int MAX_POSITIONS=50;

    private int position;
    private char [] letters;

    @Override
    public void addFirst(char charToStore) {
        letters = new char[MAX_POSITIONS];
        position=0;
        letters[position]=charToStore;
        position++;
    }

    @Override
    public void add(char charToStore) {
        letters[position]=charToStore;
        position++;
    }

    @Override
    public char get(char p) {
        return letters[p];
    }

    @Override
    public int size() {
        return position;
    }
}