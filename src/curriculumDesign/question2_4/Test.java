package curriculumDesign.question2_4;

import experiments3.Triple;
import experiments5.MatrixGraph;

import java.util.Scanner;

/**
 * @author Bugott
 */
public class Test {
	public static void main(String[] args) {
		System.out.println("欢迎使用解决医院选址问题的程序！(作者：黄流深)");
		Scanner in = new Scanner(System.in);
		System.out.print("请输入村庄数 ：");
		int counNum = in.nextInt();
		Integer [] vertices = new Integer[counNum];
		for(int i = 0; i < counNum; i++) {
            vertices[i] = i;
        }
		System.out.print("请输入路径数 ：");
		int pathNum = in.nextInt();
		Triple[] edges = new Triple[pathNum];
		for(int i = 0; i < pathNum;i++){
			System.out.print("请输入第"+(i+1)+"条路径的起始村庄名：");
			int j = in.nextInt();
			System.out.print("请输入第"+(i+1)+"条路径的终点村庄名：");
			int k = in.nextInt();
			System.out.print("请输入该路径的距离(权值)：");
			int weight = in.nextInt();
			edges[i] = new Triple(j,k,weight);
		}
		in.close();
		MatrixGraph<Integer> adMatrix = new MatrixGraph<Integer>(vertices,edges);
		Question2_4<Integer> q = new Question2_4<Integer>(adMatrix);
		q.getPath();
		System.out.println("由上可知，医院应该建在V"+q.getPoint()+"点上。");
	}
}