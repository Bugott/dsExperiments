package experiments2;

import experiments1.SeqList;

public final class SeqStack<E> {
	private SeqList<E> list;

	public SeqStack(int length) {
		this.list = new SeqList<E>(length);
	}

	public SeqStack() {
		this(64);
	}

	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	public void push(E x) {
		this.list.insert(x);
	}

	public E peek() {
		return this.list.get(list.size() - 1);

	}

	public E pop() {
		return this.list.remove(list.size() - 1);
	}

	@Override
    public String toString() {
		return this.getClass().getName() + " " + this.list.toString();
	}
}