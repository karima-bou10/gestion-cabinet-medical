package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.DAO;
import DAO.PatientDAO;
import classes.Patient;

public class ListePatientsController implements Initializable {

	 @FXML
	    private Button ajouterPatientbtn;

	    @FXML
	    private TableColumn<Patient, String> cinPatientColumn;

	    @FXML
	    private TableColumn<Patient, String> nomPatientColumn;

	    @FXML
	    private TableColumn<Patient, Integer> numPatientColumn;

	    @FXML
	    private TableColumn<Patient, String> prenomPatientColumn;

	    @FXML
	    private Button rechercherbtn;

	    @FXML
	    private TableColumn<Patient, String> sexePatientColumn;

	    @FXML
	    private Button supprimerbtn;

	    @FXML
	    private TableView<Patient> tablePatient;
    
	    @FXML
	    private TextField idSearch;

    @FXML
    void ajouterPatient(ActionEvent event) {
    	 try {
    	        Parent root = FXMLLoader.load(getClass().getResource("../view/Patient.fxml"));
    	        Scene scene = new Scene(root);
    	        
    	        Stage stage = new Stage();
    	        stage.setTitle("Nouveau Patient");
    	        stage.setScene(scene);
    	        stage.show();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    }

    @FXML
    void rechercherPatient(ActionEvent event) throws SQLException {
    	 String recherche = idSearch.getText();
    	    if (!recherche.isEmpty()) {
    	        PatientDAO patientDAO = new PatientDAO(DAO.cnx);
    	        ObservableList<Patient> resultSearch = patientDAO.find(recherche);
    	        tablePatient.setItems(resultSearch);
    	    } else {
    	    	Alert alert=new Alert(AlertType.ERROR);
			     alert.setTitle("Error");
			     alert.setHeaderText(null);
			     alert.setContentText("Veuillez remplir le champ de recherche.");
			     alert.showAndWait();    	  
			     }
    }

    @FXML
    void supprimerPatient(ActionEvent event) throws SQLException {
    	 // Récupérer la ligne sélectionnée dans la table
        Patient patient = tablePatient.getSelectionModel().getSelectedItem();
        if (patient != null) {
            PatientDAO patientDAO = new PatientDAO(DAO.cnx);
            // Supprimer le patient de la base de données en utilisant le DAO
            boolean patientDeleted = patientDAO.delete(patient.getNUM_FIC());
            if (patientDeleted) {
          
                    // Supprimer le patient de la table
                    tablePatient.getItems().remove(patient);
                } else {
                    // Si la suppression a échoué, réinsérer le patient dans la table
                    tablePatient.getItems().add(patient);
                }
            }
    }
    
    @FXML
    void modifierPatient(ActionEvent event) throws SQLException {
    	// Récupérer le patient sélectionné dans la table
        Patient patient = tablePatient.getSelectionModel().getSelectedItem();
        if (patient != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/modifierPatient.fxml"));
                Parent root = loader.load();
                
                // Passer le patient sélectionné à la controller de la nouvelle interface
                modifierPatientController modifierPatientController = loader.getController();
                modifierPatientController.modifier(patient);
                
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Modifier Patient");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Afficher une alerte si aucun patient n'est sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucun patient sélectionné");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner un patient à modifier.");
            alert.showAndWait();
        }
    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 try {
			  PatientDAO patientDAO = new PatientDAO(DAO.cnx); 
		      ObservableList<Patient> patientList = patientDAO.list();
		      numPatientColumn.setCellValueFactory(new PropertyValueFactory<Patient,Integer>("NUM_FIC"));
		      cinPatientColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("CIN"));
		      nomPatientColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("NOM"));
		      prenomPatientColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("PRENOM"));
		      sexePatientColumn.setCellValueFactory(new PropertyValueFactory<Patient,String>("SEXE"));

				// Ajouter les données à la table
		      tablePatient.setItems(patientList);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }	}

}

