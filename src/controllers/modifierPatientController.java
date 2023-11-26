package controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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

public class modifierPatientController implements Initializable{

    @FXML
    private TextField adresse;

    @FXML
    private TextField categorieAnt;

    @FXML
    private TextField cin;

    @FXML
    private DatePicker dateNaissance;

    @FXML
    private DatePicker dateVal;

    @FXML
    private TextField descriptionAnt;

    @FXML
    private Button modifierPatientbtn;

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
    private Button viderAntbtn1;

    @FXML
    private Button viderbtn;

    @FXML
    void Vider(ActionEvent event) {
    	adresse.clear();
    	categorieAnt.clear();
    	cin.clear();
    	dateNaissance.setValue(null);
    	dateVal.setValue(null);
    	descriptionAnt.clear();
    	nom.clear();
    	prenom.clear();
    	profession.clear();
    	sexe.setValue(null);
    	telephone.clear();
    	typeAssurance.clear();
    }

    @FXML
    void ViderAnt(ActionEvent event) {
    	categorieAnt.clear();
    	descriptionAnt.clear();
    }

    public void modifier(Patient pat) throws SQLException {
    	PatientDAO patientDAO = new PatientDAO(DAO.cnx);
        cin.setText(pat.getCIN());
        nom.setText(pat.getNOM());
        prenom.setText(pat.getPRENOM());
    }
    
    @FXML
    void modifierPatient(ActionEvent event){
    	try {
	  		  String txtCIN,txtnom,txtprenom,prof,adrss,tele,txtsexe,txtTypeAssur,ctg,dsc;
	  		  //Patient
			  txtCIN=cin.getText();
			  txtnom=nom.getText();
			  Date date = new Date(0);
			  txtprenom=prenom.getText();
			  prof=profession.getText();
			  adrss=adresse.getText();
			  tele=telephone.getText();
			  txtsexe=(String) sexe.getValue();
		       //CNAM
		       txtTypeAssur=typeAssurance.getText();
		     //Antecedent
			      ctg=categorieAnt.getText();
			       dsc=descriptionAnt.getText();
		       

			   // Obtenez la valeur sélectionnée du DatePicker
		        LocalDate localDate2 = dateNaissance.getValue();
	
	  		if (txtCIN.isEmpty() || txtnom.isEmpty() || txtprenom.isEmpty() || tele.isEmpty() || adrss.isEmpty() || localDate2==null) {
		  		Alert alert=new Alert(AlertType.ERROR);
			     alert.setTitle("Error");
			     alert.setHeaderText(null);
			     alert.setContentText("Données Manquantes!");
			     alert.showAndWait();
		        }
	  		else {

		        // Convertissez l'objet LocalDate en java.sql.Date
		        Date datenaiss = Date.valueOf(localDate2);
	            //vérifié si le patient a été deja crée
			  	PatientDAO patientDAO=new PatientDAO(DAO.cnx);			  

			Patient pat=new Patient(txtCIN,date,txtnom,txtprenom,datenaiss,prof,adrss,tele,txtsexe);
		    boolean statusP =patientDAO.update(pat,pat.getNUM_FIC());
 
		    //si le patient a une assurance
		    if(!txtTypeAssur.isEmpty()) {
		      LocalDate localDate1 = dateVal.getValue();
			  Date dateval = Date.valueOf(localDate1);
		    CNAM cnam=new CNAM(dateval,txtTypeAssur);
		    CNAMDAO cnamDAO = new CNAMDAO(DAO.cnx);	
		    boolean statusC =cnamDAO.update(cnam,cnam.getNUM_CNAM());
		    }
		    //si le patient a un antecedent
		    if(!ctg.isEmpty()) {
		    ANTECEDENT ant=new ANTECEDENT(ctg,dsc);
		    AntecedentDAO antDAO = new AntecedentDAO(DAO.cnx);	
			boolean statusA =antDAO.update(ant,ant.getNUM_ANT());
		    }
		    
		    if(statusP) {
		    	Alert alert=new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Modifier Patient");
			     alert.setHeaderText(null);
		    	alert.setContentText("Patient a été modifié.");
		    	alert.showAndWait();
		    }
		    else {
		    	 Alert alert=new Alert(AlertType.ERROR);
			     alert.setTitle("Modifier Patient");
			     alert.setHeaderText(null);
			     alert.setContentText("Patient non modifié.");
			     alert.showAndWait();
		    }
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		sexe.setItems(FXCollections.observableArrayList("Homme","Femme"));				
	}
}
