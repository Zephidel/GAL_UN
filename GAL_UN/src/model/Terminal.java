package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import controller.App;

public class Terminal {

	public Terminal() {
		manager = new InterpreterManager();
	}

	public String readFromConsole(String fileLocation, String fontLang,
			String destLang) {
		String value = App.PATH_ERR;

		/* Try to load file */
		BufferedReader aReader = null;
		LinkedList<String> text = new LinkedList<String>();

		try {
			/* Try load and read the file */
			aReader = new BufferedReader(new FileReader(fileLocation));
			String currentLine = aReader.readLine();

			while (currentLine != null) {
				/* Process the last read line */
				currentLine = aReader.readLine();
				text.add(currentLine);
			}
		} catch (Exception e) {
			/* Loading error management */
		} finally {
			try {
				if (aReader != null) {
					aReader.close();
					value = App.FILE_SUCCESS;
				}
			} catch (Exception e) {
				/* Error Management */
			}
		}

		if (text.size() > 0) {
			manager.processFile(text, fontLang, destLang);
		}

		return value;
	}

	public void printInConsole(String message) {

	}

	public static void main(String[] args) {

	}

	// Lexical Analyzer Generator
	private InterpreterManager manager;
}
