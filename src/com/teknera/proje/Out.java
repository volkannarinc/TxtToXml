package com.teknera.proje;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;

/**
 * @author volkan
 *
 */
public class Out {

	StreamResult out;
	TransformerHandler th;

	public Out(String file) {
		out = new StreamResult(file);
	}

	public void openXml() throws ParserConfigurationException,
			TransformerConfigurationException, SAXException {

		SAXTransformerFactory tf = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();
		th = tf.newTransformerHandler();

		Transformer serializer = th.getTransformer();
		serializer.setOutputProperty(
				"{http://xml.apache.org/xslt}indent-amount", "4");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");

		th.setResult(out);
		th.startDocument();
		th.startElement(null, null, "Root_Element", null);

	}

	public void process(String[] myArray, int index) throws SAXException {

		if (index % 5 == 0)
			th.startElement(null, null, "option", null);
		th.startElement(null, null, myArray[(index % 5)], null);
		th.characters(myArray[index].toCharArray(), 0, myArray[index].length());
		th.endElement(null, null, myArray[index % 5]);
		if (index % 5 == 4)
			th.endElement(null, null, "option");

	}

	public void closeXml() throws SAXException {
		th.endElement(null, null, "Root_Element");
		th.endDocument();
	}

}
