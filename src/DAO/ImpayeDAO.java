package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.IMPAYER;
import classes.MVT_CAISSE;
import classes.RECETTE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ImpayeDAO extends MvtCaisseDAO{

	public ImpayeDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	    public int create(MVT_CAISSE caisse,IMPAYER imp) {
	        try
	        {   super.create(caisse);
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO impayer(TELE_PAT,MONTANT_SUIVIE,MONTANT_RESTE_SUIVIE) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setString(1,imp.getTELE_PAT());
	            stat.setDouble(2,imp.getMONTANT_SUIVIE());
	            stat.setDouble(3,imp.getMONTANT_RESTE_SUIVIE());

	           stat.execute();
	           
	        // Récupérer l'ID généré pour l'impayé
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int impId = generatedKeys.getInt(1);
		            return impId;
	           }
	        }
	        catch(SQLException e)
	        {
	        	e.printStackTrace();
	        }
            return 0;
	    }
	 
	 
	    public boolean delete(int num1,int num2) {
	        try
	        {
	        	super.delete(num1);
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM impayer WHERE NUM_IMP=?");
	            preparedStmt.setInt(1,num2);
	            preparedStmt.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }    
		public boolean update(MVT_CAISSE caisse, int num1,IMPAYER imp,int num2) {
			try
	        {   super.update(caisse,num1);
	            PreparedStatement stat = cnx.prepareStatement("UPDATE impayer SET TELE_PAT=?,MONTANT_SUIVIE=?,MONTANT_RESTE_SUIVIE=? WHERE NUM_IMP=?");
	            stat.setString(1, imp.getTELE_PAT());
	            stat.setDouble(2, imp.getMONTANT_SUIVIE());
	            stat.setDouble(3, imp.getMONTANT_RESTE_SUIVIE());
	            stat.setInt(4, num2);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
		 public ObservableList<IMPAYER> list()
		    {
		        try
		        {
		            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM impayer");
		            ResultSet rs = stat.executeQuery();
		            ObservableList<IMPAYER> impList = FXCollections.observableArrayList();;
		            while(rs.next())
		            {
		            	impList.add(new IMPAYER(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5)));  
		            }
		            return impList;
		        }
		        catch(SQLException e)
		        {
		            return null;
		        }
		    }
}

