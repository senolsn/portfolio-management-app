package com.codeweh.portfolio_management_app.business.concretes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.codeweh.portfolio_management_app.business.abstracts.AuthService;
import com.codeweh.portfolio_management_app.business.dtos.LoginRequest;
import com.codeweh.portfolio_management_app.business.dtos.RegisterRequest;
import com.codeweh.portfolio_management_app.core.entities.User;
import com.codeweh.portfolio_management_app.core.security.JwtService;
import com.codeweh.portfolio_management_app.core.utilities.appConfig.AppConfig;
import com.codeweh.portfolio_management_app.core.utilities.results.DataResult;
import com.codeweh.portfolio_management_app.core.utilities.results.ErrorDataResult;
import com.codeweh.portfolio_management_app.core.utilities.results.ErrorResult;
import com.codeweh.portfolio_management_app.core.utilities.results.Result;
import com.codeweh.portfolio_management_app.core.utilities.results.SuccessDataResult;
import com.codeweh.portfolio_management_app.core.utilities.results.SuccessResult;
import com.codeweh.portfolio_management_app.dataAccess.abstracts.UserDao;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {
	
	private final UserDao _userDao;
	private final AppConfig _appConfig;
	private final JwtService _jwtService;
	
	@Autowired
	public AuthManager(UserDao userDao, AppConfig appConfig, JwtService jwtService) {
		_userDao = userDao;
		_appConfig = appConfig;
		_jwtService = jwtService;
	}
	

	@Override
	public Result register(RegisterRequest registerRequest) {
		
		//Note : CompletableFuture, Spring WebFlux
		if(_userDao.existsByUsername(registerRequest.getUsername())) {
			return new ErrorResult("Hata! Girmiş olduğunuz kullanıcı adı başkası tarafından kullanılmaktadır.");
		}
		
		User user = User.builder()
				    .username(registerRequest.getUsername())
				    .email(registerRequest.getEmail())
				    .password(_appConfig.passwordEncoder().encode(registerRequest.getPassword()))
				    .build();
		
		_userDao.save(user);
		
		return new SuccessResult("Kayıt işlemi başarılı.");
	}

	@Override
	public DataResult<String> login(LoginRequest loginRequest) {
		
		Optional<User> currentUser = _userDao.findByUsername(loginRequest.getUsername());
		
		if(currentUser == null || !(_appConfig.passwordEncoder().matches(loginRequest.getPassword(), currentUser.get().getPassword()))) {
			return new ErrorDataResult<>("Hata! Giriş yapılan bilgiler yanlıştır. Lütfen tekrar deneyiniz.");
		}
		
		String jwtToken = _jwtService.generateToken(currentUser.get());
		
        return new SuccessDataResult<>(jwtToken, "Giriş işlemi başarılı.");
	}
}
