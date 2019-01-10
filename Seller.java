package sen;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Seller extends User {
		
	public Seller(String name, String username, String userid, String accountno, String mob, String add) {
		super(name, username, userid, accountno, mob, add);
		
	}

	public void postad(String s,String z) throws IOException, ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Ad ad = new Ad();
		ad.addnewproduct(s,z);
	}
	
	public void viewRequests() throws SQLException, ClassNotFoundException
	{
		//call this method from starter.java to view requests from buyers
		int flag=0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from requestdb where r_to='"+this.name1+"'");
		ResultSet result = statement.executeQuery();
		System.out.println("You have requests from :");
		int i=1;
		//check that the loop doesn't run infinitely or generate errors
		while(result.next())
		{
			flag=1;
			System.out.println("["+i+"] "+result.getString(1)+" for product "+result.getString(3));
			i++;
		}
		if(flag==0)
			System.out.println("None");
		return;
	}
	
}
