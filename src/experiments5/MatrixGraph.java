package experiments5;

import experiments3.Matrix;
import experiments3.Triple;

//邻接矩阵表示的带权图类，T表示顶点元素类型；继承抽象图类
public class MatrixGraph<T> extends AbstractGraph<T> {
	protected Matrix matrix; // 矩阵对象，存储图的邻接矩阵

	// 构造空图，顶点数为0，边数为0；length指定顶点顺序表容量和邻接矩阵容量
	public MatrixGraph(int length) {
		super(length); // 构造容量为length的空顺序表
		this.matrix = new Matrix(length); // 构造length×length矩阵，初值为0
	}

	public MatrixGraph() { // 构造空图，顶点数为0，边数为0
		this(10); // 顶点顺序表和邻接矩阵的默认容量为10
	}

	public MatrixGraph(T[] vertices) { // 以vertices顶点集合构造图，边数为0
		this(vertices.length); // 构造指定容量的空图
		for (int i = 0; i < vertices.length; i++)
			this.insertVertex(vertices[i]); // 插入一个顶点
	}

	public MatrixGraph(T[] vertices, Triple[] edges) { // 以vertices顶点集合和edges边集合构造图
		this(vertices); // 以vertices顶点集合构造图，没有边
		for (int j = 0; j < edges.length; j++)
			this.insertEdge(edges[j]); // 插入一条边
	}

	public String toString() { // 返回图的顶点集合和邻接矩阵描述字符串
		String str = super.toString() + "邻接矩阵:  \n";
		// str+=this.matrix.toString();
		int n = this.vertexCount(); // 顶点数
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (this.matrix.get(i, j) == MAX_WEIGHT)
					str += "     ∞";
				else
					str += String.format("%6d", this.matrix.get(i, j));
			str += "\n";
		}
		return str;
	}

	public void insertEdge(int i, int j, int weight) { // 插入边〈vi,vj〉，权值为weight
		if (i != j) { // 不能表示自身环
			if (weight <= 0 || weight > MAX_WEIGHT) // 边的权值容错，视为无边，取值∞
				weight = MAX_WEIGHT;
			this.matrix.set(i, j, weight); // 设置矩阵元素[i,j]值为weight。若i、j越界，抛出序号越界异常
		} else
			throw new IllegalArgumentException("不能插入自身环，i=" + i + "，j=" + j);
	}

	public void insertEdge(Triple edge) { // 插入一条边
		this.insertEdge(edge.getRow(), edge.getColumn(), edge.getValue());
	}

	public int insertVertex(T x) { // 插入元素为x的顶点，返回x顶点序号
		int i = this.vertexlist.insert(x); // 顶点顺序表尾插入x，返回x序号，自动扩容
		if (i >= this.matrix.getRows()) // 若邻接矩阵容量不够，
			this.matrix.setRowsColumns(i + 1, i + 1); // 矩阵扩容。保持邻接矩阵行列数同图的顶点数
		for (int j = 0; j < i; j++) { // 初始化第i行、列元素值为∞。i==j值已为0
			this.matrix.set(i, j, MAX_WEIGHT);
			this.matrix.set(j, i, MAX_WEIGHT);
		}
		return i; // 返回插入顶点序号
	}

	public void removeEdge(int i, int j) { // 删除边〈vi,vj〉，忽略权值
		if (i != j)
			this.matrix.set(i, j, MAX_WEIGHT); // 设置边的权值为∞。若i、j越界，抛出序号越界异常
	}

	public void removeEdge(Triple edge) { // 删除边，忽略权值
		this.removeEdge(edge.getRow(), edge.getColumn());
	}

	public void removeVertex(int i) { // 删除顶点vi及其所有关联的边
		int n = this.vertexCount(); // 原顶点数
		if (i >= 0 && i < n) {
			this.vertexlist.remove(i); // 删除顶点顺序表第i个元素，顶点数减1。顺序表删除，若i越界，返回null
			for (int j = i + 1; j < n; j++)
				// 第i+1～n-1行元素上移一行，n为原顶点数
				for (int k = 0; k < n; k++)
					this.matrix.set(j - 1, k, this.matrix.get(j, k));
			for (int j = 0; j < n; j++)
				// 第i+1～n-1列元素左移一列
				for (int k = i + 1; k < n; k++)
					this.matrix.set(j, k - 1, this.matrix.get(j, k));
			this.matrix.setRowsColumns(n - 1, n - 1); // 邻接矩阵少一行一列
		} else
			throw new IndexOutOfBoundsException("i=" + i); // 抛出序号越界异常
	}

	public int weight(int i, int j) { // 返回<vi,vj>边的权值
		return this.matrix.get(i, j); // 返回矩阵元素[i,j]值。若i、j越界，抛出序号越界异常
	}

	// 返回顶点vi在vj后的后继邻接顶点序号 ；若j=-1，返回vi的第一个邻接顶点序号；若不存在后继邻接顶点，返回-1。
	protected int next(int i, int j) {
		int n = this.vertexCount();
		if (i >= 0 && i < n && j >= -1 && j < n && i != j)
			for (int k = j + 1; k < n; k++)
				// 当j=-1时，k从0开始寻找后继邻接顶点
				if (this.matrix.get(i, k) > 0
						&& this.matrix.get(i, k) < MAX_WEIGHT)// 权值表示有边
					return k;
		return -1;
	}

	public void removeVertex(T vertex) { // 删除顶点vertex及其关联的边
		int i = this.vertexlist.search(vertex); // 在顺序表中查找值为vertex的元素，返回序号
		this.removeVertex(i); // 删除顶点vi及其关联的边
	}

	public void depthfs(int i, boolean[] visited) {
		visited[i] = true; // 设置访问标记
		for (int j = this.next(i, -1); j >= 0; j = this.next(i, j)) { // 利用for循环，从vi的第一个邻接顶点开始，判断是否有后继的邻接顶点
			if (!visited[j]) { // 且若该后继的邻接顶点未被访问
				visited[j] = true; // 标记该位置下的标记数组为已访问
				depthfs(j, visited); // 从该顶点出发的深度优先搜索遍历，递归调用
			}
		}
	}

	// --------------作业分割线-----------------------
	public int degree(int i) { // 1.返回顶点Vi的度
		int degree = 0;
		for (int j = 0; j < this.matrix.getColumns(); j++) {
			int weight = this.matrix.get(i, j);
			if (weight != MAX_WEIGHT && weight != 0)
				degree++;
		}
		return degree;
	}

	public int edgeCount() { // 2. 返回图的边数
		int edgeCount = 0;
		for (int i = 1; i < this.matrix.getRows(); i++)
			for (int j = 0; j < i; j++) {
				int weight = this.matrix.get(i, j);
				if (weight != MAX_WEIGHT && weight != 0)
					edgeCount++;
			}
		return edgeCount;
	}

	// 全部顶点作为出发点开始深度优先遍历，如果全部都不能一次遍历通过（unConnectNum == n），说明该图不连通。
	public boolean stronglyConnected() { // 3.判断是否为连通图
		int n = this.vertexCount();
		boolean[] visited = new boolean[n];
		int unConnectNum = 0;// 记录不能一次深度优先遍历通过的数目
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				visited[j] = false;
			this.depthfs(i, visited);
			for (int k = 0; k < n; k++) {
				if (visited[k] == false) {
					unConnectNum++;
					break;// 一旦有没有被遍历到的顶点（说明该顶点不属于该连通分量），跳出循环
				}
			}
		}
		if (unConnectNum == n) {
			return false;
		} else {
			return true;
		}
	}
}
