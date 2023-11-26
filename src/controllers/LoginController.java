package controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.DAO;
import DAO.PatientDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable{

    @FXML
    private Button btnConnecterId;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @FXML
    private Label etatcnx;

    @FXML
    void btnConnecter(ActionEvent event) throws SQLException {
	  	  PatientDAO patientDAO = new PatientDAO(DAO.cnx); 
  	  try {
  		 String user=username.getText();
  		 String pass=password.getText();
  	       //DOCTOR
   			PreparedStatement statDoctor=DAO.cnx.prepareStatement("SELECT * FROM doctor WHERE user=? AND pass=?");
   			statDoctor.setString(1, user);
   			statDoctor.setString(2, pass);
  			ResultSet rsD=statDoctor.executeQuery();
  		   //SECRETAIRE
  			PreparedStatement statSecretaire=DAO.cnx.prepareStatement("SELECT * FROM secretaire WHERE user=? AND pass=?");
  			statSecretaire.setString(1, user);
  			statSecretaire.setString(2, pass);
  			ResultSet rsS=statSecretaire.executeQuery();
  			if(user.isEmpty() || pass.isEmpty()) {
	  			etatcnx.setText("username ou mot de passe vides");
  			}else {
  	  			if(rsD.next()) {
  	  				System.out.println("connexion doctor établie");
  	  			    Stage stage = (Stage) username.getScene().getWindow(); // Obtient la scène actuelle
  	  		        Parent root = FXMLLoader.load(getClass().getResource("../view/Doctor.fxml"));
  	  		        Scene scene = new Scene(root);
  	  					    				
  	  				stage.setTitle("Acceuil");
  	  				stage.setScene(scene);
  	  				stage.show();
  	  			}	
  	  			else if(rsS.next()) {
  	  				System.out.println("connexion secretaire établie");
  	  			    Stage stage = (Stage) username.getScene().getWindow(); // Obtient la scène actuelle
  	  		        Parent root = FXMLLoader.load(getClass().getResource("../view/secretaire.fxml"));
  	  		        Scene scene = new Scene(root);
  	  					    				
  	  				stage.setTitle("Acceuil");
  	  				stage.setScene(scene);
  	  				stage.show();
  	  			}	
  	  			else {
  	  				etatcnx.setText("username ou mot de passe incorrects");
  	  				username.clear();
  	  				password.clear();
  	  			}	
  			}
  			
  	} catch (Exception e) {
           e.printStackTrace();  	}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub 
		
		
	}

}