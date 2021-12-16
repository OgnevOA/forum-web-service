package telran.b7a.accounting.dto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRolesDto {
	String login;
	Set<String> roles;
}
