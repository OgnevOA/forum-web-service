package telran.b7a.accounting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserCredentialsDto;
import telran.b7a.accounting.dto.UserRegisterDto;
import telran.b7a.accounting.dto.UserResponseDto;
import telran.b7a.accounting.dto.UserRolesDto;
import telran.b7a.accounting.service.AccountingService;

@RestController
@RequestMapping("/account")
public class AccountingController {
	AccountingService accountingService;

	@Autowired
	public AccountingController(AccountingService accountingService) {
		this.accountingService = accountingService;
	}
	
	@PostMapping("/register")
	public UserResponseDto registerNewUser(@RequestBody UserRegisterDto userInfo) {
		return accountingService.regiserNewUser(userInfo);
	}
	
	@PostMapping("/login")
	public UserResponseDto loginUser(@RequestBody UserCredentialsDto userCredentials) {
		return accountingService.loginUser(userCredentials);
	}
	
	@DeleteMapping("/user/{userName}")
	public UserResponseDto deleteUser(@PathVariable String userName) {
		return accountingService.deleteUser(userName);
	}
	
	@PutMapping("/user/{userName}")
	public UserResponseDto updateUser(@RequestBody UpdateUserDto user,@PathVariable String userName) {
		return accountingService.updateUser(user, userName);
	}
	
	@PutMapping("/user/{user}/role/{role}")
	public UserRolesDto addRole(@PathVariable String userName,@PathVariable String role) {
		return accountingService.addRole(userName, role);
	}
	
	@DeleteMapping("/user/{user}/role/{role}")
	public UserRolesDto deleteRole(@PathVariable String userName,@PathVariable String role) {
		return accountingService.deleteRole(userName, role);
	}
	
	@PutMapping("/user/password")
	public void changePassword(@RequestBody UserCredentialsDto userCredentials) {
		accountingService.changePassword(userCredentials);
	}
	
	
}
