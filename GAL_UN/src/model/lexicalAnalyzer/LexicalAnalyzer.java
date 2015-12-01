package model.lexicalAnalyzer;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.lexicalAnalyzer.tokens.PythonToken;
import model.lexicalAnalyzer.tokens.Token;

public class LexicalAnalyzer {

	public LexicalAnalyzer() {
		foundTokens = new LinkedList<>();
		foundIdentifiers = new LinkedList<>();
		parser = new TextParser();
	}

	public boolean analyzeFile(String filePath) {
		/* Was the file loaded? */
		boolean value = false;
		LinkedList<String> text = parser.loadFile(filePath);

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
					boolean added = false;

					/* Is it a literal */
					if (word.matches("^\".*$")) {
						isLiteral = true;
					}

					if (isLiteral) {
						temp = temp + word + " ";
						if (word.matches("^.*\"$")) {
							isLiteral = false;
							foundTokens.add(new PythonToken(Token.Type.Literal,
									temp, -1));
							added = true;
							temp = "";
						}
					} else {
						added = clasify(word);
					}

					if (!added) {
						if (word.matches("^([^\\dA-Za-z ]*[a-zA-Z]+([0-9]*[a-zA-Z]*[-|_]*)*[^\\dA-Za-z ]*)*$")) {
							Pattern pattern = Pattern
									.compile("[^\\dA-Za-z ][a-zA-Z]+([0-9]*[a-zA-Z]*[-|_]*)*");
							Matcher matcher = pattern.matcher(word);
							if (matcher.find()) {
								String aWord = matcher.group();
								foundTokens.add(new PythonToken(
										Token.Type.SpecialSymbol, aWord
												.substring(0, 1), -1));
								clasify(aWord.substring(1, aWord.length()));
							} else {

								pattern = Pattern
										.compile("[a-zA-Z]+([0-9]*[a-zA-Z]*[-|_]*)*[^\\dA-Za-z ]");
								matcher = pattern.matcher(word);
								if (matcher.find()) {
									String aWord = matcher.group();
									clasify(aWord.substring(0,
											aWord.length() - 1));
									foundTokens
											.add(new PythonToken(
													Token.Type.SpecialSymbol,
													aWord.substring(
															aWord.length() - 1,
															aWord.length()), -1));
								} else {

									pattern = Pattern
											.compile("[^\\dA-Za-z ][a-zA-Z]+([0-9]*[a-zA-Z]*[-|_]*)*[^\\dA-Za-z ]");
									matcher = pattern.matcher(word);
									if (matcher.find()) {
										String aWord = matcher.group();
										foundTokens.add(new PythonToken(
												Token.Type.SpecialSymbol, aWord
														.substring(0, 1), -1));
										clasify(aWord.substring(1,
												aWord.length() - 1));
										foundTokens.add(new PythonToken(
												Token.Type.SpecialSymbol,
												aWord.substring(
														aWord.length() - 1,
														aWord.length()), -1));
									}
								}
							}
						}
					}

					idx = idx + 1;
				}

			}

			for (Token aToken : foundTokens) {
				System.out.println("<" + aToken.getType() + ", "
						+ aToken.getLexeme() + ", " + aToken.getId() + ">");
			}

			value = true;
		}

		return value;
	}

	private boolean clasify(String word) {
		boolean value = false;
		if (word.matches("^[a-zA-Z]+$")) {
			/* Is it a keyword */
			if (model.lexicalAnalyzer.tokens.PythonToken.PythonKeywords
					.contains(word)) {
				foundTokens.add(new PythonToken(Token.Type.Keyword, word, -1));
				value = true;
			}
			/* It is an Identifier */
			else {
				if (!foundIdentifiers.contains(word)) {
					foundIdentifiers.add(word);
				}
				foundTokens.add(new PythonToken(Token.Type.Identifier, word,
						foundIdentifiers.indexOf(word)));
				value = true;
			}
		} else {
			/* Is it a number */
			if (word.matches("^[0-9]+$")) {
				foundTokens.add(new PythonToken(Token.Type.Number, word, -1));
				value = true;
			} else {
				/* Is it an Alpha - Numerical Identifier */
				if (word.matches("^[a-zA-Z]+([a-zA-Z]*|[0-9]+|_|-)*$")) {
					if (!foundIdentifiers.contains(word)) {
						foundIdentifiers.add(word);
					}
					foundTokens.add(new PythonToken(Token.Type.Identifier,
							word, foundIdentifiers.indexOf(word)));
					value = true;
				} else {
					/* Is it a Operator */
					if (word.matches("^=$")) {
						foundTokens.add(new PythonToken(
								Token.Type.AsignationOp, word, -1));
						value = true;
					}

					if (word.matches("^(==|!=)$")) {
						foundTokens.add(new PythonToken(
								Token.Type.ComparisonOp, word, -1));
						value = true;
					}

					if (word.matches("^[+|-|*|**]$")) {
						foundTokens.add(new PythonToken(Token.Type.MathOp,
								word, -1));
						value = true;
					}

					if (word.matches("^:$")) {
						foundTokens.add(new PythonToken(
								Token.Type.DefinitionOp, word, -1));
						value = true;
					}
				}
			}
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
