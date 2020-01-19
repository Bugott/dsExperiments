package experiments3;
import experiments1.SeqList;

public class SeqMatrix {
	int rows,cols;
	SeqList<Triple> triList;
	
	public SeqMatrix(){
		rows = 0;
		cols = 0;
		triList = new SeqList<Triple>();
	}
	public SeqMatrix(int m,int n){
		rows = m;
		cols = n;
		triList = new SeqList<Triple>();
	}
	public SeqMatrix(int m){
		rows = m;
		cols = m;
		triList = new SeqList<Triple>();
	}
	public SeqMatrix(int m,int n,Triple[] value){
		this(m,n);
		for (int i = 0; i < value.length; i++)
            this.triList.insert(value[i]);
	}

	public String toString() {
        String str = "��Ԫ��˳���" + this.triList.toString() + "\n";
        str += "ϡ�����" + this.getClass().getSimpleName() + "(" + rows + " * "
                + cols + "): \n";
        int k = 0;
        // ���ص�k��Ԫ�أ���kָ�������Ч�򷵻�null
        Triple elem = this.triList.get(k++);
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++)
                if (elem != null && i == elem.row && j == elem.column) {
                    str += String.format("%4d", elem.value);
                    elem = this.triList.get(k++);
                } else {
                    str += String.format("%4d", 0);
                }
            str += "\n";
        }
        return str;
    }

	public void add(SeqMatrix mat) {
		if (this.rows != mat.rows || this.cols != mat.cols)
			throw new IllegalArgumentException("�������������ͬ���������");
		int i = 0, j = 0;
		// �����õľ���Ͳ���mat�ĸ���Ԫ�����ηֱ���뵽������Ԫ��˳�����
		while (i < this.triList.size() && j < mat.triList.size()) {
			Triple triple1 = this.triList.get(i);
			Triple triple2 = mat.triList.get(j);
			// ��������Ԫ���ʾ��ͬλ�õľ���Ԫ�أ����ӦԪ��ֵ���
			if (triple1.compareTo(triple2) == 0) {
				if (triple1.value + triple2.value != 0)				// ��ӽ����Ϊ0,���½�Ԫ��
					this.triList.set(i++,
							new Triple(triple1.getRow(), triple1.getColumn(),
									triple1.value + triple2.value));
				else     //����ӽ��Ϊ0�����Ƴ���Ԫ��
					this.triList.remove(i);
				j++;
			} else if (triple1.compareTo(triple2) < 0) { // �������Ѱ��triple2Ԫ�صĲ���Ԫ��
				i++;
			} else {
				// ����triple2Ԫ�ز�����Ϊthis.list�ĵ�i��Ԫ��
				this.triList.insert(i++, new Triple(triple2));
				j++;
			}
		}
		// ��mat��ʣ����Ԫ�����θ��Ʋ��뵱ǰ������Ԫ��˳���ĩβ
		while (mat.triList.size() > j) {
			this.triList.append(new Triple(mat.triList.get(j++)));
		}
	}
	
	public SeqMatrix transpose(){
		SeqMatrix sm = new SeqMatrix(cols,rows);     //����ת�ú�ľ���
		sm.triList.n=this.triList.size();    //ȷ��ת�ú�ľ���Ĵ�С(��ԭ������ͬ)
		int[] a = new int [cols];       //��������a�����ڴ��ÿһ�е�Ԫ�ظ���(ת�ú�������)
		for(int col = 0; col < cols;col++)    //��ʼ�����飬Ĭ��Ϊ0
			a[col] = 0;
		for(int i= 0;i < triList.size();i++)   //��һ��˳����õ�ÿһ�е�Ԫ�ظ����浽��Ӧ��ŵ�����Ԫ����
			a[triList.get(i).getColumn()]++;
		int[] p = new int [cols];     //��������p�����ڴ��ת��ǰÿһ�е�һ��Ԫ�ص�λ��
		p[0] = 0;
		for(int i = 1;i < cols;i++)   //���д�ŵĹ��̣���ǰһ�е�Ԫ�ظ�������ʼλ����ӵó�
			p[i] = p[i-1] + a[i-1];
		for(int i = 0;i<triList.size();i++){    //��һ��˳�������Ԫ��ת��(���кŽ���)�����µ��µ�˳���
			int col = triList.get(i).getColumn();   //����ǰ������Ϊת�ú����
			int j = p[col];              //�õ�ÿһ�е�һ��Ԫ�س��ֵ�λ��
			//�������Լ�ֵ�������룬�����¶���
			Triple tri = new Triple(triList.get(i).getColumn(),triList.get(i).getRow(),triList.get(i).getValue());
			sm.triList.set(j, tri);   //�趨��˳���λ�õ�ֵ
			p[col]++;
		}
		return sm;
	}
}
