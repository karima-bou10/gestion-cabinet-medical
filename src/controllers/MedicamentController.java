package controllers;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.DAO;
import DAO.MedicamentDAO;
import DAO.PatientDAO;
import classes.MEDICAMENT;
import classes.Patient;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MedicamentController implements Initializable {

	 @FXML
	    private TableColumn<MEDICAMENT, String> dosageMedColumn;

	    @FXML
	    private TableColumn<MEDICAMENT, String> nomMedColumn;

	    @FXML
	    private TableColumn<MEDICAMENT, Integer> numMedColumn;

	    @FXML
	    private TableView<MEDICAMENT> table;
	    
	    @FXML
	    private Button supprimerbtn;
	    @FXML
	    private Button ajouterbtn;
	    @FXML
	    private Button rechercherbtn;
	    @FXML
	    private TextField idsearch;
	    
	    @FXML
	    void AjouterMedicament(ActionEvent event) {
	    	 try {
	    	        Parent root = FXMLLoader.load(getClass().getResource("../view/AjouterMedicament.fxml"));
	    	        Scene scene = new Scene(root);
	    	        
	    	        Stage stage = new Stage();
	    	        stage.setTitle("Hello");
	    	        stage.setScene(scene);
	    	        stage.show();
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }
	    }

		@FXML
	    void rechercherMedicament(ActionEvent event) throws SQLException {
	    	String recherche = idsearch.getText();
    	    if (!recherche.isEmpty()) {
    	    	MedicamentDAO medicamentDAO = new MedicamentDAO(DAO.cnx);
    	    	ObservableList<MEDICAMENT> resultSearch = medicamentDAO.find(recherche);
    	        table.setItems(resultSearch);
    	    } else {
    	    	Alert alert=new Alert(AlertType.ERROR);
			     alert.setTitle("Error");
			     alert.setHeaderText(null);
			     alert.setContentText("Veuillez remplir le champ de recherche.");
			     alert.showAndWait();    	  
			     }
	    }
	    @FXML
	    void supprimerMedicament(ActionEvent event) throws SQLException {
	    	MEDICAMENT med = table.getSelectionModel().getSelectedItem();
	        if (med != null) {
    	    	MedicamentDAO medicamentDAO = new MedicamentDAO(DAO.cnx);
	            boolean medDeleted = medicamentDAO.delete(med.getNUM_MED());
	            if (medDeleted) {
	          	       table.getItems().remove(med);
	                } else {
	                	table.getItems().add(med);
	                }
	            }
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			 try {
				  MedicamentDAO medDAO = new MedicamentDAO(DAO.cnx); 
			      ObservableList<MEDICAMENT> medList = medDAO.list();
			      dosageMedColumn.setCellValueFactory(new PropertyValueFactory<MEDICAMENT,String>("NUM_MED"));
			      nomMedColumn.setCellValueFactory(new PropertyValueFactory<MEDICAMENT,String>("NOM_MED"));
			      numMedColumn.setCellValueFactory(new PropertyValueFactory<MEDICAMENT,Integer>("DOSAGE"));

					// Ajouter les données à la table
			      table.setItems(medList);
			  }catch(Exception e) {
				  e.printStackTrace();
			  }	
			}			
		}

