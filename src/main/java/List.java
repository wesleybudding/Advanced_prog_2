public class List<E extends Comparable> implements ListInterface<E>{

    private class Node {

        E data;
        Node prior,
                next;

        public Node(E d) {
            this(d, null, null);
        }

        public Node(E data, Node prior, Node next) {
            this.data = data == null ? null : data;
            this.prior = prior;
            this.next = next;
        }

    }

    Node listHeadPointer;
    int size;

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ListInterface<E> init() {
        size = 0;
        listHeadPointer = null;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListInterface<E> insert(E d) {
        Node firstNode = listHeadPointer;
        Node newNode = new Node(d,null,firstNode);
        firstNode.prior = newNode;

        listHeadPointer = newNode;
        size++;
        return (ListInterface<E>) d;
    }

    @Override
    public E retrieve() {
        if(!isEmpty()){
            return listHeadPointer.data;
        }
        return null;
    }

    @Override
    public ListInterface<E> remove() {
        return null;
    }

    @Override
    public boolean find(E d) {
        return false;
    }

    @Override
    public boolean goToFirst() {
        return false;
    }

    @Override
    public boolean goToLast() {
        return false;
    }

    @Override
    public boolean goToNext() {
        return false;
    }

    @Override
    public boolean goToPrevious() {
        return false;
    }

    @Override
    public ListInterface<E> copy() {
        return null;
    }
}
