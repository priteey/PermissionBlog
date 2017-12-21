package com.service;

import com.model.User;

public interface RegisterService {

	boolean checkUserIdExist(String userId);

	String validate(User user, boolean userExist);

}
