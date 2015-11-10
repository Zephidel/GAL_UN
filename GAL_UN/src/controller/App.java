package controller;

import model.*;
import view.*;

public final class App {

	private App() {
		manager = new InterpreterManager();
		terminal = new Terminal();
	}

	// Singleton Getter
	public static App getInstance() {
		if (instance == null) {
			instance = new App();
		}
		return instance;
	}

	public void run(String[] arguments) {
		if (arguments.length == 3) {
			readFromConsole(arguments[0], arguments[1], arguments[2]);
		} else {
			System.out.println(ARG_ERR);
		}
	}

	public void printInConsole(String message) {
		terminal.printInConsole(message);
	}

	public String readFromConsole(String fileLocation, String fontLang,
			String destLang) {

		String value = PATH_ERR;

		if (fileLocation.contains(FILE_EXT)) {
			value = terminal.readFromConsole(fileLocation, fontLang, destLang);
		}

		return value;
	}

	public LexicalAnalyzer process(String txt, String cmd) {
		return manager.process(txt, cmd);
	}

	/* Execution Example: GalUn 'FileName' 'FontLanguage' 'DestinationLanguage' */
	public static void main(String[] args) {
		App app = App.getInstance();
		app.run(args);
	}

	// Singleton Object
	private static App instance;
	// Lexical Analyzer Generator
	private InterpreterManager manager;
	// Terminal UI manager
	private Terminal terminal;

	/* Constants */
	public static final String FILE_EXT = ".laf";
	public static final String PATH_ERR = "Error : Not a valid path / File not found";
	public static final String LEX_ERR = "Error : Not a valid sintax";
	public static final String ARG_ERR = "Error : Missing Arguments / Invalid argument sintax";
	public static final String SUCCESS = "Success : File saved / Path: ";
}
