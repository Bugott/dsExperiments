package experiments7;

public class Question_2 {
	public static void partitionBy50(int [] arr) {
        int key = 50;     //keyΪ��׼�㣬ֵ�趨Ϊ50 
        int j = 0;
        //�ӵڶ���Ԫ�ؿ�ʼ��ÿһ�������׼���Ƚϣ�����Ȼ�׼С����ӵ�һ��Ԫ�ؿ�ʼ���ν��н���
        for (int i = 1; i < arr.length; i++) {
            if (key>arr[i]) {
                j++;
                swap(arr, j, i);
            }
        }
        //����һ��Ԫ�ص�������Ԫ�رȽ���Ϻ��ٽ���һ��Ԫ����ֹͣ�����ķֶε���н�������ɻ�׼��Ϊ50�ķ��鶨λ
        swap(arr, 0, j);
    }
 
    public static void swap(int [] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    
    public static void main(String[] args) {
		System.out.println("ʵ�鱨��7�ĵڶ���������п�ʼ��");
//		Scanner s = new Scanner(System.in);
//		System.out.println("��������Ҫ���������20������(��ΧΪ1~100)");
//		int[] arr = new int[20];
//		for (int i = 0; i < 20; i++) {
//			System.out.print("�������" + (i + 1) + "�����֣�");
//			int num = s.nextInt();
//			while (num > 100 || num < 1) {
//				System.out.println("��������ֲ���1~100�ķ�Χ�ڣ����������룡");
//				num = s.nextInt();
//				if (num < 100 && num < 1) {
//					arr[i] = num;
//					break;
//				}
//			}
//		}
		int arr[] = {51,20,46,90,84,74,12,41,31,51,62,10,13,19,74,61,48,42,45,99};
		System.out.println("���������Ϊ��");
		for(int i = 0;i<20;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
		Question_2.partitionBy50(arr);
		System.out.println("ʹ����Ƶķ����㷨��������Ϊ��");
		for(int i = 0;i<20;i++)
			System.out.print(arr[i]+" ");
	}
}
