package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.MEDICAMENT;
import classes.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MedicamentDAO extends DAO<MEDICAMENT>{

	public MedicamentDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(MEDICAMENT med) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO medicament(NOM_MED,DOSAGE) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setString(1,med.getNOM_MED());
	            stat.setString(2,med.getDOSAGE());

	           stat.execute();
	           
	        // Récupérer l'ID généré pour le medicament
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int medId = generatedKeys.getInt(1);
		            return medId;
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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM medicament WHERE NUM_MED=?");
	            preparedStmt.setInt(1,num);
	            preparedStmt.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }
	 
	    public ObservableList<MEDICAMENT> list()
	    {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM medicament");
	            ResultSet rs = stat.executeQuery();
	            ObservableList<MEDICAMENT> medicList = FXCollections.observableArrayList();;
	            while(rs.next())
	            {
	            	medicList.add(new MEDICAMENT(rs.getInt(1),rs.getString(2),rs.getString(3)));  
	            }
	            return medicList;
	        }
	        catch(SQLException e)
	        {
	            return null;
	        }
	    }
  
	    public int nombreMedicament()
	    {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT COUNT(*) FROM medicament");
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
		public boolean update(MEDICAMENT med, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE medicament SET NOM_MED=?,DOSAGE=?  WHERE NUM_MED=?");
	            stat.setString(1, med.getNOM_MED());
	            stat.setString(2, med.getDOSAGE());
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
		public ObservableList<MEDICAMENT> find (String recherche) {
    	    ObservableList<MEDICAMENT> resultSearch = FXCollections.observableArrayList();
			 try
		        {
		            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM medicament WHERE (NOM_MED LIKE ?)");
	    	        stat.setString(1, "%" + recherche + "%");
		            ResultSet rs = stat.executeQuery();
		            while(rs.next()) {
		            	MEDICAMENT med = new MEDICAMENT (rs.getInt("NUM_MED"),  rs.getString("NOM_MED"), rs.getString("DOSAGE"));
	    	            resultSearch.add(med);
		            }
		        
		 } catch (SQLException e) {
 	        e.printStackTrace();
 	    }
 	    return resultSearch;
 	}
    
}