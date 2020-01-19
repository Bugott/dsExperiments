package experiments2;

import java.util.Scanner;

public class Question_1_2 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.print("��������Ҫת��Ϊʮ�����Ƶ�ʮ�������֣�");
		int num=s.nextInt();
		SeqStack<String> stack = new SeqStack<String>();
		String str="";
		while(num>0) {     //O(n),��ʮ����ת�Ƶķ�����֪�����ǳ���ȡģ�����������������м���ת��Ϊʮ�����ƺ����
			int t=num%16;
			switch(t) {      //O(n),����16��������9�Ժ��������Ҫ����ĸ��ʾ����������Switch�����бȽ��жϴ�����ջ
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
		System.out.print("������Ϊ��");
		while(!stack.isEmpty()) {     //O(n),��ӡ�õ��Ķ�����������ջ
			System.out.print(stack.peek());
			stack.pop();
		}
		s.close();
	}
}
