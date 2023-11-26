package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.CERTIFICAT;

public class CertificatDAO extends DAO<CERTIFICAT>{

	public CertificatDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(CERTIFICAT crt) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO certificat(COMMENTAIRE_CERT,DATE_CERT) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setString(1,crt.getCOMMENTAIRE_CERT());
	            stat.setDate(2,(Date) crt.getDATE_CERT());
	           stat.execute();
	           
	        // Récupérer l'ID généré pour le certificat
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int crtId = generatedKeys.getInt(1);
		            return crtId;
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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM certificat WHERE NUM_CERT=?");
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
		public boolean update(CERTIFICAT crt, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE certificat SET COMMENTAIRE_CERT=?,DATE_CERT=?  WHERE NUM_CERT=?");
	            stat.setString(1, crt.getCOMMENTAIRE_CERT());
	            stat.setDate(2, (Date) crt.getDATE_CERT());
	            stat.setInt(3, num);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}

}
