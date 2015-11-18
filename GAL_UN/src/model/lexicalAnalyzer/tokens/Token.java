package model.lexicalAnalyzer.tokens;

public abstract class Token {

	public enum Type {
		id, asignationOp, sumOp, multOp, powOp, comparisonOp, keyword
	};

	protected void setType(Type type) {
		if (type == null) {
			this.type = type;
		}
	}

	protected void setLexeme(String lexeme) {
		if (lexeme == null) {
			this.lexeme = lexeme;
		}
	}

	public Type getType() {
		return type;
	}

	public String getLexeme() {
		return lexeme;
	}

	private Type type;
	private String lexeme;
}
