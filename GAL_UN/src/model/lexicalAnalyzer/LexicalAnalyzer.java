package model.lexicalAnalyzer;

public class LexicalAnalyzer {

	public LexicalAnalyzer() {
		foundTokens = new java.util.LinkedList<>();
		foundIdentifiers = new java.util.LinkedList<>();
		parser = new TextParser();
	}

	public boolean analyzeFile(String filePath) {
		/* Was the file loaded? */
		boolean value = false;
		java.util.LinkedList<String> text = parser.loadFile(filePath);

		/* text.size > 0 means the file was loaded */
		if (text.size() > 0) {
			/* Empty the token list */
			if (foundTokens.size() > 0) {
				foundTokens.clear();
			}

			if (foundIdentifiers.size() > 0) {
				foundIdentifiers.clear();
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
							foundTokens
									.add(new model.lexicalAnalyzer.tokens.PythonToken(
											model.lexicalAnalyzer.tokens.Token.Type.Literal,
											temp, -1));
							temp = "";
						}
					} else {

						if (word.matches("^[a-zA-Z]+$")) {
							/* Is it a keyword */
							if (model.lexicalAnalyzer.tokens.PythonToken.PythonKeywords
									.contains(word)) {
								foundTokens
										.add(new model.lexicalAnalyzer.tokens.PythonToken(
												model.lexicalAnalyzer.tokens.Token.Type.Keyword,
												word, -1));
							}
							/* It is an Identifier */
							else {
								if (!foundIdentifiers.contains(word)) {
									foundIdentifiers.add(word);
								}
								foundTokens
										.add(new model.lexicalAnalyzer.tokens.PythonToken(
												model.lexicalAnalyzer.tokens.Token.Type.Identifier,
												word, foundIdentifiers
														.indexOf(word)));
							}
						} else {
							/* Is it a number */
							if (word.matches("^[0-9]+$")) {
								foundTokens
										.add(new model.lexicalAnalyzer.tokens.PythonToken(
												model.lexicalAnalyzer.tokens.Token.Type.Number,
												word, foundIdentifiers
														.indexOf(word)));
							} else {
								/* Is it an Alpha - Numerical Identifier */
								if (word.matches("^[a-zA-Z]+([a-zA-Z]+|[0-9]+|_|-)+$")) {
									if (!foundIdentifiers.contains(word)) {
										foundIdentifiers.add(word);
									}
									foundTokens
											.add(new model.lexicalAnalyzer.tokens.PythonToken(
													model.lexicalAnalyzer.tokens.Token.Type.Identifier,
													word, foundIdentifiers
															.indexOf(word)));
								} else {
									
								}
							}
						}
					}
					idx = idx + 1;
				}

			}

			for (model.lexicalAnalyzer.tokens.Token aToken : foundTokens) {
				System.out.println("<" + aToken.getType() + ", "
						+ aToken.getLexeme() + ", " + aToken.getId() + ">");
			}

			value = true;
		}

		return value;
	}

	public java.util.LinkedList<model.lexicalAnalyzer.tokens.Token> getLastFoundTokens() {
		return foundTokens;
	}

	private TextParser parser;
	private java.util.LinkedList<model.lexicalAnalyzer.tokens.Token> foundTokens;
	private java.util.LinkedList<String> foundIdentifiers;
}
