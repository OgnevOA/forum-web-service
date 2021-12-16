package telran.b7a.accounting.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class User {
	@Id
	String login;
	String password;
	String firstName;
	String lastName;
	@Singular
	Set<String> roles;
	
	public User() {
		roles = new HashSet<>();
	}
	
	public Set<String> addRole(String role) {
		roles.add(role);
		return new HashSet<String>(roles);
	}
	
	public Set<String> deleteRole(String role) {
		roles.remove(role);
		return new HashSet<String>(roles);
	}
}
