package controllers;

import java.awt.Label;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import DAO.AntecedentDAO;
import DAO.CNAMDAO;
import DAO.DAO;
import DAO.PatientDAO;
import classes.ANTECEDENT;
import classes.CNAM;
import classes.Patient;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class PatientController implements Initializable{

	    @FXML
	    private TextField categorieAnt;
	    @FXML
	    private TextField descriptionAnt;
	    @FXML
	    private Label Profession;

	    @FXML
	    private TextField adresse;

	    @FXML
	    private Button ajouterPatientbtn;

	    @FXML
	    private TextField cin;

	    @FXML
	    private DatePicker dateNaissance;
	    @FXML
	    private Button viderAntbtn1;

	    @FXML
	    private DatePicker dateVal;

	    @FXML
	    private TextField nom;

	    @FXML
	    private TextField prenom;

	    @FXML
	    private TextField profession;

	    @FXML
	    private ComboBox<String> sexe;

	    @FXML
	    private TextField telephone;

	    @FXML
	    private TextField typeAssurance;

	    @FXML
	    private Button viderbtn;

	    @FXML
	    void Vider(ActionEvent event) {
	    	
	    	cin.clear();
	    	nom.clear();
	    	prenom.clear();
	    	profession.clear();
	    	adresse.clear();
	    	telephone.clear();
	    	sexe.setValue(null);
	    	dateNaissance.setValue(null);
	    	dateVal.setValue(null);
	    	typeAssurance.clear();
	    	categorieAnt.clear();
	    	descriptionAnt.clear();
	    }

	    @FXML
	    void ajouterPatient(ActionEvent event) throws SQLException {
	  	  PatientDAO patientDAO = new PatientDAO(DAO.cnx); 
	  	  CNAMDAO cnamDAO = new CNAMDAO(DAO.cnx); 
	  	  AntecedentDAO antDAO = new AntecedentDAO(DAO.cnx); 
	  	   
	  	  try {
	  		  String txtCIN,txtnom,txtprenom,prof,adrss,tele,txtsexe,txtTypeAssur,ctg,dsc;
	  		  //Patient
			  txtCIN=cin.getText();
			  Date date = new Date(0);
			  txtnom=nom.getText();
			  txtprenom=prenom.getText();
			  prof=profession.getText();
			  adrss=adresse.getText();
			  tele=telephone.getText();
			  txtsexe=sexe.getValue();
			  //CNAM
		       LocalDate localDate1 = dateVal.getValue();
		       Date dateval = Date.valueOf(localDate1);
		       txtTypeAssur=typeAssurance.getText();
		       //Antecedent
		      ctg=categorieAnt.getText();
		       dsc=descriptionAnt.getText();
	
	  		if (txtCIN.isEmpty() || txtnom.isEmpty() || txtprenom.isEmpty() || tele.isEmpty() || adrss.isEmpty()) {
		  		Alert alert=new Alert(AlertType.ERROR);
			     alert.setTitle("Error");
			     alert.setHeaderText(null);
			     alert.setContentText("Patient invalid!");
			     alert.showAndWait();
		        }
			
			   // Obtenez la valeur sélectionnée du DatePicker
		        LocalDate localDate2 = dateNaissance.getValue();

		        // Convertissez l'objet LocalDate en java.sql.Date
		        Date datenaiss = Date.valueOf(localDate2);
	            //vérifié si le patient a été deja crée
			  	Patient checker = patientDAO.findCIN(txtCIN);
			   if(checker.getNUM_FIC() != 0) {
			        	Alert alert=new Alert(AlertType.ERROR);
					     alert.setTitle("Error");
					     alert.setHeaderText(null);
					     alert.setContentText("Patient déjà enregistré");
					     alert.showAndWait();
			        }
			        else {
		    Patient pat=new Patient(txtCIN,date,txtnom,txtprenom,datenaiss,prof,adrss,tele,txtsexe);
		    int statusP =patientDAO.create(pat);
   
		    //si le patient a une assurance
		    if(!txtTypeAssur.isEmpty()) {
		    CNAM cnam=new CNAM(dateval,txtTypeAssur);
		    int statusC =cnamDAO.create(cnam);
		    
		  //liaison du Patient avec CNAM
		    if(statusC!=0) {
		   PreparedStatement stat = DAO.cnx.prepareStatement("UPDATE patient SET NUM_CNAM=? WHERE NUM_FIC=?");
            stat.setInt(1,statusC);
            stat.setInt(2,statusP);
            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update failed");
            }
		    }
		    }
		    //si le patient a un antecedent
		    if(!ctg.isEmpty()) {
		    ANTECEDENT ant=new ANTECEDENT(ctg,dsc);
		    int statusA =antDAO.create(ant);
		    
		  //liaison du Patient avec antecedent
		    if(statusA!=0) {
		   PreparedStatement stat = DAO.cnx.prepareStatement("UPDATE antecedent SET PATIENT_ID=? WHERE NUM_ANT=?");
            stat.setInt(1,statusP);
            stat.setInt(2,statusA);
            int rowsAffected = stat.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update failed");
            }
		    }
		    }
		    
		    if(statusP!=0) {
				System.out.println("Record inserted successfully");
		    	Alert alert=new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Ajouter Patient");
			     alert.setHeaderText(null);
		    	alert.setContentText("Patient bien ajouté.");
		    	alert.showAndWait();
		    }
		    else {
		    	 Alert alert=new Alert(AlertType.ERROR);
			     alert.setTitle("Ajouter Patient");
			     alert.setHeaderText(null);
			     alert.setContentText("Patient non ajouté.");
			     alert.showAndWait();
		    }
	    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	    
	    @FXML
	    void ViderAnt(ActionEvent event) {
	    	categorieAnt.clear();
	    	descriptionAnt.clear();
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sexe.setItems(FXCollections.observableArrayList("Homme","Femme"));		
	}

}
