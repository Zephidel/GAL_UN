package model.lexicalAnalyzer.tokens;

public abstract class Token {

	public enum Type {
		Identifier, AsignationOp, SumOp, MultOp, PowOp, ComparisonOp, Keyword, Number, Literal, Parenthesis
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
