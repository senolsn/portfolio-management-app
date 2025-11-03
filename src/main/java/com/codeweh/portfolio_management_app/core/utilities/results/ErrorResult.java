package com.codeweh.portfolio_management_app.core.utilities.results;

public class ErrorResult extends Result {
	
	public ErrorResult() {
		super(false);
	}

	public ErrorResult(String message) {
		super(false, message);
	}

}
