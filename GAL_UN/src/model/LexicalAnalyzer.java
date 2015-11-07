package model;

public class LexicalAnalyzer {

	public LexicalAnalyzer(String sourceCode) {
		super();
		this.sourceCode = sourceCode;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	private String sourceCode;
}
