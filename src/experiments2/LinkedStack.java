package experiments2;

import experiments1.SinglyList;

/**
 * @author Bugott
 *  µœ÷¡¥ Ω’ª
 */
public final class LinkedStack<T> {
	private experiments1.SinglyList<T> list;

	public LinkedStack() {
		this.list = new SinglyList<T>();
	}

	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	public void push(T x) {
		this.list.insert(0, x);
	}

	public T peek() {
		return this.list.get(0);
	}

	public T pop() {
		return this.list.remove(0);
	}

	@Override
    public String toString() {
		return this.getClass().getName() + " " + this.list.toString();
	}
}