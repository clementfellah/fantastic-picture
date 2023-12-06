package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import service.NotationModele;
import service.PictureModele;
import service.UserModele;

public class SampleController {
	
	private UserModele userModele;
	
	private PictureModele pictureModele;
	
	private NotationModele notationModele;
	
	private String currentCityButtonText;
	
	@FXML
    private VBox vboxCityButton;
		
	@FXML
    private ImageView pictureCity;

	@FXML
    private TextField inputLogin;

    @FXML
    private PasswordField inputPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private TextField inputNotation;

    @FXML
    private Button btnValidate;
      
    @FXML
    private Button clickedButton;
    

    @FXML
    void onClickLogin(ActionEvent event) {
    	userModele = new UserModele();
    	notationModele = new NotationModele();
    	String loginUser;
    	String passwordUser;   	
    	loginUser = inputLogin.getText();
    	passwordUser = inputPassword.getText(); 	
    	boolean isLogged = userModele.login(loginUser, passwordUser);	
		pictureCity.setImage(null);
		inputNotation.setEditable(false);
		btnValidate.setDisable(true);
		inputNotation.clear();
		vboxCityButton.getChildren().clear();
    	if(isLogged) {
    		ArrayList<String> citiesAllowed = notationModele.getCitiesAllowedForUser(loginUser);
    		createCityButtons(citiesAllowed);
    		inputNotation.setEditable(true);
    	}
    }
    

    @FXML
    void onClickValidate(ActionEvent event) {
        notationModele = new NotationModele();
        Integer notationGive = 0;
        String currentCity = "";
        String inputText = inputNotation.getText();
        try {
            validateNotationInput(inputText);
            notationGive = Integer.valueOf(inputText);
            String userLogin = inputLogin.getText();
            if (currentCityButtonText != null) {
                currentCity = currentCityButtonText;
            }
            notationModele.setNotationForUserPicture(userLogin, currentCity, notationGive);
            saveToTxtFile(userLogin, currentCity, notationGive);
        } catch (NumberFormatException e) {
            System.err.println("Veuillez entrer un nombre compris entre 0 et 20.");
        }
    }

    private void validateNotationInput(String inputText) throws NumberFormatException {
        int notationValue = Integer.parseInt(inputText);
        if (notationValue < 0 || notationValue > 20) {
            throw new NumberFormatException("La note doit être comprise entre 0 et 20.");
        }
    }


    private void saveToTxtFile(String userLogin, String currentCity, Integer notationGive) {
        String fileName = "notations.txt";
        try {
            File file = new File(fileName);
            ArrayList<String> newNotations = new ArrayList<>();
            boolean cityExists = false;
            if (file.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("User: " + userLogin) && line.contains("City: " + currentCity)) {
                            newNotations.add("User: " + userLogin + ", City: " + currentCity + ", Notation: " + notationGive);
                            cityExists = true;
                        } else {
                            newNotations.add(line);
                        }
                    }
                }
            }
            if (!cityExists) newNotations.add("User: " + userLogin + ", City: " + currentCity + ", Notation: " + notationGive);
            try (PrintWriter printWriter = new PrintWriter(file)) {
                newNotations.forEach(printWriter::println);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    
    @FXML
    void onClickToCity(ActionEvent event) {
    	inputNotation.clear();
        Button clickedButton = (Button) event.getSource();
        String cityName = clickedButton.getText();
        pictureModele = new PictureModele();
        String urlToDisplay = pictureModele.getCityURL(cityName);       
        File file = new File(urlToDisplay);
        Image finalImage = new Image(file.toURI().toString());        
        pictureCity.setImage(finalImage);
        btnValidate.setDisable(false);        
        loadNotation();
    }
    
    private void loadNotation() {
        String userLogin = inputLogin.getText();
        String currentCity = currentCityButtonText;
        String fileName = "notations.txt";
        try {
            File file = new File(fileName);
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains("User: " + userLogin) && line.contains("City: " + currentCity)) {
                        String[] parts = line.split("Notation: ");
                        if (parts.length > 1) {
                            String notationString = parts[1].trim();
                            inputNotation.setText(notationString);
                            break; 
                        }
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  
    private void createCityButtons(ArrayList<String> cities) {
    	for (String city : cities) {
    		Button button = new Button(city) ;
    		button.setOnAction(event -> {
                currentCityButtonText = button.getText();
                onClickToCity(event);
            });
    		vboxCityButton.getChildren().add(button); 
            vboxCityButton.setSpacing(20);
            vboxCityButton.setAlignment(Pos.CENTER);
    	}
    }
}
