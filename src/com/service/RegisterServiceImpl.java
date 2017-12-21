package com.service;

import com.dao.RegisterDao;
import com.dao.RegisterDaoImpl;
import com.model.User;

public class RegisterServiceImpl implements RegisterService {
	RegisterDao regDao= new RegisterDaoImpl();
	
	public boolean checkUserIdExist(String userId) {
		boolean users= regDao.checkUserIdExist(userId);		
        return users;
	}

	@Override
	public String validate(User user, boolean userExist) {
		if(!user.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") || userExist==true ){
			 if(userExist){
				return "userExist";
			}
			 else if(!user.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
				return "password";
			}
		}
			return null;
		}
	}
