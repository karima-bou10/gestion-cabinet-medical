package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.CNAM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CNAMDAO extends DAO<CNAM>{
	public CNAMDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(CNAM cnam) {
	        try
	        {    
	        	// Specify the option to return generated keys
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO cnam(DATE_VAL_CNAM,TYPE_CNAM) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setDate(1,(Date) cnam.getDATE_VAL_CNAM());
	            stat.setString(2,cnam.getTYPE_CNAM());
	           stat.execute();
	           
	        // Récupérer l'ID généré pour le CNAM
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int cnamId = generatedKeys.getInt(1);
		            return cnamId;
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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM cnam WHERE NUM_CNAM=?");
	            preparedStmt.setInt(1,num);
	            preparedStmt.execute();
	            System.out.println(num+"interieur de delate dao");
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
	    }
	 
	    public ObservableList<CNAM> list()
	    {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("SELECT NUM_CNAM, DATE_VAL_CNAM, TYPE_CNAM  FROM cnam");
	            ResultSet rs = stat.executeQuery();
	            ObservableList<CNAM> cnamList = FXCollections.observableArrayList();;
	            while(rs.next())
	            {
	            	cnamList.add(new CNAM(rs.getInt(1),rs.getDate(2),rs.getString(3)));  
	            }
	           // Collections.sort(listClients, Comparator.comparing(Client::getNomComplet));
	            return cnamList;
	        }
	        catch(SQLException e)
	        {
	            return null;
	        }
	    }

		@Override
		public boolean update(CNAM cnam, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE cnam SET DATE_VAL_CNAM=?, TYPE_CNAM=?  WHERE NUM_CNAM=?");
	            stat.setDate(1,(Date) cnam.getDATE_VAL_CNAM());
	            stat.setString(2,cnam.getTYPE_CNAM());
	            stat.setInt(3, num);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
		/*
		 	public CNAM find(int num) {
		        try
		        {
		            PreparedStatement stat = cnx.prepareStatement("SELECT * FROM cnam WHERE NUM_CNAM=?");
		            stat.setInt(1,num);
		            ResultSet rs = stat.executeQuery();
		            while(rs.next()) {
		                return new CNAM(num, rs.getDate("DATE_VAL_CNAM"), rs.getString("TYPE_CNAM"));
		            }
		        }
		        catch(SQLException e)
		        {
		            return new CNAM(num,null,"");
		        }
	            return new CNAM(num,null,"");
		    }
		 */
	
}
  

