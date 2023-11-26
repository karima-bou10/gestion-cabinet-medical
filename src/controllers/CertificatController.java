package controllers;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import DAO.*;
import classes.CERTIFICAT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

public class CertificatController {

    @FXML
    private TextArea cmntCertificat;

    @FXML
    private DatePicker dateCertificat;

    @FXML
    private Button enregistrerCertbtn;

    @FXML
    void enregistrerCertificat(ActionEvent event) throws SQLException {
    	CertificatDAO certificatDAO = new CertificatDAO(DAO.cnx); 

	  	  try {
	  		  String commentaire;
	  		// Obtenez la valeur sélectionnée du DatePicker
		        LocalDate localDate2 = dateCertificat.getValue();
		       
		        commentaire= cmntCertificat.getText();

	  		if (commentaire.isEmpty() || localDate2 == null) {
		  		Alert alert=new Alert(AlertType.ERROR);
			     alert.setTitle("Error");
			     alert.setHeaderText(null);
			     alert.setContentText("Remplissez tous les champs!");
			     alert.showAndWait();
		        }
	  		else {
	  			 // Convertissez l'objet LocalDate en java.sql.Date
		        Date dateCert = Date.valueOf(localDate2);
	  		   CERTIFICAT cert =new CERTIFICAT (commentaire,dateCert);
			    int statusP = certificatDAO.create(cert);
			    if(statusP!=0) {
			    	Alert alert=new Alert(AlertType.INFORMATION);
				     alert.setTitle("Creation d'une certificat");
				     alert.setHeaderText(null);
				     alert.setContentText("Certificat bien crée");
				     alert.showAndWait();
			    }
			    else {
			    	Alert alert=new Alert(AlertType.ERROR);
				     alert.setTitle("Error");
				     alert.setHeaderText(null);
				     alert.setContentText("Certificat non crée");
				     alert.showAndWait();
			    }	
	  		}
	 
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }

}
