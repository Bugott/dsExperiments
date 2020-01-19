package curriculumDesign.question1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import experiments1.SeqList;

public class SInfoList {
	SeqList<Student> sInfoList = new SeqList<Student>();
	String allTitle = "姓名\t学号\t班号\t班级的总人数\t数据库\tJava\t\t数据结构\t统计学";
	String sgqTitle = "姓名\t学号\t班号\t总分\t\t平均分\t\t绩点";
	String rankTitle = "科目\t\t优秀\t良好\t中等\t及格\t不及格";
	private int listLength = 0;

    public SeqList<Student> getsInfoList() {
        return sInfoList;
    }

    public void setsInfoList(SeqList<Student> sInfoList) {
        this.sInfoList = sInfoList;
    }

    public String getAllTitle() {
        return allTitle;
    }
//    new SInfoList();
    public void setAllTitle(String allTitle) {
        this.allTitle = allTitle;
    }

    public String getSgqTitle() {
        return sgqTitle;
    }

    public void setSgqTitle(String sgqTitle) {
        this.sgqTitle = sgqTitle;
    }

    public String getRankTitle() {
        return rankTitle;
    }

    public void setRankTitle(String rankTitle) {
        this.rankTitle = rankTitle;
    }

    public SInfoList(){
	}
	
	public int getListLength() {
		return listLength;
	}

	public void setListLength(int listLength) {
		this.listLength = listLength;
	}

	public void printsGradeQuery(long sNo) {
		System.out.println(sgqTitle);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			if (s.getsNo() == sNo) {
				String str = s.getsName() + "\t" + s.getsNo() + "\t"
						+ s.getcNo() + "\t" + s.getScoreSum() + "\t"
						+ String.format("%.2f", s.getsAvgScore()) + "\t\t"
						+ String.format("%.4f", s.getGpa());
				System.out.println(str);
			}
		}
	}

	public void print(long sNo) {
//		System.out.println(allTitle);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			if (s.getsNo() == sNo) {
				String str = s.getsName() + "\t" + s.getsNo() + "\t"
						+ s.getcNo() + "\t\t" + s.getcNum() + "\t\t"
						+ s.getDbScore() + "\t\t" + s.getJavaScore() + "\t\t"
						+ s.getDsScore() + "\t\t" + s.getStatScore();
				System.out.println(str);
			}
		}
	}

	public void printAll() {
		System.out.println(allTitle);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			String str = s.getsName() + "\t" + s.getsNo() + "\t" + s.getcNo()
					+ "\t\t" + s.getcNum() + "\t\t" + s.getDbScore() + "\t\t"
					+ s.getJavaScore() + "\t\t" + s.getDsScore() + "\t\t"
					+ s.getStatScore();
			System.out.println(str);
		}
	}

	public void insert() {
		System.out.println("欢迎使用添加学生信息和成绩的功能！");
		Scanner in = new Scanner(System.in);
		System.out.print("请输入需要添加信息和成绩的学生个数： ");
		int addNum = in.nextInt();
		for (int i = 0; i < addNum; i++) {
			Student s = new Student();
			sInfoList.insert(listLength, s);
			System.out.print("请输入第" + (i + 1) + "位学生的姓名：");
			s.setsName(in.next());
			System.out.print("请输入第" + (i + 1) + "位学生的学号：");
			s.setsNo(in.nextLong());
			System.out.print("请输入第" + (i + 1) + "位学生的班号：");
			s.setcNo(in.nextInt());
			System.out.print("请输入第" + (i + 1) + "位学生的所在班级的总人数：");
			s.setcNum(in.nextInt());
			System.out.print("请输入第" + (i + 1) + "位学生的数据库课程成绩：");
			s.setDbScore(in.nextInt());
			System.out.print("请输入第" + (i + 1) + "位学生的Java程序语言设计课程成绩：");
			s.setJavaScore(in.nextInt());
			System.out.print("请输入第" + (i + 1) + "位学生的数据结构课程成绩：");
			s.setDsScore(in.nextInt());
			System.out.print("请输入第" + (i + 1) + "位学生的统计学课程成绩：");
			s.setStatScore(in.nextInt());
			System.out.println("该学生添加成功！");
			listLength++;
		}
		printAll();
	}

	public void delete() {
		Scanner in = new Scanner(System.in);
		System.out.println("欢迎使用删除学生信息和成绩的功能！");
		System.out.print("请输入你所要删除的学生的学号：");
		long sNo = in.nextLong();
		if (sNo > 0) {
			boolean flag = true;
			for (int i = 0; i < listLength && flag; i++) {
				Student s = sInfoList.get(i);
				if (s.getsNo() == sNo) {
					sInfoList.remove(i);
					System.out.println("该学号为" + sNo + "的学生信息及成绩已经删除！");
					listLength--;
					flag = false;
				}
			}
			printAll();
			if (flag == true)
				System.out.println("学号" + sNo + "的学生未找到，请重新确认后正确输入！");
		} else
			System.out.println("学号输入错误，请确认正确后重新输入！");
	}

	public void search() {
		Scanner in = new Scanner(System.in);
		System.out.println("欢迎使用查询学生信息及成绩的功能！");
		System.out.print("请输入你所要依据查找的线索的代号(1为单科成绩范围;2为班号;3为学号)");
		int num = in.nextInt();
		switch (num) {
		case 1:
			System.out.print("请输入你所要依据查找的科目的代号(1为数据库;2为Java;3为数据结构;4为统计学)：");
			int subNum = in.nextInt();
			System.out.print("请输入该科目成绩查询依据的下限：");
			int low = in.nextInt();
			System.out.print("请输入该科目成绩查询依据的上限：");
			int high = in.nextInt();
			System.out.println(allTitle);
			switch (subNum) {
			case 1:
				for (int i = 0; i < listLength; i++) {
					Student s = sInfoList.get(i);
					if (s.getDbScore() >low && s.getDbScore()<high) 
						print(s.getsNo());
				}
				break;
			case 2:
				for (int i = 0; i < listLength; i++) {
					Student s = sInfoList.get(i);
					if (s.getJavaScore() >low && s.getJavaScore()<high) 
						print(s.getsNo());
				}
				break;
			case 3:
				for (int i = 0; i < listLength; i++) {
					Student s = sInfoList.get(i);
					if (s.getDsScore() >low && s.getDsScore()<high) 
						print(s.getsNo());
				}
				break;
			case 4:
				for (int i = 0; i < listLength; i++) {
					Student s = sInfoList.get(i);
					if (s.getStatScore() >low && s.getStatScore()<high) 
						print(s.getsNo());
				}
				break;
			}
			break;
		case 2:
			System.out.print("请输入你所要依据查找的班号：");
			int cNo = in.nextInt();
			System.out.println(allTitle);
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo) 
					print(s.getsNo());
			}
			break;
		case 3:
		System.out.print("请输入你所要查询的学生的学号：");
		long sNo = 0;
		sNo = in.nextLong();
		if (sNo > 0) {
			boolean flag = true;
			for (int i = 0; i < listLength && flag; i++) {
				Student s = sInfoList.get(i);
				if (s.getsNo() == sNo) {
					System.out.println(allTitle);
					print(sNo);
					flag = false;
				}
			}
			if (flag == true)
				System.out.println("学号" + sNo + "的学生未找到，请重新确认后正确输入！");
		} else
			System.out.println("学号输入错误，请确认正确后重新输入！");
			break;
		}
	}

	public void searchOnStudent() {
		Scanner in = new Scanner(System.in);
		System.out.println("欢迎使用查询学生成绩统计信息的功能！");
		System.out.print("请输入你所要查询的学生的学号：");
		long sNo = 0;
		sNo = in.nextLong();
		if (sNo > 0) {
			boolean flag = true;
			for (int i = 0; i < listLength && flag; i++) {
				Student s = sInfoList.get(i);
				if (s.getsNo() == sNo) {
					printsGradeQuery(sNo);
					flag = false;
				}
			}
			if (flag == true)
				System.out.println("学号" + sNo + "的学生未找到，请重新确认后正确输入！");
		} else
			System.out.println("学号输入错误，请确认正确后重新输入！");
	}

	public void searchOnClass() {
		Scanner in = new Scanner(System.in);
		System.out.println("欢迎使用查询班级成绩统计信息的功能！");
		System.out.print("请输入你所要查询的班级的班号：");
		int cNo = in.nextInt();
		System.out.print("请输入你所要查询的班级的科目的代号(1为数据库;2为Java;3为数据结构;4为统计学)：");
		int subNum = in.nextInt();
		String subName = "";
		switch (subNum) {
		case 1:
			subName = "数据库";
			break;
		case 2:
			subName = "Java";
			break;
		case 3:
			subName = "数据结构";
			break;
		case 4:
			subName = "统计学";
			break;
		}
		int cNoMax = 0;
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			cNoMax = cNoMax > s.getcNo() ? cNoMax : s.getcNo();
		}
		if (cNo > 0 && cNo <= cNoMax && subNum > 0 && subNum < 5) {
			System.out.println("班号\t\t科目\t\t最高分\t最低分\t\t平均分");
			System.out.println(cNo + "\t" + subName + "\t\t"
					+ getcMax(subNum, cNo) + "\t\t" + getcMin(subNum, cNo)
					+ "\t\t\t"
					+ String.format("%.2f", getcAvgScore(subNum, cNo)));
		} else
			System.out.println("班号或科目代号输入错误，请确认正确后重新输入！");
	}

	public double getcMax(int subNum, int cNo) {
		switch (subNum) {
		case 1:
			Double dbMax = 0.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo)
					dbMax = dbMax > s.getDbScore() ? dbMax : s.getDbScore();
			}
			return dbMax;
		case 2:
			Double javaMax = 0.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo)
					javaMax = javaMax > s.getJavaScore() ? javaMax : s
							.getJavaScore();
			}
			return javaMax;
		case 3:
			Double dsMax = 0.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo)
					dsMax = dsMax > s.getDsScore() ? dsMax : s.getDsScore();
			}
			return dsMax;
		case 4:
			Double statMax = 0.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo)
					statMax = statMax > s.getStatScore() ? statMax : s
							.getStatScore();
			}
			return statMax;
		}
		return 0;
	}

	public double getcMin(int subNum, int cNo) {
		switch (subNum) {
		case 1:
			Double dbMin = 100.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo)
					dbMin = dbMin < s.getDbScore() ? dbMin : s.getDbScore();
			}
			return dbMin;
		case 2:
			Double javaMin = 100.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo)
					javaMin = javaMin < s.getJavaScore() ? javaMin : s
							.getJavaScore();
			}
			return javaMin;
		case 3:
			Double dsMin = 100.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo)
					dsMin = dsMin < s.getDsScore() ? dsMin : s.getDsScore();
			}
			return dsMin;
		case 4:
			Double statMin = 100.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo)
					statMin = statMin < s.getStatScore() ? statMin : s
							.getStatScore();
			}
			return statMin;
		}
		return 0;
	}

	public double getcAvgScore(int subNum, int cNo) {
		int cNum = 0;
		switch (subNum) {
		case 1:
			Double dbsum = 0.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo) {
					cNum = s.getcNum();
					dbsum = dbsum + s.getDbScore();
				}
			}
			return dbsum / cNum;
		case 2:
			Double javaSum = 0.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo) {
					cNum = s.getcNum();
					javaSum = javaSum + s.getJavaScore();
				}

			}
			return javaSum / cNum;
		case 3:
			Double dsSum = 0.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo) {
					cNum = s.getcNum();
					dsSum = dsSum + s.getDsScore();
				}
			}
			return dsSum / cNum;
		case 4:
			Double statSum = 0.0;
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo) {
					cNum = s.getcNum();
					statSum = statSum + s.getStatScore();
				}
			}
			return statSum / cNum;
		}
		return 0;
	}

	public void getAllRankNum() {
		Scanner in = new Scanner(System.in);
		System.out.println("欢迎使用依据学生成绩分等级统计人数的功能！");
		System.out.print("请输入你所要查找的班级的科目的代号(1为数据库;2为Java;3为数据结构;4为统计学)：");
		int subNum = in.nextInt();
		switch (subNum) {
		case 1:
			getDbRankNum();
			break;
		case 2:
			getJavaRankNum();
			break;
		case 3:
			getDsRankNum();
			break;
		case 4:
			getStatRankNum();
			break;
		default:
			System.out.println("为了更好地使用功能，请输入正确的代号！");
		}
	}

	public void getDbRankNum() {
		int excRankNum = 0;
		int goodRankNum = 0;
		int medRankNum = 0;
		int pasRankNum = 0;
		int failRankNum = 0;
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			if (s.getDbScore() >= 90) {
				excRankNum = excRankNum + 1;
			} else if (s.getDbScore() >= 80 && s.getDbScore() < 90) {
				goodRankNum = goodRankNum + 1;
			} else if (s.getDbScore() >= 70 && s.getDbScore() < 80) {
				medRankNum = medRankNum + 1;
			} else if (s.getDbScore() >= 60 && s.getDbScore() < 70) {
				pasRankNum = pasRankNum + 1;
			} else {
				failRankNum = failRankNum + 1;
			}
		}
		System.out.println(rankTitle);
		System.out.println("数据库" + "\t" + excRankNum + "\t" + goodRankNum
				+ "\t" + medRankNum + "\t" + pasRankNum + "\t" + failRankNum);
	}

	public void getJavaRankNum() {
		int excRankNum = 0;
		int goodRankNum = 0;
		int medRankNum = 0;
		int pasRankNum = 0;
		int failRankNum = 0;
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			if (s.getJavaScore() >= 90) {
				excRankNum = excRankNum + 1;
			} else if (s.getJavaScore() >= 80 && s.getJavaScore() < 90) {
				goodRankNum = goodRankNum + 1;
			} else if (s.getJavaScore() >= 70 && s.getJavaScore() < 80) {
				medRankNum = medRankNum + 1;
			} else if (s.getJavaScore() >= 60 && s.getJavaScore() < 70) {
				pasRankNum = pasRankNum + 1;
			} else {
				failRankNum = failRankNum + 1;
			}
		}
		System.out.println(rankTitle);
		System.out.println("Java   " + "\t" + excRankNum + "\t" + goodRankNum
				+ "\t" + medRankNum + "\t" + pasRankNum + "\t" + failRankNum);
	}

	public void getDsRankNum() {
		int excRankNum = 0;
		int goodRankNum = 0;
		int medRankNum = 0;
		int pasRankNum = 0;
		int failRankNum = 0;
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			if (s.getDsScore() >= 90) {
				excRankNum = excRankNum + 1;
			} else if (s.getDsScore() >= 80 && s.getDsScore() < 90) {
				goodRankNum = goodRankNum + 1;
			} else if (s.getDsScore() >= 70 && s.getDsScore() < 80) {
				medRankNum = medRankNum + 1;
			} else if (s.getDsScore() >= 60 && s.getDsScore() < 70) {
				pasRankNum = pasRankNum + 1;
			} else {
				failRankNum = failRankNum + 1;
			}
		}
		System.out.println(rankTitle);
		System.out.println("数据结构" + "\t" + excRankNum + "\t" + goodRankNum
				+ "\t" + medRankNum + "\t" + pasRankNum + "\t" + failRankNum);
	}

	public void getStatRankNum() {
		int excRankNum = 0;
		int goodRankNum = 0;
		int medRankNum = 0;
		int pasRankNum = 0;
		int failRankNum = 0;
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			if (s.getStatScore() >= 90) {
				excRankNum = excRankNum + 1;
			} else if (s.getStatScore() >= 80 && s.getStatScore() < 90) {
				goodRankNum = goodRankNum + 1;
			} else if (s.getStatScore() >= 70 && s.getStatScore() < 80) {
				medRankNum = medRankNum + 1;
			} else if (s.getStatScore() >= 60 && s.getStatScore() < 70) {
				pasRankNum = pasRankNum + 1;
			} else {
				failRankNum = failRankNum + 1;
			}
		}
		System.out.println(rankTitle);
		System.out.println("统计学" + "\t" + excRankNum + "\t" + goodRankNum
				+ "\t" + medRankNum + "\t" + pasRankNum + "\t" + failRankNum);
	}

	public void sort() {
		Scanner in = new Scanner(System.in);
		System.out.println("欢迎使用排序成绩的功能！");
		System.out
				.print("请输入你所要排序的依据的代号(成绩排序依据:1为数据库,2为Java,3为数据结构,4为统计学;5为学号依据排序)：");
		int subNum = in.nextInt();
		switch (subNum) {
		case 1:
			sortByDbScore();
			break;
		case 2:
			sortByJavaScore();
			break;
		case 3:
			sortByDsScore();
			break;
		case 4:
			sortByStatScore();
			break;
		case 5:
			sortBysNo();
			break;
		default:
			System.out.println("为了更好地使用功能，请输入正确的代号！");
		}
	}

	public void sortBysNo() {
		for (int i = 0; i < listLength - 1; i++) {
			for (int j = i + 1; j < listLength; j++) {
				Student s1 = sInfoList.get(i);
				Student s2 = sInfoList.get(j);
				if (s1.getsNo() > s2.getsNo()) {
					sInfoList.replaceFirst(s1, s2);
					sInfoList.replaceLast(s2, s1);
				}
			}
		}
		System.out.println("已按照学号完成排序！");
		printAll();
//		SeqList<Long> temp = new SeqList<Long>(listLength);
//		for (int i = 0; i < listLength; i++) {
//			Student s = sInfoList.get(i);
//			temp.insert(i, s.getsNo());
//		}
//		BinarySortTree<Long> result = new BinarySortTree<Long>(temp);
//		System.out.println("已用二叉排序树存储！结果为：" + result.toString());
		SeqList<Student> temp = new SeqList<Student>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s);
		}
		BinarySortTree<Student> result = new BinarySortTree<Student>(temp);
		System.out.println("已用二叉排序树存储！结果为：\n" + result.toString());
	}

	public void sortByDbScore() {
		for (int i = 0; i < listLength - 1; i++) {
			for (int j = i + 1; j < listLength; j++) {
				Student s1 = sInfoList.get(i);
				Student s2 = sInfoList.get(j);
				if (s1.getDbScore() < s2.getDbScore()) {
					sInfoList.replaceFirst(s1, s2);
					sInfoList.replaceLast(s2, s1);
				}
			}
		}
		System.out.println("已按照数据库成绩完成排序！");
		printAll();
		SeqList<Double> temp = new SeqList<Double>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s.getDbScore());
		}
		BinarySortTree<Double> result = new BinarySortTree<Double>(temp);
		System.out.println("已用二叉排序树存储！结果为：" + result.toString());
	}

	public void sortByJavaScore() {
		for (int i = 0; i < listLength - 1; i++) {
			for (int j = i + 1; j < listLength; j++) {
				Student s1 = sInfoList.get(i);
				Student s2 = sInfoList.get(j);
				if (s1.getJavaScore() < s2.getJavaScore()) {
					sInfoList.replaceFirst(s1, s2);
					sInfoList.replaceLast(s2, s1);
				}
			}
		}
		System.out.println("已按照Java成绩完成排序！");
		printAll();
		SeqList<Double> temp = new SeqList<Double>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s.getJavaScore());
		}
		BinarySortTree<Double> result = new BinarySortTree<Double>(temp);
		System.out.println("已用二叉排序树存储！结果为：" + result.toString());
	}

	public void sortByDsScore() {
		for (int i = 0; i < listLength - 1; i++) {
			for (int j = i + 1; j < listLength; j++) {
				Student s1 = sInfoList.get(i);
				Student s2 = sInfoList.get(j);
				if (s1.getDsScore() < s2.getDsScore()) {
					sInfoList.replaceFirst(s1, s2);
					sInfoList.replaceLast(s2, s1);
				}
			}
		}
		System.out.println("已按照数据结构成绩完成排序！");
		printAll();
		SeqList<Double> temp = new SeqList<Double>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s.getDsScore());
		}
		BinarySortTree<Double> result = new BinarySortTree<Double>(temp);
		System.out.println("已用二叉排序树存储！结果为：" + result.toString());
	}

	public void sortByStatScore() {
		for (int i = 0; i < listLength - 1; i++) {
			for (int j = i + 1; j < listLength; j++) {
				Student s1 = sInfoList.get(i);
				Student s2 = sInfoList.get(j);
				if (s1.getStatScore() < s2.getStatScore()) {
					sInfoList.replaceFirst(s1, s2);
					sInfoList.replaceLast(s2, s1);
				}
			}
		}
		System.out.println("已按照统计学成绩完成排序！");
		printAll();
		SeqList<Double> temp = new SeqList<Double>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s.getStatScore());
		}
		BinarySortTree<Double> result = new BinarySortTree<Double>(temp);
		System.out.println("已用二叉排序树存储！结果为：" + result.toString());
	}

	public void printToFile() throws IOException {
		System.out.println("欢迎使用将学生信息和成绩表写入文本文件的功能！");
		FileWriter fw = new FileWriter("SaveGradeTable.txt");
		fw.write("姓名\t学号\t班号\t班级的总人数\t数据库\tJava\t数据结构\t统计学" + "\n");
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			String str = s.getsName() + "\t" + s.getsNo() + "\t" + s.getcNo()
					+ "\t" + s.getcNum() + "\t\t" + s.getDbScore() + "\t"
					+ s.getJavaScore() + "\t" + s.getDsScore() + "\t"
					+ s.getStatScore() + "\n";
			fw.write(str);
		}
		fw.close();
		System.out.println("写入成功！写入文件为项目根目录下的SaveGradeTable.txt");
	}

	public void read() throws IOException {
		System.out.println("欢迎使用从文本文件中读取学生信息和成绩表的功能！\nP.S.需要将欲读取文本文件放入项目根目录下，并命名为GradeTable.txt");
		InputStream is = new FileInputStream("GradeTable.txt");
		String line; // 用来保存每行读取的内容
		Student s = new Student();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		line = reader.readLine(); // 读取第一行
		if (line == null)
			System.out.println("文件为空");
		line = reader.readLine();
		while (line != null) { // 如果 line 为空说明读完了
			s = transformToStudent(line);
			sInfoList.insert(s);
			listLength++;
			line = reader.readLine(); // 读取下一行
		}
		reader.close();
		is.close();
		System.out.println("读取GradeTable.txt成功！");
		printAll();
	}

	public Student transformToStudent(String line) {
		Student s;
		String temp = line.replaceAll("\t", ",");
		String lineFinal = temp.replaceAll(",,", ",");
		String[] element = lineFinal.split(",");
		long sNo = Long.parseLong(element[1]);
		int cNo = Integer.parseInt(element[2]);
		int cNum = Integer.parseInt(element[3]);
		double dbScore = Double.parseDouble(element[4]);
		double javaScore = Double.parseDouble(element[5]);
		double dsScore = Double.parseDouble(element[6]);
		double statScore = Double.parseDouble(element[7]);
		s = new Student(element[0], sNo, cNo, cNum, dbScore, javaScore,
				dsScore, statScore);
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		SInfoList list = new SInfoList();
		Scanner in = new Scanner(System.in);
		System.out.println("*****欢迎使用低配命令行版成绩管理系统(作者:黄流深)*****");
		boolean flag = true;
		while (flag) {
			System.out.println("  	  ==========================================================\n	|| 请输入你所要执行的功能的代号：\t\t\t\t\t||\n"+"	||       1.添加学生信息和成绩\t\t\t\t\t\t\t||\n" + "	||       2.删除学生信息和成绩\t\t\t\t\t\t\t||\n"
					+ "	||       3.查询学生信息及成绩\t\t\t\t\t\t\t||\n" + "	||       4.查询学生成绩统计信息\t\t\t\t\t\t||\n" + "	||       5.查询班级成绩统计信息\t\t\t\t\t\t||\n"
					+ "	||       6.依据学生成绩分等级统计人数\t\t\t\t\t||\n" + "	||       7.依据学号和成绩排序成绩并用二叉排序树存储结果\t\t||\n"
					+ "	||       8.将学生信息和成绩表写入文本文件\t\t\t\t||\n" + "	||       9.从文本文件中读取学生信息和成绩表\t\t\t\t||\n" + "	||       10.退出系统\t\t\t\t\t\t\t\t||\n  	  ==========================================================");
			int code = in.nextInt();
			switch (code) {
			case 1:
				list.insert();
				break;
			case 2:
				list.delete();
				break;
			case 3:
				list.search();
				break;
			case 4:
				list.searchOnStudent();
				break;
			case 5:
				list.searchOnClass();
				break;
			case 6:
				list.getAllRankNum();
				break;
			case 7:
				list.sort();
				break;
			case 8:
				list.printToFile();
				break;
			case 9:
				list.read();
				break;
			case 10:
				flag = false;
				System.out.println("感谢您的使用，已退出系统，欢迎下次光临！");
				break;
			default:
				System.out.println("为了更好地使用功能，请输入正确的代号！");
			}
		}
		in.close();
	}
}