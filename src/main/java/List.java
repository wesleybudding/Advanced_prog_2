public class List<E extends Comparable> implements ListInterface<E>{


    // Remove and Find classes clean-up
    // Deep copy

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

    List(){
        size = 0;
        head = new Node(null);
        currentNode = head;
    }

    @Override
    public boolean isEmpty() {
        return !(size > 0);
    }

    @Override
    public ListInterface<E> init() {
        size = 0;
        head = new Node(null);
        currentNode = head;
        return this;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ListInterface<E> insert(E d) {

        Node oldHead = head;
        Node newHead = new Node(d,null,oldHead);
        oldHead.prior = newHead;

        head = newHead;
        currentNode = head;
        size++;
        return this;
    }

    @Override
    public E retrieve() {
        if(!isEmpty()){
            return currentNode.data;
        }
        return null;
    }

    @Override
    public ListInterface<E> remove() {
        if(isEmpty() | (currentNode == head && (currentNode.next == null))){
            head = new Node(null);
            currentNode = head;
        } else if(currentNode == head){
            head.next.prior = null;
            head = head.next;
        } else if (currentNode.next == null){
            currentNode.prior.next = null;
            currentNode = currentNode.prior;
        }
        size--;
        return this;
    }

    @Override
    public boolean find(E d) {
        currentNode = head;

        if(isEmpty()){
            currentNode = null;
            return false;
        } else if (head.data.compareTo(d) > 0){
            currentNode = head;
            return false;
        } else {
            while (currentNode.data != d) {
                if (currentNode.next == null) {
                    return false;
                }
                currentNode = currentNode.next;
            }
            return true;
        }
    }

    @Override
    public boolean goToFirst() {
        if(isEmpty()){
            return false;
        } else {
            currentNode = head;
            return true;
        }
    }

    @Override
    public boolean goToLast() {
        if(isEmpty()){
            return false;
        }
        while(currentNode.next != null){
            currentNode = currentNode.next;
        }
        return true;
    }

    @Override
    public boolean goToNext() {
        if((isEmpty()) | (currentNode.next == null)){
            return false;
        }
        currentNode = currentNode.next;
        return true;
    }

    @Override
    public boolean goToPrevious() {
        if((isEmpty()) | (currentNode.prior == null)){
            return false;
        }
        currentNode = currentNode.prior;
        return true;
    }

    @Override
    public ListInterface<E> copy() {
        return this;
    }
}
