package slmt.dcard.recommender;

import java.util.LinkedList;

import slmt.dcard.recommender.sinica.SinicaOnlineTokenizer;
import slmt.dcard.recommender.sinica.SinicaToken;

public class SinicaTokenizerDemo {

	public static void main(String[] args) {
		// From "http://www.dcard.tw/f/all/p/104330"
		String article = "�ڦۻ{�����ٺ�i�H\n" + "�i�O��Ǩ�a�H\n" + "�k�k����Y�����D�W�ŧx�Z��\n" + "�j�a���S�����˪��v�k���{�� QQ\n";
		
		LinkedList<SinicaToken> tokens = SinicaOnlineTokenizer.tokenize(article);
		
		for (SinicaToken token : tokens)
			System.out.println(token);
	}
}
