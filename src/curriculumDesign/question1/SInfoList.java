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
	String allTitle = "����\tѧ��\t���\t�༶��������\t���ݿ�\tJava\t\t���ݽṹ\tͳ��ѧ";
	String sgqTitle = "����\tѧ��\t���\t�ܷ�\t\tƽ����\t\t����";
	String rankTitle = "��Ŀ\t\t����\t����\t�е�\t����\t������";
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
		System.out.println("��ӭʹ�����ѧ����Ϣ�ͳɼ��Ĺ��ܣ�");
		Scanner in = new Scanner(System.in);
		System.out.print("��������Ҫ�����Ϣ�ͳɼ���ѧ�������� ");
		int addNum = in.nextInt();
		for (int i = 0; i < addNum; i++) {
			Student s = new Student();
			sInfoList.insert(listLength, s);
			System.out.print("�������" + (i + 1) + "λѧ����������");
			s.setsName(in.next());
			System.out.print("�������" + (i + 1) + "λѧ����ѧ�ţ�");
			s.setsNo(in.nextLong());
			System.out.print("�������" + (i + 1) + "λѧ���İ�ţ�");
			s.setcNo(in.nextInt());
			System.out.print("�������" + (i + 1) + "λѧ�������ڰ༶����������");
			s.setcNum(in.nextInt());
			System.out.print("�������" + (i + 1) + "λѧ�������ݿ�γ̳ɼ���");
			s.setDbScore(in.nextInt());
			System.out.print("�������" + (i + 1) + "λѧ����Java����������ƿγ̳ɼ���");
			s.setJavaScore(in.nextInt());
			System.out.print("�������" + (i + 1) + "λѧ�������ݽṹ�γ̳ɼ���");
			s.setDsScore(in.nextInt());
			System.out.print("�������" + (i + 1) + "λѧ����ͳ��ѧ�γ̳ɼ���");
			s.setStatScore(in.nextInt());
			System.out.println("��ѧ����ӳɹ���");
			listLength++;
		}
		printAll();
	}

	public void delete() {
		Scanner in = new Scanner(System.in);
		System.out.println("��ӭʹ��ɾ��ѧ����Ϣ�ͳɼ��Ĺ��ܣ�");
		System.out.print("����������Ҫɾ����ѧ����ѧ�ţ�");
		long sNo = in.nextLong();
		if (sNo > 0) {
			boolean flag = true;
			for (int i = 0; i < listLength && flag; i++) {
				Student s = sInfoList.get(i);
				if (s.getsNo() == sNo) {
					sInfoList.remove(i);
					System.out.println("��ѧ��Ϊ" + sNo + "��ѧ����Ϣ���ɼ��Ѿ�ɾ����");
					listLength--;
					flag = false;
				}
			}
			printAll();
			if (flag == true)
				System.out.println("ѧ��" + sNo + "��ѧ��δ�ҵ���������ȷ�Ϻ���ȷ���룡");
		} else
			System.out.println("ѧ�����������ȷ����ȷ���������룡");
	}

	public void search() {
		Scanner in = new Scanner(System.in);
		System.out.println("��ӭʹ�ò�ѯѧ����Ϣ���ɼ��Ĺ��ܣ�");
		System.out.print("����������Ҫ���ݲ��ҵ������Ĵ���(1Ϊ���Ƴɼ���Χ;2Ϊ���;3Ϊѧ��)");
		int num = in.nextInt();
		switch (num) {
		case 1:
			System.out.print("����������Ҫ���ݲ��ҵĿ�Ŀ�Ĵ���(1Ϊ���ݿ�;2ΪJava;3Ϊ���ݽṹ;4Ϊͳ��ѧ)��");
			int subNum = in.nextInt();
			System.out.print("������ÿ�Ŀ�ɼ���ѯ���ݵ����ޣ�");
			int low = in.nextInt();
			System.out.print("������ÿ�Ŀ�ɼ���ѯ���ݵ����ޣ�");
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
			System.out.print("����������Ҫ���ݲ��ҵİ�ţ�");
			int cNo = in.nextInt();
			System.out.println(allTitle);
			for (int i = 0; i < listLength; i++) {
				Student s = sInfoList.get(i);
				if (s.getcNo() == cNo) 
					print(s.getsNo());
			}
			break;
		case 3:
		System.out.print("����������Ҫ��ѯ��ѧ����ѧ�ţ�");
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
				System.out.println("ѧ��" + sNo + "��ѧ��δ�ҵ���������ȷ�Ϻ���ȷ���룡");
		} else
			System.out.println("ѧ�����������ȷ����ȷ���������룡");
			break;
		}
	}

	public void searchOnStudent() {
		Scanner in = new Scanner(System.in);
		System.out.println("��ӭʹ�ò�ѯѧ���ɼ�ͳ����Ϣ�Ĺ��ܣ�");
		System.out.print("����������Ҫ��ѯ��ѧ����ѧ�ţ�");
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
				System.out.println("ѧ��" + sNo + "��ѧ��δ�ҵ���������ȷ�Ϻ���ȷ���룡");
		} else
			System.out.println("ѧ�����������ȷ����ȷ���������룡");
	}

	public void searchOnClass() {
		Scanner in = new Scanner(System.in);
		System.out.println("��ӭʹ�ò�ѯ�༶�ɼ�ͳ����Ϣ�Ĺ��ܣ�");
		System.out.print("����������Ҫ��ѯ�İ༶�İ�ţ�");
		int cNo = in.nextInt();
		System.out.print("����������Ҫ��ѯ�İ༶�Ŀ�Ŀ�Ĵ���(1Ϊ���ݿ�;2ΪJava;3Ϊ���ݽṹ;4Ϊͳ��ѧ)��");
		int subNum = in.nextInt();
		String subName = "";
		switch (subNum) {
		case 1:
			subName = "���ݿ�";
			break;
		case 2:
			subName = "Java";
			break;
		case 3:
			subName = "���ݽṹ";
			break;
		case 4:
			subName = "ͳ��ѧ";
			break;
		}
		int cNoMax = 0;
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			cNoMax = cNoMax > s.getcNo() ? cNoMax : s.getcNo();
		}
		if (cNo > 0 && cNo <= cNoMax && subNum > 0 && subNum < 5) {
			System.out.println("���\t\t��Ŀ\t\t��߷�\t��ͷ�\t\tƽ����");
			System.out.println(cNo + "\t" + subName + "\t\t"
					+ getcMax(subNum, cNo) + "\t\t" + getcMin(subNum, cNo)
					+ "\t\t\t"
					+ String.format("%.2f", getcAvgScore(subNum, cNo)));
		} else
			System.out.println("��Ż��Ŀ�������������ȷ����ȷ���������룡");
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
		System.out.println("��ӭʹ������ѧ���ɼ��ֵȼ�ͳ�������Ĺ��ܣ�");
		System.out.print("����������Ҫ���ҵİ༶�Ŀ�Ŀ�Ĵ���(1Ϊ���ݿ�;2ΪJava;3Ϊ���ݽṹ;4Ϊͳ��ѧ)��");
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
			System.out.println("Ϊ�˸��õ�ʹ�ù��ܣ���������ȷ�Ĵ��ţ�");
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
		System.out.println("���ݿ�" + "\t" + excRankNum + "\t" + goodRankNum
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
		System.out.println("���ݽṹ" + "\t" + excRankNum + "\t" + goodRankNum
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
		System.out.println("ͳ��ѧ" + "\t" + excRankNum + "\t" + goodRankNum
				+ "\t" + medRankNum + "\t" + pasRankNum + "\t" + failRankNum);
	}

	public void sort() {
		Scanner in = new Scanner(System.in);
		System.out.println("��ӭʹ������ɼ��Ĺ��ܣ�");
		System.out
				.print("����������Ҫ��������ݵĴ���(�ɼ���������:1Ϊ���ݿ�,2ΪJava,3Ϊ���ݽṹ,4Ϊͳ��ѧ;5Ϊѧ����������)��");
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
			System.out.println("Ϊ�˸��õ�ʹ�ù��ܣ���������ȷ�Ĵ��ţ�");
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
		System.out.println("�Ѱ���ѧ���������");
		printAll();
//		SeqList<Long> temp = new SeqList<Long>(listLength);
//		for (int i = 0; i < listLength; i++) {
//			Student s = sInfoList.get(i);
//			temp.insert(i, s.getsNo());
//		}
//		BinarySortTree<Long> result = new BinarySortTree<Long>(temp);
//		System.out.println("���ö����������洢�����Ϊ��" + result.toString());
		SeqList<Student> temp = new SeqList<Student>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s);
		}
		BinarySortTree<Student> result = new BinarySortTree<Student>(temp);
		System.out.println("���ö����������洢�����Ϊ��\n" + result.toString());
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
		System.out.println("�Ѱ������ݿ�ɼ��������");
		printAll();
		SeqList<Double> temp = new SeqList<Double>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s.getDbScore());
		}
		BinarySortTree<Double> result = new BinarySortTree<Double>(temp);
		System.out.println("���ö����������洢�����Ϊ��" + result.toString());
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
		System.out.println("�Ѱ���Java�ɼ��������");
		printAll();
		SeqList<Double> temp = new SeqList<Double>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s.getJavaScore());
		}
		BinarySortTree<Double> result = new BinarySortTree<Double>(temp);
		System.out.println("���ö����������洢�����Ϊ��" + result.toString());
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
		System.out.println("�Ѱ������ݽṹ�ɼ��������");
		printAll();
		SeqList<Double> temp = new SeqList<Double>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s.getDsScore());
		}
		BinarySortTree<Double> result = new BinarySortTree<Double>(temp);
		System.out.println("���ö����������洢�����Ϊ��" + result.toString());
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
		System.out.println("�Ѱ���ͳ��ѧ�ɼ��������");
		printAll();
		SeqList<Double> temp = new SeqList<Double>(listLength);
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			temp.insert(i, s.getStatScore());
		}
		BinarySortTree<Double> result = new BinarySortTree<Double>(temp);
		System.out.println("���ö����������洢�����Ϊ��" + result.toString());
	}

	public void printToFile() throws IOException {
		System.out.println("��ӭʹ�ý�ѧ����Ϣ�ͳɼ���д���ı��ļ��Ĺ��ܣ�");
		FileWriter fw = new FileWriter("SaveGradeTable.txt");
		fw.write("����\tѧ��\t���\t�༶��������\t���ݿ�\tJava\t���ݽṹ\tͳ��ѧ" + "\n");
		for (int i = 0; i < listLength; i++) {
			Student s = sInfoList.get(i);
			String str = s.getsName() + "\t" + s.getsNo() + "\t" + s.getcNo()
					+ "\t" + s.getcNum() + "\t\t" + s.getDbScore() + "\t"
					+ s.getJavaScore() + "\t" + s.getDsScore() + "\t"
					+ s.getStatScore() + "\n";
			fw.write(str);
		}
		fw.close();
		System.out.println("д��ɹ���д���ļ�Ϊ��Ŀ��Ŀ¼�µ�SaveGradeTable.txt");
	}

	public void read() throws IOException {
		System.out.println("��ӭʹ�ô��ı��ļ��ж�ȡѧ����Ϣ�ͳɼ���Ĺ��ܣ�\nP.S.��Ҫ������ȡ�ı��ļ�������Ŀ��Ŀ¼�£�������ΪGradeTable.txt");
		InputStream is = new FileInputStream("GradeTable.txt");
		String line; // ��������ÿ�ж�ȡ������
		Student s = new Student();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		line = reader.readLine(); // ��ȡ��һ��
		if (line == null)
			System.out.println("�ļ�Ϊ��");
		line = reader.readLine();
		while (line != null) { // ��� line Ϊ��˵��������
			s = transformToStudent(line);
			sInfoList.insert(s);
			listLength++;
			line = reader.readLine(); // ��ȡ��һ��
		}
		reader.close();
		is.close();
		System.out.println("��ȡGradeTable.txt�ɹ���");
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
		System.out.println("*****��ӭʹ�õ��������а�ɼ�����ϵͳ(����:������)*****");
		boolean flag = true;
		while (flag) {
			System.out.println("  	  ==========================================================\n	|| ����������Ҫִ�еĹ��ܵĴ��ţ�\t\t\t\t\t||\n"+"	||       1.���ѧ����Ϣ�ͳɼ�\t\t\t\t\t\t\t||\n" + "	||       2.ɾ��ѧ����Ϣ�ͳɼ�\t\t\t\t\t\t\t||\n"
					+ "	||       3.��ѯѧ����Ϣ���ɼ�\t\t\t\t\t\t\t||\n" + "	||       4.��ѯѧ���ɼ�ͳ����Ϣ\t\t\t\t\t\t||\n" + "	||       5.��ѯ�༶�ɼ�ͳ����Ϣ\t\t\t\t\t\t||\n"
					+ "	||       6.����ѧ���ɼ��ֵȼ�ͳ������\t\t\t\t\t||\n" + "	||       7.����ѧ�źͳɼ�����ɼ����ö����������洢���\t\t||\n"
					+ "	||       8.��ѧ����Ϣ�ͳɼ���д���ı��ļ�\t\t\t\t||\n" + "	||       9.���ı��ļ��ж�ȡѧ����Ϣ�ͳɼ���\t\t\t\t||\n" + "	||       10.�˳�ϵͳ\t\t\t\t\t\t\t\t||\n  	  ==========================================================");
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
				System.out.println("��л����ʹ�ã����˳�ϵͳ����ӭ�´ι��٣�");
				break;
			default:
				System.out.println("Ϊ�˸��õ�ʹ�ù��ܣ���������ȷ�Ĵ��ţ�");
			}
		}
		in.close();
	}
}