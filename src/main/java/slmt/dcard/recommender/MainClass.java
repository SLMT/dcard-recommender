package slmt.dcard.recommender;

import slmt.dcard.recommender.sinica.SinicaOnlineTokenizer;

public class MainClass {

	public static void main(String[] args) {
		// 取自 "http://www.dcard.tw/f/all/p/103692" 第三句話
		System.out.println(SinicaOnlineTokenizer.convertToList("　就(ADV)　在(P)　今天(N)　跟(P)　閃光(N)　第一(DET)　次(M)　愛(Vt)　愛(N)　後(POST)"));
	}
}
