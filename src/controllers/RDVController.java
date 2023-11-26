package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Time;
import java.util.Date;
import java.util.ResourceBundle;

import DAO.DAO;
import DAO.PatientDAO;
import DAO.RDVDAO;
import classes.Patient;
import classes.RDV;

public class RDVController implements Initializable{

    @FXML
    private DatePicker dateRDV;

    @FXML
    private TableColumn<RDV, Date> dateRDVcolumn;

    @FXML
    private TextField descriptionRDV;

    @FXML
    private TableColumn<RDV, String> descriptionRDVcolumn;

    @FXML
    private AnchorPane enregistrerRDVbtn;

    @FXML
    private TextField heureRDV;

    @FXML
    private TableColumn<RDV, Time> heureRDVcolumn;

    @FXML
    private TableColumn<RDV, Integer> numRDVcolumn;

    @FXML
    private TableView<RDV> tableRDV;

    @FXML
    void ajouterRDV(ActionEvent event) {

    }

    @FXML
    void enregistrerRDV(MouseEvent event) {

    }

    @FXML
    void modifierRDV(ActionEvent event) {

    }

    @FXML
    void supprimerRDV(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		 try {
			  RDVDAO rdvDAO = new RDVDAO(DAO.cnx); 
		      ObservableList<RDV> rdvList = rdvDAO.list();
		      numRDVcolumn.setCellValueFactory(new PropertyValueFactory<RDV,Integer>("NUM_RDV"));
		      descriptionRDVcolumn.setCellValueFactory(new PropertyValueFactory<RDV,String>("DESCRIPTION"));
		      dateRDVcolumn.setCellValueFactory(new PropertyValueFactory<RDV,Date>("DATE_RDV"));
		      heureRDVcolumn.setCellValueFactory(new PropertyValueFactory<RDV,Time>("HEURE_RDV"));

				// Ajouter les données à la table
		      tableRDV.setItems(rdvList);
		  }catch(Exception e) {
			  e.printStackTrace();
		  }	
		}		
}



