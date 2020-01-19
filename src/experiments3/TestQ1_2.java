package experiments3;

public class TestQ1_2 {
    public static void main(String[] args) {
    	int[][] value1={{9,6,4,5},
    						  {6,6,3,3},
    						  {4,2,1,2},
    						  {5,3,2,1}};
//    	int[][] value2={{1,2,3,4},
//				  			  {2,3,4,4},
//				  			  {3,3,4,2},
//				  			  {4,3,2,1}};
    	//int[][] value2={{9,0,0,0},{8,7,0,0},{6,5,4,0},{3,2,1,1}};
    	//int[][] value3={{1,2,3,4},{0,4,5,6},{5,6,8,9},{0,0,0,9}};
    	Matrix m1=new Matrix(4, 4, value1);
//    	Matrix m2=new Matrix(4, 4, value2);
    	//Matrix mat1=new Matrix(4, 4, value3);
    	int n = m1.saddlePoint();
    	//boolean flag3 = mat1.isTriangular(true);
    	//boolean flag4 = mat1.isTriangular(false);
    	System.out.println(m1.toString()+"\n该矩阵的鞍点值为(不存在则为0)："+n);
    	//System.out.println(m2.toString()+"\n该矩阵是否为对称矩阵："+flag2);
	}
}
