package experiments7;

public class Question_2 {
	public static void partitionBy50(int [] arr) {
        int key = 50;     //key为基准点，值设定为50 
        int j = 0;
        //从第二个元素开始，每一个都与基准作比较，如果比基准小则与从第一个元素开始依次进行交换
        for (int i = 1; i < arr.length; i++) {
            if (key>arr[i]) {
                j++;
                swap(arr, j, i);
            }
        }
        //除第一个元素的其他有元素比较完毕后，再将第一个元素与停止交换的分段点进行交换，完成基准点为50的分组定位
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
		System.out.println("实验报告7的第二题程序运行开始！");
//		Scanner s = new Scanner(System.in);
//		System.out.println("请输入需要进行排序的20个数字(范围为1~100)");
//		int[] arr = new int[20];
//		for (int i = 0; i < 20; i++) {
//			System.out.print("请输入第" + (i + 1) + "个数字：");
//			int num = s.nextInt();
//			while (num > 100 || num < 1) {
//				System.out.println("输入的数字不在1~100的范围内，请重新输入！");
//				num = s.nextInt();
//				if (num < 100 && num < 1) {
//					arr[i] = num;
//					break;
//				}
//			}
//		}
		int arr[] = {51,20,46,90,84,74,12,41,31,51,62,10,13,19,74,61,48,42,45,99};
		System.out.println("输入的数组为：");
		for(int i = 0;i<20;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
		Question_2.partitionBy50(arr);
		System.out.println("使用设计的分组算法后，排序结果为：");
		for(int i = 0;i<20;i++)
			System.out.print(arr[i]+" ");
	}
}
