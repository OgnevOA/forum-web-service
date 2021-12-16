package telran.b7a.accounting.service;

import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserCredentialsDto;
import telran.b7a.accounting.dto.UserRegisterDto;
import telran.b7a.accounting.dto.UserResponseDto;
import telran.b7a.accounting.dto.UserRolesDto;

public interface AccountingService {
	UserResponseDto regiserNewUser(UserRegisterDto userInfo);
	
	UserResponseDto loginUser(UserCredentialsDto userCredentials);
	
	UserResponseDto deleteUser(String userName);
	
	UserResponseDto updateUser(UpdateUserDto user, String userName);
	
	UserRolesDto addRole(String userName, String role);
	
	UserRolesDto deleteRole(String userName, String role);
	
	void changePassword(UserCredentialsDto userCredentials);
}
