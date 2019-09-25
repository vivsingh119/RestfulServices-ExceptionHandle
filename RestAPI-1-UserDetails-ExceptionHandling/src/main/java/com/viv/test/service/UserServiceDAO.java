package com.viv.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.viv.test.domain.UserDetails;

@Component
public class UserServiceDAO {
	private static List<UserDetails> user = new ArrayList<UserDetails>();
	private static int userID = 3;
	static {
		user.add(new UserDetails(1, "Vivek", new Date()));
		user.add(new UserDetails(2, "Ramesh", new Date()));
		user.add(new UserDetails(3, "Dinesh", new Date()));
	}

	public List<UserDetails> findAll() {
		return user;
	}

	public UserDetails findById(int id) {
		for (UserDetails users : user) {
			if (users.getId() == id)
				return users;
		}
		return null;
	}

	public UserDetails save(UserDetails details) {
		if (details.getId() == null)
			details.setId(++userID);
		user.add(details);
		return details;
	}

}
