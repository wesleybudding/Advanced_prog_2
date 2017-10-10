public class Set<E extends Comparable> implements SetInterface<E> {

    // Look at retrieve

    List set;

    Set(){
       set = new List();
    }

    @Override
    public SetInterface<E> union(SetInterface t) {
        return null;
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
        }
        return this;
    }

    @Override
    public E retrieve(E d) {
        if (isElement(d)) {
            set.retrieve();
            return d;
        }
        return null;
    }

    @Override
    public SetInterface<E> remove(E d) {
        if(isElement(d)){
            set.remove();
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
        return set.size();
    }
}
