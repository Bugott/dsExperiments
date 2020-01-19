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
		System.out.print("先根次序遍历二叉树：  ");
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
		System.out.print("中根次序遍历二叉树：  ");
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
		System.out.print("后根次序遍历二叉树：  ");
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
			return "∧";
		return p.data.toString() + " " + toString(p.left) + toString(p.right);
	}
	
//————————作业分割线——————
	public BinaryTree<T> create(SeqList<T> postList, SeqList<T> inList) {		//以中根和后根遍历序列构造二叉树
		this.root = buildBitree(postList, 0, postList.size()-1, inList, 0, inList.size()-1);
		return null;
	}
	public BinaryNode<T> buildBitree(SeqList<T> postList, int postStart, int postEnd, SeqList<T> inList, int inStart, int inEnd) {		//以中根和后根遍历序列构造二叉树
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
	public void leaf(){		//输出叶子结点
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

	//返回p结点的父母结点，若空树、未找到或p为根，则返回null
	public BinaryNode<T> parent(BinaryNode<T> p) {	
		if (root == null || p == null || p == root)
            return null; 
        return parent(root, p);
	}
	 //在以root为根的子树中查找并返回p结点的父母结点
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
		System.out.println("该二叉树的叶子节点为：");
		bitree.leaf();
		System.out.println("\n度为2的节点数为："+fullNode_num+"\n叶子节点数为："+leaves_num);
		if(fullNode_num+1==leaves_num){
			System.out.println("成功验证二叉树性质③");
		}else System.out.println("异常，验证错误！");
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
		System.out.println("先根遍历带空子树标记的结果为：\n"+tree);
//		bt.swap(bt);
//		String stree = bt.toString();
//		System.out.println("交换左右子节点后先根遍历带空子树标记的结果为：\n"+stree);
		bt.property3(bt);
		BinaryNode<String> e = bt.root.left.right;
		BinaryNode<String> e_parent = bt.parent(e);
		System.out.println("e节点的父母节点为："+e_parent);
		System.out.println("该二叉树的叶子节点为：");
		bt.leaf();
		System.out.println();
		System.out.println("二叉树的先序排列为：");
		bt.preorder(bt.root);
		System.out.println();
		System.out.println("二叉树的中序排列为：");
		bt.inorder(bt.root);
		System.out.println();
		System.out.println("二叉树的后序排列为：");
		bt.postorder(bt.root);
		System.out.println();
	}
}