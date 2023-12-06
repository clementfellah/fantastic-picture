package entite;

public class Notation {
	
	private Picture picture;
	private User user;
	private Integer notation;
	
	
	public Notation(Picture picture, User user, Integer notation) {
		this.picture = picture;
		this.user = user;
		this.notation = notation;
	}


	public Picture getpicture() {
		return picture;
	}


	public void setpicture(Picture picture) {
		this.picture = picture;
	}


	public User getuser() {
		return user;
	}


	public void setuser(User user) {
		this.user = user;
	}


	public Integer getNotation() {
		return notation;
	}


	public void setNotation(Integer notation) {
		this.notation = notation;
	}
}
