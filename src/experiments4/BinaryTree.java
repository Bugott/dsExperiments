package experiments4;

import experiments1.SeqList;

public class BinaryTree<T> {
	public BinaryNode<T> root;

	public BinaryTree() {
		this.root = null;
	}

	public boolean isEmpty() {
		return this.root == null;
	}

	public BinaryNode<T> insert(T x) {
		return this.root = new BinaryNode<T>(x, this.root, null);
	}

	public BinaryNode<T> insert(BinaryNode<T> parent, T x, boolean leftChild) {
		if (x == null)
			return null;
		if (leftChild)
			return parent.left = new BinaryNode<T>(x, parent.left, null);
		return parent.right = new BinaryNode<T>(x, null, parent.right);
	}

	public void remove(BinaryNode<T> parent, boolean leftChild) {
		if (leftChild)
			parent.left = null;
		else
			parent.right = null;
	}

	public void clear() {
		this.root = null;
	}

	public void preorder() {
		System.out.print("�ȸ����������������  ");
		preorder(this.root);
		System.out.println();
	}

	private void preorder(BinaryNode<T> p) {
		if (p != null) {
			System.out.print(p.data.toString() + " ");
			preorder(p.left);
			preorder(p.right);
		}
	}

	public void inorder() {
		System.out.print("�и����������������  ");
		inorder(this.root);
		System.out.println();
	}

	private void inorder(BinaryNode<T> p) {
		if (p != null) {
			inorder(p.left);
			System.out.print(p.data.toString() + " ");
			inorder(p.right);
		}
	}

	public void postorder() {
		System.out.print("������������������  ");
		postorder(this.root);
		System.out.println();
	}

	private void postorder(BinaryNode<T> p) {
		if (p != null) {
			postorder(p.left);
			postorder(p.right);
			System.out.print(p.data.toString() + " ");
		}
	}

	public String toString() {
		return toString(this.root);
	}

	private String toString(BinaryNode<T> p) {
		if (p == null)
			return "��";
		return p.data.toString() + " " + toString(p.left) + toString(p.right);
	}
	
//������������������ҵ�ָ��ߡ�����������
	public BinaryTree<T> create(SeqList<T> postList, SeqList<T> inList) {		//���и��ͺ���������й��������
		this.root = buildBitree(postList, 0, postList.size()-1, inList, 0, inList.size()-1);
		return null;
	}
	public BinaryNode<T> buildBitree(SeqList<T> postList, int postStart, int postEnd, SeqList<T> inList, int inStart, int inEnd) {		//���и��ͺ���������й��������
		if(postStart <= postEnd){
			T r = postList.get(postEnd);
			BinaryNode<T> node = new BinaryNode<T>(r);
			int rindex = inList.search(r);
			int ll = rindex - inStart;
			int rl = inEnd - rindex;
			node.right = buildBitree(postList, postEnd-rl, postEnd-1, inList, rindex+1, rindex+rl);
			node.left = buildBitree(postList, postEnd-ll-rl, postEnd-rl-1, inList, inStart, rindex-1);
			return node;
		}else return null;
	}
	
	public static int leaves_num = 0;
	public void leaf(){		//���Ҷ�ӽ��
		leaf(root);
	}
	private void leaf(BinaryNode<T> p){
        if(p != null){
        	if (p.left == null && p.right == null){
            	System.out.print(p.data.toString()+" ");
            	leaves_num++;
            }
            leaf(p.left);
            leaf(p.right);
        }
    }

	//����p���ĸ�ĸ��㣬��������δ�ҵ���pΪ�����򷵻�null
	public BinaryNode<T> parent(BinaryNode<T> p) {	
		if (root == null || p == null || p == root)
            return null; 
        return parent(root, p);
	}
	 //����rootΪ���������в��Ҳ�����p���ĸ�ĸ���
	public BinaryNode<T> parent(BinaryNode<T> root, BinaryNode<T> p){
        if (root == null)
            return null;
        if (root.left == p || root.right == p) 
            return root;
        BinaryNode<T> find = parent(root.left, p);
        if (find == null)
            find = parent(root.right, p);
        return find;
    }

	public int fullNode(BinaryNode<T> p) {
		int i, j;
		if (p == null)
			return 0;
		else {
			i = fullNode(p.left);
			j = fullNode(p.right);
			if (p.left != null && p.right != null)
				return (i + j + 1);
			else
				return (i + j);
		}
	}
	public void property3(BinaryTree<T> bitree){
		int fullNode_num = fullNode(bitree.root);
		System.out.println("�ö�������Ҷ�ӽڵ�Ϊ��");
		bitree.leaf();
		System.out.println("\n��Ϊ2�Ľڵ���Ϊ��"+fullNode_num+"\nҶ�ӽڵ���Ϊ��"+leaves_num);
		if(fullNode_num+1==leaves_num){
			System.out.println("�ɹ���֤���������ʢ�");
		}else System.out.println("�쳣����֤����");
	}

	public void swap(BinaryNode<T> p) {
		BinaryNode<T> node = null;
		if (p != null) {
			node = p.left;
			p.left = p.right;
			p.right = node;
			if (p.left != null)
				swap(p.left);
			if (p.right != null)
				swap(p.right);
		}
	}
	public void swap(BinaryTree<T> bitree){
		swap(bitree.root);
	}
	
	public static void main(String[] args) {
		SeqList<String> postList = new SeqList<String>();
		SeqList<String> inList = new SeqList<String>();
		String postStr = "dfgebca";
		String inStr = "dbfegac";
		for(int i = 0;i<postStr.length();i++){
			postList.insert(postStr.charAt(i)+" ");
			inList.insert(inStr.charAt(i)+" ");
		}
		BinaryTree<String> bt = new BinaryTree<String>();
		bt.create(postList, inList);
		String tree = bt.toString();
		System.out.println("�ȸ���������������ǵĽ��Ϊ��\n"+tree);
//		bt.swap(bt);
//		String stree = bt.toString();
//		System.out.println("���������ӽڵ���ȸ���������������ǵĽ��Ϊ��\n"+stree);
		bt.property3(bt);
		BinaryNode<String> e = bt.root.left.right;
		BinaryNode<String> e_parent = bt.parent(e);
		System.out.println("e�ڵ�ĸ�ĸ�ڵ�Ϊ��"+e_parent);
		System.out.println("�ö�������Ҷ�ӽڵ�Ϊ��");
		bt.leaf();
		System.out.println();
		System.out.println("����������������Ϊ��");
		bt.preorder(bt.root);
		System.out.println();
		System.out.println("����������������Ϊ��");
		bt.inorder(bt.root);
		System.out.println();
		System.out.println("�������ĺ�������Ϊ��");
		bt.postorder(bt.root);
		System.out.println();
	}
}