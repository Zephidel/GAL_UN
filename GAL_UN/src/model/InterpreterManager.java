package model;

import java.util.LinkedList;

public class InterpreterManager {

	public InterpreterManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Process some text depending on a command. The command defines which
	// generator is used
	public LexicalAnalyzer processFile(LinkedList<String> text,
			String fontLang, String destLang) {

		LexicalAnalyzer value = null;

		if (fontLang == PYTHON_MODIFIER) {
			value = generators.get(0).processTxt(text, destLang);
		}

		return value;
	}

	private LinkedList<LexicalAnalyzerGenerator> generators;

	public static final String PYTHON_MODIFIER = "-p";
	public static final String REGULAR_EXPRESIONS_MODIFIER = "-r";
}
