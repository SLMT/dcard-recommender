package slmt.dcard.recommender.sinica;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import slmt.dcard.recommender.util.PropertiesFetcher;

public class SinicaXMLParser {

	private static final String SINICA_ACCOUNT_NAME;
	private static final String SINICA_ACCOUNT_PASSWORD;
	
	static {
		SINICA_ACCOUNT_NAME = PropertiesFetcher.getPropertyAsString("sinica_account_name");
		SINICA_ACCOUNT_PASSWORD = PropertiesFetcher.getPropertyAsString("sinica_account_password");
	}

	static String toXML(String content) {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// <wordsegmentation version="0.1"></wordsegmentation>
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("wordsegmentation");
			rootElement.setAttribute("version", "0.1");
			doc.appendChild(rootElement);

			// <option showcategory="1" />
			Element option = doc.createElement("option");
			option.setAttribute("showcategory", "1");
			rootElement.appendChild(option);

			// <authentication username="iis" password="iis" />
			Element auth = doc.createElement("authentication");
			auth.setAttribute("username", SINICA_ACCOUNT_NAME);
			auth.setAttribute("password", SINICA_ACCOUNT_PASSWORD);
			rootElement.appendChild(auth);

			// <text>(Content)</text>
			Element text = doc.createElement("text");
			text.appendChild(doc.createTextNode(content));
			rootElement.appendChild(text);

			// convert to String object
			DOMSource source = new DOMSource(doc);
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			StreamResult result = new StreamResult(output);
			TransformerFactory.newInstance().newTransformer()
					.transform(source, result);

			return new String(output.toByteArray(), StandardCharsets.UTF_8);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		return null;
	}

	static String retrieveFromXML(String xml) {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new ByteArrayInputStream(xml
					.getBytes(StandardCharsets.UTF_8)));
			
			NodeList nodeList = doc.getElementsByTagName("sentence");
			Node sentenseNode = nodeList.item(0);
			
			// TODO: Add error code detection
			
			if (sentenseNode == null)
				return null;
			
			return sentenseNode.getTextContent();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
