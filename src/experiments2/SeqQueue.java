package experiments2;

/**
 * @author Bugott
 * ʵ��˳�����
 */
public final class SeqQueue<T> {
	//��������洢����
    private Object element[];

    //������βָ��
	private int front, rear;

	//������������Ϊ�����Ĺ��췽��
	public SeqQueue(int length) {
		if (length < 64) {
            length = 64;
        }
		this.element = new Object[length];
		this.front = this.rear = 0;
	}

	//�����޲ι��췽��
	public SeqQueue() {
		this(64);
	}

	//�ж������Ƿ�Ϊ��
	public boolean isEmpty() {
		return this.front == this.rear;
	}

	//���Ԫ��
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

	//���ض���Ԫ��
	public T peek() {
		return this.isEmpty() ? null : (T) this.element[this.front];
	}

	//�Ӷ��׻�ȡԪ�ء���ȡ���Ԫ�ؾʹӶ����б��Ƴ��ˣ������Ӳ���
	public T poll() {
		if (this.isEmpty()) {
            return null;
        }
		T temp = (T) this.element[this.front];
		this.front = (this.front + 1) % this.element.length;
		return temp;
	}

	//��дtoString()����
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