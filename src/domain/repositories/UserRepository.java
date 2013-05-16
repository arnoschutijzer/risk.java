package domain.repositories;

import java.util.List;
import java.util.ArrayList;

import domain.baseclass.User;
import domain.enums.Symbol;
import exceptions.UserAlreadyExistsException;

public class UserRepository {
	private List<User> users = new ArrayList<>();
	
	public void createUser(String name) throws UserAlreadyExistsException {
		boolean check = false;
		for (User user : users) {
			if (name.equals(user.getName())) {
				check = true;
			}
		}
		if (check == true) {
			throw new UserAlreadyExistsException("userAlreadyExists");
		} else {
			Symbol symbol = Symbol.values()[User.getUserCount()];
			users.add(new User(name, symbol));
		}
	}
	
	public List<User> getUsers(){
		return this.users;
	}

	@SuppressWarnings("static-access")
	public void clearUsers() {
		for(int i= 0; i< users.size(); i++)
			users.get(i).setUserCount(0);
		users.clear();
	}

	public User getUser(int index) {
		return users.get(index);
	}
}
