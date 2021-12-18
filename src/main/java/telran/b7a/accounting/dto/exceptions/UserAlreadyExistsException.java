package telran.b7a.accounting.dto.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5698414529709711623L;
	
	public UserAlreadyExistsException() {
		super("User already exist");
	}

}
