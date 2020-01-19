package experiments3;

public class TestQ1_1 {
    public static void main(String[] args) {
    	int[][] value1={{1,2,3},{4,5,6},{7,8,9}};
    	int[][] value2={{1,2,3},{4,5,6},{7,8,9}};
//    	int[][] value3={{1,2,3},{4,5,6},{7,9,9}};
    	int[][] value4={{1,2,3},{6,5,6},{7,9,9}};
    	Matrix mat1=new Matrix(3, 3, value1);
    	Matrix mat2=new Matrix(3, 3, value2);
//    	Matrix mat3=new Matrix(3, 3, value3);
    	Matrix mat4=new Matrix(3, 3, value4);
    	Matrix m = mat1.multi(mat2);
    	System.out.println(mat1.equals(mat4));
    	System.out.println(mat1.toString());
    	System.out.println(mat2.toString());
    	System.out.println("该两矩阵相乘得：\n"+m.toString());
	}
}
