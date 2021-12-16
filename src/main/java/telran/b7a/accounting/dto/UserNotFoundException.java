package telran.b7a.accounting.dto;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9199987187089618927L;
	
	public UserNotFoundException() {
		super("User not found");
	}

}
