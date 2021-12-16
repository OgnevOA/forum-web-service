package telran.b7a.accounting.dto;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UpdateUserDto {
	String firstName;
	String lastName;
	
	@JsonIgnore
	public Optional<String> getFirstNameOptional() {
		return Optional.ofNullable(firstName);
	}
	
	@JsonIgnore
	public Optional<String> getLastNameOptional() {
		return Optional.ofNullable(lastName);
	}
}
