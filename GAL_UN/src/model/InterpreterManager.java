package model;

import java.util.LinkedList;

public class InterpreterManager {

	public InterpreterManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Process some text depending on a command. The command defines which
	// generator is used
	public LexicalAnalyzer process(String text, String command) {
		LexicalAnalyzer value = null;

		if (command == "") {
			value = generators.get(0).processTxt(text);
		}

		return value;
	}

	private LinkedList<LexicalAnalyzerGenerator> generators;
}
