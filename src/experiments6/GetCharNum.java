package experiments6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GetCharNum {
	public GetCharNum(){
	}
	Map<String, Integer> map = new HashMap<String, Integer>();
	public Map<String, Integer> charCount(String text) {
		 System.out.println("�ı�����Ϊ��\"" + text + "\"\n");
		for (int i = 0; i < text.length(); i++) { 							//����ַ����Ҽ���
			String key = text.substring(i, i + 1); 						//���1���ַ�����Ϊ�ؼ���
			Integer value = map.get(key); 								//��ùؼ���key���ַ���ӳ���ֵ
			int count = value == null ? 0 : value.intValue();			 //ת����int����
			map.put(key, new Integer(count + 1)); 						//���Ӽ������ؼ�����ͬʱ���滻ֵ
		}
		return map;
	}
	
	public String getCharNum(Map<String, Integer> charMap) {
		String str = "";
		String charSet = "";
		Iterator<String> keyit = charMap.keySet().iterator();
		for(int i = 0;i < charMap.size() && keyit.hasNext();i++) 
			charSet += keyit.next();
		Iterator<Integer> valit = charMap.values().iterator();
		str += "���ı��ļ���ÿ���ַ��ĳ��ִ���Ϊ��";
		for (int i = 0; i < charSet.length(); i++) {
			if (i % 4 == 0)
				str += "\n";
			str += "�ַ���" + charSet.charAt(i) + "������ "
					+ valit.next() + " �Σ�";
		}
		return str;
	}
	public static void main(String[] args) throws IOException {
		System.out.println("���¶�����Ŀ��Ŀ¼�µ�Article.txt����ͳ���ı��и��ַ����ֵĴ���"
				+ "\nP.S.��������ı��ļ������뽫���ĵ��ļ�������Ŀ��Ŀ¼��������ΪArticle.txt");
		String line;
		String text = "";
		FileReader fr = new FileReader("Article.txt");
		BufferedReader reader = new BufferedReader(fr);
		while ((line = reader.readLine()) != null)
			text += line; // ��� line Ϊ��˵��������
		reader.close();
		GetCharNum gcn = new GetCharNum();
		System.out.println(gcn.getCharNum(gcn.charCount(text)));
	}
}
