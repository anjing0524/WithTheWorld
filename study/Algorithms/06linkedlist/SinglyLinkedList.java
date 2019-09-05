public class SinglyLinkedList{

    private Node head = null;

    public Node findByValue(int value){
        Node p = head;
        while(p != null && p.data != value){
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index){
        Node p = head;
        int pos = 0;
        while(p != null && pos != index){
            p = p.next;
            pos++;
        }
        return p;
    }

    public void insertTohead(Node newNode){
        if(head == null){
            this.head = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertToHead(int value){
        Node newNode = new Node(value,null);
        insertTohead(newNode);
    }

    public void insertTail(int value){
        Node newNode = new Node(value,null);
        if(head ==null){
            head = newNode;
        }else{
            Node p = head;
            while(p.next != null){
                p = p.next;
            }
            newNode.next = p.next;
            p.next = newNode;
        }
    }

    public void insertAfter(Node p,int value){
        Node newNode = new Node(value,null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode){
        if(p == null) return;
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, int value){
        Node newNode = new Node(value,null);
        insertBefore(p, newNode);
    }

    public void insertBefore(Node p,Node newNode){
        if(p == null)  return;
        if(head == p){
            insertTohead(newNode);
            return;
        }
        Node q = head;
        while(p != null && q.next != p){
            q = q.next;
        }
        if(q == null){
            return ;
        }
        newNode.next = p;
        q.next = newNode;
    }

    public void deleteByNode(Node p){
        if(p == null || head == null) return;
        if(p = head){
            head = head.next;
            return;
        }
        Node q = head;
        while(q != null && q.next != p){
            q = q.next;
        }
        if(q == null) return ;
        q.next = q.next.next;
    }

    public void deleteByValue(int value){
        if(head == null) return;
        Node p = head;
        Node q = null;
        if(p != null && p.data != value){
            q= p;
            p = p.next;
        }
        if(p == null) return ;
        if(q == null) {
            head = head.next;
        }else{
            q.next = q.next.next;
        }

        // if(head != null && head.data ==value){
        //     head = head.next;
        // }
        // Node pNode = head;
        // while(pNode != null ){
        //     if(pNode.next != null && pNode.next.data == value){
        //         pNode.next = pNode.next.next;
        //         continue;
        //     }
        //     pNode = pNode.next;
        // }

    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public boolean TFResult(Node left,Node right){
        Node l = left;
        Node r = right;
        System.out.println("left_"+l.data);
        System.out.println("right_"+r.data);
        while(l != null && r != null){
            if(l.data == r.data){
                l = l.next;
                r = r.next;
            }else{
                break;
            }
        }
        if(l == null && r == null){
            return true;
        }
        return false;
    }

    




    public static class Node{
        private int data;
        private Node next;

        public Node(int data,Node next){
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }
}