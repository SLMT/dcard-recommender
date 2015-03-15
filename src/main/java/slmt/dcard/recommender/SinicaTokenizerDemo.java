package slmt.dcard.recommender;

import java.util.LinkedList;

import slmt.dcard.recommender.sinica.SinicaOnlineTokenizer;
import slmt.dcard.recommender.sinica.SinicaToken;

public class SinicaTokenizerDemo {

	public static void main(String[] args) {
		// From "http://www.dcard.tw/f/all/p/104330"
		String article = "我自認長的還算可以\n" + "可是遺傳到家人\n" + "痘痘跟黑頭的問題超級困擾我\n" + "大家有沒有推薦的治痘療程啊 QQ\n";
		
		LinkedList<SinicaToken> tokens = SinicaOnlineTokenizer.tokenize(article);
		
		for (SinicaToken token : tokens)
			System.out.println(token);
	}
}
