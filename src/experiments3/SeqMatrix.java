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
        String str = "三元组顺序表：" + this.triList.toString() + "\n";
        str += "稀疏矩阵" + this.getClass().getSimpleName() + "(" + rows + " * "
                + cols + "): \n";
        int k = 0;
        // 返回第k个元素，若k指定序号无效则返回null
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
			throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");
		int i = 0, j = 0;
		// 将调用的矩阵和参数mat的各三元组依次分别插入到两个三元组顺序表中
		while (i < this.triList.size() && j < mat.triList.size()) {
			Triple triple1 = this.triList.get(i);
			Triple triple2 = mat.triList.get(j);
			// 若两个三元组表示相同位置的矩阵元素，则对应元素值相加
			if (triple1.compareTo(triple2) == 0) {
				if (triple1.value + triple2.value != 0)				// 相加结果不为0,则新建元素
					this.triList.set(i++,
							new Triple(triple1.getRow(), triple1.getColumn(),
									triple1.value + triple2.value));
				else     //若相加结果为0，则移除该元素
					this.triList.remove(i);
				j++;
			} else if (triple1.compareTo(triple2) < 0) { // 继续向后寻找triple2元素的插入元素
				i++;
			} else {
				// 复制triple2元素插入作为this.list的第i个元素
				this.triList.insert(i++, new Triple(triple2));
				j++;
			}
		}
		// 将mat中剩余三元组依次复制插入当前矩阵三元组顺序表末尾
		while (mat.triList.size() > j) {
			this.triList.append(new Triple(mat.triList.get(j++)));
		}
	}
	
	public SeqMatrix transpose(){
		SeqMatrix sm = new SeqMatrix(cols,rows);     //创建转置后的矩阵
		sm.triList.n=this.triList.size();    //确定转置后的矩阵的大小(与原矩阵相同)
		int[] a = new int [cols];       //创建数组a，用于存放每一列的元素个数(转置后则是行)
		for(int col = 0; col < cols;col++)    //初始化数组，默认为0
			a[col] = 0;
		for(int i= 0;i < triList.size();i++)   //过一遍顺序表，得到每一列的元素个数存到对应序号的数组元素中
			a[triList.get(i).getColumn()]++;
		int[] p = new int [cols];     //创建数组p，用于存放转置前每一列第一个元素的位置
		p[0] = 0;
		for(int i = 1;i < cols;i++)   //进行存放的过程，将前一列的元素个数及开始位置相加得出
			p[i] = p[i-1] + a[i-1];
		for(int i = 0;i<triList.size();i++){    //过一遍顺序表，将各元素转置(行列号交换)存入新的新的顺序表
			int col = triList.get(i).getColumn();   //将先前的列作为转置后的行
			int j = p[col];              //得到每一列第一个元素出现的位置
			//将行列以及值参数传入，创建新对象
			Triple tri = new Triple(triList.get(i).getColumn(),triList.get(i).getRow(),triList.get(i).getValue());
			sm.triList.set(j, tri);   //设定该顺序表位置的值
			p[col]++;
		}
		return sm;
	}
}
