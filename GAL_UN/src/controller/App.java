package controller;

public final class App {

	private App() {

	}

	// Singleton Getter
	public static App getInstance() {
		if (instance == null) {
			instance = new App();
		}
		return instance;
	}

	public void run(String[] arguments) {
		if (arguments.length == 2) {

		} else {
			System.out.println(ARG_ERR);
		}
	}

	public void printInConsole(String message) {

	}

	/* Execution Example: GalUn 'FontLanguage' 'DestinationLanguage' */
	public static void main(String[] args) {
		App app = App.getInstance();
		app.run(args);
	}

	// Singleton Object
	private static App instance;
	// Terminal UI manager

	/* Constants */
	public static final String FILE_EXT = ".laf";
	public static final String PATH_ERR = "Error : Not a valid path / File not found";
	public static final String LEX_ERR = "Error : Not a valid sintax";
	public static final String ARG_ERR = "Error : Missing Arguments / Invalid argument sintax";
	public static final String SUCCESS = "Success : File saved / Path: ";
	public static final String FILE_SUCCESS = "Success : File read";
}
