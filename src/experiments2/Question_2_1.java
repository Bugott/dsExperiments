package experiments2;

public class Question_2_1 {
	private static final int canNotWalk = -1;
	private static final int n = 7;
	private static final int m = 7;
	private int[][] grid = { 
			{ 0, 0, -1, 0, 0, 0, 0 },
			{ 0, 0, -1, -1, 0, 0, 0 }, 
			{ 0, 0, 0, 0, -1, 0, 0 },
			{ 0, 0, 0, -1, -1, 0, 0 }, 
			{ -1, 0, 0, 0, -1, 0, 0 },
			{ -1, -1, -1, 0, 0, 0, 0 }, 
			{ -1, -1, -1, 0, 0, 0, 0 } 
			};
	private int start_x = 2;
	private int start_y = 1;
	private int end_x = 3;
	private int end_y = 5;
	private static int[][] distance = new int[n][m];
	private int[] position_x = { 1, 0, -1, 0 };
	private int[] position_y = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		setDistance();
		Question_2_1 q2 = new Question_2_1();
		int minlen = q2.coremethod();
		System.out.println("最短线路长度是：" + minlen);
		printDistance();
	}

	private int coremethod() {
		SeqQueue<Point> que = new SeqQueue<Point>();
		que.add(new Point(start_x, start_y));
		distance[start_x][start_y] = 0;
		while (!que.isEmpty()) {
			Point point = que.poll();
			if (point.getX() == end_x && point.getY() == end_y)
				break;
			for (int i = 0; i < 4; i++) {
				int xp = point.getX() + position_x[i];
				int yp = point.getY() + position_y[i];
				if (xp >= 0 && xp < n && yp >= 0 && yp < m
						&& grid[xp][yp] != -1 && distance[xp][yp] == canNotWalk) {
					que.add(new Point(xp, yp));
					distance[xp][yp] = distance[point.getX()][point.getY()] + 1;
				}
			}
		}
		return distance[end_x][end_y];
	}

	private static void setDistance() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				distance[i][j] = canNotWalk;
		}
	}

	private static void printDistance() {
		for (int i = 0; i < n; i++) {
			System.out.println();
			for (int j = 0; j < m; j++)
				System.out.print("\t\t" + distance[i][j]);
		}
	}

	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
}