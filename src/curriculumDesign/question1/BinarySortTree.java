package curriculumDesign.question1;

import experiments1.SeqList;

public class BinarySortTree<T extends Comparable<? super T>> {
	public TriNode<T> root; // 根结点，三叉链表结点结构

	public BinarySortTree() { // 构造空二叉排序树
		this.root = null;
	}

	public BinarySortTree(SeqList<T> list) { 	// 构造二叉排序树，由list顺序表提供元素
		this(); 													// 构造空二叉排序树
		for (int i = 0; i < list.size(); i++)
			this.add(list.get(i)); 							// 插入list顺序表所有元素
	}
	
	public BinarySortTree(T[] values) { // 构造二叉排序树，由values数组提供元素
		this(); // 构造空二叉排序树
		for (int i = 0; i < values.length; i++)
			this.add(values[i]); // 插入values数组所有元素
	}

	public boolean isEmpty() { // 判断是否空二叉树
		return this.root == null;
	}

	// 查找关键字为key元素，若查找成功，返回结点，否则返回null。非递归算法。
	public TriNode<T> searchNode(T key) {
		TriNode<T> p = this.root;
		while (p != null && key.compareTo(p.data) != 0) {
			if (key.compareTo(p.data) < 0) // 若key较小
				p = p.left; // 进入左子树
			else
				p = p.right; // 进入右子树
		}
		return p != null ? p : null; // 若查找成功，返回结点，否则返回null
	}

	public T search(T key) { // 查找关键字为key元素，若查找成功，返回元素，否则返回null
		TriNode<T> find = this.searchNode(key);
		return find != null ? find.data : null;
	}

	// 插入元素x结点，不插入关键字重复元素和空对象，返回插入与否状态。
	public boolean add(T x) {
		if (this.root == null)
			this.root = new TriNode<T>(x); // 创建根结点
		else { // 将x插入到以root为根的二叉排序树中
			TriNode<T> p = this.root, parent = null;
			while (p != null) { // 查找确定插入位置
				if (x.compareTo(p.data) == 0)
					return false; // 查找成功，不插入关键字重复的元素
				parent = p;
				if (x.compareTo(p.data) < 0)
					p = p.left;
				else
					p = p.right;
			}
			if (x.compareTo(parent.data) < 0) // 插入x叶子结点作为parent的左/右孩子
				parent.left = new TriNode<T>(x, parent, null, null);
			else
				parent.right = new TriNode<T>(x, parent, null, null);
		}
		return true;
	}

	// 返回中根次序遍历二叉树所有结点的描述字符串，迭代遍历，非递归算法
	public String toString() {
		String str = "[";
		TriNode<T> p = this.first(this.root); // 寻找第一个访问结点，最小值
		while (p != null) {
			str += p.data.toString() + " ";
			p = this.next(p); // 返回p在中根次序下的后继结点
		}
		return str + "]";
	}

	// 在以p为根的子树中，返回中根次序下第一个访问结点，即是根的最左边的子孙结点，最小值
	public TriNode<T> first(TriNode<T> p) {
		if (p != null)
			while (p.left != null)
				p = p.left;
		return p;
	}

	public TriNode<T> next(TriNode<T> p) { // 返回p在中根次序下的后继结点
		if (p != null) {
			if (p.right != null) // 若p有右孩子，
				return this.first(p.right); // 则p的后继是其右子树第一个访问结点
			while (p.parent != null) { // 若p没有右孩子，向上寻找某个祖先结点
				if (p.parent.left == p) // 若p是其父母的左孩子，则p的后继是其父母
					return p.parent;
				p = p.parent;
			}
		}
		return null;
	}

	public T remove(T key) { // 删除关键字为key结点，返回被删除元素；若没找到则返回null。
		TriNode<T> p = this.searchNode(key); // 查找关键字为key元素，若查找成功，返回结点，否则返回null
		if (p != null && p.left != null && p.right != null) { // 找到待删除结点p，若p是2度结点
			TriNode<T> insucc = this.first(p.right); // 寻找p在中根次序下的后继结点insucc
			T temp = p.data; // 交换待删除元素，作为返回值
			p.data = insucc.data; // 以后继结点值替换p结点值
			insucc.data = temp;
			p = insucc; // 转化为删除insucc，删除1、0度结点
		}

		if (p != null && p == this.root) { // p是1度或叶子结点，删除根结点，p.parent==null
			if (this.root.left != null)
				this.root = p.left; // 以p的左孩子顶替作为新的根结点
			else
				this.root = p.right; // 以p的右孩子顶替作为新的根结点
			if (this.root != null)
				this.root.parent = null;
			return p.data; // 返回被删除根结点元素
		}

		if (p != null && p == p.parent.left) // p是1度或叶子结点，p是父母的左孩子
			if (p.left != null) {
				p.parent.left = p.left; // 以p的左孩子顶替
				p.left.parent = p.parent; // p的左孩子的parent域指向p的父母
			} else {
				p.parent.left = p.right; // 以p的右孩子顶替
				if (p.right != null)
					p.right.parent = p.parent;
			}

		if (p != null && p == p.parent.right) // p是1度或叶子结点，p是父母的右孩子
			if (p.left != null) {
				p.parent.right = p.left; // 以p的左孩子顶替
				p.left.parent = p.parent;
			} else {
				p.parent.right = p.right; // 以p的右孩子顶替
				if (p.right != null)
					p.right.parent = p.parent;
			}
		return p != null ? p.data : null;
	}
}