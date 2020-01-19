package experiments1;

public class DSWork_1 {

    public void josephus(int totalNo, int startNo, int distance) {
        if (totalNo < 1 || startNo < 0 || distance < 1) {
            System.out.println("�����߼���������������");
        }
        System.out.println("Josephus��������������Ϊ��" + totalNo + ",	��ʼλ��Ϊ��" + startNo + ", ������СΪ��" + distance);
        SinglyList<Integer> list = new SinglyList<Integer>();
        for (int i = totalNo; i >= 1; i--) {
            list.insert(0, (Integer) i);   //�������ͷ���� O(1)
        }
        System.out.println(list);  //��������� O(n)
        Node<Integer> front = list.head;
        for (int j = 0; front != null && j < startNo - 1; j++) //������frontָ����ʼ����ǰһ���ڵ�
        {
            front = front.next;
        }
        while (totalNo > 1) {            //ʣ��1������������ѭ��
            for (int j = 0; j < distance - 1; j++) {   //������Ѱ��ɾ����㡣����һ����frontָ���ɾ������ǰ��
                front = front.next;
                if (front == null)     //��ָ������β��ѭ����ͷ���
                {
                    front = list.head.next;
                }
            }
            if (front.next == null) {
                front = list.head;   //��ָ������β����ɾ����һ�����
            }
            System.out.print("���̵�" + front.next.data + "�ŷ��ˣ�");
            front.next = front.next.next;
            totalNo--;
            System.out.println(list);
        }
        System.out.println("����������" + list.get(0) + "�ŷ���");
    }


    public static void partition(int[] arr) {
        int key = arr[0];     //keyΪ��׼�㣬����һ��Ԫ��
        int j = 0;
        //�ӵڶ���Ԫ�ؿ�ʼ��ÿһ�������׼���Ƚϣ�����Ȼ�׼С����ӵ�һ��Ԫ�ؿ�ʼ���ν��н���
        for (int i = 1; i < arr.length; i++) {
            if (key > arr[i]) {
                j++;
                swap(arr, j, i);
            }
        }
        //ȫ���Ƚ���Ϻ��ٽ���׼����ֹͣ�����ķֶε���н�������ɻ�׼��ķ��鶨λ
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


