package com.codeweh.portfolio_management_app.business.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	
	private String username;
	private String password;

}
