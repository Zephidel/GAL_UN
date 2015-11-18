package model.lexicalAnalyzer;

public class LexicalAnalyzer {

	public LexicalAnalyzer() {
		lastFoundTokens = new java.util.LinkedList<>();
	}

	public boolean analyzeFile(String filePath) {
		/* Was the file loaded? */
		boolean value = false;
		java.util.LinkedList<String> text = parser.loadFile(filePath);

		/* text.size > 0 means the file was loaded */
		if (text.size() > 0) {
			/* Empty the token list */
			if (lastFoundTokens.size() > 0) {
				lastFoundTokens.clear();
			}
			value = true;
		}

		return value;
	}

	public java.util.LinkedList<model.lexicalAnalyzer.tokens.Token> getLastFoundTokens() {
		return lastFoundTokens;
	}

	private TextParser parser;
	private java.util.LinkedList<model.lexicalAnalyzer.tokens.Token> lastFoundTokens;
}
