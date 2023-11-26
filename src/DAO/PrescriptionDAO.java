package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import classes.PRESCRIPTION;

public class PrescriptionDAO extends DAO<PRESCRIPTION>{

	public PrescriptionDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(PRESCRIPTION pres) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO prescription(NBRE_FOIS,QUANTITE_MED,FORM_MED) VALUES(?,?,?)");
	            stat.setInt(1,pres.getNBRE_FOIS());
	            stat.setInt(1,pres.getQUANTITE_MED());
	            stat.setString(1,pres.getFORM_MED());

	           stat.execute();
		            return 1;
	           
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
	            PreparedStatement stat = cnx.prepareStatement("DELETE FROM prescription WHERE (MEDICAMENT_ID=?) OR (ORDONNANCE_ID=?");
	            stat.setInt(1,num);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }
	
		@Override
		public boolean update(PRESCRIPTION pres, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE prescription SET NBRE_FOIS=?,QUANTITE_MED=?,FORM_MED=? WHERE (MEDICAMENT_ID=?) OR (ORDONNANCE_ID=?");
	            stat.setInt(1,pres.getNBRE_FOIS());
	            stat.setInt(2,pres.getQUANTITE_MED());
	            stat.setString(3,pres.getFORM_MED());
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
    
}