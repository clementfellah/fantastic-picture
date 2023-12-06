package service;

import java.util.ArrayList;

import entite.Picture;

public class PictureModele {
	ArrayList<Picture> pics = new ArrayList<>();
	
	public PictureModele() {
		this.pics = createPicture();
	}

	public ArrayList<Picture> getPics() {
		return pics;
	}

	public void setPics(ArrayList<Picture> pics) {
		this.pics = pics;
	}


	public ArrayList<Picture> createPicture() {	
		Picture toulouse = new Picture(1, "Toulouse", "src/asset/toulouse.jpg");
		Picture persepolis = new Picture(2, "Persepolis", "src/asset/persepolis.jpg");
		Picture tokyo = new Picture(3, "Tokyo", "src/asset/tokyo.jpg");
		Picture bamburgh = new Picture(4, "Bamburgh", "src/asset/bamburgh.jpg");	
		pics.add(toulouse);
		pics.add(persepolis);
		pics.add(tokyo);
		pics.add(bamburgh);		
		return pics;
	}
	
	public String getCityURL(String city) {
		String urlToDisplay = null;
		ArrayList<Picture> listPicture = createPicture();
		for(Picture picture : listPicture) {
			if(picture.getImage().equals(city)) {
				urlToDisplay = picture.getUrl();
			}
		}
		return urlToDisplay;
	}
	


}
