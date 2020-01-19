package experiments5;

import experiments1.SeqList;

public abstract class AbstractGraph<T> { // ����ͼ�࣬T��ʾ����Ԫ������
	public static final int MAX_WEIGHT = 99999; // ���Ȩֵ����ʾ�����ޣ���������Integer.MAX_VALUE;
	protected SeqList<T> vertexlist; // ����˳����洢ͼ�Ķ��㼯��

	public AbstractGraph(int length) { // �����ͼ��������Ϊ0��lengthָ������˳�������
		this.vertexlist = new SeqList<T>(length); // ��������Ϊlength�Ŀ�˳���//��length<0��Java�׳������鳤���쳣
	}

	public AbstractGraph() { // �����ͼ��������Ϊ0
		this(10); // ˳���Ĭ������Ϊ10
	}

	public int vertexCount() { // ����ͼ�Ķ�����
		return this.vertexlist.size(); // ���ض���˳����Ԫ�ظ���
	}

	public String toString() { // ����ͼ�Ķ��㼯�������ַ���
		return "���㼯�ϣ�" + this.vertexlist.toString() + "\n";
	}

	public T getVertex(int i) { // ���ض���viԪ��
		return this.vertexlist.get(i); // ��iԽ�磬�򷵻�null
	}

	public void setVertex(int i, T x) { // ���ö���viԪ��Ϊx
		this.vertexlist.set(i, x); // ��iԽ�磬���׳��쳣
	}

	// ���³��󷽷�û�з����壬�������ṩʵ��
	public abstract int insertVertex(T x); // β����Ԫ��Ϊx�Ķ��㣬����x�������

	public abstract void removeVertex(int i); // ɾ������vi�������й����ı�

	public abstract int weight(int i, int j); // ����<vi,vj>�ߵ�Ȩֵ

	// ����vi��vj��ĺ���ڽӶ������ ����j=-1������vi�ĵ�һ���ڽӶ�����ţ��������ں���ڽӶ��㣬����-1��
	protected abstract int next(int i, int j);
}