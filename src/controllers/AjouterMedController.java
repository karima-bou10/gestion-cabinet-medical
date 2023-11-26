package controllers;

import java.sql.SQLException;
import DAO.DAO;
import DAO.MedicamentDAO;
import classes.MEDICAMENT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AjouterMedController {

    @FXML
    private TextField dosageMed;

    @FXML
    private Button enregistrerMedbtn;

    @FXML
    private TextField nomMed;

    @FXML
    void enregistrerMed(ActionEvent event) throws SQLException {
    	MedicamentDAO medicamentDAO = new MedicamentDAO(DAO.cnx); 

	  	  try {
	  		  String nom,dosage;
	  		 
		        nom=  nomMed.getText();
		        dosage=  dosageMed.getText();
		        
	  		if (nom.isEmpty() || dosage.isEmpty()) {
		  		Alert alert=new Alert(AlertType.ERROR);
			     alert.setTitle("Error");
			     alert.setHeaderText(null);
			     alert.setContentText("Remplissez tous les champs!");
			     alert.showAndWait();
		        }
	  		else {
	  		
		        MEDICAMENT med =new MEDICAMENT(nom,dosage);
			    int statusP = medicamentDAO.create(med);
			    if(statusP!=0) {
			    	Alert alert=new Alert(AlertType.INFORMATION);
				     alert.setTitle("Creation d'un médicament");
				     alert.setHeaderText(null);
				     alert.setContentText("médicament bien crée");
				     alert.showAndWait();
			    }
			    else {
			    	Alert alert=new Alert(AlertType.ERROR);
				     alert.setTitle("Error");
				     alert.setHeaderText(null);
				     alert.setContentText("médicament non crée");
				     alert.showAndWait();
			    }	
	  		}
	 
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }

}
