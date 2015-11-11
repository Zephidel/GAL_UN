package model;

import java.util.LinkedList;

public interface LexicalAnalyzerGenerator {
	
	LexicalAnalyzer processTxt (LinkedList<String> text, String destLang);
	
}
