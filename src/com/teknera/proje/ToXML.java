package com.teknera.proje;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;

/**
 * @author volkan
 *
 */
public class ToXML {

	BufferedReader in;
	FileReader reader;
	String[] myArray = new String[200];
	Out out1;
	Out out2;
	Out out3;

	public ToXML() throws TransformerConfigurationException,
			ParserConfigurationException, SAXException, FileNotFoundException {
		in = new BufferedReader(new FileReader("MyFile.txt"));
		reader = new FileReader("MyFile.txt");
		out1 = new Out("101.xml");
		out2 = new Out("102.xml");
		out3 = new Out("103.xml");
		out1.openXml();
		out2.openXml();
		out3.openXml();

	}

	public void begin() {
		try {
			char character;
			String word = "", TXTX = "";
			int counter = 0, counter2 = 0;
			while ((TXTX = in.readLine()) != null && !TXTX.trim().equals("")) {
				while (counter <= TXTX.length()) {
					character = (char) reader.read();
					if (character != '\t' && counter != TXTX.length()) {
						word += character;
					} else if (character == '\t' || counter == TXTX.length()) {
						myArray[counter2++] = word;
						word = "";
					}
					++counter;
				}
				counter = 0;
			}
			counter = 4;

			for (int i = 5; i < counter2; i++) {

				if (counter < 0)
					counter = 4;
				if (myArray[i + counter].equals("101"))
					out1.process(myArray, i);
				if (myArray[i + counter].equals("102"))
					out2.process(myArray, i);
				if (myArray[i + counter].equals("103"))
					out3.process(myArray, i);
				counter--;
			}
			reader.close();
			in.close();
			out1.closeXml();
			out2.closeXml();
			out3.closeXml();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}