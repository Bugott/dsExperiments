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
		String familyName = "白包毕卞蔡曹岑曾陈程邓董杜范方冯高龚巩辜古关郭何洪华黄贾江蒋靳金邝孔李黎梁廖刘卢鲁陆罗吕马孟聂潘彭阮邵沈钱乔石谭陶万汪王韦魏温吴伍习肖谢熊徐许薛闫杨姚叶俞袁翟詹张章赵郑钟周朱庄卓邹左";
		String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽";
		String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
		for (int k = 0; k < 200; k++) {
			String number = "13";
			int index = random.nextInt(familyName.length() - 1);
			String name = familyName.substring(index, index + 1); // 获得一个随机的姓氏
			int i = random.nextInt(3);// 可以根据这个数设置产生的男女比例
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
