package controllers;
import classes.CONSULTATION;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import DAO.*;
public class ListeConsultation implements Initializable {
	@FXML
    private TableColumn<CONSULTATION, Date> dateConsultColumn;
    @FXML
    private Button ajouterbtn;

    @FXML
    private TableColumn<CONSULTATION, String> dignosticColumn;

    @FXML
    private TableColumn<CONSULTATION, String> motifColumn;

    @FXML
    private TableColumn<CONSULTATION, Integer> numConsColumn;

    @FXML
    private TableColumn<CONSULTATION, Integer> patientIdConsult;

    @FXML
    private TextField recherchebarre;

    @FXML
    private Button rechercherbtn;

    @FXML
    private Button supprimerbtn;

    @FXML
    private TableView<CONSULTATION> table;

    @FXML
    void AjouterConsultation(ActionEvent event) {
    	 try {
 	        Parent root = FXMLLoader.load(getClass().getResource("../view/Consultation.fxml"));
 	        Scene scene = new Scene(root);
 	        
 	        Stage stage = new Stage();
 	        stage.setTitle("Nouveau Consultation");
 	        stage.setScene(scene);
 	        stage.show();
 	    } catch (IOException e) {
 	        e.printStackTrace();
 	    }
    }

    @FXML
    void rechercherConsultation(ActionEvent event) throws SQLException {
    	 String recherche = recherchebarre.getText();
 	    if (!recherche.isEmpty()) {
 	        ConsultationDAO consultDAO = new ConsultationDAO(DAO.cnx);
 	        ObservableList<CONSULTATION> resultSearch = consultDAO.find(recherche);
 	        table.setItems(resultSearch);
 	    } else {
 	    	Alert alert=new Alert(AlertType.ERROR);
			     alert.setTitle("Error");
			     alert.setHeaderText(null);
			     alert.setContentText("Consultation introuvable!");
			     alert.showAndWait();    	    }
    }

    @FXML
    void supprimerConsultation(ActionEvent event) throws SQLException {
    	// Récupérer la ligne sélectionnée dans la table
        CONSULTATION consult = table.getSelectionModel().getSelectedItem();
        if (consult != null) {
            ConsultationDAO consultationDAO = new ConsultationDAO(DAO.cnx);
            // Supprimer la consultation de la base de données en utilisant le DAO
            boolean consultationDeleted = consultationDAO .delete(consult.getNUM_CONS());
            if (consultationDeleted ) {
                    // Supprimer la consultation de la table
                    table.getItems().remove(consult);
                } else {
                    // Si la suppression a échoué, réinsérer le patient dans la table
                    table.getItems().add(consult);
                }
            }
    }
    @FXML
    void etablirCertificat(ActionEvent event) {
    	try {
 	        Parent root = FXMLLoader.load(getClass().getResource("../view/Certificat.fxml"));
 	        Scene scene = new Scene(root);
 	        
 	        Stage stage = new Stage();
 	        stage.setTitle("Nouveau Certificat");
 	        stage.setScene(scene);
 	        stage.show();
 	    } catch (IOException e) {
 	        e.printStackTrace();
 	    }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 try {
			  ConsultationDAO consultationDAO = new ConsultationDAO(DAO.cnx); 
		      ObservableList<CONSULTATION> consultList = consultationDAO.list();
		      numConsColumn.setCellValueFactory(new PropertyValueFactory<CONSULTATION,Integer>("NUM_CONS"));
		     // dateConsultColumn.setCellValueFactory(new PropertyValueFactory<CONSULTATION,Date>("DATE_CONS"));
		      motifColumn.setCellValueFactory(new PropertyValueFactory<CONSULTATION,String>("MOTIF"));
		      dignosticColumn.setCellValueFactory(new PropertyValueFactory<CONSULTATION,String>("DIAGNOSTIC"));
		      patientIdConsult.setCellValueFactory(new PropertyValueFactory<CONSULTATION,Integer>("PATIENT_ID"));

				// Ajouter les données à la table
		      table.setItems(consultList);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		
	}

}