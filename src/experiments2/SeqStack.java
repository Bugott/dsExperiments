package experiments2;

import experiments1.SeqList;

/**
 * @author Bugott
 * 实现顺序栈
 */
public final class SeqStack<E> {
	//利用顺序表实现顺序栈
    private SeqList<E> list;

    //定义一个带栈大小参数的构造方法
	public SeqStack(int length) {
		this.list = new SeqList<E>(length);
	}

	//定义一个无参的构造方法(默认栈大小为64)
	public SeqStack() {
		this(64);
	}

	//判断栈是否为空
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	//从栈顶压入一个元素
	public void push(E x) {
		this.list.insert(x);
	}

	//获得栈顶元素(不取出)
	public E peek() {
		return this.list.get(list.size() - 1);
	}

	//取出栈顶元素
	public E pop() {
		return this.list.remove(list.size() - 1);
	}

	//重写toString方法
	@Override
    public String toString() {
		return this.getClass().getName() + " " + this.list.toString();
	}
}