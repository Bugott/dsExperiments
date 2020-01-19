package curriculumDesign.question2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HuffmanTree { // Huffman树类
	private String charset = ""; // 字符集合
	private TriElement[] huftree; // 静态三叉链表结点数组
	Map<String, Integer> map = new TreeMap<String, Integer>();

	public HuffmanTree() {
	}

	public Map<String, Integer> charCount(String text) {
		// System.out.println("text=\"" + text + "\"");
		for (int i = 0; i < text.length(); i++) { // 逐个字符查找计数
			String key = text.substring(i, i + 1); // 获得1个字符，作为关键字
			Integer value = map.get(key); // 获得关键字key（字符）映射的值
			int count = value == null ? 0 : value.intValue(); // 转换成int整数
			map.put(key, new Integer(count + 1)); // 增加计数，关键字相同时，替换值
		}
		return map;
	}

	public static int[] getCountNum(Map<String, Integer> map) {
		int[] countNum = new int[map.size()];
		int i = 0;
		Iterator<String> it = map.keySet().iterator();
		while (i < map.size() && it.hasNext()) {
			String key = it.next();
			countNum[i] = map.get(key);
			i++;
		}
		return countNum;
	}

	public String getCharSet(Map<String, Integer> map) {
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			charset = charset + key;
		}
		return charset;
	}

	public HuffmanTree(int[] weights) {
		int n = weights.length; // 叶子结点数
		this.huftree = new TriElement[2 * n - 1]; // n个叶子的Huffman树共有2n-1个结点
		for (int i = 0; i < n; i++)
			// Huffman树初始化n个叶子结点
			this.huftree[i] = new TriElement(weights[i]); // 构造无父母的叶子结点
		for (int i = n; i < 2 * n - 1; i++) { // 构造n-1个2度结点
			int min1 = Integer.MAX_VALUE;
			int min2 = min1; // 最小和次小权值，初值为整数最大值
			int x1 = -1;
			int x2 = -1; // 最小和次小权值结点下标,初值皆为-1
			for (int j = 0; j < i; j++)
				// 寻找两个无父母的最小权值结点下标
				if (this.huftree[j].parent == -1) // 第j个结点无父母
					if (this.huftree[j].data < min1) { // 第j个结点权值最小
						min2 = min1; // min2记得次小权值
						x2 = x1; // x2记得次小权值结点下标
						min1 = this.huftree[j].data; // min1记得最小权值
						x1 = j; // x1记得最小权值结点下标
					} else if (this.huftree[j].data < min2) { // 第j个结点权值次小
						min2 = huftree[j].data;
						x2 = j;
					}
			this.huftree[x1].parent = i; // 合并两棵权值最小的子树，左孩子最小
			this.huftree[x2].parent = i;
			this.huftree[i] = new TriElement(min1 + min2, -1, x1, x2); // 构造结点，指定值、父母、左右孩子
		}
	}

	private String getCode(int i) { // 返回charset第i个字符的Huffman编码字符串
		char hufcode[] = new char[charset.length()]; // 声明字符数组暂存Huffman编码
		for (int j = 0; j < charset.length(); j++)
			hufcode[j] = charset.charAt(j);
		int child = i;
		int parent = this.huftree[child].parent;
		for (i = charset.length() - 1; parent != -1; i--) { // 由叶结点向上直到根结点，反序存储编码
			hufcode[i] = (huftree[parent].left == child) ? '0' : '1'; // 左、右孩子编码为0、1
			child = parent;
			parent = huftree[child].parent;
		}
		return new String(hufcode, i + 1, charset.length() - 1 - i); // 由hufcode数组从i+1开始的n-1-i个字符构造串
	}

	public String toString() { // 返回Huffman树的结点数组和所有字符的编码字符串
		String str = "Huffman树的结点数组:";
		for (int i = 0; i < this.huftree.length; i++) {
			if (i % 5 == 0)
				str += "\n";
			str += this.huftree[i].toString() + "，";
		}
		str += "\nHuffman编码： ";
		for (int i = 0; i < charset.length(); i++) {
			// 输出所有叶子结点的Huffman编码
			if (i % 5 == 0)
				str += "\n";
			str += "“" + charset.charAt(i) + "”：" + getCode(i) + "，";
		}

		return str;
	}

	public String getCharRate(String text) {
		String str = "";
		Map<String, Integer> map = charCount(text);
		int[] cn = getCountNum(map);
		str += "该文本文件中每个字符的使用频率为：";
		NumberFormat nt = NumberFormat.getPercentInstance();// 获取格式化对象
		nt.setMinimumFractionDigits(2);// 设置百分数精确度2即保留两位小数
		for (int i = 0; i < charset.length(); i++) {
			if (i % 5 == 0)
				str += "\n";
			str += "“" + charset.charAt(i) + "”："
					+ nt.format((double) cn[i] / text.length()) + "，";
		}
		return str;
	}

	// 数据压缩，将text各字符转换成Huffman编码存储，返回压缩字符串
	public String encode(String text) {
		String compressed = ""; // 被压缩的数据，以字符串显示
		String charset = getCharSet(this.map);
		for (int i = 0; i < text.length(); i++)
			compressed += this.getCode(charset.indexOf(text.charAt(i)));
		System.out.println("\n压缩比为： "
				+ String.format("%.2f",
						(double) text.length() * 8 / compressed.length())
				+ "：1");
		return compressed;
	}

	// 数据解压缩，将压缩compressed中的0/1序列进行Huffman译码，返回译码字符串
	public String decode(String compressed) {
		String text = "";
		int node = this.huftree.length - 1; // node搜索一条从根到达叶子的路径
		for (int i = 0; i < compressed.length(); i++) {
			if (compressed.charAt(i) == '0') // 根据0、1分别向左或右孩子走
				node = huftree[node].left;
			else
				node = huftree[node].right;
			if (huftree[node].isLeaf()) { // 到达叶子结点
				text += this.charset.charAt(node); // 获得一个字符
				node = this.huftree.length - 1; // node再从根结点开始
			}
		}
		return text;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("欢迎使用哈夫曼编码与译码的功能！");
		System.out.println("以下读入项目根目录下的Article.txt，并进行编码！"
				+ "\nP.S.如需更改文本文件，则请将更改的文件放入项目根目录并重命名为Article.txt\n");
		String line;
		String text = "";
		FileReader fr = new FileReader("Article.txt");
		BufferedReader reader = new BufferedReader(fr);
		while ((line = reader.readLine()) != null)
			text += line; // 如果 line 为空说明读完了
		reader.close();
		HuffmanTree hf = new HuffmanTree();
		Map<String, Integer> map = hf.charCount(text);
		int[] charCount = getCountNum(map);
		hf = new HuffmanTree(charCount);
		hf.getCharSet(map);
		System.out.println(hf.getCharRate(text));

		System.out.println("\n开始根据使用频率建立Huffman树！");
		System.out.println(hf.toString());

		System.out.print("\n开始进行数据压缩！");
		String hufcode = hf.encode(text);
		FileWriter fw = new FileWriter("SaveHufCode.txt");
		fw.write(hufcode);
		fw.close();
		System.out.println("压缩成功！并将编码存入项目根目录下的SaveHufCode.txt\n");

		System.out.println("现在开始将经Huffman编码压缩的二进制位序列文本文件SaveHufCode.txt进行解压！");
		FileReader fr2 = new FileReader("SaveHufCode.txt");
		BufferedReader reader1 = new BufferedReader(fr2);
		while ((line = reader1.readLine()) != null)
			text += line;
		reader1.close();
		String article = hf.decode(text);
		FileWriter fw1 = new FileWriter("deArticle.txt");
		fw1.write(article);
		fw1.close();
		System.out.println("解压缩成功！并将原文本存入项目根目录下的deArticle.txt");
	}
}