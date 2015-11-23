package model.lexicalAnalyzer.tokens;

public abstract class Token {

	public enum Type {
		id, asignationOp, sumOp, multOp, powOp, comparisonOp, keyword
	};

	protected void setType(Type type) {
		this.type = type;
	}

	protected void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	protected void setId(int id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public String getLexeme() {
		return lexeme;
	}

	public int getId() {
		return id;
	}

	private Type type;
	private String lexeme;
	private int id;
}
