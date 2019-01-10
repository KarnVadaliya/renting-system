package sen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Product {
    private int productID,ownerID,price,quantity,rating,condition;
    private String productName,status,availabilityDate; 
   
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
   
	public void search(String temp,int k,String buyerName) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		int flag=0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
		if(k==1)
		{
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from addb where type='"+temp+"' ");
		ResultSet result = statement.executeQuery();
		System.out.println("Items matching your search : \n");
		while(result.next())
		{
			flag=1;
			System.out.println("Product_id: "+result.getString(2));
			System.out.println("Product_name: "+result.getString(3));
			System.out.println("Product_description: "+result.getString(4));
			System.out.println("Product_price: "+result.getInt(5));
			System.out.println("Product_condition: "+result.getString(6));
			System.out.println("Negotiability: "+result.getString(8));
			System.out.println();
			System.out.println();
			System.out.println("*******************************************************************************************");
		}
		}
		else if (k==2)
		{
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from addb where product_name='"+temp+"' ");
			ResultSet result = statement.executeQuery();
			System.out.println("Items matching your search : \n");
			while(result.next())
			{
				flag=1;
				System.out.println("Product_id: "+result.getString(2));
				System.out.println("Product_name: "+result.getString(3));
				System.out.println("Product_description: "+result.getString(4));
				System.out.println("Product_price: "+result.getInt(5));
				System.out.println("Product_condition: "+result.getString(6));
				System.out.println("Negotiability: "+result.getString(8));
				System.out.println();
				System.out.println("*******************************************************************************************");
			}
			
		}
		if(flag==1)
		{
		System.out.println("Enter the product id of your desired product");
		int x=Integer.parseInt(bf.readLine());
		PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from addb where product_id='"+x+"' ");
		ResultSet result = statement.executeQuery();
		result.next();
		String seller=result.getString(9);
		String pname=result.getString(3);
		int totalviews=result.getInt(13)+1;
		PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("update addb set totalviews='"+totalviews+"' where product_id='"+x+"'");
		statement2.execute();
		System.out.println();
		System.out.println("(a) Rent the product \t\t\t (b) Request details of Owner \t\t\t (c) to return \t");
		System.out.println("Enter a or b or c");
		String d=bf.readLine();
		if(d.equals("a"))
		{
			System.out.println("Proceed to payment");
			System.out.println("Payment successful !!");
		}
		else if (d.equals("b"))
		{
			
			PreparedStatement statement3 = (PreparedStatement) con.prepareStatement("select distinct * from userdb where user_id='"+seller+"' ");
			ResultSet result1 = statement3.executeQuery();
			//make a new table in database named "requests"
			//			table - requests
			// 			coloumn 1 - from
			//			coloumn 2 - to
			result1.next();
			PreparedStatement statement4 = (PreparedStatement) con.prepareStatement("insert into requestdb values('"+buyerName+"','"+result1.getString(1)+"','"+pname+"')");
			statement4.execute();
			System.out.println("request sent");
		}
		else
			return;
		}
		else
			System.out.println("None");
		return;
		
	}
}
