package experiments6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

import experiments1.SeqList;
import experiments1.SinglyList;

public class ContactBook {
	String familyName = "�װ��ϱ�̲�����³̵˶��ŷ�����߹������Źع��κ黪�Ƽ�"+
									"����������������������¬³½��������������������Ǯ��ʯ̷��������Τκ������ϰФ"+
									"л������Ѧ����ҦҶ��Ԭ��ղ������֣������ׯ׿����";
	HashMap<String, SinglyList<TwoTuple<String, Long>>> indexOfBook = new HashMap<>(); // ������
	SinglyList<TwoTuple<String, Long>> pieceOfSinglyList = new SinglyList<>(); // �鵥����
	TwoTuple<String, Long> records = new TwoTuple<String, Long>(); // �绰����С��¼
	SeqList<TwoTuple<String, Long>> allRecords = new SeqList<>(); // ��˳���洢���е绰����¼
	
	public void keyMethod(){
		for (int i = 0; i < familyName.length(); i++)	// �������ַ���˳�򴴽����������(Ĭ�Ͽ�)�Ĺ�ϣ��
			indexOfBook.put(familyName.substring(i, i + 1),new SinglyList<TwoTuple<String, Long>>());
//		Iterator<String> keyit = indexOfBook.keySet().iterator();
//		for (int j = 0; j < indexOfBook.size() && keyit.hasNext(); j++){
//			String temp = keyit.next();			//�洢�������е�ÿ��Keyֵ
		for (int i = 0; i < allRecords.size(); i++)
//				if (allRecords.get(i).getFirst().substring(0, 1).equals(temp)) 
			indexOfBook.get(allRecords.get(i).getFirst().substring(0, 1)).insert(allRecords.get(i));
		}
//	}
	public void bookToString(){
		Iterator<String> finkeyit = indexOfBook.keySet().iterator();
		for(int i = 0; i < indexOfBook.size(); i++){
			String key = finkeyit.next();
			System.out.print(" "+key+" �յ���ϵ���У�"+indexOfBook.get(key)+"\n");
		}
	}
	public void readContactBook() throws IOException{
		InputStream is = new FileInputStream("ContactBook.txt");
		String record; // ��������ÿ�ж�ȡ������
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		record = br.readLine(); // ��ȡ��һ��
		if (record == null)
			System.out.println("�绰��Ϊ�գ�");
		while (record != null) { // ��� line Ϊ��˵��������
			String[] element = record.split(",");
			long phoneNumber = Long.parseLong(element[1]);
			allRecords.insert(records.set(element[0], phoneNumber)); // �������е绰����¼���绰����¼˳���
			record = br.readLine(); // ��ȡ��һ��
		}
		br.close();
	}
	
	public ContactBook() {
	}

	public static void main(String args[]) throws IOException {
		ContactBook book = new ContactBook();
		System.out.println("��ʼ��ȡͨѶ¼���ı��ļ���");
		System.out.println("P.S.��Ҫ������ȡ�ı��ļ�������Ŀ��Ŀ¼�£�������ΪContactBook.txt\n");
		book.readContactBook();
		book.keyMethod();
		System.out.println("�����Ǹ�ͨѶ¼�Ĵ�������");
		book.bookToString();
	}
}