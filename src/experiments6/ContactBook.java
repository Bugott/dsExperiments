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
	String familyName = "白包毕卞蔡曹岑曾陈程邓董杜范方冯高龚巩辜古关郭何洪华黄贾"+
									"江蒋靳金邝孔李黎梁廖刘卢鲁陆罗吕马孟聂潘彭阮邵沈钱乔石谭陶万汪王韦魏温吴伍习肖"+
									"谢熊徐许薛闫杨姚叶俞袁翟詹张章赵郑钟周朱庄卓邹左";
	HashMap<String, SinglyList<TwoTuple<String, Long>>> indexOfBook = new HashMap<>(); // 索引表
	SinglyList<TwoTuple<String, Long>> pieceOfSinglyList = new SinglyList<>(); // 块单链表
	TwoTuple<String, Long> records = new TwoTuple<String, Long>(); // 电话簿最小记录
	SeqList<TwoTuple<String, Long>> allRecords = new SeqList<>(); // 用顺序表存储所有电话簿记录
	
	public void keyMethod(){
		for (int i = 0; i < familyName.length(); i++)	// 按照姓字符串顺序创建姓与块链表(默认空)的哈希表
			indexOfBook.put(familyName.substring(i, i + 1),new SinglyList<TwoTuple<String, Long>>());
//		Iterator<String> keyit = indexOfBook.keySet().iterator();
//		for (int j = 0; j < indexOfBook.size() && keyit.hasNext(); j++){
//			String temp = keyit.next();			//存储索引表中的每个Key值
		for (int i = 0; i < allRecords.size(); i++)
//				if (allRecords.get(i).getFirst().substring(0, 1).equals(temp)) 
			indexOfBook.get(allRecords.get(i).getFirst().substring(0, 1)).insert(allRecords.get(i));
		}
//	}
	public void bookToString(){
		Iterator<String> finkeyit = indexOfBook.keySet().iterator();
		for(int i = 0; i < indexOfBook.size(); i++){
			String key = finkeyit.next();
			System.out.print(" "+key+" 姓的联系人有："+indexOfBook.get(key)+"\n");
		}
	}
	public void readContactBook() throws IOException{
		InputStream is = new FileInputStream("ContactBook.txt");
		String record; // 用来保存每行读取的内容
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		record = br.readLine(); // 读取第一行
		if (record == null)
			System.out.println("电话簿为空！");
		while (record != null) { // 如果 line 为空说明读完了
			String[] element = record.split(",");
			long phoneNumber = Long.parseLong(element[1]);
			allRecords.insert(records.set(element[0], phoneNumber)); // 插入所有电话簿记录到电话簿记录顺序表
			record = br.readLine(); // 读取下一行
		}
		br.close();
	}
	
	public ContactBook() {
	}

	public static void main(String args[]) throws IOException {
		ContactBook book = new ContactBook();
		System.out.println("开始读取通讯录的文本文件！");
		System.out.println("P.S.需要将欲读取文本文件放入项目根目录下，并命名为ContactBook.txt\n");
		book.readContactBook();
		book.keyMethod();
		System.out.println("下面是该通讯录的储存结果：");
		book.bookToString();
	}
}