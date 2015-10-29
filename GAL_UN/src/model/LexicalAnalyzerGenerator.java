package model;

public class LexicalAnalyzerGenerator {

	public LexicalAnalyzerGenerator(LanguageInterpreter anInterpreter) {
		setInterpreter(anInterpreter);
	}

	public void setInterpreter(LanguageInterpreter anInterpreter) {
		interpreter = anInterpreter;
	}
	
	private LanguageInterpreter interpreter;
}
