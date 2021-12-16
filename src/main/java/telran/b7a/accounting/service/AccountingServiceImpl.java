package telran.b7a.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import telran.b7a.accounting.dao.AccountingMongoRepository;
import telran.b7a.accounting.dto.UpdateUserDto;
import telran.b7a.accounting.dto.UserCredentialsDto;
import telran.b7a.accounting.dto.UserNotFoundException;
import telran.b7a.accounting.dto.UserRegisterDto;
import telran.b7a.accounting.dto.UserResponseDto;
import telran.b7a.accounting.dto.UserRolesDto;
import telran.b7a.accounting.model.User;

@Service
public class AccountingServiceImpl implements AccountingService {
	
	AccountingMongoRepository accRepository;
	ModelMapper modelMapper;
	
	@Autowired
	public AccountingServiceImpl(AccountingMongoRepository accRepository, ModelMapper modelMapper) {
		this.accRepository = accRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserResponseDto regiserNewUser(UserRegisterDto userInfo) {
		User user = modelMapper.map(userInfo, User.class);
		accRepository.save(user);
		return modelMapper.map(user, UserResponseDto.class);
	}

	@Override
	public UserResponseDto loginUser(UserCredentialsDto userCredentials) {
		User user = accRepository.findById(userCredentials.getLogin()).orElseThrow(() -> new UserNotFoundException());
		return modelMapper.map(user, UserResponseDto.class);
	}

	@Override
	public UserResponseDto deleteUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserResponseDto updateUser(UpdateUserDto user, String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRolesDto addRole(String userName, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserRolesDto deleteRole(String userName, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(UserCredentialsDto userCredentials) {
		// TODO Auto-generated method stub

	}

}
