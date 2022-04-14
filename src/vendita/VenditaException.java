package vendita;

public class VenditaException extends Exception{
	
	public VenditaException(int i) {
		super();
		System.out.println("Il dato "+i+" inserito non è valido");
	}
	
	public VenditaException() {
		super();
	}

}
