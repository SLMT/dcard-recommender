package slmt.dcard.recommender.sinica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import slmt.dcard.recommender.util.PropertiesFetcher;

public class SinicaConnecter {

	private static final String SINICA_SERVER_IP;
	private static final int SINICA_SERVER_PORT;
	
	static {
		SINICA_SERVER_IP = PropertiesFetcher.getPropertyAsString("sinica_server_ip");
		SINICA_SERVER_PORT = PropertiesFetcher.getPropertyAsInteger("sinica_server_port");
	}

	static String sendAndReceive(String data) {
		try {
			Socket skt = new Socket(SINICA_SERVER_IP, SINICA_SERVER_PORT);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
					skt.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(
					skt.getInputStream()));
			
			out.append(data);
			out.flush();

			String result = in.readLine(); // Read one line and output it

			skt.close();
			
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
