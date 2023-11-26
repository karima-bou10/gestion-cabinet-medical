package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import classes.ORDONNANCE;

public class OrdonnanaceDAO extends DAO<ORDONNANCE>{

	public OrdonnanaceDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(ORDONNANCE ord) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO ordonnance(DATE_ORDO) VALUES(?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setDate(1,(Date) ord.getDATE_ORDO());
	           stat.execute();
	           
	        // Récupérer l'ID généré pour l'ordonnance
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int ordId = generatedKeys.getInt(1);
		            return ordId;
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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM ordonnance WHERE NUM_ORDO=?");
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
		public boolean update(ORDONNANCE ord, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE ordonnance SET DATE_ORDO=? WHERE NUM_ORDO=?");
	            stat.setDate(1,(Date) ord.getDATE_ORDO());
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
    
}