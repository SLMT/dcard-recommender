package slmt.dcard.recommender;

import slmt.dcard.recommender.sinica.SinicaOnlineTokenizer;

public class MainClass {

	public static void main(String[] args) {
		System.out.println(SinicaOnlineTokenizer.convertToList("　就(ADV)　在(P)　今天(N)　跟(P)　閃光(N)　第一(DET)　次(M)　愛(Vt)　愛(N)　後(POST)"));
	}
}
