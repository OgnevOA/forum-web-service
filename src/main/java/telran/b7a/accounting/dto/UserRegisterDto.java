package telran.b7a.accounting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRegisterDto {
	String login;
	String password;
	String firstName;
	String lastName;
}
