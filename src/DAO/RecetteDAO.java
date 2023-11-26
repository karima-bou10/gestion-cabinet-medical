package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.MVT_CAISSE;
import classes.RDV;
import classes.RECETTE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RecetteDAO extends MvtCaisseDAO{

	public RecetteDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	    public int create(MVT_CAISSE caisse,RECETTE rec) {
	        try
	        {   super.create(caisse);
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO recette(DATE_REC,TARIF_CONS,MODE_PAIEMENT) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setDate(1,(Date) rec.getDATE_REC());
	            stat.setDouble(2,rec.getTARIF_CONS());
	            stat.setString(3,rec.getMODE_PAIEMENT());

	           stat.execute();
	           
	        // Récupérer l'ID généré par la recette
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int recId = generatedKeys.getInt(1);
		            return recId;
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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM recette WHERE NUM_REC=?");
	            preparedStmt.setInt(1,num2);
	            preparedStmt.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }    
		public boolean update(MVT_CAISSE caisse, int num1,RECETTE rec,int num2) {
			try
	        {   super.update(caisse,num1);
	            PreparedStatement stat = cnx.prepareStatement("UPDATE recette SET DATE_REC=?,TARIF_CONS=?,MODE_PAIEMENT=? WHERE NUM_REC=?");
	            stat.setDate(1,(Date) rec.getDATE_REC());
	            stat.setDouble(2,rec.getTARIF_CONS());
	            stat.setString(3,rec.getMODE_PAIEMENT());
	            stat.setInt(4,num2);

	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
		 public ObservableList<RECETTE> list()
		    {
		        try
		        {
		            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM recette");
		            ResultSet rs = stat.executeQuery();
		            ObservableList<RECETTE> rectList = FXCollections.observableArrayList();;
		            while(rs.next())
		            {
		            	rectList.add(new RECETTE(rs.getInt(1),rs.getDate(2),rs.getDouble(3),rs.getString(4),rs.getInt(5)));  
		            }
		            return rectList;
		        }
		        catch(SQLException e)
		        {
		            return null;
		        }
		    }
	  
}


