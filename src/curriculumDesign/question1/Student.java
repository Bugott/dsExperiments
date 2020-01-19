package curriculumDesign.question1;

// implements Serializable
public class Student implements Comparable<Student> { // 学生类
	private String sName; // 姓名
	private long sNo; // 学号
	private int cNo; // 班级
	private int cNum; // 班级人数
	private double dbScore; // 数据库课程成绩
	private double javaScore; // Java程序语言设计课程成绩
	private double dsScore; // 数据结构课程成绩
	private double statScore; // 统计学课程成绩
	private double scoreSum; // 学生总分
	private double gpa; // 学生所有课程绩点
	private double sAvgScore; // 学生所有课程的平均分
	private double cAvgScore; // 指定课程的班级平均分
	private double cMax; // 指定课程的班级最高分
	private double cMin; // 指定课程的班级最低分

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public long getsNo() {
		return sNo;
	}

	public void setsNo(long sNo) {
		this.sNo = sNo;
	}

	public int getcNo() {
		return cNo;
	}

	public void setcNo(int cNo) {
		this.cNo = cNo;
	}

	public int getcNum() {
		return cNum;
	}

	public void setcNum(int cNum) {
		this.cNum = cNum;
	}

	public double getDbScore() {
		return dbScore;
	}

	public void setDbScore(int dbScore) {
		this.dbScore = dbScore;
	}

	public double getJavaScore() {
		return javaScore;
	}

	public void setJavaScore(int javaScore) {
		this.javaScore = javaScore;
	}

	public double getDsScore() {
		return dsScore;
	}

	public void setDsScore(int dsScore) {
		this.dsScore = dsScore;
	}

	public double getStatScore() {
		return statScore;
	}

	public void setStatScore(int statScore) {
		this.statScore = statScore;
	}

	public double getScoreSum() {
		return (dbScore + javaScore + statScore + dsScore);
	}

	public void setScoreSum(int scoreSum) {
		this.scoreSum = scoreSum;
	}

	public void setScoreSum(int dbScore, int javaScore, int statScore,
			int dsScore) {
		this.scoreSum = dbScore + javaScore + statScore + dsScore;
	}

	public double getGpa() {
		double dbGpa, javaGpa, statGpa, dsGpa;
		if (dbScore >= 60) {
			dbGpa = ((dbScore - 50) / 10);
		} else {
			dbGpa = 0;
		}
		if (javaScore >= 60) {
			javaGpa = ((javaScore - 50) / 10);
		} else {
			javaGpa = 0;
		}
		if (statScore >= 60) {
			statGpa = ((statScore - 50) / 10);
		} else {
			statGpa = 0;
		}
		if (dsScore >= 60) {
			dsGpa = ((dsScore - 50) / 10);
		} else {
			dsGpa = 0;
		}
		return ((dbGpa * 3 + javaGpa * 2 + statGpa * 2 + dsGpa * 2.5) / 9.5);
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public void setGpa(int dbScore, int javaScore, int statScore, int dsScore) {
		this.gpa = ((dbScore * 3 + javaScore * 2 + statScore * 2 + dsScore * 2.5) / (9.5 * 10)) - 5;
	}

	public double getsAvgScore() {
		return (double) ((dbScore + javaScore + statScore + dsScore) / 4);
	}

	public void setsAvgScore(double sAvgScore) {
		this.sAvgScore = sAvgScore;
	}

	public void setsAvgScore(int dbScore, int javaScore, int statScore,
			int dsScore) {
		this.sAvgScore = (dbScore + javaScore + statScore + dsScore) / 4;
	}

	public double getcAvgScore() {
		return cAvgScore;
	}

	public void setcAvgScore(double cAvgScore) {
		this.cAvgScore = cAvgScore;
	}

	public double getcMax() {
		return cMax;
	}

	public void setcMax(int cMax) {
		this.cMax = cMax;
	}

	public double getcMin() {
		return cMin;
	}

	public void setcMin(int cMin) {
		this.cMin = cMin;
	}

	public Student() {
	}

	public Student(String sName, long sNo, int cNo, int cNum, double dbScore,
			double javaScore, double dsScore, double statScore) {
		this.sName = sName;
		this.sNo = sNo;
		this.cNo = cNo;
		this.cNum = cNum;
		this.dbScore = dbScore;
		this.javaScore = javaScore;
		this.dsScore = dsScore;
		this.statScore = statScore;
	}

	public String toString() {
		String str = "[" + this.getsName() + "," + this.getsNo() + ","
				+ this.getcNo() + "," + this.getcNum() + ","
				+ this.getDbScore() + "," + this.getJavaScore() + ","
				+ this.getDsScore() + "," + this.getStatScore() + "]";
		return str;
	}

	public int compareTo(Student stu) {
		if (this.sNo == stu.sNo)
			return 0;
		return (this.sNo < stu.sNo) ? -1 : 1;
	}
}