package entite;

public class Picture {
	
	private int id;
	private String image;
	private String url;
	
	public Picture(int id, String image, String url) {
		this.id = id;
		this.image = image;
		this.url = url;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	

}
