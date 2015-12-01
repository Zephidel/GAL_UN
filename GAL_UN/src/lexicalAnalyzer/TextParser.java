package lexicalAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

public class TextParser {

	public TextParser() {

	}

	public LinkedList<String> loadFile(String path) {
		/* Try to load file */
		boolean isCommentedBlock = false;
		BufferedReader aReader = null;
		LinkedList<String> text = new LinkedList<String>();

		try {
			/* Try load and read the file */
			aReader = new BufferedReader(new FileReader(path));
			String currentLine = aReader.readLine();

			while (currentLine != null) {
				/* Process the last read line */
				if (currentLine.startsWith("\"\"\"") && !isCommentedBlock) {
					currentLine = aReader.readLine();
					isCommentedBlock = true;
				}

				if (isCommentedBlock) {
					if (currentLine.endsWith("\"\"\"")) {
						isCommentedBlock = false;
					}
				} else {
					if (!currentLine.startsWith("#")) {
						currentLine = currentLine.replaceAll("\n", "");
						currentLine = currentLine.replaceAll("\t", "");					

						if (currentLine.length() > 0) {
							text.add(currentLine);
						}
					}
				}
				currentLine = aReader.readLine();
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
