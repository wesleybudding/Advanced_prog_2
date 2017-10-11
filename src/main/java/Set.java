public class Set<E extends Comparable> implements SetInterface<E> {

    // Look at retrieve

    private List set;
    private int size;

    Set(){
       set = new List();
       size = 0;
    }

    @Override
    public SetInterface<E> union(SetInterface t) {
        Set result = new Set();
        int counter = 0;

        firstElement();
        while(counter < cardinality()){
            result.add(retrieve());
            nextElement();
        }
        counter = 0;
        firstElement();
        while(counter < t.cardinality()){
            result.add(t.retrieve());
            t.nextElement();

        }
        return result;
    }

    @Override
    public SetInterface<E> intersection(SetInterface t) {
        return null;
    }

    @Override
    public SetInterface<E> complement(SetInterface t) {
        return null;
    }

    @Override
    public SetInterface<E> indifference(SetInterface t) {
        return null;
    }

    @Override
    public SetInterface<E> add(E d) {
        if(!isElement(d)){
            set.insert(d);
            size++;
        }
        return this;
    }

    @Override
    public E retrieve() {
        set.retrieve();
        return null;
    }

    @Override
    public SetInterface<E> remove(E d) {
        if(isElement(d)){
            set.remove();
            size--;
        }
        return this;
    }

    @Override
    public boolean isElement(E d) {
        return set.find(d);
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public int cardinality() {
        return size;
    }

    @Override
    public void firstElement(){
        set.goToFirst();
    }

    @Override
    public void nextElement(){
        set.goToNext();
    }
}
