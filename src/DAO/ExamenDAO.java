package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import classes.EXAMEN;


public class ExamenDAO extends DAO<EXAMEN>{

	public ExamenDAO(Connection cnx) throws SQLException {
		super(cnx);
	}
	
	 @Override
	    public int create(EXAMEN examen) {
	        try
	        {
	            PreparedStatement stat = cnx.prepareStatement("INSERT INTO examen(DESCRIPTION,TAILLE,POIDS,TENSION,	TEMP,FREQ_CARD,IMC) VALUES(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS );
	            stat.setString(1,examen.getDESCRIPTION());
	            stat.setFloat(2,examen.getTAILLE());
	            stat.setFloat(3,examen.getPOIDS());
	            stat.setFloat(4,examen.getTENSION());
	            stat.setFloat(5,examen.getTEMP());
	            stat.setInt(6,examen.getFREQ_CARD());
	            stat.setFloat(7,examen.getIMC());
	
	           stat.execute();
	           
	        // Récupérer l'ID généré pour l'examen
	           ResultSet generatedKeys = stat.getGeneratedKeys();
	           if (generatedKeys.next()) {
	               int examenId = generatedKeys.getInt(1);
		            return examenId;

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
	            PreparedStatement preparedStmt = cnx.prepareStatement("DELETE FROM examen WHERE NUM_EXM=?");
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
		public boolean update(EXAMEN examen, int num) {
			try
	        {
	            PreparedStatement stat = cnx.prepareStatement("UPDATE examen SET DESCRIPTION=?,TAILLE=?,POIDS=?,TENSION=?,TEMP=?,FREQ_CARD=?,IMC=?  WHERE NUM_EXM=?");
	            stat.setString(1,examen.getDESCRIPTION());
	            stat.setFloat(2,examen.getTAILLE());
	            stat.setFloat(3,examen.getPOIDS());
	            stat.setFloat(4,examen.getTENSION());
	            stat.setFloat(5,examen.getTEMP());
	            stat.setInt(6,examen.getFREQ_CARD());
	            stat.setFloat(7,examen.getIMC());
	            stat.setInt(8, num);
	            stat.execute();
	            return true;
	        }
	        catch(SQLException e)
	        {
	            return false;
	        }
		}
  
}
