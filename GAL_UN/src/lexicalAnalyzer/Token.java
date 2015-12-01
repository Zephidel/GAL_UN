package lexicalAnalyzer;

public abstract class Token {

	public enum Type {
		Identifier, AsignationOp, MathOp, ComparisonOp, Keyword, Number, Literal, DefinitionOp, SpecialSymbol
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
