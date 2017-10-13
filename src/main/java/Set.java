public class Set<E extends Comparable> implements SetInterface<E> {

    private List set;
    private int size;

    Set(){
        set = new List();
        size = 0;
    }

    @Override
    public Set<E> union(Set t) {
        Set result = new Set();
        int counter = 1;

        while(counter < cardinality()){
            result.add(retrieve(counter));
            counter++;
        }

        counter = 1;

        while(counter < t.cardinality()){
            result.add(retrieve(counter));
            counter++;
        }
        return result;
    }

    @Override
    public Set<E> intersection(Set t) {
        Set result = new Set();
        int counter = 1;

        while(counter < cardinality()){
            if(t.isElement(retrieve(counter))){
                result.add(retrieve(counter));
                counter++;
            }
        }
        return result;
    }

    @Override
    public Set<E> complement(Set t) {
        Set result = new Set();
        int counter = 1;

        while(counter < cardinality()){
            if(!(t.isElement(retrieve(counter)))){
                result.add(retrieve(counter));
                counter++;
            }
        }
        return  result;
    }

    @Override
    public Set<E> indifference(Set t) {
        Set result1 = union(t);
        Set result2 = intersection(t);

        return result1.complement(result2);
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
    public E retrieve(int i) {
        set.goToFirst();
        if (i == 1){
            return (E) set.retrieve();
        }
        if (i <= size){
            for(int j = 2; j <= i;){
                set.goToNext();
            }
            return (E) set.retrieve();
        }
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
