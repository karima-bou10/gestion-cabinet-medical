package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.DEPENSES;
import classes.IMPAYER;
import classes.MVT_CAISSE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DepensesDAO extends MvtCaisseDAO{

	public DepensesDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	    public int create(MVT_CAISSE caisse,DEPENSES dep) {
	        try
	        {   super.create(caisse);
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO depenses(DATE_DEP,MOTIF_DEP,	MONTANT_DEP) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setDate(1,(Date) dep.getDATE_DEP());
	            stat.setString(2,dep.getMOTIF_DEP());
	            stat.setDouble(3,dep.getMONTANT_DEP());

	           stat.execute();
	           
	        // Récupérer l'ID généré par la depense
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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM depenses WHERE NUM_DEP=?");
	            preparedStmt.setInt(1,num2);
	            preparedStmt.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }    
		public boolean update(MVT_CAISSE caisse, int num1,DEPENSES dep,int num2) {
			try
	        {   super.update(caisse,num1);
	            PreparedStatement stat = cnx.prepareStatement("UPDATE depenses SET DATE_DEP=?,MOTIF_DEP=?,MONTANT_DEP=? WHERE NUM_DEP=?");
	            stat.setDate(1,(Date) dep.getDATE_DEP());
	            stat.setString(2,dep.getMOTIF_DEP());
	            stat.setDouble(3,dep.getMONTANT_DEP());
	            stat.setInt(4,num2);

	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
		 public ObservableList<DEPENSES> list()
		    {
		        try
		        {
		            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM depenses");
		            ResultSet rs = stat.executeQuery();
		            ObservableList<DEPENSES> depList = FXCollections.observableArrayList();;
		            while(rs.next())
		            {
		            	depList.add(new DEPENSES(rs.getInt(1),rs.getDate(2),rs.getString(3),rs.getDouble(4),rs.getInt(5)));  
		            }
		            return depList;
		        }
		        catch(SQLException e)
		        {
		            return null;
		        }
		    }
}


