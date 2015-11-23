package controller;

public final class App {

	private App() {
		/* Creation */
		analyzer = new model.lexicalAnalyzer.LexicalAnalyzer();
	}

	// Singleton Getter
	public static App getInstance() {
		if (instance == null) {
			instance = new App();
		}
		return instance;
	}

	public void run(String[] arguments) {
		/* Initialization if needed */
		if (arguments.length == 2) {

		} else {
			System.out.println(ARG_ERR);
		}
	}

	public void lexicalAnalyzer(String filePath) {
		if (filePath.endsWith(".py")) {
			analyzer.analyzeFile(filePath);
		} else {
			System.out.println(PATH_ERR);
		}
	}

	public void printInConsole(String message) {

	}

	/* Execution Example: GalUn 'FontLanguage' 'DestinationLanguage' */
	public static void main(String[] args) {
		App app = App.getInstance();
		// app.run(args);
		/* For testing purposes */
		if (args.length > 0) {
			app.lexicalAnalyzer(args[0]);
		}
	}

	// Singleton Object
	private static App instance;

	private model.lexicalAnalyzer.LexicalAnalyzer analyzer;

	/* Constants */
	public static final String FILE_EXT = ".laf";
	public static final String PATH_ERR = "Error : Not a valid path / File not found";
	public static final String LEX_ERR = "Error : Not a valid sintax";
	public static final String ARG_ERR = "Error : Missing Arguments / Invalid argument sintax";
	public static final String SUCCESS = "Success : File saved / Path: ";
	public static final String FILE_SUCCESS = "Success : File read";
}
