package model.lexicalAnalyzer.tokens;

import java.util.Arrays;
import java.util.LinkedList;

public class PythonToken extends Token {

	public PythonToken(Type type, String lexeme) {
		this.setType(type);
		this.setLexeme(lexeme);
	}

	public static LinkedList<String> PythonKeywords = new LinkedList<>(
			Arrays.asList("and", "as", "assert", "break", "class", "continue",
					"def", "del", "elif", "else", "except", "exec", "False",
					"finally", "for", "from", "global", "if", "import", "in",
					"is", "lambda", "None", "nonlocal", "not", "or", "pass",
					"print", "raise", "return", "True", "try", "while", "with",
					"yield"));
}
