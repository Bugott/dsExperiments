package experiments5;

import experiments1.SeqList;

public abstract class AbstractGraph<T> { // 抽象图类，T表示顶点元素类型
	public static final int MAX_WEIGHT = 99999; // 最大权值（表示无穷大∞），不能用Integer.MAX_VALUE;
	protected SeqList<T> vertexlist; // 顶点顺序表，存储图的顶点集合

	public AbstractGraph(int length) { // 构造空图，顶点数为0，length指定顶点顺序表容量
		this.vertexlist = new SeqList<T>(length); // 构造容量为length的空顺序表。//若length<0，Java抛出负数组长度异常
	}

	public AbstractGraph() { // 构造空图，顶点数为0
		this(10); // 顺序表默认容量为10
	}

	public int vertexCount() { // 返回图的顶点数
		return this.vertexlist.size(); // 返回顶点顺序表的元素个数
	}

	public String toString() { // 返回图的顶点集合描述字符串
		return "顶点集合：" + this.vertexlist.toString() + "\n";
	}

	public T getVertex(int i) { // 返回顶点vi元素
		return this.vertexlist.get(i); // 若i越界，则返回null
	}

	public void setVertex(int i, T x) { // 设置顶点vi元素为x
		this.vertexlist.set(i, x); // 若i越界，则抛出异常
	}

	// 以下抽象方法没有方法体，由子类提供实现
	public abstract int insertVertex(T x); // 尾插入元素为x的顶点，返回x顶点序号

	public abstract void removeVertex(int i); // 删除顶点vi及其所有关联的边

	public abstract int weight(int i, int j); // 返回<vi,vj>边的权值

	// 返回vi在vj后的后继邻接顶点序号 ；若j=-1，返回vi的第一个邻接顶点序号；若不存在后继邻接顶点，返回-1。
	protected abstract int next(int i, int j);
}