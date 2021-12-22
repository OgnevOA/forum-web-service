package telran.b7a.accounting.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserRegisterDto;
import telran.b7a.accounting.dto.UserResponseDto;
import telran.b7a.accounting.dto.UserRolesDto;
import telran.b7a.accounting.service.UserAccountService;

@RestController
@RequestMapping("/account")
public class AccountingController {
	UserAccountService accountingService;

	@Autowired
	public AccountingController(UserAccountService accountingService) {
		this.accountingService = accountingService;
	}
	
	@PostMapping("/register")
	public UserResponseDto registerNewUser(@RequestBody UserRegisterDto userInfo) {
		return accountingService.regiserNewUser(userInfo);
	}
	
	@PostMapping("/login")
	public UserResponseDto loginUser(Principal principal) {
		return accountingService.getUser(principal.getName());
	}
	
	@DeleteMapping("/user/{userName}")
	public UserResponseDto deleteUser(@PathVariable String userName) {
		return accountingService.deleteUser(userName);
	}
	
	@PutMapping("/user/{userName}")
	public UserResponseDto updateUser(@RequestBody UpdateUserDto user,@PathVariable String userName) {
		return accountingService.editUser(user, userName);
	}
	
	@PutMapping("/user/{user}/role/{role}")
	public UserRolesDto addRole(@PathVariable String user,@PathVariable String role) {
		return accountingService.addRole(user, role);
	}
	
	@DeleteMapping("/user/{user}/role/{role}")
	public UserRolesDto deleteRole(@PathVariable String user,@PathVariable String role) {
		return accountingService.deleteRole(user, role);
	}
	
	@PutMapping("/password")
	public void changePassword(Principal principal,@RequestHeader("X-Password") String password) {
		accountingService.changePassword(principal.getName(), password);
	}
	
}
