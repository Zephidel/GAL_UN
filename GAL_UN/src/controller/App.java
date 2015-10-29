package controller;

import model.*;
import view.*;

public final class App {

	private App() {
		generator = new LexicalAnalyzerGenerator(
				new RegularExpressionsInterpreter());
		terminal = new Terminal();
	}

	// Singleton Getter
	public static App getInstance() {
		if (instance == null) {
			instance = new App();
		}
		return instance;
	}

	public void run() {

	}

	public void printInConsole(String message) {
		terminal.printInConsole(message);
	}

	public String readFromConsole() {
		return terminal.readFromConsole();
	}

	public static void main(String[] args) {
		App app = App.getInstance();
		app.run();
	}

	// Singleton Object
	private static App instance;
	// Lexical Analyzer Generator
	private LexicalAnalyzerGenerator generator;
	// Terminal UI manager
	private Terminal terminal;
}
