package com.bazinga.au.exception;

import java.util.ArrayList;

public class ErrorResponse {

public void addError(ErrorItem error) {
	ArrayList<String> list = new ArrayList<>();
	list.add(error.getCode());
	list.add(error.getMessage());
}
}
