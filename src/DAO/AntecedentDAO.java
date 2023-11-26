package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.ANTECEDENT;


public class AntecedentDAO extends DAO<ANTECEDENT>{
	public AntecedentDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(ANTECEDENT ant) {
	        try
	        {    
	        	// Specify the option to return generated keys
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO antecedent(CATEGORIE,DESCRIPTION) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setString(1,ant.getCATEGORIE());
	            stat.setString(2,ant.getDESCRIPTION());
	           stat.execute();
	           
	        // Récupérer l'ID généré pour l' antecedent
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int antId = generatedKeys.getInt(1);
		            return antId;
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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM antecedent WHERE NUM_ANT=?");
	            preparedStmt.setInt(1,num);
	            preparedStmt.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }
	 
		@Override
		public boolean update(ANTECEDENT ant, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE antecedent SET CATEGORIE=?, DESCRIPTION=?  WHERE NUM_ANT=?");
	            stat.setString(1,ant.getCATEGORIE());
	            stat.setString(2,ant.getDESCRIPTION());
	            stat.setInt(3, num);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
		public int selectAnt(int numP) {
			try
		    {
		        PreparedStatement stat = cnx.prepareStatement("SELECT NUM_ANT FROM antecedent WHERE PATIENT_ID=?");
		        stat.setInt(1, numP);
		        ResultSet rs = stat.executeQuery();      
		        
		        if (rs.next()) {
		            return rs.getInt("NUM_ANT");
		        } else {
		            return 0;
		        }    }
		    catch(SQLException e)
		    {
		        return 0;
		    }
		}
}
  