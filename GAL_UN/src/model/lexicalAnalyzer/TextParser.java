package model.lexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class TextParser {

	public TextParser() {

	}

	public LinkedList<String> loadFile(String path) {
		/* Try to load file */
		BufferedReader aReader = null;
		LinkedList<String> text = new LinkedList<String>();

		try {
			/* Try load and read the file */
			aReader = new BufferedReader(new FileReader(path));
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
				}
			} catch (Exception e) {
				/* Error Management */
			}
		}

		if (text.size() == 0) {
			/* No file found */
		}

		return text;
	}
}
