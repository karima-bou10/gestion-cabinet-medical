package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import DAO.DAO;
import DAO.DepensesDAO;
import DAO.ImpayeDAO;
import DAO.MedicamentDAO;
import DAO.RecetteDAO;
import classes.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListeComptaController implements Initializable{

    @FXML
    private TableColumn<IMPAYER, Integer> ConsultIdImp;

    @FXML
    private Button caissebtn;

    @FXML
    private TableColumn<DEPENSES, Integer> consultIdDepense;

    @FXML
    private TableColumn<RECETTE, Integer> consultIdRecette;

    @FXML
    private TableColumn<DEPENSES, Date> dateDepense;

    @FXML
    private TableColumn<RECETTE, Date> dateRecetteColumn;

    @FXML
    private TableColumn<RECETTE, String> modePaiementRecette;

    @FXML
    private Button modifierImpbtn;

    @FXML
    private TableColumn<DEPENSES, Double> montantDepense;

    @FXML
    private TableColumn<IMPAYER, Double> montantReste;

    @FXML
    private TableColumn<IMPAYER, Double> montantSuivis;

    @FXML
    private TableColumn<DEPENSES, String> motifDepense;

    @FXML
    private TableColumn<DEPENSES, Integer> numDepense;

    @FXML
    private TableColumn<IMPAYER, Integer> numImpColumn;

    @FXML
    private TableColumn<RECETTE, Integer> numRecetteColumn;

    @FXML
    private TableColumn<RECETTE, Double> tarifRecetteColumn;

    @FXML
    private TableColumn<IMPAYER, String> telePatient;

    @FXML
    private TableView <DEPENSES> tableDepenses;

    @FXML
    private TableView<IMPAYER> tableImpayes;

    @FXML
    private TableView<RECETTE> tableRecettes;

    @FXML
    void caisse(ActionEvent event) {
    	try {
	        Parent root = FXMLLoader.load(getClass().getResource("/view/Comptabilité.fxml"));
	        Scene scene = new Scene(root);
	        
	        Stage stage = new Stage();
	        stage.setTitle("La Caisse");
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }

    @FXML
    void modifierImp(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			 //RECETTES
			  RecetteDAO recDAO = new RecetteDAO(DAO.cnx); 
		      ObservableList<RECETTE> recList = recDAO.list();
		      numRecetteColumn.setCellValueFactory(new PropertyValueFactory<RECETTE,Integer>("NUM_REC"));
		      dateRecetteColumn.setCellValueFactory(new PropertyValueFactory<RECETTE,Date>("DATE_REC"));
		      tarifRecetteColumn.setCellValueFactory(new PropertyValueFactory<RECETTE,Double>("TARIF_CONS"));
		      modePaiementRecette.setCellValueFactory(new PropertyValueFactory<RECETTE,String>("MODE_PAIEMENT"));
		      consultIdRecette.setCellValueFactory(new PropertyValueFactory<RECETTE,Integer>("CONSULTATION_ID"));

			  tableRecettes.setItems(recList);

		      //IMPAYES
		      ImpayeDAO impDAO = new ImpayeDAO(DAO.cnx); 
		      ObservableList<IMPAYER> impList = impDAO.list();
		      numImpColumn.setCellValueFactory(new PropertyValueFactory<IMPAYER,Integer>("NUM_IMP"));
		      telePatient.setCellValueFactory(new PropertyValueFactory<IMPAYER,String>("TELE_PAT"));
		      montantSuivis.setCellValueFactory(new PropertyValueFactory<IMPAYER,Double>("MONTANT_SUIVIE"));    
		      montantReste.setCellValueFactory(new PropertyValueFactory<IMPAYER,Double>("MONTANT_RESTE_SUIVIE"));
		      ConsultIdImp.setCellValueFactory(new PropertyValueFactory<IMPAYER,Integer>("CONSULTATION_ID"));
		      
		      tableImpayes.setItems(impList);
		      
		      //DEPENSES
		      DepensesDAO depDAO = new DepensesDAO(DAO.cnx); 
		      ObservableList<DEPENSES> depList = depDAO.list();
		      numDepense.setCellValueFactory(new PropertyValueFactory<DEPENSES,Integer>("NUM_DEP"));
		      dateDepense.setCellValueFactory(new PropertyValueFactory<DEPENSES,Date>("DATE_DEP"));
		      motifDepense.setCellValueFactory(new PropertyValueFactory<DEPENSES,String>("MOTIF_DEP"));
		      montantDepense.setCellValueFactory(new PropertyValueFactory<DEPENSES,Double>("MONTANT_DEP"));
		      consultIdDepense.setCellValueFactory(new PropertyValueFactory<DEPENSES,Integer>("CONSULTATION_ID"));

		      tableDepenses.setItems(depList);
		      
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
	}

}
