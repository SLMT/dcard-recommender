package slmt.dcard.recommender.sinica;

public class SinicaToken {

	public String text;
	public String type;
	
	public SinicaToken(String text, String type) {
		this.text = text;
		this.type = type;
	}
	
	public String getText() {
		return text;
	}
	
	public String getType() {
		return type;
	}
	
	public String toString() {
		return "[Text: " + text + ", Type: " + type + "]";
	}
}
