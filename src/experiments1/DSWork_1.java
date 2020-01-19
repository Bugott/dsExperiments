package experiments1;

public class DSWork_1 {

    public void josephus(int totalNo, int startNo, int distance) {
        if (totalNo < 1 || startNo < 0 || distance < 1) {
            System.out.println("输入逻辑错误，请重新输入");
        }
        System.out.println("Josephus环开启！环长度为；" + totalNo + ",	起始位置为：" + startNo + ", 计数大小为：" + distance);
        SinglyList<Integer> list = new SinglyList<Integer>();
        for (int i = totalNo; i >= 1; i--) {
            list.insert(0, (Integer) i);   //单链表表头插入 O(1)
        }
        System.out.println(list);  //输出单链表 O(n)
        Node<Integer> front = list.head;
        for (int j = 0; front != null && j < startNo - 1; j++) //计数，front指向起始结点的前一个节点
        {
            front = front.next;
        }
        while (totalNo > 1) {            //剩余1名犯人再跳出循环
            for (int j = 0; j < distance - 1; j++) {   //计数，寻找删除结点。少数一个，front指向待删除结点的前驱
                front = front.next;
                if (front == null)     //若指向链表尾则循环到头结点
                {
                    front = list.head.next;
                }
            }
            if (front.next == null) {
                front = list.head;   //若指向链表尾，则删除第一个结点
            }
            System.out.print("处刑第" + front.next.data + "号犯人，");
            front.next = front.next.next;
            totalNo--;
            System.out.println(list);
        }
        System.out.println("被赦免者是" + list.get(0) + "号犯人");
    }


    public static void partition(int[] arr) {
        int key = arr[0];     //key为基准点，即第一个元素
        int j = 0;
        //从第二个元素开始，每一个都与基准作比较，如果比基准小则与从第一个元素开始依次进行交换
        for (int i = 1; i < arr.length; i++) {
            if (key > arr[i]) {
                j++;
                swap(arr, j, i);
            }
        }
        //全部比较完毕后，再将基准点与停止交换的分段点进行交换，完成基准点的分组定位
        swap(arr, 0, j);
    }

    public static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}


