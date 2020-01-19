package curriculumDesign.question2_4;

import experiments3.Triple;
import experiments5.MatrixGraph;

import java.util.Scanner;

/**
 * @author Bugott
 */
public class Test {
	public static void main(String[] args) {
		System.out.println("��ӭʹ�ý��ҽԺѡַ����ĳ���(���ߣ�������)");
		Scanner in = new Scanner(System.in);
		System.out.print("�������ׯ�� ��");
		int counNum = in.nextInt();
		Integer [] vertices = new Integer[counNum];
		for(int i = 0; i < counNum; i++) {
            vertices[i] = i;
        }
		System.out.print("������·���� ��");
		int pathNum = in.nextInt();
		Triple[] edges = new Triple[pathNum];
		for(int i = 0; i < pathNum;i++){
			System.out.print("�������"+(i+1)+"��·������ʼ��ׯ����");
			int j = in.nextInt();
			System.out.print("�������"+(i+1)+"��·�����յ��ׯ����");
			int k = in.nextInt();
			System.out.print("�������·���ľ���(Ȩֵ)��");
			int weight = in.nextInt();
			edges[i] = new Triple(j,k,weight);
		}
		in.close();
		MatrixGraph<Integer> adMatrix = new MatrixGraph<Integer>(vertices,edges);
		Question2_4<Integer> q = new Question2_4<Integer>(adMatrix);
		q.getPath();
		System.out.println("���Ͽ�֪��ҽԺӦ�ý���V"+q.getPoint()+"���ϡ�");
	}
}