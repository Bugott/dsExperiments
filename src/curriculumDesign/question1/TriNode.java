package curriculumDesign.question1;
public class TriNode<T> { // �������������������࣬Tָ������Ԫ������

	public T data; 										// �����򣬴洢����Ԫ��
	public TriNode<T> parent, left, right; 		// ��ַ�򣬷ֱ�ָ��ĸ��㡢����Һ��ӽ��

	// �����㣬�����ֱ�ָ��Ԫ�ء���ĸ��㡢����Һ��ӽ��
	public TriNode(T data, TriNode<T> parent, TriNode<T> left, TriNode<T> right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	public TriNode(T data) {					// ����ָ��ֵ��Ҷ�ӽ��
		this(data, null, null, null);
	}

	public TriNode() {
		this(null, null, null, null);
	}

	public String toString() {
		return this.data.toString();
	}

	public boolean isLeaf() {						// �ж��Ƿ�Ҷ�ӽ��
		return this.left == null && this.right == null;
	}
}
