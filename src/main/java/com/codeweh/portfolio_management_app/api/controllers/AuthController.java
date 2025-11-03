package com.codeweh.portfolio_management_app.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.codeweh.portfolio_management_app.business.abstracts.AuthService;
import com.codeweh.portfolio_management_app.business.dtos.LoginRequest;
import com.codeweh.portfolio_management_app.business.dtos.RegisterRequest;
import com.codeweh.portfolio_management_app.core.utilities.results.DataResult;
import com.codeweh.portfolio_management_app.core.utilities.results.Result;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private final AuthService _authService;
	
	public AuthController(AuthService authService) {
		_authService = authService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<Result> register(@RequestBody RegisterRequest request) {
        var result = _authService.register(request);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
	}

	@PostMapping("/login")
	public ResponseEntity<DataResult<String>> login(@RequestBody LoginRequest request) {
        var result = _authService.login(request);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED).body(result);
	}
}
