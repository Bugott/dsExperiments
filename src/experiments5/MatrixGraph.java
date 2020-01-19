package experiments5;

import experiments3.Matrix;
import experiments3.Triple;

//�ڽӾ����ʾ�Ĵ�Ȩͼ�࣬T��ʾ����Ԫ�����ͣ��̳г���ͼ��
public class MatrixGraph<T> extends AbstractGraph<T> {
	protected Matrix matrix; // ������󣬴洢ͼ���ڽӾ���

	// �����ͼ��������Ϊ0������Ϊ0��lengthָ������˳����������ڽӾ�������
	public MatrixGraph(int length) {
		super(length); // ��������Ϊlength�Ŀ�˳���
		this.matrix = new Matrix(length); // ����length��length���󣬳�ֵΪ0
	}

	public MatrixGraph() { // �����ͼ��������Ϊ0������Ϊ0
		this(10); // ����˳�����ڽӾ����Ĭ������Ϊ10
	}

	public MatrixGraph(T[] vertices) { // ��vertices���㼯�Ϲ���ͼ������Ϊ0
		this(vertices.length); // ����ָ�������Ŀ�ͼ
		for (int i = 0; i < vertices.length; i++)
			this.insertVertex(vertices[i]); // ����һ������
	}

	public MatrixGraph(T[] vertices, Triple[] edges) { // ��vertices���㼯�Ϻ�edges�߼��Ϲ���ͼ
		this(vertices); // ��vertices���㼯�Ϲ���ͼ��û�б�
		for (int j = 0; j < edges.length; j++)
			this.insertEdge(edges[j]); // ����һ����
	}

	public String toString() { // ����ͼ�Ķ��㼯�Ϻ��ڽӾ��������ַ���
		String str = super.toString() + "�ڽӾ���:  \n";
		// str+=this.matrix.toString();
		int n = this.vertexCount(); // ������
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				if (this.matrix.get(i, j) == MAX_WEIGHT)
					str += "     ��";
				else
					str += String.format("%6d", this.matrix.get(i, j));
			str += "\n";
		}
		return str;
	}

	public void insertEdge(int i, int j, int weight) { // ����ߡ�vi,vj����ȨֵΪweight
		if (i != j) { // ���ܱ�ʾ����
			if (weight <= 0 || weight > MAX_WEIGHT) // �ߵ�Ȩֵ�ݴ���Ϊ�ޱߣ�ȡֵ��
				weight = MAX_WEIGHT;
			this.matrix.set(i, j, weight); // ���þ���Ԫ��[i,j]ֵΪweight����i��jԽ�磬�׳����Խ���쳣
		} else
			throw new IllegalArgumentException("���ܲ���������i=" + i + "��j=" + j);
	}

	public void insertEdge(Triple edge) { // ����һ����
		this.insertEdge(edge.getRow(), edge.getColumn(), edge.getValue());
	}

	public int insertVertex(T x) { // ����Ԫ��Ϊx�Ķ��㣬����x�������
		int i = this.vertexlist.insert(x); // ����˳���β����x������x��ţ��Զ�����
		if (i >= this.matrix.getRows()) // ���ڽӾ�������������
			this.matrix.setRowsColumns(i + 1, i + 1); // �������ݡ������ڽӾ���������ͬͼ�Ķ�����
		for (int j = 0; j < i; j++) { // ��ʼ����i�С���Ԫ��ֵΪ�ޡ�i==jֵ��Ϊ0
			this.matrix.set(i, j, MAX_WEIGHT);
			this.matrix.set(j, i, MAX_WEIGHT);
		}
		return i; // ���ز��붥�����
	}

	public void removeEdge(int i, int j) { // ɾ���ߡ�vi,vj��������Ȩֵ
		if (i != j)
			this.matrix.set(i, j, MAX_WEIGHT); // ���ñߵ�ȨֵΪ�ޡ���i��jԽ�磬�׳����Խ���쳣
	}

	public void removeEdge(Triple edge) { // ɾ���ߣ�����Ȩֵ
		this.removeEdge(edge.getRow(), edge.getColumn());
	}

	public void removeVertex(int i) { // ɾ������vi�������й����ı�
		int n = this.vertexCount(); // ԭ������
		if (i >= 0 && i < n) {
			this.vertexlist.remove(i); // ɾ������˳����i��Ԫ�أ���������1��˳���ɾ������iԽ�磬����null
			for (int j = i + 1; j < n; j++)
				// ��i+1��n-1��Ԫ������һ�У�nΪԭ������
				for (int k = 0; k < n; k++)
					this.matrix.set(j - 1, k, this.matrix.get(j, k));
			for (int j = 0; j < n; j++)
				// ��i+1��n-1��Ԫ������һ��
				for (int k = i + 1; k < n; k++)
					this.matrix.set(j, k - 1, this.matrix.get(j, k));
			this.matrix.setRowsColumns(n - 1, n - 1); // �ڽӾ�����һ��һ��
		} else
			throw new IndexOutOfBoundsException("i=" + i); // �׳����Խ���쳣
	}

	public int weight(int i, int j) { // ����<vi,vj>�ߵ�Ȩֵ
		return this.matrix.get(i, j); // ���ؾ���Ԫ��[i,j]ֵ����i��jԽ�磬�׳����Խ���쳣
	}

	// ���ض���vi��vj��ĺ���ڽӶ������ ����j=-1������vi�ĵ�һ���ڽӶ�����ţ��������ں���ڽӶ��㣬����-1��
	protected int next(int i, int j) {
		int n = this.vertexCount();
		if (i >= 0 && i < n && j >= -1 && j < n && i != j)
			for (int k = j + 1; k < n; k++)
				// ��j=-1ʱ��k��0��ʼѰ�Һ���ڽӶ���
				if (this.matrix.get(i, k) > 0
						&& this.matrix.get(i, k) < MAX_WEIGHT)// Ȩֵ��ʾ�б�
					return k;
		return -1;
	}

	public void removeVertex(T vertex) { // ɾ������vertex��������ı�
		int i = this.vertexlist.search(vertex); // ��˳����в���ֵΪvertex��Ԫ�أ��������
		this.removeVertex(i); // ɾ������vi��������ı�
	}

	public void depthfs(int i, boolean[] visited) {
		visited[i] = true; // ���÷��ʱ��
		for (int j = this.next(i, -1); j >= 0; j = this.next(i, j)) { // ����forѭ������vi�ĵ�һ���ڽӶ��㿪ʼ���ж��Ƿ��к�̵��ڽӶ���
			if (!visited[j]) { // �����ú�̵��ڽӶ���δ������
				visited[j] = true; // ��Ǹ�λ���µı������Ϊ�ѷ���
				depthfs(j, visited); // �Ӹö��������������������������ݹ����
			}
		}
	}

	// --------------��ҵ�ָ���-----------------------
	public int degree(int i) { // 1.���ض���Vi�Ķ�
		int degree = 0;
		for (int j = 0; j < this.matrix.getColumns(); j++) {
			int weight = this.matrix.get(i, j);
			if (weight != MAX_WEIGHT && weight != 0)
				degree++;
		}
		return degree;
	}

	public int edgeCount() { // 2. ����ͼ�ı���
		int edgeCount = 0;
		for (int i = 1; i < this.matrix.getRows(); i++)
			for (int j = 0; j < i; j++) {
				int weight = this.matrix.get(i, j);
				if (weight != MAX_WEIGHT && weight != 0)
					edgeCount++;
			}
		return edgeCount;
	}

	// ȫ��������Ϊ�����㿪ʼ������ȱ��������ȫ��������һ�α���ͨ����unConnectNum == n����˵����ͼ����ͨ��
	public boolean stronglyConnected() { // 3.�ж��Ƿ�Ϊ��ͨͼ
		int n = this.vertexCount();
		boolean[] visited = new boolean[n];
		int unConnectNum = 0;// ��¼����һ��������ȱ���ͨ������Ŀ
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				visited[j] = false;
			this.depthfs(i, visited);
			for (int k = 0; k < n; k++) {
				if (visited[k] == false) {
					unConnectNum++;
					break;// һ����û�б��������Ķ��㣨˵���ö��㲻���ڸ���ͨ������������ѭ��
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
