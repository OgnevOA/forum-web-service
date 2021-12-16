package telran.b7a.accounting.dto.exceptions;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9199987187089618927L;
	
	public UserNotFoundException() {
		super("User not found");
	}

}
