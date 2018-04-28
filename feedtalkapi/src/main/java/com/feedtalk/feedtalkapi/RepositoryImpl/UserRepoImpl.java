package com.feedtalk.feedtalkapi.RepositoryImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.feedtalk.feedtalkapi.Models.Feed;
import com.feedtalk.feedtalkapi.Models.User;
import com.feedtalk.feedtalkapi.Repositories.UserRepository;
import com.feedtalk.feedtalkapi.utility.UtilityHelper;

public class UserRepoImpl {

	@Autowired
	UserRepository userRepository;

	public User addNewUserImpl(User user) {
		return userRepository.save(user);
	}

	public User getUserByUserName(String username) {
		return userRepository.findByEmailAndActiveTrue(username);
	}

	public boolean isUserAuthorized(String useremail, String password, UtilityHelper.UserAuthourities type) {
		try {
			String orginalPassword = UtilityHelper.getBase64DecodedValue(password);
			String OriginalUserEmail = UtilityHelper.getBase64DecodedValue(useremail);
			User user = userRepository.findByEmailAndPasswordAndUsertypeAndActiveTrue(OriginalUserEmail,
					UtilityHelper.encrypt(orginalPassword, orginalPassword), type.toString());
			if (user != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

	}

	public Map<String,String> loginAndAuthourityDetails(String useremail, String password) {
		try {
			String orginalPassword = UtilityHelper.getBase64DecodedValue(password);
			String OriginalUserEmail = UtilityHelper.getBase64DecodedValue(useremail);
			User user = userRepository.findByEmailAndPasswordAndActiveTrue(OriginalUserEmail,
					UtilityHelper.encrypt(orginalPassword, orginalPassword));

			Map<String, String> response = new HashMap<String, String>();
			if (user != null) {
				response.put("ATHOURIZED", "TRUE");
				response.put("USER_TYPE", user.getUsertype());
				response.put("USER_NAME", user.getUsername());
			} else {
				response.put("ATHOURIZED", "FALSE");
				response.put("USER_TYPE", "");
			}
			return response;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
}
