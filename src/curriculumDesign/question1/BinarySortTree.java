package curriculumDesign.question1;

import experiments1.SeqList;

public class BinarySortTree<T extends Comparable<? super T>> {
	public TriNode<T> root; // ����㣬����������ṹ

	public BinarySortTree() { // ����ն���������
		this.root = null;
	}

	public BinarySortTree(SeqList<T> list) { 	// �����������������list˳����ṩԪ��
		this(); 													// ����ն���������
		for (int i = 0; i < list.size(); i++)
			this.add(list.get(i)); 							// ����list˳�������Ԫ��
	}
	
	public BinarySortTree(T[] values) { // �����������������values�����ṩԪ��
		this(); // ����ն���������
		for (int i = 0; i < values.length; i++)
			this.add(values[i]); // ����values��������Ԫ��
	}

	public boolean isEmpty() { // �ж��Ƿ�ն�����
		return this.root == null;
	}

	// ���ҹؼ���ΪkeyԪ�أ������ҳɹ������ؽ�㣬���򷵻�null���ǵݹ��㷨��
	public TriNode<T> searchNode(T key) {
		TriNode<T> p = this.root;
		while (p != null && key.compareTo(p.data) != 0) {
			if (key.compareTo(p.data) < 0) // ��key��С
				p = p.left; // ����������
			else
				p = p.right; // ����������
		}
		return p != null ? p : null; // �����ҳɹ������ؽ�㣬���򷵻�null
	}

	public T search(T key) { // ���ҹؼ���ΪkeyԪ�أ������ҳɹ�������Ԫ�أ����򷵻�null
		TriNode<T> find = this.searchNode(key);
		return find != null ? find.data : null;
	}

	// ����Ԫ��x��㣬������ؼ����ظ�Ԫ�غͿն��󣬷��ز������״̬��
	public boolean add(T x) {
		if (this.root == null)
			this.root = new TriNode<T>(x); // ���������
		else { // ��x���뵽��rootΪ���Ķ�����������
			TriNode<T> p = this.root, parent = null;
			while (p != null) { // ����ȷ������λ��
				if (x.compareTo(p.data) == 0)
					return false; // ���ҳɹ���������ؼ����ظ���Ԫ��
				parent = p;
				if (x.compareTo(p.data) < 0)
					p = p.left;
				else
					p = p.right;
			}
			if (x.compareTo(parent.data) < 0) // ����xҶ�ӽ����Ϊparent����/�Һ���
				parent.left = new TriNode<T>(x, parent, null, null);
			else
				parent.right = new TriNode<T>(x, parent, null, null);
		}
		return true;
	}

	// �����и�����������������н��������ַ����������������ǵݹ��㷨
	public String toString() {
		String str = "[";
		TriNode<T> p = this.first(this.root); // Ѱ�ҵ�һ�����ʽ�㣬��Сֵ
		while (p != null) {
			str += p.data.toString() + " ";
			p = this.next(p); // ����p���и������µĺ�̽��
		}
		return str + "]";
	}

	// ����pΪ���������У������и������µ�һ�����ʽ�㣬���Ǹ�������ߵ������㣬��Сֵ
	public TriNode<T> first(TriNode<T> p) {
		if (p != null)
			while (p.left != null)
				p = p.left;
		return p;
	}

	public TriNode<T> next(TriNode<T> p) { // ����p���и������µĺ�̽��
		if (p != null) {
			if (p.right != null) // ��p���Һ��ӣ�
				return this.first(p.right); // ��p�ĺ��������������һ�����ʽ��
			while (p.parent != null) { // ��pû���Һ��ӣ�����Ѱ��ĳ�����Ƚ��
				if (p.parent.left == p) // ��p���丸ĸ�����ӣ���p�ĺ�����丸ĸ
					return p.parent;
				p = p.parent;
			}
		}
		return null;
	}

	public T remove(T key) { // ɾ���ؼ���Ϊkey��㣬���ر�ɾ��Ԫ�أ���û�ҵ��򷵻�null��
		TriNode<T> p = this.searchNode(key); // ���ҹؼ���ΪkeyԪ�أ������ҳɹ������ؽ�㣬���򷵻�null
		if (p != null && p.left != null && p.right != null) { // �ҵ���ɾ�����p����p��2�Ƚ��
			TriNode<T> insucc = this.first(p.right); // Ѱ��p���и������µĺ�̽��insucc
			T temp = p.data; // ������ɾ��Ԫ�أ���Ϊ����ֵ
			p.data = insucc.data; // �Ժ�̽��ֵ�滻p���ֵ
			insucc.data = temp;
			p = insucc; // ת��Ϊɾ��insucc��ɾ��1��0�Ƚ��
		}

		if (p != null && p == this.root) { // p��1�Ȼ�Ҷ�ӽ�㣬ɾ������㣬p.parent==null
			if (this.root.left != null)
				this.root = p.left; // ��p�����Ӷ�����Ϊ�µĸ����
			else
				this.root = p.right; // ��p���Һ��Ӷ�����Ϊ�µĸ����
			if (this.root != null)
				this.root.parent = null;
			return p.data; // ���ر�ɾ�������Ԫ��
		}

		if (p != null && p == p.parent.left) // p��1�Ȼ�Ҷ�ӽ�㣬p�Ǹ�ĸ������
			if (p.left != null) {
				p.parent.left = p.left; // ��p�����Ӷ���
				p.left.parent = p.parent; // p�����ӵ�parent��ָ��p�ĸ�ĸ
			} else {
				p.parent.left = p.right; // ��p���Һ��Ӷ���
				if (p.right != null)
					p.right.parent = p.parent;
			}

		if (p != null && p == p.parent.right) // p��1�Ȼ�Ҷ�ӽ�㣬p�Ǹ�ĸ���Һ���
			if (p.left != null) {
				p.parent.right = p.left; // ��p�����Ӷ���
				p.left.parent = p.parent;
			} else {
				p.parent.right = p.right; // ��p���Һ��Ӷ���
				if (p.right != null)
					p.right.parent = p.parent;
			}
		return p != null ? p.data : null;
	}
}