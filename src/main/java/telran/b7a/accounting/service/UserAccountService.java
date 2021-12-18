package telran.b7a.accounting.service;

import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserRegisterDto;
import telran.b7a.accounting.dto.UserResponseDto;
import telran.b7a.accounting.dto.UserRolesDto;

public interface UserAccountService {
	UserResponseDto regiserNewUser(UserRegisterDto userInfo);
	
	UserResponseDto getUser(String login);
	
	UserResponseDto deleteUser(String userName);
	
	UserResponseDto editUser(UpdateUserDto user, String userName);
	
	UserRolesDto addRole(String userName, String role);
	
	UserRolesDto deleteRole(String userName, String role);
	
	void changePassword(String login, String password);
}
