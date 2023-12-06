package service;

import java.util.ArrayList;

import entite.Notation;
import entite.Picture;
import entite.User;

public class NotationModele {

    private UserModele userModele;
    private PictureModele pictureModele;

    private ArrayList<Notation> listNotations = new ArrayList<>();
    private ArrayList<User> listUsers = new ArrayList<>();
    private ArrayList<Picture> listPictures = new ArrayList<>();

    public NotationModele() {
        this.listUsers = createUsers();
        this.listPictures = createPictures();
        this.listNotations = createNotations();
    }

    private ArrayList<User> createUsers() {
        userModele = new UserModele();
        ArrayList<User> getUserList = userModele.createUser();

        return getUserList;
    }

    private ArrayList<Picture> createPictures() {
        pictureModele = new PictureModele();
        ArrayList<Picture> getPicturesList = pictureModele.createPicture();

        return getPicturesList;
    }

    private ArrayList<Notation> createNotations() {

        Picture toulouse = listPictures.stream().filter(city -> "Toulouse".equals(city.getImage())).findFirst().orElse(null);
        Picture persepolis = listPictures.stream().filter(city -> "Persepolis".equals(city.getImage())).findFirst().orElse(null);
        Picture tokyo = listPictures.stream().filter(city -> "Tokyo".equals(city.getImage())).findFirst().orElse(null);
        Picture bamburgh = listPictures.stream().filter(city -> "Bamburgh".equals(city.getImage())).findFirst().orElse(null);

        User clement = listUsers.stream().filter(login -> "Clement".equals(login.getLogin())).findFirst().orElse(null);
        User kamyar = listUsers.stream().filter(login -> "Kamyar".equals(login.getLogin())).findFirst().orElse(null);
        User uthred = listUsers.stream().filter(login -> "Uthred".equals(login.getLogin())).findFirst().orElse(null);
        User lucas = listUsers.stream().filter(login -> "Lucas".equals(login.getLogin())).findFirst().orElse(null);
        User raphael = listUsers.stream().filter(login -> "Raphael".equals(login.getLogin())).findFirst().orElse(null);

        Notation forToulouse = new Notation(toulouse, clement, null);
        Notation forToulouse1 = new Notation(toulouse, kamyar, null);
        Notation forToulouse2 = new Notation(toulouse, lucas, null);
        Notation forToulouse3 = new Notation(toulouse, raphael, null);

        Notation forShiraz = new Notation(persepolis, clement, null);
        Notation forShiraz1 = new Notation(persepolis, kamyar, null);

        Notation forBamburgh = new Notation(bamburgh, uthred, null);

        Notation forTokyo = new Notation(tokyo, clement, null);
        Notation forTokyo1 = new Notation(tokyo, kamyar, null);
        Notation forTokyo2 = new Notation(tokyo, lucas, null);
        Notation forTokyo3 = new Notation(tokyo, raphael, null);

        this.listNotations.add(forToulouse);
        this.listNotations.add(forToulouse1);
        this.listNotations.add(forToulouse2);
        this.listNotations.add(forToulouse3);

        this.listNotations.add(forShiraz);
        this.listNotations.add(forShiraz1);

        this.listNotations.add(forBamburgh);

        this.listNotations.add(forTokyo);
        this.listNotations.add(forTokyo1);
        this.listNotations.add(forTokyo2);
        this.listNotations.add(forTokyo3);



        return this.listNotations;
    }

    public ArrayList<String> getCitiesAllowedForUser(String user) {
        ArrayList<String> citiesAllowed = new ArrayList<>();      
        ArrayList<Notation> listNotations = createNotations();
        for (Notation notation : listNotations) {
            if (user.equals(notation.getuser().getLogin()) && !citiesAllowed.contains(notation.getpicture().getImage())) {
                citiesAllowed.add(notation.getpicture().getImage());
            }
        }

        return citiesAllowed;
    }
    
    public Integer setNotationForUserPicture(String user, String picture, Integer notation) {
    	ArrayList<Notation> listNotations = createNotations();
    	for(Notation notationByUserPicture : listNotations) {
    		if(user.equals(notationByUserPicture.getuser().getLogin()) && picture.equals(notationByUserPicture.getpicture().getImage())) {
                notationByUserPicture.setNotation(notation);
                return notationByUserPicture.getNotation();
            }
        }
        return 0;
    }


}
