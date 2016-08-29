import java.sql.*;
import java.util.Scanner;

import javax.swing.*;

public class sqliteConnection {
	
	Connection conn=null;
	
	public static Connection dbConnector(){
		Scanner sc = new Scanner(System.in);
		
		try{
			Class.forName("org.sqlite.JDBC");
			
			//for testing uses
			System.out.println("C:\\\\Users\\\\1\\\\mdb-project\\\\SQLite\\\\TestTable.sqlite");
			
			//user input path to .sqlite file
			System.out.println("******\tInsert Path\t******");
			System.out.println("Note: use double \\\\ if directory error");
			System.out.println("Example path:C:\\Users\\PCName\\Documents\\filename.sqlite");
			System.out.print("Path to file:");
			String inputDB = "jdbc:sqlite:" + sc.next();
			Connection conn=DriverManager.getConnection(inputDB);
			JOptionPane.showMessageDialog(null, "Connection Succesful");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		
	}
	
}
