package sen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;



public class Ad {
	
	
	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
	public void addnewproduct(String s,String z) throws IOException, ClassNotFoundException, SQLException
	    {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add("Electronics");
		arr.add("Furniture");
		arr.add("Books");
		arr.add("Clothing");
		arr.add("Movies and music");
		arr.add("Footwear");
		arr.add("others");
	    	String type,name,priority="normal",description,price,condition,negotiable,city, pincode;
	    	
	    	int totalviews=0;
	    	if(z.equals("normal"))
	    		priority="normal";
	    	else if(z.equals("premium"))
	    		priority="high";
		 	System.out.println("Select your product type");
	    	System.out.println("1 Electronics");
	    	System.out.println("2 Furniture");
	    	System.out.println("3 Books");
	    	System.out.println("4 Clothing");
	    	System.out.println("5 Movies and music");
	    	System.out.println("6 Footwear");
	    	System.out.println("7 others");
	    	int x=Integer.parseInt(bf.readLine());
	    	type = arr.get(x-1);
	    	System.out.println("Enter your Product name :");
	    	name=bf.readLine();
	    	System.out.println("Enter product description");
	    	description=bf.readLine();
	    	System.out.println("Enter price: ");
	    	price =bf.readLine();
	    	System.out.println("Enter condition: old or new ? ");
	    	condition=bf.readLine();
	    	System.out.println("Negotiable: yes or no ? ");
	    	negotiable = bf.readLine();
	    	System.out.println("Enter your city");
	    	city=bf.readLine();
	    	System.out.println("Enter your pincode");
	    	pincode=bf.readLine();
	    	Class.forName("com.mysql.jdbc.Driver");
	    	Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
	    	PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("select * from addb order by product_id desc");
	    	ResultSet result = statement2.executeQuery();
	    	result.next();
	    	long f=result.getLong(2)+5;
	    	String query = " insert into addb (product_id,product_name,description,price,pcondition,type,negotiable,seller_id,priority,city,pincode,totalviews)"
			        + " values (?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
			statement.setLong(1, f);
			statement.setString(2, name);
			statement.setString(3,description);
			statement.setString(4,price);
			statement.setString(5, condition);
			statement.setString(6, type);
			statement.setString(7, negotiable);
			statement.setString(8, s);
			statement.setString(9, priority);
			statement.setString(10, city);
			statement.setString(11, pincode);
			statement.setLong(12, totalviews);
			statement.execute();
			System.out.println("Your ad has been posted !");
	    	
	    }
	    public void editad(String seller_id) throws ClassNotFoundException, SQLException, NumberFormatException, IOException
	    {
	    	int flag=0;
	    	Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("select distinct * from addb where seller_id='"+seller_id+"' ");
			ResultSet result = statement.executeQuery();
			System.out.println("All your ads:");
			System.out.println();
			while(result.next())
			{
				flag=1;
				System.out.println("Ad_id: "+result.getInt(1));
				System.out.println("Product_type: " +result.getString(7));
				System.out.println("Product_name: "+result.getString(3));
				System.out.println("Product_description: "+result.getString(4));
				System.out.println("Product_price: "+result.getInt(5));
				System.out.println("Product_pcondition: "+result.getString(6));
				System.out.println("Negotiability: "+result.getString(8));
				System.out.println("Priority: "+result.getString(10));
				System.out.println("Product_totalviews: "+result.getInt(13));
				System.out.println("***********************************************************************************");
			}
			if(flag==0)
			{
				System.out.println("Sorry you have no ads to edit");
				return;
			}
			System.out.println("Select the Ad id you want to edit");
			String temp;
			int x=Integer.parseInt(bf.readLine());
			while(true)
			{
				System.out.println("Enter 1 to edit name");
				System.out.println("Enter 2 to edit type");
				System.out.println("Enter 3 to edit description");
				System.out.println("Enter 4 to edit price");
				System.out.println("Enter 5 to edit condition");
				System.out.println("Enter 6 to edit negotiability");
				System.out.println("Enter 7 to save all changes and return");
				int y = Integer.parseInt(bf.readLine());
				if(y==7)
					break;
				System.out.println("Enter new input :");
				temp=bf.readLine();
				switch(y)
				{
				case 1: 
						PreparedStatement statement3 = (PreparedStatement) con.prepareStatement("update addb set product_name='"+temp+"' where ad_id='"+x+"' and seller_id='"+seller_id+"' ");
						statement3.execute();
				break;
				case 2:
					PreparedStatement statement4 = (PreparedStatement) con.prepareStatement("update addb set type='"+temp+"' where ad_id='"+x+"' and seller_id='"+seller_id+"' ");
					statement4.execute();
					break;
				case 3:
					PreparedStatement statement5 = (PreparedStatement) con.prepareStatement("update addb set description='"+temp+"' where ad_id='"+x+"' and seller_id='"+seller_id+"'");
					statement5.execute();
					break;
				case 4:
					PreparedStatement statement6 = (PreparedStatement) con.prepareStatement("update addb set price='"+temp+"' where ad_id='"+x+"'and seller_id='"+seller_id+"' ");
					statement6.execute();
					break;
				case 5:
					PreparedStatement statement7 = (PreparedStatement) con.prepareStatement("update addb set pcondition='"+temp+"' where ad_id='"+x+"' and seller_id='"+seller_id+"' ");
					statement7.execute();
					break;
				case 6:
					PreparedStatement statement8 = (PreparedStatement) con.prepareStatement("update addb set negotiable='"+temp+"' where ad_id='"+x+"' and seller_id='"+seller_id+"' ");
					statement8.execute();
					break;
					default:
						break;
				}
				
				
			}
			return;
			
			
	    }
	    public void deletead(String seller_id) throws SQLException, ClassNotFoundException, NumberFormatException, IOException
	    {
	    	int flag=0;
	    	Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from addb where seller_id='"+seller_id+"' ");
			ResultSet result = statement.executeQuery();
			System.out.println("All your ads:");
			
			while(result.next())
			{
				flag=1;
				System.out.println("Ad_id: "+result.getInt(1));
				System.out.println("Product_type: " +result.getString(7));
				System.out.println("Product_name: "+result.getString(3));
				System.out.println("Product_description: "+result.getString(4));
				System.out.println("Product_price: "+result.getInt(5));
				System.out.println("Product_condition: "+result.getString(6));
				System.out.println("Negotiability: "+result.getString(8));
				System.out.println("Priority: "+result.getString(10));
				System.out.println("Product_totalviews: "+result.getInt(13));
				System.out.println("***********************************************************************************");
			}
			if(flag==0)
			{
				System.out.println("Sorry you have no ads to delete");
				return;
			}
			System.out.println("Select the Ad id you want to delete");
			int x=Integer.parseInt(bf.readLine());
			PreparedStatement statement2 = (PreparedStatement) con.prepareStatement("delete from addb where ad_id='"+x+"'and seller_id='"+seller_id+"' ");
			statement2.execute();
			System.out.println("Ad successfully deleted");
			return;
	    }
		public void viewad(String seller_id) throws SQLException, ClassNotFoundException {
			// TODO Auto-generated method stub
			int i=1,flag=0;
	    	Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
			PreparedStatement statement = (PreparedStatement) con.prepareStatement("select distinct * from addb where seller_id='"+seller_id+"' ");
			ResultSet result = statement.executeQuery();
			System.out.println("All your ads:");
			System.out.println();
			while(result.next())
			{
				flag=1;
				System.out.print(i+")");
				System.out.println("  Product_type: " +result.getString(7));
				System.out.println("    Product_name: "+result.getString(3));
				System.out.println("    Product_description: "+result.getString(4));
				System.out.println("    Product_price: "+result.getInt(5));
				System.out.println("    Product_condition: "+result.getString(6));
				System.out.println("    Negotiability: "+result.getString(8));
				System.out.println("    Priority: "+result.getString(10));
				System.out.println("    Product_totalviews: "+result.getInt(13));
				System.out.println("***********************************************************************************");
				i++;
			}
			if(flag==0)
			{
				System.out.println("Sorry you have no ads");
				return;
			}
		}
}
