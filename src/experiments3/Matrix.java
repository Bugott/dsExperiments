package experiments3;

public class Matrix {
	private int row, columns;
	private int[][] element;

	public Matrix(int m, int n) {
		this.element = new int[m][n];
		this.row = m;
		this.columns = n;
	}

	public Matrix(int n) {
		this(n, n);
	}

	public Matrix(int m, int n, int[][] value) {
		this(m, n);
		for (int i = 0; i < row && i < m; i++)
			for (int j = 0; j < value[i].length && j < n; j++)
				this.element[i][j] = value[i][j];
	}

	public int getRows() {
		return this.row;
	}

	public int getColumns() {
		return this.columns;
	}

	public int get(int i, int j) {
		if (i >= 0 && i < this.row && j >= 0 && j < this.columns)
			return this.element[i][j];
		throw new IndexOutOfBoundsException("i=" + i + "，j=" + j);
	}

	public void set(int i, int j, int x) {
		if (i >= 0 && i < this.row && j >= 0 && j < this.columns)
			this.element[i][j] = x;
		else
			throw new IndexOutOfBoundsException("i=" + i + "，j=" + j);
	}

	public String toString() {
		String str = "矩阵" + this.getClass().getName() + "（" + this.row + "×"
				+ this.columns + "）：\n";
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.columns; j++)
				str += String.format("%6d", this.element[i][j]);
			str += "\n";
		}
		return str;
	}

	public void setRowsColumns(int m, int n) {
		if (m > 0 && n > 0) {
			if (m > this.element.length || n > this.element[0].length) {
				int[][] source = this.element;
				this.element = new int[m * 2][n * 2];
				for (int i = 0; i < this.row; i++)
					for (int j = 0; j < this.columns; j++)
						this.element[i][j] = source[i][j];
			}
			this.row = m;
			this.columns = n;
		} 
		else throw new IllegalArgumentException("矩阵行列数不能≤0，m=" + m + "，n=" + n);
	}
//————————作业分割线——————
	public boolean equals(Matrix mat) { // 比较矩阵是否相等
		int i, j = 0;
		boolean flag = false;
		if (this.getRows() != mat.getRows()
				|| this.getColumns() != mat.getColumns())
			return false;
		for (i = 0; i < this.getRows() && !flag; i++)
			for (j = 0; j < this.getColumns(); j++)
				if (this.get(i, j) != mat.get(i, j))
					flag = true;
		if (flag == false)
			return true;
		return false;
	}

	public boolean isTriangular(boolean up) {
		int i, j = 0;
		boolean flag = false;
		if (up == true) {
			for (i = 1; i < this.getRows() && !flag; i++)
				for (j = 0; j < i; j++)
					if (this.get(i, j) != 0)
						flag = true;
			if (flag == false)
				return true;
			return false;
		} else {
			for (i = 0; i < this.getRows() - 1 && !flag; i++)
				for (j = i + 1; j < this.getColumns(); j++)
					if (this.get(i, j) != 0)
						flag = true;
			if (flag == false)
				return true;
			return false;
		}
	}
	
	public boolean isSymmetric(){
		int i, j = 0;
		boolean flag = false;
		for (i = 1; i < this.getRows() && !flag; i++)
			for (j = 0; j < i; j++)
				if (this.get(i, j) != this.get(j, i))
					flag = true;
		if (flag == false)
			return true;
		return false;
	}
	
	public Matrix multi(Matrix mat) {
		int i, j, k;
		int sum;
		Matrix mtr;
		if (this.columns != mat.row) {
			return null;
		}
		mtr = new Matrix(this.row, mat.columns);
		for (i = 0; i < this.row; i++) {
			for (k = 0; k < mat.columns; k++) {
				for (sum = 0, j = 0; j < this.columns; j++) {
					sum += this.get(i, j) * mat.get(j, k);
					mtr.set(i, k, sum);
				}
			}
		}
		return mtr;
	}
	
	public int saddlePoint() {
		int min_row,min_y = 0;
		for (int i = 0; i < this.row; i++) {
			min_row = this.get(i, 0);
			boolean flag = true;
			for (int j = 1; j < this.columns; j++) {
				if (min_row > this.get(i, j)) {
					min_row = this.get(i, j);
					min_y = j;
				}
			}
			for (int k = 1; k < this.row; k++) {
				if (min_row < this.get(k, min_y)) {
					flag = false;
					break;
				}
			}
			if (flag)
				return min_row;
		}
		return 0;
	}
}
