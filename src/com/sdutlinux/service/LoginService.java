package com.sdutlinux.service;

public class LoginService {
	
	public static boolean Login(String userName, String password) {
		if (userName.equals("root") && password.equals("123")) {
			return true;
		} else {
			return false;
		}
	}
}
