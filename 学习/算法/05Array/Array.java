public class Array{
    // 定义整数型data保存数据
    public int data[];
    // 定义数组长度
    private int len;
    // 定义实际个数
    private int count;

    // 构造函数
    public Array(int capacity) {
        this.data = new int[capacity];
        this.len = capacity;
        this.count = 0;
    }

    // 查找
    public int find(int index){
        if(index < 0 || index >= len){
            return -1;
        }
        return data[index];
    }

    // 插入
    public boolean insert(int index,int value){
        if(count == len){
            System.out.println("没有位置了");
            return falsel;
        }
        if(index < 0 || index >= len){
            System.out.println("位置不合法");
            return false;
        }
        for (int i = count; i > index; --i) {
            data[i] = data[i-1];
        }
        data[index] = value;
        count++;
        return true;
    }
    
    // 删除数组中的元素
    public boolean delete(int index) {
        if(index < 0 || index >=len) return false;
        for (int i = index; i <= count; i++) {
            data[i] = data[i+1];
        }
        count--;
        return true;
    }
    public void printAll() {
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Array array = new Array(5);
        array.printAll();
        array.insert(0, 3);
        array.insert(0, 4);
        array.insert(1, 5);
        array.insert(3, 9);
        array.insert(3, 10);
        //array.insert(3, 11);
        array.printAll();
    }


}