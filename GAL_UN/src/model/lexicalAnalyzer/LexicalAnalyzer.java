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
				String[] words = string.split(" ");
				int idx = 0;

				boolean isLiteral = false;
				String temp = "";

				while (idx < words.length) {
					String word = words[idx];

					/* Is it a literal */
					if (word.matches("^\".*$")) {
						isLiteral = true;
					}

					if (isLiteral) {
						temp = temp + word + " ";
						if (word.matches("^.*\"$")) {
							isLiteral = false;
							lastFoundTokens
									.add(new model.lexicalAnalyzer.tokens.PythonToken(
											model.lexicalAnalyzer.tokens.Token.Type.Literal,
											temp, -1));
						}
					} else {

						if (word.matches("^[a-zA-Z]+$")) {
							/* Is it a keyword */
							if (model.lexicalAnalyzer.tokens.PythonToken.PythonKeywords
									.contains(word)) {
								lastFoundTokens
										.add(new model.lexicalAnalyzer.tokens.PythonToken(
												model.lexicalAnalyzer.tokens.Token.Type.Keyword,
												word, -1));
							}
						}
					}
					idx = idx + 1;
				}

			}

			for (model.lexicalAnalyzer.tokens.Token aToken : lastFoundTokens) {
				System.out.println("<" + aToken.getType() + ", "
						+ aToken.getLexeme() + ", " + aToken.getId() + ">");
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
