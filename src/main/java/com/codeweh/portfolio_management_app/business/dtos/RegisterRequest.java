package com.codeweh.portfolio_management_app.business.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	private String email;
	private String username;
	private String password;

}
