package curriculumDesign.question2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HuffmanTree { // Huffman����
	private String charset = ""; // �ַ�����
	private TriElement[] huftree; // ��̬��������������
	Map<String, Integer> map = new TreeMap<String, Integer>();

	public HuffmanTree() {
	}

	public Map<String, Integer> charCount(String text) {
		// System.out.println("text=\"" + text + "\"");
		for (int i = 0; i < text.length(); i++) { // ����ַ����Ҽ���
			String key = text.substring(i, i + 1); // ���1���ַ�����Ϊ�ؼ���
			Integer value = map.get(key); // ��ùؼ���key���ַ���ӳ���ֵ
			int count = value == null ? 0 : value.intValue(); // ת����int����
			map.put(key, new Integer(count + 1)); // ���Ӽ������ؼ�����ͬʱ���滻ֵ
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
		int n = weights.length; // Ҷ�ӽ����
		this.huftree = new TriElement[2 * n - 1]; // n��Ҷ�ӵ�Huffman������2n-1�����
		for (int i = 0; i < n; i++)
			// Huffman����ʼ��n��Ҷ�ӽ��
			this.huftree[i] = new TriElement(weights[i]); // �����޸�ĸ��Ҷ�ӽ��
		for (int i = n; i < 2 * n - 1; i++) { // ����n-1��2�Ƚ��
			int min1 = Integer.MAX_VALUE;
			int min2 = min1; // ��С�ʹ�СȨֵ����ֵΪ�������ֵ
			int x1 = -1;
			int x2 = -1; // ��С�ʹ�СȨֵ����±�,��ֵ��Ϊ-1
			for (int j = 0; j < i; j++)
				// Ѱ�������޸�ĸ����СȨֵ����±�
				if (this.huftree[j].parent == -1) // ��j������޸�ĸ
					if (this.huftree[j].data < min1) { // ��j�����Ȩֵ��С
						min2 = min1; // min2�ǵô�СȨֵ
						x2 = x1; // x2�ǵô�СȨֵ����±�
						min1 = this.huftree[j].data; // min1�ǵ���СȨֵ
						x1 = j; // x1�ǵ���СȨֵ����±�
					} else if (this.huftree[j].data < min2) { // ��j�����Ȩֵ��С
						min2 = huftree[j].data;
						x2 = j;
					}
			this.huftree[x1].parent = i; // �ϲ�����Ȩֵ��С��������������С
			this.huftree[x2].parent = i;
			this.huftree[i] = new TriElement(min1 + min2, -1, x1, x2); // �����㣬ָ��ֵ����ĸ�����Һ���
		}
	}

	private String getCode(int i) { // ����charset��i���ַ���Huffman�����ַ���
		char hufcode[] = new char[charset.length()]; // �����ַ������ݴ�Huffman����
		for (int j = 0; j < charset.length(); j++)
			hufcode[j] = charset.charAt(j);
		int child = i;
		int parent = this.huftree[child].parent;
		for (i = charset.length() - 1; parent != -1; i--) { // ��Ҷ�������ֱ������㣬����洢����
			hufcode[i] = (huftree[parent].left == child) ? '0' : '1'; // ���Һ��ӱ���Ϊ0��1
			child = parent;
			parent = huftree[child].parent;
		}
		return new String(hufcode, i + 1, charset.length() - 1 - i); // ��hufcode�����i+1��ʼ��n-1-i���ַ����촮
	}

	public String toString() { // ����Huffman���Ľ������������ַ��ı����ַ���
		String str = "Huffman���Ľ������:";
		for (int i = 0; i < this.huftree.length; i++) {
			if (i % 5 == 0)
				str += "\n";
			str += this.huftree[i].toString() + "��";
		}
		str += "\nHuffman���룺 ";
		for (int i = 0; i < charset.length(); i++) {
			// �������Ҷ�ӽ���Huffman����
			if (i % 5 == 0)
				str += "\n";
			str += "��" + charset.charAt(i) + "����" + getCode(i) + "��";
		}

		return str;
	}

	public String getCharRate(String text) {
		String str = "";
		Map<String, Integer> map = charCount(text);
		int[] cn = getCountNum(map);
		str += "���ı��ļ���ÿ���ַ���ʹ��Ƶ��Ϊ��";
		NumberFormat nt = NumberFormat.getPercentInstance();// ��ȡ��ʽ������
		nt.setMinimumFractionDigits(2);// ���ðٷ�����ȷ��2��������λС��
		for (int i = 0; i < charset.length(); i++) {
			if (i % 5 == 0)
				str += "\n";
			str += "��" + charset.charAt(i) + "����"
					+ nt.format((double) cn[i] / text.length()) + "��";
		}
		return str;
	}

	// ����ѹ������text���ַ�ת����Huffman����洢������ѹ���ַ���
	public String encode(String text) {
		String compressed = ""; // ��ѹ�������ݣ����ַ�����ʾ
		String charset = getCharSet(this.map);
		for (int i = 0; i < text.length(); i++)
			compressed += this.getCode(charset.indexOf(text.charAt(i)));
		System.out.println("\nѹ����Ϊ�� "
				+ String.format("%.2f",
						(double) text.length() * 8 / compressed.length())
				+ "��1");
		return compressed;
	}

	// ���ݽ�ѹ������ѹ��compressed�е�0/1���н���Huffman���룬���������ַ���
	public String decode(String compressed) {
		String text = "";
		int node = this.huftree.length - 1; // node����һ���Ӹ�����Ҷ�ӵ�·��
		for (int i = 0; i < compressed.length(); i++) {
			if (compressed.charAt(i) == '0') // ����0��1�ֱ�������Һ�����
				node = huftree[node].left;
			else
				node = huftree[node].right;
			if (huftree[node].isLeaf()) { // ����Ҷ�ӽ��
				text += this.charset.charAt(node); // ���һ���ַ�
				node = this.huftree.length - 1; // node�ٴӸ���㿪ʼ
			}
		}
		return text;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("��ӭʹ�ù���������������Ĺ��ܣ�");
		System.out.println("���¶�����Ŀ��Ŀ¼�µ�Article.txt�������б��룡"
				+ "\nP.S.��������ı��ļ������뽫���ĵ��ļ�������Ŀ��Ŀ¼��������ΪArticle.txt\n");
		String line;
		String text = "";
		FileReader fr = new FileReader("Article.txt");
		BufferedReader reader = new BufferedReader(fr);
		while ((line = reader.readLine()) != null)
			text += line; // ��� line Ϊ��˵��������
		reader.close();
		HuffmanTree hf = new HuffmanTree();
		Map<String, Integer> map = hf.charCount(text);
		int[] charCount = getCountNum(map);
		hf = new HuffmanTree(charCount);
		hf.getCharSet(map);
		System.out.println(hf.getCharRate(text));

		System.out.println("\n��ʼ����ʹ��Ƶ�ʽ���Huffman����");
		System.out.println(hf.toString());

		System.out.print("\n��ʼ��������ѹ����");
		String hufcode = hf.encode(text);
		FileWriter fw = new FileWriter("SaveHufCode.txt");
		fw.write(hufcode);
		fw.close();
		System.out.println("ѹ���ɹ����������������Ŀ��Ŀ¼�µ�SaveHufCode.txt\n");

		System.out.println("���ڿ�ʼ����Huffman����ѹ���Ķ�����λ�����ı��ļ�SaveHufCode.txt���н�ѹ��");
		FileReader fr2 = new FileReader("SaveHufCode.txt");
		BufferedReader reader1 = new BufferedReader(fr2);
		while ((line = reader1.readLine()) != null)
			text += line;
		reader1.close();
		String article = hf.decode(text);
		FileWriter fw1 = new FileWriter("deArticle.txt");
		fw1.write(article);
		fw1.close();
		System.out.println("��ѹ���ɹ�������ԭ�ı�������Ŀ��Ŀ¼�µ�deArticle.txt");
	}
}