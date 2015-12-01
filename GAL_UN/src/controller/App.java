package controller;

public final class App {

	private App() {
		/* Creation */
		analyzer = new lexicalAnalyzer.LexicalAnalyzer();
	}

	// Singleton Getter
	public static App getInstance() {
		if (instance == null) {
			instance = new App();
		}
		return instance;
	}

	public void run(String argument) {
		/* Initialization if needed */
		if (argument.contains("-j") || argument.contains("-p")) {			
			analyzer.generate(argument);
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
		app.run(args[0]);
	}

	// Singleton Object
	private static App instance;

	private lexicalAnalyzer.LexicalAnalyzer analyzer;

	/* Constants */
	public static final String FILE_EXT = ".laf";
	public static final String PATH_ERR = "Error : Not a valid path / File not found";
	public static final String LEX_ERR = "Error : Not a valid sintax";
	public static final String ARG_ERR = "Error : Missing Arguments / Invalid argument sintax";
	public static final String SUCCESS = "Success : File saved / Path: ";
	public static final String FILE_SUCCESS = "Success : File read";
}
