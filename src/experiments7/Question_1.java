package experiments7;

public class Question_1 {
	public static int[] countSorting(int[] arr) {
		int[] sortedArr = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int count = 0;
			for (int j = 0; j < arr.length; j++)
				if (arr[i] > arr[j])
					count++;
			sortedArr[count] = arr[i];
		}
		return sortedArr;
	}

	public static void main(String[] args) {
		int[] arr = { 78, 48, 41, 74, 50, 40, 21, 16 };
		System.out.print("原数字排序(未排序)为：\t\t\t");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + "\t");
		int[] resultArray = countSorting(arr);
		System.out.print("\n通过计数排序后的数字序列为：\t");
		for (int i = 0; i < resultArray.length; i++)
			System.out.print(resultArray[i] + "\t");
	}
}