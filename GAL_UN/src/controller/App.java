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

	public void run() {

	}

	public void printInConsole(String message) {
		terminal.printInConsole(message);
	}

	public String readFromConsole() {
		return terminal.readFromConsole();
	}

	public LexicalAnalyzer process(String txt, String cmd) {
		return manager.process(txt, cmd);
	}

	public static void main(String[] args) {
		App app = App.getInstance();
		app.run();
	}

	// Singleton Object
	private static App instance;
	// Lexical Analyzer Generator
	private InterpreterManager manager;
	// Terminal UI manager
	private Terminal terminal;
}
