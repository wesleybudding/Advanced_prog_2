public class List<E extends Comparable> implements ListInterface<E> {

    // Deep copy
    // CLEANUP FIND!
    //APExceptions?

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

    private Node head;
    private Node currentNode;
    private int size;

    List() {
        size = 0;
        head = null;
        currentNode = head;
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    @Override
    public ListInterface<E> init() {
        size = 0;
        head = null;
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListInterface<E> insert(E d) {
        if(isEmpty()){
            head = new Node(d,null,null);
            currentNode = head;
        } else{
            Node newNode;
            find(d);

            if ((currentNode == head) && (currentNode.data.compareTo(d) > 0)){
                newNode = new Node(d,null,currentNode);
                head = newNode;
                currentNode.prior = newNode;
            } else if (currentNode == head && currentNode.data.compareTo(d) < 0){
                newNode = new Node(d,currentNode,currentNode.next);
                currentNode.next = newNode;
            }
            else if (currentNode.next == null){
                newNode = new Node(d,currentNode,null);
                currentNode.next = newNode;
            }
            else{
                newNode = new Node(d,currentNode,currentNode.next);
                currentNode.next.prior = newNode;
                currentNode.next = newNode;
            }

            currentNode = newNode;
        }
        size++;
        return this;
    }

    @Override
    public E retrieve() {
        if (!isEmpty()) {
            return currentNode.data;
        }
        return null;
    }

    @Override
    public ListInterface<E> remove() {
        if (isEmpty() | (currentNode == head && (currentNode.next == null))) {
            head = new Node(null);
            currentNode = head;
        } else if (currentNode == head) {
            head.next.prior = null;
            head = head.next;
        } else if (currentNode.next == null) {
            currentNode.prior.next = null;
            currentNode = currentNode.prior;
        } else {
            currentNode.prior.next = currentNode.next;
            currentNode.next.prior = currentNode.prior;
            currentNode = currentNode.next;
        }
        size--;
        return this;
    }

    @Override
    public boolean find(E d) {
        if (goToLast()) {
            while ((!currentNode.data.equals(d)) && (currentNode.data.compareTo(d) > 0)) {
                if (currentNode.prior == null) {
                    return false;
                } else {
                    currentNode = currentNode.prior;
                }
            }

            if(currentNode.data.equals(d)){
                return true;
            }else{
                return false;
            }
        } else {
            currentNode = null;
            return false;
        }
    }


    @Override
    public boolean goToLast() {
        if(isEmpty()){
            return false;
        } else {
            while(currentNode.next != null){
                currentNode = currentNode.next;
            }
            return true;
        }
    }

    @Override
    public boolean goToFirst() {
        if(isEmpty()){
            return false;
        }
        currentNode = head;
        return true;
    }

    @Override
    public boolean goToPrevious() {
        if(isEmpty()){
            return false;
        }
        else if (currentNode.prior == null){
            return false;
        }
        currentNode = currentNode.prior;
        return true;
    }

    @Override
    public boolean goToNext() {
        if(isEmpty()){
            return false;
        } else if (currentNode.next == null){
            return false;
        }
        currentNode = currentNode.next;
        return true;
    }

    @Override
    public ListInterface<E> copy() {
        return this;
    }
}
