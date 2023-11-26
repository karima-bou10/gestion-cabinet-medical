package controllers;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;


public class DoctorController implements Initializable {
    @FXML
    private Button compta;
    @FXML
    private Button consultation_btn;

    @FXML
    private Button dashBoard_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button medicament_btn;

    @FXML
    private Button patients_btn;

    @FXML
    private Button profile_btn;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button rendezVousbtn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		try {
		
			dashboard();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}
	
	@FXML
    void createPatient(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/ListePatients.fxml"));
        rootPane.getChildren().setAll(pane);
        
        }
	@FXML
    void dashboard() throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/DoctorDashboard.fxml"));
        rootPane.getChildren().setAll(pane);
    }

	@FXML
    void medicament(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/Medicament.fxml"));
        rootPane.getChildren().setAll(pane);
    }
	@FXML
    void consultation(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/ListeConsultation.fxml"));
        rootPane.getChildren().setAll(pane);
    }
        
	@FXML
    void rendezVous(ActionEvent event) throws IOException{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/RDV.fxml"));
        rootPane.getChildren().setAll(pane);
    }
	@FXML
    void profil(ActionEvent event) {
		try {
	        Parent root = FXMLLoader.load(getClass().getResource("/view/Profil.fxml"));
	        Scene scene = new Scene(root);
	        
	        Stage stage = new Stage();
	        stage.setTitle("Dr Bouchra");
	        stage.setScene(scene);
	        stage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
    }
	 @FXML
	    void logout(ActionEvent event) {
		 System.out.println("Logout button clicked");
		    // Load the login FXML file
		    try {
		        Parent root = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
		        Scene scene = new Scene(root);
		        
		        // Get the current stage and set the scene
		        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setScene(scene);
		        stage.show();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	    }
	 @FXML
	    void Comptabilité(ActionEvent event) throws IOException {
		    AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/ListeCompta.fxml"));
	        rootPane.getChildren().setAll(pane);
	    }
	   

}
