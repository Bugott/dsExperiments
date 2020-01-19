package experiments2;

import java.util.Scanner;

public class Question_1_1 {
	public static void main(String[] args) {
		System.out.println("请输入需要转换为二进制的十进制数字：");
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		SeqStack<Integer> stack = new SeqStack<Integer>();
		while(num != 0) {	
			stack.push(num%2);
			num = num/2;
		}
		System.out.print("处理结果为：");
		while(!stack.isEmpty()) {
			System.out.print(stack.peek());
			stack.pop();
		}
		scanner.close();
	}
}
