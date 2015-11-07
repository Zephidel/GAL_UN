package model;

import util.InterpreterIdentifiers;

public final class InterpreterFactory {

	private InterpreterFactory() {

	}

	public static LanguageInterpreter createInterpreter(
			InterpreterIdentifiers id) {
		LanguageInterpreter instance = null;

		if (id == InterpreterIdentifiers.Regular_Expressions) {
			instance = new RegularExpressionsInterpreter();
		}

		return instance;
	}

}