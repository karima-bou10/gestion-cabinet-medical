package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public abstract class DAO<T> {
	private static final String dbURL = "jdbc:mysql://localhost:3306/gestion_cabinet";
	private static final String username="root";
	private static final String password="1234";
    public static Connection cnx;
    
    //Constructor
    public DAO(Connection cnx) {
        DAO.cnx = cnx;
    }
    
    static {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
    		cnx = DriverManager.getConnection(dbURL, username, password);
        } 
        catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
            System.out.println("connection failed !");
		}
    }
  //les methodes crud  
    public abstract int create(T obj);
    public abstract boolean delete(int id);
    public abstract boolean update(T obj,int id);
    //public abstract ObservableList<T> list();

}
