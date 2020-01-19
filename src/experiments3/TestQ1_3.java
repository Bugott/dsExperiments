package experiments3;

public class TestQ1_3 {
    public static void main(String[] args) {
    	Triple[] t1 = { new Triple(0, 2, 11), new Triple(0, 4, 17),
                new Triple(1, 1, 20), new Triple(3, 0, 19),
                new Triple(3, 5, 28), new Triple(4, 4, 50) };
//    	Triple[] t2 = { new Triple(0, 2, -11), new Triple(0, 4, -17),
//                new Triple(2, 3, 51), new Triple(3, 0, 10),
//                new Triple(4, 5, 99), new Triple(1, 1, 0) };
    	SeqMatrix smat1=new SeqMatrix(5, 6, t1);
//    	SeqMatrix smat2=new SeqMatrix(5, 6, t2);
       	System.out.println(smat1.toString());
       	//System.out.println(smat2.toString());
    	SeqMatrix sm = smat1.transpose();
    	System.out.println("该稀疏矩阵的转置如下：\n"+sm.toString());
	}
}
