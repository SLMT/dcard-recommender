package slmt.dcard.recommender.sinica;

import java.util.LinkedList;
import java.util.StringTokenizer;

public class SinicaOnlineTokenizer {

	public static LinkedList<SinicaToken> tokenize(String target) {
		String sendXML = SinicaXMLParser.toXML(target);
		String resultXML = SinicaConnecter.sendAndReceive(sendXML);
		String resultText = SinicaXMLParser.retrieveFromXML(resultXML);
		return convertToList(resultText);
	}

	private static LinkedList<SinicaToken> convertToList(String target) {
		LinkedList<SinicaToken> result = new LinkedList<SinicaToken>();
		
		// The target string will be something like "��(N)�@�R(V)�@�ۤv(N)"
		// We need to tokenize it by '�@'(���Ϊť�) first
		StringTokenizer tokenizer = new StringTokenizer(target, "�@");
		
		// Iterate tokens
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();

			// split the text and the type
			// "��(N)" => "��" and "N"
			int leftPsIndex = token.indexOf("(");
			
			String text = token.substring(0, leftPsIndex);
			String type = token.substring(leftPsIndex + 1, token.length() - 1);
			
			// add this token to the list
			result.add(new SinicaToken(text, type));
		}

		return result;
	}
}
