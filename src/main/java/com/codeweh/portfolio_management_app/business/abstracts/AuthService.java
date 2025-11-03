package com.codeweh.portfolio_management_app.business.abstracts;

import com.codeweh.portfolio_management_app.business.dtos.LoginRequest;
import com.codeweh.portfolio_management_app.business.dtos.RegisterRequest;
import com.codeweh.portfolio_management_app.core.utilities.results.DataResult;
import com.codeweh.portfolio_management_app.core.utilities.results.Result;

public interface AuthService {
	
	Result register(RegisterRequest registerRequest);
	DataResult<String> login(LoginRequest loginRequest);

}
