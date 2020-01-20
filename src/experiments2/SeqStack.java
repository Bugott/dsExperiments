package experiments2;

import experiments1.SeqList;

/**
 * @author Bugott
 * ʵ��˳��ջ
 */
public final class SeqStack<E> {
	//����˳���ʵ��˳��ջ
    private SeqList<E> list;

    //����һ����ջ��С�����Ĺ��췽��
	public SeqStack(int length) {
		this.list = new SeqList<E>(length);
	}

	//����һ���޲εĹ��췽��(Ĭ��ջ��СΪ64)
	public SeqStack() {
		this(64);
	}

	//�ж�ջ�Ƿ�Ϊ��
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	//��ջ��ѹ��һ��Ԫ��
	public void push(E x) {
		this.list.insert(x);
	}

	//���ջ��Ԫ��(��ȡ��)
	public E peek() {
		return this.list.get(list.size() - 1);
	}

	//ȡ��ջ��Ԫ��
	public E pop() {
		return this.list.remove(list.size() - 1);
	}

	//��дtoString����
	@Override
    public String toString() {
		return this.getClass().getName() + " " + this.list.toString();
	}
}