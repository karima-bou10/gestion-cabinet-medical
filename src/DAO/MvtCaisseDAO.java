package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.MVT_CAISSE;

public class MvtCaisseDAO extends DAO<MVT_CAISSE>{

	public MvtCaisseDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(MVT_CAISSE caisse) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO mvt_caisse(TYPE_MVT) VALUES(?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setString(1,caisse.getTYPE_MVT());
	           stat.execute();
	           
	        // Récupérer l'ID généré pour le mvt
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int mvttId = generatedKeys.getInt(1);
		            return mvttId;

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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM mvt_caisse WHERE NUM_MVT=?");
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
		public boolean update(MVT_CAISSE caisse, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE mvt_caisse SET TYPE_MVT=? WHERE NUM_MVT=?");
	            stat.setInt(2, num);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}

}

