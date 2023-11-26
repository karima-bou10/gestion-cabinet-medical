package DAO;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.RDV;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RDVDAO extends DAO<RDV>{

	public RDVDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(RDV rdv) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO rdv(DESCRIPTION,DATE_RDV,HEURE_RDVE) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setString(1,rdv.getDESCRIPTION());
	            stat.setDate(2,(Date) rdv.getDATE_RDV());
	            stat.setTime(3,rdv.getHEURE_RDV());
	       
	           stat.execute();
	           
	        // Récupérer l'ID généré pour le rdv
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int rdvId = generatedKeys.getInt(1);
		            return rdvId;

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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM rdv WHERE NUM_RDV=?");
	            preparedStmt.setInt(1,num);
	            preparedStmt.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }
	 
	    public ObservableList<RDV> list()
	    {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM rdv");
	            ResultSet rs = stat.executeQuery();
	            ObservableList<RDV> rdvList = FXCollections.observableArrayList();;
	            while(rs.next())
	            {
	            	rdvList.add(new RDV(rs.getInt(1),rs.getString(2),rs.getDate(3),rs.getTime(4)));  
	            }
	            return rdvList;
	        }
	        catch(SQLException e)
	        {
	            return null;
	        }
	    }
  
	    public int nombreRdv()
	    {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT COUNT(*) FROM rdv");
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
		public boolean update(RDV rdv, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE rdv SET DESCRIPTION=?,DATE_RDV=?,HEURE_RDVE=?  WHERE NUM_RDV=?");
	            stat.setString(1, rdv.getDESCRIPTION());
	            stat.setDate(2, (Date) rdv.getDATE_RDV());
	            stat.setTime(3, rdv.getHEURE_RDV());	           
	            stat.setInt(4, num);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
    
}