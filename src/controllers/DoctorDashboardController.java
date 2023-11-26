package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import DAO.ConsultationDAO;
import DAO.DAO;
import DAO.MedicamentDAO;
import DAO.PatientDAO;
import DAO.RDVDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class DoctorDashboardController implements Initializable {

    @FXML
    private Label consultCount;

    @FXML
    private Label medCount;

    @FXML
    private Label patientCount;

    @FXML
    private Label rdvCount;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Obtenez le nombre de patients depuis votre source de données
        try {
  	  	     PatientDAO patientDAO = new PatientDAO(DAO.cnx); 
        	 int nbrPatients=patientDAO.nombrePatient() ; 
        	 patientCount.setText(Integer.toString(nbrPatients));
        }catch(Exception e) {
        	e.printStackTrace();
        }
		
        // Obtenez le nombre de consultations depuis votre source de données
        try {
 	  	     ConsultationDAO consultDAO = new ConsultationDAO(DAO.cnx); 
       	 int nbrconsultations=consultDAO.nbrConsultation();
       	consultCount.setText(Integer.toString(nbrconsultations));
       }catch(Exception e) {
       	e.printStackTrace();
       }
       
	     // Obtenez le nombre de rendez-vous depuis votre source de données
        try {
	  	     RDVDAO rdvDAO = new RDVDAO(DAO.cnx); 
      	 int nbrrdv=rdvDAO.nombreRdv();
      	rdvCount.setText(Integer.toString(nbrrdv));
        }catch(Exception e) {
        	e.printStackTrace();
        }
        // Obtenez le nombre de médicaments depuis votre source de données
        try {
	  	     MedicamentDAO medDAO = new MedicamentDAO(DAO.cnx); 
     	 int nbrmedicaments=medDAO.nombreMedicament();
     	medCount.setText(Integer.toString(nbrmedicaments));
       }catch(Exception e) {
       	e.printStackTrace();
       }	
	}
}
