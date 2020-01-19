package experiments2;

import java.util.Scanner;

public class Question_1_2 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.print("请输入需要转换为十六进制的十进制数字：");
		int num=s.nextInt();
		SeqStack<String> stack = new SeqStack<String>();
		String str="";
		while(num>0) {     //O(n),由十进制转制的方法可知，即是持续取模，所得余数倒序排列即是转换为十六进制后的数
			int t=num%16;
			switch(t) {      //O(n),由于16进制数中9以后的数字需要用字母表示，所有运用Switch语句进行比较判断代替入栈
			case 10:stack.push(str+'A');
					break;
			case 11:stack.push(str+'B');
					break;
			case 12:stack.push(str+'C');
					break;
			case 13:stack.push(str+'D');
					break;
			case 14:stack.push(str+'E');
					break;
			case 15:stack.push(str+'F');
					break;
			default:stack.push(str+t);
			        break;
			}
			num=num/16;
		}
		System.out.print("处理结果为：");
		while(!stack.isEmpty()) {     //O(n),打印得到的二进制数并出栈
			System.out.print(stack.peek());
			stack.pop();
		}
		s.close();
	}
}
