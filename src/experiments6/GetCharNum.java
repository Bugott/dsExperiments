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
		 System.out.println("文本内容为：\"" + text + "\"\n");
		for (int i = 0; i < text.length(); i++) { 							//逐个字符查找计数
			String key = text.substring(i, i + 1); 						//获得1个字符，作为关键字
			Integer value = map.get(key); 								//获得关键字key（字符）映射的值
			int count = value == null ? 0 : value.intValue();			 //转换成int整数
			map.put(key, new Integer(count + 1)); 						//增加计数，关键字相同时，替换值
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
		str += "该文本文件中每个字符的出现次数为：";
		for (int i = 0; i < charSet.length(); i++) {
			if (i % 4 == 0)
				str += "\n";
			str += "字符“" + charSet.charAt(i) + "”出现 "
					+ valit.next() + " 次，";
		}
		return str;
	}
	public static void main(String[] args) throws IOException {
		System.out.println("以下读入项目根目录下的Article.txt，并统计文本中各字符出现的次数"
				+ "\nP.S.如需更改文本文件，则请将更改的文件放入项目根目录并重命名为Article.txt");
		String line;
		String text = "";
		FileReader fr = new FileReader("Article.txt");
		BufferedReader reader = new BufferedReader(fr);
		while ((line = reader.readLine()) != null)
			text += line; // 如果 line 为空说明读完了
		reader.close();
		GetCharNum gcn = new GetCharNum();
		System.out.println(gcn.getCharNum(gcn.charCount(text)));
	}
}
