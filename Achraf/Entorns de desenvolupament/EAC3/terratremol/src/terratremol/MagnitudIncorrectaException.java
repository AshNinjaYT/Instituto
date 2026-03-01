package terratremol;

@SuppressWarnings("serial")
public class MagnitudIncorrectaException extends Exception {
	public MagnitudIncorrectaException (String missatge) {
		super(missatge);
	}
}
