import java.math.BigInteger;

public class Set<E extends Comparable> implements SetInterface<E> {

    private List<BigInteger> set;
    private int size;

    Set(){
        set = new List<>();
        size = 0;
    }

    @Override
    public Set<E> union(Set t) {
        Set<E> result = new Set<>();
        int counter = 1;

        while(counter <= cardinality()){
            result.add(retrieve(counter));
            counter++;
        }

        counter = 1;

        while(counter <= t.cardinality()){
            result.add((E) t.retrieve(counter));
            counter++;
        }
        return result;
    }

    @Override
    public Set<E> intersection(Set t) {
        Set<E> result = new Set<>();
        int counter = 1;

        while(counter <= cardinality()){
            if(t.isElement(retrieve(counter))){
                result.add(retrieve(counter));
            }
            counter++;
        }
        return result;
    }

    @Override
    public Set<E> complement(Set t) {
        Set<E> result = new Set<>();
        int counter = 1;

        while(counter <= cardinality()){
            if(!(t.isElement(retrieve(counter)))){
                result.add(retrieve(counter));
            }
            counter++;
        }
        return  result;
    }

    @Override
    public Set<E> indifference(Set t) {
        Set<E> result1 = union(t);
        Set<E> result2 = intersection(t);

        return result1.complement(result2);
    }

    @Override
    public Set<E> add(E d) {
        if(!isElement(d)){
            set.insert((BigInteger) d);
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
            for(int j = 2; j <= i;j++){
                set.goToNext();
            }
            return (E) set.retrieve();
        }
        return null;
    }

    @Override
    public Set<E> remove(E d) {
        if(isElement(d)){
            set.remove();
            size--;
        }
        return this;
    }

    @Override
    public boolean isElement(E d) {
        return set.find((BigInteger) d);
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public int cardinality() {
        return size;
    }
}
