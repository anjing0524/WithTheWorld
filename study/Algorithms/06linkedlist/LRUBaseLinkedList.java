import java.util.Scanner;
public class LRUBaseLinkedList<T>{
    // 默认长度
    private final static int DEDAULT_CAPACITY = 10;
    // 头节点
    private MyNode<T> headNode;
    // 链表长度
    private int length;
    // 链表容量
    private int capacity;

    public LRUBaseLinkedList(){
        this.headNode = new MyNode<>();
        this.capacity = DEDAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(int capacity){
        this.headNode = new MyNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add (T data){
        MyNode preNode = this.findPreNode(data);
        if(preNode != null){
            deleteElemOptim(preNode);
            insertElemAtBegin(data);
        }else {
            if (length >= this.capacity) {
                //删除尾结点
                deleteElemAtEnd();
            }
            insertElemAtBegin(data);
        }
    }

     /**
     * 删除preNode结点下一个元素
     *
     * @param preNode
     */
    private void deleteElemOptim(MyNode preNode) {
        MyNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        this.length--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void insertElemAtBegin(T data) {
        MyNode next = this.headNode.getNext();
        headNode.setNext(new MyNode(data, next));
        length++;
    }

    public void deleteElemAtEnd(){
        MyNode node = this.headNode;
        if(node.getNext() == null){
            return ;
        }
        while(node.getNext().getNext() != null){
            node = node.getNext();
        }
        MyNode temp = node.getNext();
        node.setNext(null);
        temp = null;
        this.length --;
    }
    
    private MyNode findPreNode(T data){
        MyNode node = this.headNode;
        while(node.getNext() != null){
            if(data.equals(node.getNext().getElement())){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    private void printAll() {
        MyNode node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }
    

    public class MyNode<T>{
        private T element;
        private MyNode next;
        
        public MyNode(T element){
            this.element = element;
        }

        public MyNode(T element,MyNode next){
            this.element = element;
            this.next = next;
        }
        
        public MyNode() {
            this.next = null;
        }

        public T getElement(){
            return this.element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public MyNode getNext() {
            return this.next;
        }

        public void setNext(MyNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedList list = new LRUBaseLinkedList();
        Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }
    }
}