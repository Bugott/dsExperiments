package experiments5;

import experiments3.Triple;

public class Test {

	public static void main(String[] args) {
		Integer [] vertices = new Integer[5];
		for(int i = 0; i < 5; i++)
			vertices[i] = i;
		Triple[] edges = new Triple[14];
		edges[0] = new Triple(0,1,2);
		edges[1] = new Triple(0,2,6);
		edges[2] = new Triple(0,3,3);
		edges[3] = new Triple(1,0,2);
		edges[4] = new Triple(1,2,4);
		edges[5] = new Triple(1,3,5);
		edges[6] = new Triple(2,0,6);
		edges[7] = new Triple(2,1,4);
		edges[8] = new Triple(2,4,8);
		edges[9] = new Triple(3,0,3);
		edges[10] = new Triple(3,1,5);
		edges[11] = new Triple(3,4,7);
		edges[12] = new Triple(4,2,8);
		edges[13] = new Triple(4,3,7);
		MatrixGraph<Integer> adMatrix = new MatrixGraph<Integer>(vertices,edges);
		for(int i = 0;i < 5;i++)
			System.out.print("顶点V"+i+"的度为："+adMatrix.degree(i)+"\n");
		System.out.println("该图的边数为："+adMatrix.edgeCount());
		System.out.println("该图是否为连通图："+adMatrix.stronglyConnected());
//		AdjListGraph<Integer> adList = new AdjListGraph<Integer>(vertices,edges);		
	}
}
