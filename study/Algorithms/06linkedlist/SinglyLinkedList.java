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
            System.out.print(p.data + "=>");
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

    public boolean palindrome(){
        if(head == null) return false;
        System.out.println("开始执行找到中间节点");
        Node fast = head;
        Node slow = head;

        if(slow.next == null) return true;
        while(fast.next != null &&  fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        System.out.println("中间节点"+slow.data);
        Node leftLink = null;
        Node rightLink = null;
        if(fast.next == null){
            System.out.println("奇数");
            //  slow 一定为整个链表的中点 并且节点数为奇数
            rightLink = slow.next;
            leftLink = inverseLinkList(slow).next;
        }else{
            System.out.println("偶数");
            rightLink = slow.next;
            leftLink = inverseLinkList(slow);
        }
        return TFResult(leftLink, rightLink);

    }

    // 一直在头后面插入
    public Node inverseLinkList_head(Node p){
        Node Head = new Node(9999,null);
        Head.next = p;
        Node cur = p.next;
        p.next = null;
        Node next = null;
        while(cur != null){
            next = cur.next;
            cur.next = Head.next;
            Head.next = cur;
            System.out.println("first " + Head.data);
            cur = next;
        }
        return Head;

    }

    public Node inverseLinkList(Node p){
        System.out.println(p.data);
        System.out.println(head.data);
        Node pre = null;
        Node r = head;
        Node next= null;
        System.out.println("z---" + r.data);
        while(r != p){
            // 额外存储了一下 保证r+next都在往后走
            next = r.next;
            // r是pre 也就是新链表
            r.next = pre;
            pre = r;
            // r后是原链表
            r = next;
        }
        r.next = pre;
        return r;
    }
    
    public static void main(String[]args){

        SinglyLinkedList link = new SinglyLinkedList(); 
        System.out.println("hello");
        //int data[] = {1};
        //int data[] = {1,2};
        //int data[] = {1,2,3,1};
        //int data[] = {1,2,5};
        //int data[] = {1,2,2,1};
       // int data[] = {1,2,5,2,1};
        int data[] = {1,2,3,3,2,1};

        for(int i =0; i < data.length; i++){
            //link.insertToHead(data[i]);
            link.insertTail(data[i]);
        }
       // link.printAll();
       // Node p = link.inverseLinkList_head(link.head);
       // while(p != null){
       //     System.out.println("aa"+p.data);
       //     p = p.next;
       // }

        System.out.println("打印原始:");
        link.printAll();
        if (link.palindrome()){
            System.out.println("回文");
        }else{
            System.out.println("不是回文");
        }
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