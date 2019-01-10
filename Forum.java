package sen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Forum {

	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	public void createnewpost(String string) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		String pdetails,city,pincode;
		int totallikes=0;
		System.out.println("Enter post details:");
    	pdetails=bf.readLine();
    	System.out.println("Enter your city");
    	city=bf.readLine();
    	System.out.println("Enter your pincode");
    	pincode=bf.readLine();
    	String query = " insert into forumdb (user_id,post_details,city,pincode,totallikes)"
		        + " values (?,?,?,?,?)";
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
		PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
		statement.setString(1, string);
		statement.setString(2, pdetails);
		statement.setString(3,city);
		statement.setString(4,pincode);
		statement.setLong(5, totallikes);
		statement.execute();
		return;
	}

	public void deletepost(String s) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		int flag=0;
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from forumdb where user_id='"+s+"' ");
		ResultSet result = statement.executeQuery();
		while(result.next())
		{
			flag=1;
			System.out.println("post id: "+result.getString(2));
			System.out.println("post_details: "+result.getString(3));
			System.out.println("*******************************************************************************");
		}
		if(flag==0)
		{
			System.out.println("You have no past threads");
			return;
		}
		System.out.println("Enter the post id of the post you want to delete ");
		int temp=Integer.parseInt(bf.readLine());
		PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("delete from forumdb where user_id='"+s+"'and post_id='"+temp+"' ");
		statement2.execute();
		System.out.println("Deleted successsfully");
		return;
		
		
	}

	public void seeallpost() throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		long total;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from forumdb");
		ResultSet result = statement.executeQuery();
		while(result.next())
		{
			System.out.println("post id: "+result.getString(2));
			System.out.println("posted by: "+result.getString(1));
			System.out.println("post_details: "+result.getString(3));
			System.out.println("post_likes: "+result.getString(6));
			System.out.println("*******************************************************************************");
			System.out.println();
		}
		System.out.println("Enter the post id of the post you want to like , press 0 if none ");
		String temp=bf.readLine();
		if(temp.equals("0"))
			return;
		PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("select * from forumdb where post_id='"+temp+"'");
		ResultSet result2 = statement2.executeQuery();
		result2.next();
		total=result2.getLong(6)+1;
		PreparedStatement statement3 = (PreparedStatement) con.prepareStatement("update forumdb set totallikes='"+total+"' where post_id='"+temp+"'");
		statement3.execute();
	}

}
