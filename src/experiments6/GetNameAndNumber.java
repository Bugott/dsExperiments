package experiments6;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GetNameAndNumber {
	public GetNameAndNumber() {
	}
	public static void main(String args[]) throws IOException {
		FileWriter fw = new FileWriter("ContactBook.txt");
		Random random = new Random();
		String familyName = "�װ��ϱ�̲�����³̵˶��ŷ�����߹������Źع��κ黪�Ƽֽ���������������������¬³½��������������������Ǯ��ʯ̷��������Τκ������ϰФл������Ѧ����ҦҶ��Ԭ��ղ������֣������ׯ׿����";
		String girl = "���Ӣ���������Ⱦ���������֥��Ƽ�����ҷ���ʴ��������÷���������滷ѩ�ٰ���ϼ����ݺ�����𷲼Ѽ�������������Ҷ�������������ɺɯ������ٻ�������ӱ¶������������Ǻɵ���ü������ޱݼ���Է�ܰ�������԰��ӽ�������ع���ѱ�ˬ������ϣ����Ʈ�����������������������ܿ�ƺ������˿ɼ���Ӱ��֦˼��";
		String boy = "ΰ�����㿡��ǿ��ƽ�����Ļ�������������־��������ɽ�ʲ���������Ԫȫ��ʤѧ��ŷ���������ɱ�˳���ӽ��β��ɿ��ǹ���ﰲ����ï�����м�ͱ벩���Ⱦ�����׳��˼Ⱥ���İ�����ܹ����ƺ���������ԣ���ܽ���������ǫ�����֮�ֺ��ʲ����������������ά�������������󳿳�ʿ�Խ��������׵���ʱ̩ʢ��衾��ڲ�����ŷ纽��";
		for (int k = 0; k < 200; k++) {
			String number = "13";
			int index = random.nextInt(familyName.length() - 1);
			String name = familyName.substring(index, index + 1); // ���һ�����������
			int i = random.nextInt(3);// ���Ը�����������ò�������Ů����
			if (i == 2) {
				int j = random.nextInt(girl.length()-2);
				if (j % 2 == 0) {
					name = name + girl.substring(j, j + 2);
				} else {
					name = name + girl.substring(j, j + 1);
				}
			} else {
				int j = random.nextInt(girl.length()-2);
				if (j % 2 == 0) {
					name = name + boy.substring(j, j + 2);
				} else {
					name = name + boy.substring(j, j + 1);
				}

			}
			for (int j = 0; j < 9; j++) 
				number += random.nextInt(10);
			fw.write(name + "," + number+"\n");
		}
		fw.close();
	}
}
