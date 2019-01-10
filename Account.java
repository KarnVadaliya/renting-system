package sen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Account {
    
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    
    public void viewprofile(String z) throws ClassNotFoundException, SQLException
    {
    	System.out.println("YOUR PROFILE");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("select distinct * from userdb natural join accountdb where Account_no='"+z+"'");
		ResultSet result = statement.executeQuery();
		result.next();
		System.out.println("Name: "+result.getString(2));
		System.out.println("Username: "+result.getString(3));
		System.out.println("Email id: "+result.getString(4));
		System.out.println("Mobile no: "+result.getString(5));
		System.out.println("Address: "+result.getString(6));
		System.out.println("type: "+result.getString(7));
		System.out.println("validity: "+result.getString(8));
		System.out.println();
		System.out.println();
		return;
    }
    
	public void editaccount(int ac) throws ClassNotFoundException, SQLException, NumberFormatException, IOException {
		// TODO Auto-generated method stub
		int flag=0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
		while(true)
		{
		System.out.println("Enter 1 to edit name");
		System.out.println("Enter 2 to edit username");
		System.out.println("Enter 3 to edit password");
		System.out.println("Enter 4 to edit mobile_no");
		System.out.println("Enter 5 to edit address");
		System.out.println("Enter 6 to save and exit");
		int x=Integer.parseInt(bf.readLine());
		String temp;
		switch(x)
		{
		case 1:System.out.println("Enter new details");
			temp=bf.readLine();
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("update userdb set Name='"+temp+"' where Account_no='"+ac+"'");
			statement.execute();
			break;
		case 2:System.out.println("Enter new details");
			temp=bf.readLine();
			PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("update userdb set User_name='"+temp+"' where Account_no='"+ac+"'");
			statement2.execute();
			break;
		case 3:System.out.println("Enter new details");
			temp=bf.readLine();
			PreparedStatement statement3 = (PreparedStatement) con.prepareStatement("update accountdb set password='"+temp+"' where Account_no='"+ac+"'");
			statement3.execute();
			break;
		case 4:System.out.println("Enter new details");
			temp=bf.readLine();
			PreparedStatement statement4 = (PreparedStatement) con.prepareStatement("update userdb set Mobile_no='"+temp+"' where Account_no='"+ac+"'");
			statement4.execute();
			break;
		case 5:System.out.println("Enter new details");
			temp=bf.readLine();
			PreparedStatement statement5 = (PreparedStatement) con.prepareStatement("update userdb set Address='"+temp+"' where Account_no='"+ac+"'");
			statement5.execute();
			break;
		case 6:
			flag=1;
			System.out.println("All changes saved.");
			break;		
		}
		if(flag==1)
			break;
	}
		return;
	}
	public void upgradeaccount(int ac) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("redirecting to payment page....");
		System.out.println("Payment Successful !!");
		String p="premium";
		String q="1year";
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("update accountdb set type='"+p+"' where Account_no='"+ac+"'");
		statement.execute();
		PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("update accountdb set validity='"+q+"' where Account_no='"+ac+"'");
		statement2.execute();

		System.out.println("Account converted to Premium.");
		return;
	}
	public void deleteaccount(int ac) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("delete from userdb where Account_no='"+ac+"'");
		statement.execute();
		PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("delete from accountdb where Account_no='"+ac+"'");
		statement2.execute();
		System.out.println("Account deleted successfully !!");
	}
	
}
