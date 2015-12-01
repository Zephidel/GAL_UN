package lexicalAnalyzer;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class LexicalAnalyzer {

	public class PythonToken extends Token {

		public PythonToken(Type type, String lexeme, int id) {
			this.setType(type);
			this.setLexeme(lexeme);
			this.setId(id);
		}

		public LinkedList<String> PythonKeywords = new LinkedList<>(
				Arrays.asList("and", "as", "assert", "break", "class",
						"continue", "def", "del", "elif", "else", "except",
						"exec", "False", "finally", "for", "from", "global",
						"if", "import", "in", "is", "lambda", "None",
						"nonlocal", "not", "or", "pass", "print", "raise",
						"return", "True", "try", "while", "with", "yield"));

		public LinkedList<String> Operators = new LinkedList<>(Arrays.asList(
				"=", "==", "!=", "+", "-", "*", "**", "/", ":"));

		public LinkedList<String> Parenthesis = new LinkedList<>(Arrays.asList(
				"(", ")", "{", "}", "[", "]"));
	};

	public LexicalAnalyzer() {
		foundTokens = new LinkedList<>();
		foundIdentifiers = new LinkedList<>();
		parser = new TextParser();
		aToken = new PythonToken(null, "", 0);
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
			if (aToken.PythonKeywords.contains(word)) {
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

	public void generate(String modifier) {
		if (modifier.contains("-j")) {
			URL analyzerPath = this.getClass().getResource(
					"LexicalAnalyzer.java");
			URL parserPath = this.getClass().getResource("TextParser.java");
			URL tokenPath = this.getClass().getResource("Token.java");

			InputStream input = null;
			OutputStream output = null;

			try {
				output = new FileOutputStream((new JFileChooser())
						.getFileSystemView().getDefaultDirectory()
						+ "\\lexicalAnalyzer");
				input = new FileInputStream(analyzerPath.toString());

				byte[] buffer = new byte[1024];
				int bytesRead;

				while ((bytesRead = input.read(buffer)) > 0) {
					output.write(buffer, 0, bytesRead);
				}

				input.close();
				input = new FileInputStream(parserPath.toString());

				bytesRead = 0;

				while ((bytesRead = input.read(buffer)) > 0) {
					output.write(buffer, 0, bytesRead);
				}

				input.close();
				input = new FileInputStream(tokenPath.toString());

				bytesRead = 0;

				while ((bytesRead = input.read(buffer)) > 0) {
					output.write(buffer, 0, bytesRead);
				}

				output.close();
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("=P");
		}
	}

	public java.util.LinkedList<Token> getLastFoundTokens() {
		return foundTokens;
	}

	public static void main(String[] args) {
		LexicalAnalyzer la = new LexicalAnalyzer();
		la.analyzeFile(args[0]);
	}

	private TextParser parser;
	private LinkedList<Token> foundTokens;
	private LinkedList<String> foundIdentifiers;
	private PythonToken aToken;
}
