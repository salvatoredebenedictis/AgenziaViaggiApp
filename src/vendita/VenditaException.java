package vendita;

public class VenditaException extends Exception{
	
	public VenditaException(int i) {
		super();
		System.out.println("Il dato "+i+" inserito non � valido");
	}
	
	public VenditaException() {
		super();
	}

}
