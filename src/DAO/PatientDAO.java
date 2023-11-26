package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PatientDAO extends DAO<Patient>{

	public PatientDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(Patient patient) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO patient(CIN,DAT_FIC,NOM,PRENOM,DAT_NAI,PROF,ADRESSE,PHONE,SEXE) VALUES(?,?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setString(1,patient.getCIN());
	            stat.setDate(2,(Date) patient.getDAT_FIC());
	            stat.setString(3,patient.getNOM());
	            stat.setString(4,patient.getPRENOM());
	            stat.setDate(5,(Date) patient.getDAT_NAI());
	            stat.setString(6,patient.getPROF());
	            stat.setString(7,patient.getADRESSE());
	            stat.setString(8,patient.getPHONE());
	            stat.setString(9,patient.getSEXE());

	           stat.execute();
	           
	        // Récupérer l'ID généré pour le patient
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int patientId = generatedKeys.getInt(1);
		            return patientId;

	           }
	        }
	        catch(SQLException e)
	        {
	        	e.printStackTrace();
	        }
            return 0;
	    }
	 
	 @Override
	    public boolean delete(int num) {
	        try
	        {
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM patient WHERE NUM_FIC=?");
	            preparedStmt.setInt(1,num);
	            preparedStmt.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }
	 
	    public ObservableList<Patient> list()
	    {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT NUM_FIC, CIN, NOM, PRENOM, SEXE FROM patient");
	            ResultSet rs = stat.executeQuery();
	            ObservableList<Patient> patientList = FXCollections.observableArrayList();;
	            while(rs.next())
	            {
	  	  		  patientList.add(new Patient(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));  
	            }
	            return patientList;
	        }
	        catch(SQLException e)
	        {
	            return null;
	        }
	    }
	    public Patient findNom(String nom) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM patient WHERE NOM=?");
	            stat.setString(1,nom);
	            ResultSet rs = stat.executeQuery();
	            while(rs.next()) {
	                return new Patient(rs.getInt("NUM_FIC"), rs.getString("CIN"), nom, rs.getString("PRENOM"),rs.getString("SEXE"));
	            }
	        }
	        catch(SQLException e)
	        {
	            return new Patient(0,"",nom,"","");
	        }
            return new Patient(0,"",nom,"","");
	    }
	    
	    public Patient findCIN(String cin) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM patient WHERE CIN=?");
	            stat.setString(1,cin);
	            ResultSet rs = stat.executeQuery();
	            while(rs.next()) {
	                return new Patient(rs.getInt("NUM_FIC"), cin,rs.getString("NOM"), rs.getString("PRENOM"),rs.getString("SEXE"));
	            }
	        }
	        catch(SQLException e)
	        {
	            return new Patient(0,cin,"","","");
	        }
            return new Patient(0,cin,"","","");
	    }

	    
	    
	    public int nombrePatient()
	    {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT COUNT(*) FROM patient");
	            ResultSet rs = stat.executeQuery();
	            while(rs.next())
	            {
	                return rs.getInt("COUNT(*)");
	            }
	            return 0;
	        }
	        catch(SQLException e)
	        {
	            return 0;
	        }
	    }

		@Override
		public boolean update(Patient patient, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE patient SET CIN=?,DAT_FIC=?,NOM=?,PRENOM=?,DAT_NAI=?,PROF=?,ADRESSE=?,PHONE=?,SEXE=?  WHERE NUM_FIC=?");
	            stat.setString(1, patient.getCIN());
	            stat.setDate(2, (Date) patient.getDAT_FIC());
	            stat.setString(3, patient.getNOM());
	            stat.setString(4, patient.getPRENOM());
	            stat.setDate(5, (Date) patient.getDAT_NAI());
	            stat.setString(6, patient.getPROF());
	            stat.setString(7, patient.getADRESSE());
	            stat.setString(8, patient.getPHONE());
	            stat.setString(9, String.valueOf(patient.getSEXE()));
	            stat.setInt(10, num);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}

     public int selectCnam(int num) {
	   try
      {
        PreparedStatement stat = cnx.prepareStatement("SELECT NUM_CNAM FROM patient WHERE NUM_FIC=?");
        stat.setInt(1, num);
        ResultSet rs = stat.executeQuery();      
        
        if (rs.next()) {
            return rs.getInt("NUM_CNAM");
        } else {
            return 0;
        }    }
       catch(SQLException e)
       {
        return 0;
       }
     }
     public ObservableList<Patient> find(String txtSearch) {
    	    ObservableList<Patient> resultSearch = FXCollections.observableArrayList();
    	    try {
    	        PreparedStatement stat = cnx.prepareStatement("SELECT * FROM patient WHERE (NOM LIKE ?) OR (PRENOM LIKE ?)");
    	        stat.setString(1, "%" + txtSearch + "%");
    	        stat.setString(2, "%" + txtSearch + "%");
    	        ResultSet rs = stat.executeQuery();

    	        while (rs.next()) {
    	            int numFic = rs.getInt("NUM_FIC");
    	            String nom = rs.getString("NOM");
    	            String prenom = rs.getString("PRENOM");
    	            String cin = rs.getString("CIN");
    	            String sexe = rs.getString("SEXE");

    	            Patient patient = new Patient(numFic,cin,nom,prenom,sexe);
    	            // Ajouter le patient à la liste des résultats de recherche
    	            resultSearch.add(patient);
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return resultSearch;
    	}

}
