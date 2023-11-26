package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.CONSULTATION;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConsultationDAO extends DAO<CONSULTATION>{

	public ConsultationDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(CONSULTATION cns) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO consultation(DATE_CONS,MOTIF,DIAGNOSTIC) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setDate(1,cns.getDATE_CONS());
	            stat.setString(2,cns.getMOTIF());
	            stat.setString(3,cns.getDIAGNOSTIC());
	      
	           stat.execute();
	           
	        // Récupérer l'ID généré pour le consultation
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int cnsltId = generatedKeys.getInt(1);
		            return cnsltId;

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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM consultation WHERE NUM_CONS=?");
	            preparedStmt.setInt(1,num);
	            preparedStmt.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }
	 
	    public ObservableList<CONSULTATION> list()
	    {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM consultation");
	            ResultSet rs = stat.executeQuery();
	            ObservableList<CONSULTATION> consList = FXCollections.observableArrayList();;
	            while(rs.next())
	            {
	            	consList.add(new CONSULTATION(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getString(4),rs.getInt(5)));  
	            }
	            return consList;
	        }
	        catch(SQLException e)
	        {
	            return null;
	        }
	    }
	   
	    public int nbrConsultation()
	    {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT COUNT(*) FROM consultation");
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
		public boolean update(CONSULTATION cns, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE consultation SET DATE_CONS=?,MOTIF=?,DIAGNOSTIC=?)  WHERE NUM_CONS=?");
	            stat.setDate(1, (Date) cns.getDATE_CONS());
	            stat.setString(2, cns.getMOTIF());
	            stat.setString(3, cns.getDIAGNOSTIC());
	            stat.setInt(4, num);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}

     public int selectPatient(int num) {
	   try
      {
        PreparedStatement stat = cnx.prepareStatement("SELECT NUM_FIC FROM patient WHERE NUM_FIC=?");
        stat.setInt(1, num);
        ResultSet rs = stat.executeQuery();      
        
        if (rs.next()) {
            return rs.getInt("NUM_FIC");
        } else {
            return 0;
        }    }
       catch(SQLException e)
       {
        return 0;
       }
     }
     public ObservableList<CONSULTATION> find(String txtSearch) {
    	    ObservableList<CONSULTATION> resultSearch = FXCollections.observableArrayList();
    	    try {
    	        PreparedStatement stat = cnx.prepareStatement("SELECT * FROM consultation WHERE MOTIF LIKE ?");
    	        stat.setString(1, "%" + txtSearch + "%");
    	        ResultSet rs = stat.executeQuery();

    	        while (rs.next()) {
    	            int numCns = rs.getInt("NUM_CONS");
    	            Date date = rs.getDate("DATE_CONS");
    	            String motif = rs.getString("MOTIF");
    	            String diag = rs.getString("DIAGNOSTIC");
    	            int patient_id = rs.getInt("PATIENT_ID");

    	            CONSULTATION cns = new CONSULTATION(numCns,date,motif,diag,patient_id);
    	            // Ajouter le consultation à la liste des résultats de recherche
    	            resultSearch.add(cns);
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return resultSearch;
    	}
}