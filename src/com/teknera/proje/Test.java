package com.teknera.proje;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.SAXException;

public class Test {
	public static void main(String args[]) {

		File file = new File("MyFile.txt");
		String path = file.getAbsolutePath();
		TimerTask task = new Monitor(new File(path)) {
			protected void onChange(File file) {
				System.out.println(file.getName() + " güncellendi !");
				try {

					ToXML convertProcess = new ToXML();
					convertProcess.begin();
				} catch (TransformerConfigurationException
						| FileNotFoundException | ParserConfigurationException
						| SAXException e) {
					e.printStackTrace();
				}
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, new Date(), 5000);
	}
}