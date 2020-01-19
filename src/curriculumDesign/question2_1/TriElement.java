package curriculumDesign.question2_1;

public class TriElement { // �������ľ�̬�������������
	int data; // ������
	int parent, left, right; // ��ĸ�������Һ��ӽ���±�

	// �����㣬����������ָ��Ԫ�ء���ĸ����±ꡢ��/�Һ��ӽ���±�
	public TriElement() {
		this(0, -1, -1, -1);
	}

	public TriElement(int data, int parent, int left, int right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	public TriElement(int data) { // ����Ԫ��ֵΪdata���޸�ĸ��Ҷ�ӽ��
		this(data, -1, -1, -1);
	}

	public String toString() { // ���ؽ��������ַ���
		return "(" + this.data + "," + this.parent + "," + this.left + ","
				+ this.right + ")";
	}

	public boolean isLeaf() { // �ж��Ƿ�Ҷ�ӽ��
		return this.left == -1 && this.right == -1;
	}

	public boolean equals(Object obj) { // �Ƚ��Ƿ����
										// ������Object���equals(obj)���������Ƚ�Ԫ��ֵ
		return obj == this || obj instanceof TriElement
				&& this.data == ((TriElement) obj).data;
	}
}