package kr.or.ddit.Test0623;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	static Properties prop;
	
	static {
		prop = new Properties();
		
		File f = new File("RE/dfd/fd/fdf/df/df/");
		
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f);
			prop.load(fin);
			
			Class.forName(prop.getProperty("driver"));			
			
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getConnection() {
		try {
			
			return DriverManager.getConnection(
					prop.getProperty("url"), 
					prop.getProperty("user"), 
					prop.getProperty("pass"));
					
			
			
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	
	
	
	
}
