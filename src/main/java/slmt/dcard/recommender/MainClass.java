package slmt.dcard.recommender;

import slmt.dcard.recommender.sinica.SinicaOnlineTokenizer;

public class MainClass {

	public static void main(String[] args) {
		// ���� "http://www.dcard.tw/f/all/p/103692" �ĤT�y��
		System.out.println(SinicaOnlineTokenizer.convertToList("�@�N(ADV)�@�b(P)�@����(N)�@��(P)�@�{��(N)�@�Ĥ@(DET)�@��(M)�@�R(Vt)�@�R(N)�@��(POST)"));
	}
}
