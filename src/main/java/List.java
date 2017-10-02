public class List<E extends Comparable> implements ListInterface<E>{

    /* TO-DO:
    Implement lastnode and current node in all methods
    Return statements
    Deep copy?
    Init
    */

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
    private Node tail;
    private Node currentNode;
    private int size;

    List(){
        head = new Node(null);
        tail = head;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ListInterface<E> init() {
        size = 0;
        head = new Node(null);
        tail = head;
        return null;
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
        size++;
        return (ListInterface<E>) d;
    }

    @Override
    public E retrieve() {
        if(!isEmpty()){
            return head.data;
        }
        return null;
    }

    @Override
    public ListInterface<E> remove() {
        if(!isEmpty()){
            head.next.prior = null;
            head = head.next;
        }

        if(isEmpty()){
            return null;
        }
        else{
            //return head;
            return null;
        }

    }

    @Override
    public boolean find(E d) {
        currentNode = head;
        int counter = 0;

        while(currentNode.data != d && counter != size){
            currentNode = currentNode.next;
            counter++;
        }
        if(currentNode.data == d) {
            return true;
        }
        return false;
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
        currentNode = tail.prior;
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
        return null;
    }
}
