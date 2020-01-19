package curriculumDesign.question2_4;

import experiments1.SeqList;
import experiments1.SinglyList;
import experiments5.MatrixGraph;

public class Question2_4<T> {
	MatrixGraph<T> adMatrix = new MatrixGraph<T>();	//����ͼ���ڽӾ�����adMatrix���ڱ�ʾͼ�Ľ���Ȩֵ����
	Integer[] pathMaxList;										//������������pathMaxList���ڴ洢������ׯ֮����·��
	SeqList<Integer> maxDistSeqList;					//��������˳���maxDistSeqList���ڴ洢������ׯ֮����·��
	public static final int MAX_WEIGHT = 99999; // ���Ȩֵ����ʾ�����ޣ���������Integer.MAX_VALUE;

	public Question2_4() {
	}

	public Question2_4(MatrixGraph<T> adMatrix) {
		this.adMatrix = adMatrix;
		pathMaxList = new Integer[adMatrix.vertexCount()];
		maxDistSeqList = new SeqList<Integer>(adMatrix.vertexCount());
	}

	public void dijkstra(int i) { 					// ���Ȩͼ�ж���vi�ĵ�Դ���·����Dijkstra�㷨
		int n = adMatrix.vertexCount(); 			// ͼ�Ķ�����
		boolean[] vset = new boolean[n];		 	// ��������·���Ķ��㼯�ϣ���ֵȫΪfalse
		vset[i] = true; 									// ���Դ��vi�ڼ���S�С���iԽ�磬Java�׳����Խ���쳣
		int[] dist = new int[n]; 						// ���·������
		int[] path = new int[n]; 						// ���·�����յ��ǰһ������
		for (int j = 0; j < n; j++) { 					// ��ʼ��dist��path����
			dist[j] = adMatrix.weight(i, j);
			path[j] = (j != i && dist[j] < MAX_WEIGHT) ? i : -1;
		}
		for (int j = (i + 1) % n; j != i; j = (j + 1) % n) { 		// Ѱ�Ҵ�vi��vj�����·����vj��V-S����
			int mindist = MAX_WEIGHT, min = 0; 			// ��·��������Сֵ�����±�
			for (int k = 0; k < n; k++)
				if (!vset[k] && dist[k] < mindist) {
					mindist = dist[k]; 									// ·��������Сֵ
					min = k; 												// ·��������Сֵ�±�
				}
			if (mindist == MAX_WEIGHT) 						// ��û���������·�����㷨������ �����Է���ͨͼ����
				break;
			vset[min] = true; 											// ȷ��һ�����·�����յ�min���뼯��S
			for (int k = 0; k < n; k++)
				// ������vi��V-S��������������·��������
				if (!vset[k] && adMatrix.weight(min, k) < MAX_WEIGHT
						&& dist[min] + adMatrix.weight(min, k) < dist[k]) {
					dist[k] = dist[min] + adMatrix.weight(min, k); 	// �ø���·���滻
					path[k] = min; 												// ���·������min����
				}
		}
		System.out.print("��ׯV" + adMatrix.getVertex(i) + "���������ׯ�����·��Ϊ��\n");
		for (int j = 0; j < n; j++) {
			// �������vi����������������·��
			if (j != i) {
				SinglyList<T> pathlink = new SinglyList<T>(); 	// ·����������¼���·�������ĸ����㣬���ڷ���
				pathlink.insert(0, adMatrix.getVertex(j)); 		// ������������·���յ�vj
				for (int k = path[j]; k != i && k != j && k != -1; k = path[k])
					pathlink.insert(0, adMatrix.getVertex(k)); 	// ������ͷ���뾭���Ķ��㣬����
				pathlink.insert(0, adMatrix.getVertex(i)); 		// ���·�������vi
				System.out.print(pathlink.toString() + "\t·�����ȣ�"
						+ (dist[j] == MAX_WEIGHT ? "��" : dist[j]) + "\n");
			}
		}
		int pathMax = 0;
		for (int a = 0; a < n; a++)
			pathMax = (dist[a] > pathMax ? dist[a] : pathMax);
		System.out.println("���У���ׯV"+i+"����Զ����ľ���Ϊ" + (pathMax == MAX_WEIGHT ? "��" : pathMax) + "��\n");
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
