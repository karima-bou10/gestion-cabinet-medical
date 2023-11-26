package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import DAO.ConsultationDAO;
import DAO.DAO;
import classes.CERTIFICAT;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ConsultationController implements Initializable {

	 @FXML
	    private TextField IdPatientConsult;
	    @FXML
	    private TextField Diagnostic;

	    @FXML
	    private TextField Motif;

	    @FXML
	    private TextField NbrFois;

	    @FXML
	    private TextField QtMed;

	    @FXML
	    private DatePicker dateConsultation;

	    @FXML
	    private DatePicker dateOrdonnance;

	    @FXML
	    private TextArea description;

	    @FXML
	    private TextField dosage;

	    @FXML
	    private Button enregistrerbtn;

	    @FXML
	    private TextField frequenceCard;

	    @FXML
	    private TextField fromMed;

	    @FXML
	    private TextField nomMed;

	    @FXML
	    private TextField poids;

	    @FXML
	    private TextField taille;

	    @FXML
	    private TextField tarif;

	    @FXML
	    private TextField temperature;

	    @FXML
	    private TextField tension;

	    @FXML
	    private Button viderConsultationbtn;

	    @FXML
	    private Button viderMedicamentbtn;

	    @FXML
	    void enregistrerConsultation(ActionEvent event) {
	    	//ConsultationDAO certificatDAO = new ConsultationDAO(DAO.cnx); 

		  	 
	    }

	    @FXML
	    void viderConsultation(ActionEvent event) {

	    }

	    @FXML
	    void viderMedicament(ActionEvent event) {

	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
          			
		}


}
