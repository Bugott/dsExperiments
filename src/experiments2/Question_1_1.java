package experiments2;

import java.util.Scanner;

public class Question_1_1 {
	public static void main(String[] args) {
		System.out.println("��������Ҫת��Ϊ�����Ƶ�ʮ�������֣�");
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		SeqStack<Integer> stack = new SeqStack<Integer>();
		while(num != 0) {	
			stack.push(num%2);
			num = num/2;
		}
		System.out.print("������Ϊ��");
		while(!stack.isEmpty()) {
			System.out.print(stack.peek());
			stack.pop();
		}
		scanner.close();
	}
}
