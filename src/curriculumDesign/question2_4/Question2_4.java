package curriculumDesign.question2_4;

import experiments1.SeqList;
import experiments1.SinglyList;
import experiments5.MatrixGraph;

public class Question2_4<T> {
	MatrixGraph<T> adMatrix = new MatrixGraph<T>();	//建立图的邻接矩阵类adMatrix用于表示图的结点和权值属性
	Integer[] pathMaxList;										//定义整型数组pathMaxList用于存储两两村庄之间的最长路程
	SeqList<Integer> maxDistSeqList;					//定义整型顺序表maxDistSeqList用于存储两两村庄之间的最长路程
	public static final int MAX_WEIGHT = 99999; // 最大权值（表示无穷大∞），不能用Integer.MAX_VALUE;

	public Question2_4() {
	}

	public Question2_4(MatrixGraph<T> adMatrix) {
		this.adMatrix = adMatrix;
		pathMaxList = new Integer[adMatrix.vertexCount()];
		maxDistSeqList = new SeqList<Integer>(adMatrix.vertexCount());
	}

	public void dijkstra(int i) { 					// 求带权图中顶点vi的单源最短路径，Dijkstra算法
		int n = adMatrix.vertexCount(); 			// 图的顶点数
		boolean[] vset = new boolean[n];		 	// 已求出最短路径的顶点集合，初值全为false
		vset[i] = true; 									// 标记源点vi在集合S中。若i越界，Java抛出序号越界异常
		int[] dist = new int[n]; 						// 最短路径长度
		int[] path = new int[n]; 						// 最短路径的终点的前一个顶点
		for (int j = 0; j < n; j++) { 					// 初始化dist和path数组
			dist[j] = adMatrix.weight(i, j);
			path[j] = (j != i && dist[j] < MAX_WEIGHT) ? i : -1;
		}
		for (int j = (i + 1) % n; j != i; j = (j + 1) % n) { 		// 寻找从vi到vj的最短路径，vj在V-S集合
			int mindist = MAX_WEIGHT, min = 0; 			// 求路径长度最小值及其下标
			for (int k = 0; k < n; k++)
				if (!vset[k] && dist[k] < mindist) {
					mindist = dist[k]; 									// 路径长度最小值
					min = k; 												// 路径长度最小值下标
				}
			if (mindist == MAX_WEIGHT) 						// 若没有其他最短路径则算法结束； 此语句对非连通图必需
				break;
			vset[min] = true; 											// 确定一条最短路径的终点min并入集合S
			for (int k = 0; k < n; k++)
				// 调整从vi到V-S中其他顶点的最短路径及长度
				if (!vset[k] && adMatrix.weight(min, k) < MAX_WEIGHT
						&& dist[min] + adMatrix.weight(min, k) < dist[k]) {
					dist[k] = dist[min] + adMatrix.weight(min, k); 	// 用更短路径替换
					path[k] = min; 												// 最短路径经过min顶点
				}
		}
		System.out.print("村庄V" + adMatrix.getVertex(i) + "到其余各村庄的最短路径为：\n");
		for (int j = 0; j < n; j++) {
			// 输出顶点vi到其他各顶点的最短路径
			if (j != i) {
				SinglyList<T> pathlink = new SinglyList<T>(); 	// 路径单链表，记录最短路径经过的各顶点，用于反序
				pathlink.insert(0, adMatrix.getVertex(j)); 		// 单链表插入最短路径终点vj
				for (int k = path[j]; k != i && k != j && k != -1; k = path[k])
					pathlink.insert(0, adMatrix.getVertex(k)); 	// 单链表头插入经过的顶点，反序
				pathlink.insert(0, adMatrix.getVertex(i)); 		// 最短路径的起点vi
				System.out.print(pathlink.toString() + "\t路径长度："
						+ (dist[j] == MAX_WEIGHT ? "∞" : dist[j]) + "\n");
			}
		}
		int pathMax = 0;
		for (int a = 0; a < n; a++)
			pathMax = (dist[a] > pathMax ? dist[a] : pathMax);
		System.out.println("其中，村庄V"+i+"到最远顶点的距离为" + (pathMax == MAX_WEIGHT ? "∞" : pathMax) + "；\n");
		pathMaxList[i] = pathMax;
	}

	public void getPath() {
		for (int i = 0; i < adMatrix.vertexCount(); i++)
			this.dijkstra(i);
	}

	public int getPoint() {
		int shortestLength = MAX_WEIGHT;
		maxDistSeqList = new SeqList<Integer>(pathMaxList);
		for (int j = 0; j < adMatrix.vertexCount(); j++)
			shortestLength = (maxDistSeqList.get(j) < shortestLength ? maxDistSeqList
					.get(j) : shortestLength);
		return maxDistSeqList.search(shortestLength);
	}
}
