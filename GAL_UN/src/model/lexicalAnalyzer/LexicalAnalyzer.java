package model.lexicalAnalyzer;

public class LexicalAnalyzer {

	public LexicalAnalyzer() {
		lastFoundTokens = new java.util.LinkedList<>();
		parser = new TextParser();
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

			for (String string : text) {
				String word = "";
				int idx = 0;				
				while (idx < string.length()) {
					char temp = string.charAt(idx);
					/* Is number */
					if ((temp >= 48 && temp <= 57)
					/* Is capital letter */
					|| (temp >= 65 && temp <= 90)
					/* Is lower case letter */
					|| (temp >= 97 && temp <= 122)) {

					} else {
						word = string.substring(0, idx);
						if (model.lexicalAnalyzer.tokens.PythonToken.PythonKeywords
								.contains(word)) {
							/* Add a keyword token */
							lastFoundTokens
									.add(new model.lexicalAnalyzer.tokens.PythonToken(
											model.lexicalAnalyzer.tokens.Token.Type.keyword,
											word, -1));
						} else {

						}
					}
					idx = idx + 1;
				}
			}

			for (model.lexicalAnalyzer.tokens.Token aToken : lastFoundTokens) {
				System.out.println("<" + aToken.getType() + ", "
						+ aToken.getLexeme() + ", " + aToken.getId() + ", "
						+ ">");
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
