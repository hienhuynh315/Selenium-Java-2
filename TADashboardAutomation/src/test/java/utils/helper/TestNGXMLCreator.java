package utils.helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TestNGXMLCreator {

	public static void main(String[] args) throws Exception {
		String testClasses = null;
		if (args[1].endsWith(".txt")) {
			testClasses = Files.readAllLines(Paths.get(args[1])).toString()
					.replaceAll("\\[|\\]", "").replaceAll(", ", "\t");
		} else {
			testClasses = args[1];
		}

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document document = documentBuilder.parse(args[0]);
		Element root = document.getDocumentElement();

		Element test = (Element) root.getElementsByTagName("test").item(0);
		Element classes = (Element) test.getElementsByTagName("classes")
				.item(0);
		test.appendChild(classes);

		for (String testClass : testClasses.split(",")) {
			Element clzz = document.createElement("class");
			clzz.setAttribute("name", testClass);
			classes.appendChild(clzz);
		}

		root.appendChild(test);
		DOMSource source = new DOMSource(document);
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
				"http://testng.org/testng-1.0.dtd");
		StreamResult result = new StreamResult(args[0]);
		transformer.transform(source, result);
		System.out
				.println("[Info]: A custom TESTNG xml file has been created!!");
	}

}
