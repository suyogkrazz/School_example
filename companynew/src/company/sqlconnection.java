package company;
import java.sql.Connection;
import java.sql.DriverManager;

public class sqlconnection {
	 static Connection con=null;
	public static  Connection dbConnector(){

		try {
			Class.forName("com.mysql.jdbc.Driver");
		    String url ="jdbc:mysql://localhost:3306/testdb";
		    con = DriverManager.getConnection(url,"root","");
		    System.out.println("connection Established");
		    return con;
//			PreparedStatement st = con.prepareStatement("select * from EmployeeInfo");
//			   ResultSet rs = st.executeQuery();
//			  while(rs.next()) { 
//				 String str1 = rs.getString(1); 
//				 String str2 = rs.getString(2);
//				 System.out.println(str1+str2);
//				}
		    }
		    catch(Exception e) {
		                System.out.println("Couldnt get connection");
		            	return null;
		    }
		    
	
		
	}
	
	
}
