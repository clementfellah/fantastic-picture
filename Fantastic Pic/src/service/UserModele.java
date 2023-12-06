package service;

import java.util.ArrayList;

import entite.User;

public class UserModele {
	ArrayList<User> users = new ArrayList<>();
	
	public UserModele() {
		this.users = createUser();
	}
	
	
	public ArrayList<User> getUsers() {
		return this.users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}


	public ArrayList<User> createUser() {	
		User clement = new User(1, "Clement", "clement");
		User kamyar = new User(2, "Kamyar", "kamyar");
		User uthred = new User(3, "Uthred", "uthred");
		User lucas = new User(4, "Lucas", "lucas");
		User raphael = new User(5, "Raphael", "raphael");	
		this.users.add(clement);
		this.users.add(kamyar);
		this.users.add(uthred);
		this.users.add(lucas);
		this.users.add(raphael);			
		return users;
	}
	
	public boolean login(String login, String password) {
		boolean isLogged = false;
		ArrayList<User> listUsers = this.users;
		for(User user : listUsers) {
			if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
				isLogged = true;
			}
		}	
		return isLogged;
	}
}
