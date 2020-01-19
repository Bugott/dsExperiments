package experiments2;

public class Question_3 {
	public static StringBuffer toPrefix(String infix) {
		SeqStack<String> stack = new SeqStack<String>(infix.length());
		StringBuffer tempPrefix = new StringBuffer(infix.length() * 2);
		StringBuffer prefix = new StringBuffer(infix.length() * 2);
		int i = infix.length()-1;
		while (i >= 0) {
			char ch = infix.charAt(i);
			switch (ch) {
			case '+':
			case '-':
				while (!stack.isEmpty() && !stack.peek().equals(")")&&!stack.peek().equals("+")&&!stack.peek().equals("-"))
					tempPrefix.append(stack.pop());
				stack.push(ch + "");
				i--;
				break;

			case '*':
			case '/':
				while (!stack.isEmpty()
						&& (stack.peek().equals("*") || stack.peek()
								.equals("/")))
					tempPrefix.append(stack.pop());
				stack.push(ch + "");
				i--;
				break;

			case ')':
				stack.push(ch + "");
				i--;
				break;

			case '(':
				String out = stack.pop();
				while (out != null && !out.equals(")")) {
					tempPrefix.append(out);
					out = stack.pop();
				}
				i--;
				break;

			default:
				while (i >=0 && ch >= '0' && ch <= '9') {
					tempPrefix.append(ch);
					i--;
					if (i>=0)
						ch = infix.charAt(i);
				}
				tempPrefix.append(" ");
			}
		}
		while (!stack.isEmpty())
			tempPrefix.append(stack.pop());
		prefix = tempPrefix.reverse();
		return prefix;
	}

	public static Double toValue(StringBuffer prefix) {
		LinkedStack<Double> stack = new LinkedStack<Double>();
		double value = 0;
		for (int i = prefix.length()-1; i >=0; i--) {
			char ch = prefix.charAt(i);
			if (ch >= '0' && ch <= '9') {
				value = 0;
				int j = 0;
				while (ch != ' ') {
					value  = (double) (value + (ch-'0')*Math.pow(10, j++));
					ch = prefix.charAt(--i);
				}
				stack.push(value);
			} else if (ch != ' ') {
				Double x = stack.pop();
				Double y = stack.pop();
				switch (ch) {
				case '+':
					value = x + y;
					break;
				case '-':
					value = x - y;
					break;
				case '*':
					value = x * y;
					break;
				case '/':
					value = x / y;
					break;
				}
				System.out.print(x + (ch + "") + y + "=" + value + "£¬");
				stack.push(value);
			}
		}
		System.out.println();
		return stack.pop();
	}
	public static void main(String args[]) {
		String[] infix = { "123", "123+10*(45-50+20)/((35-25)*2+10)-11",
				"45+(10-15)*((25+35)/(60-40))-11" };
		for (int i = 0; i < infix.length ; i++) {
			StringBuffer prefix = toPrefix(infix[i]);
			System.out.println("infix=" + infix[i]);
			System.out.println("prefix=" + prefix);
			System.out.println("value=" + toValue(prefix) + "\n");
		}
	}
}