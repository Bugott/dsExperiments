package experiments2;

/**
 * @author Bugott
 * 实现顺序队列
 */
public final class SeqQueue<T> {
	//定义数组存储数据
    private Object element[];

    //定义首尾指针
	private int front, rear;

	//定义以链表长度为参数的构造方法
	public SeqQueue(int length) {
		if (length < 64) {
            length = 64;
        }
		this.element = new Object[length];
		this.front = this.rear = 0;
	}

	//定义无参构造方法
	public SeqQueue() {
		this(64);
	}

	//判断链表是否为空
	public boolean isEmpty() {
		return this.front == this.rear;
	}

	//添加元素
	public boolean add(T x) {
		if (x == null) {
            return false;
        }
		if (this.front == (this.rear + 1) % this.element.length) {
			Object[] temp = this.element;
			this.element = new Object[temp.length * 2];
			int j = 0;
			for (int i = this.front; i != this.rear; i = (i + 1) % temp.length) {
                this.element[j++] = temp[i];
            }
			this.front = 0;
			this.rear = j;
		}
		this.element[this.rear] = x;
		this.rear = (this.rear + 1) % this.element.length;
		return true;
	}

	//返回队首元素
	public T peek() {
		return this.isEmpty() ? null : (T) this.element[this.front];
	}

	//从队首获取元素。获取后该元素就从队列中被移除了，即出队操作
	public T poll() {
		if (this.isEmpty()) {
            return null;
        }
		T temp = (T) this.element[this.front];
		this.front = (this.front + 1) % this.element.length;
		return temp;
	}

	//重写toString()方法
	@Override
    public String toString() {
		StringBuffer strbuf = new StringBuffer(this.getClass().getName() + "(");
		for (int i = this.front; i != this.rear; i = (i + 1)
				% this.element.length) {
            strbuf.append(this.element[i].toString() + ",");
        }
		strbuf.setCharAt(strbuf.length() - 1, ')');
		return new String(strbuf);
	}
}