package sen;

import java.io.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
public class Starter {

   
    
    public static void main(String[] args) throws Exception{
            // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("******************    Welcome to Rent-It   ******************");
        String userID;
        int input;
        while(true)
        {
        	System.out.println();
            System.out.println("Enter :");
            System.out.println("1 to login" );
            System.out.println("2 to register");
            System.out.println("3 to exit");
            input = Integer.parseInt(bf.readLine());
            
            if(input==1)
            {
                String user,pass;
                System.out.println("Enter Email-id :");
                user = bf.readLine();
                System.out.println("Enter password :");
                pass = bf.readLine();
                Class.forName("com.mysql.jdbc.Driver");
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
				PreparedStatement statement = (PreparedStatement) con.prepareStatement("select distinct * from userdb natural join accountdb where User_id='"+user+"' and password='"+pass+"'");
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					while(true)
					{
					System.out.println();
					System.out.println();
					System.out.println("*****  Welcome to the Rent-it, "+result.getString(2)+" !!   *****");
					System.out.println("Enter :");
		            System.out.println("1 to view profile");
		            System.out.println("2 to view requests");
		            System.out.println("3 to post ad/edit ad/delete ad");
		            System.out.println("4 to search a product");
		            System.out.println("5 to enter forum");
		            System.out.println("6 to logout");
		            int x=Integer.parseInt(bf.readLine());
		            switch(x)
		            {
		            case 1:
		            	Account acc = new Account();
		            	acc.viewprofile(result.getString(1));
		            	System.out.println("Enter 1 to Edit account");
		            	System.out.println("Enter 2 to Delete account");
		            	System.out.println("Enter 3 to upgrade to premium");
		            	System.out.println("Enter 4 to go back");
		            	int y=Integer.parseInt(bf.readLine());
		            	switch(y)
		            	{
		            	case 1:
		            		acc.editaccount(result.getInt(1));
		            		break;
		            	case 2:
		            		acc.deleteaccount(result.getInt(1));
		            		x=6;
		            		break;
		            	case 3:
		            		if(result.getString(7).equals("normal"))
		            		acc.upgradeaccount(result.getInt(1));
		            		else
		            		System.out.println("Cannot proceed your request");
		            		break;
		            		default:
		            			break;
		            	}
		            	break;
		            case 2:
		            	Seller sel2 = new Seller(result.getString(2),result.getString(3),result.getString(4),result.getString(1),result.getString(5),result.getString(6));
		            	sel2.viewRequests();
		            	break;
		            case 3:	
		            	
		            	Seller sel = new Seller(result.getString(2),result.getString(3),result.getString(4),result.getString(1),result.getString(5),result.getString(6));
		            	Ad ad = new Ad();
		            	
		            	System.out.println("1 to post ad");
		            	System.out.println("2 to edit ad");
		            	System.out.println("3 to delete ad");
		            	System.out.println("4 to view your past ad");
		            	switch(bf.readLine())
		            	{
		            	case "1":
		            		sel.postad(sel.userid1,result.getString(7));
		            		break;
		            	case "2":
		            		ad.editad(sel.userid1);
		            		break;
		            	case "3":
		            		ad.deletead(sel.userid1);
		            		break;
		            	case "4":
		            		ad.viewad(sel.userid1);
		            		break;
		            	default:
		            		break;
		            	}
		            	break;
		            case 4:
		            	System.out.println("1 for Shop by category");
		            	System.out.println("2 for Shop by Product-name");
		            	Buyer buy = new Buyer(result.getString(2),result.getString(3),result.getString(4),result.getString(1),result.getString(5),result.getString(6));
		            	int k=Integer.parseInt(bf.readLine());
		            	if(k==1)
		            	{
		            		ArrayList<String> arr = new ArrayList<String>();
		            		arr.add("Electronics");
		            		arr.add("Furniture");
		            		arr.add("Books");
		            		arr.add("Clothing");
		            		arr.add("Movies and music");
		            		arr.add("Footwear");
		            		arr.add("others");
		            		System.out.println("Select your Category");
		            		System.out.println("1 Electronics");
		    	    	System.out.println("2 Furniture");
		    	    	System.out.println("3 Books");
		    	    	System.out.println("4 Clothing");
		    	    	System.out.println("5 Movies and music");
		    	    	System.out.println("6 Footwear");
		    	    	System.out.println("7 others");
		            		int q=Integer.parseInt(bf.readLine());
		            		String temp=arr.get(q-1);
		            		buy.searchproduct(temp,k);
		            		
		            	}
		            	else if(k==2)
		            	{
		            		System.out.println("Enter Product name");
		            		String temp=bf.readLine();
		            		buy.searchproduct(temp,k);
		            	}
		            	System.out.println();
		            	break;
		            case 5:
		            	System.out.println("Welcome to Forum");
		            	System.out.println("Enter 1 to Post a new thread");
		            	System.out.println("Enter 2 to see all posts");
		            	System.out.println("Enter 3 to delete your thread");
		            	int g=Integer.parseInt(bf.readLine());
		            	Forum fo = new Forum();
		            	if(g==1)
		            	{
		            		fo.createnewpost(result.getString(4));
		            	}
		            	else if(g==2)
		            	{
		            		fo.seeallpost();
		            	}
		            	else if(g==3)
		            	{
		            		fo.deletepost(result.getString(4));
		            	}
		            	else
		            		break;
		            	
		            	break;
		            case 6:
		            	break;
		            	default:
		            		break;
		            }
		            if(x==6)
		            	break;
					}
				}
				else
				{
					System.out.println("Invalid Email or Password");
					System.out.println("1 to forgot username or password || 2 to try again");
					int x=Integer.parseInt(bf.readLine());
					if(x==1)
					{
						System.out.println("Enter your mobile number");
						bf.readLine();
						System.out.println("Password reset link sent to your Mobile");
					}
					else
					continue;
				}
			
            }
            else if(input==2)
            {
                String name,mob,add,type,u_name,pass;
                System.out.println("|| Register User ||");
                System.out.println("Enter name :");
                name=bf.readLine();
                System.out.println("Create user name :");
                u_name = bf.readLine(); 
                while(true)
                {
                System.out.println("Enter email id :");
                userID=bf.readLine();
                Class.forName("com.mysql.jdbc.Driver");
    			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
                PreparedStatement statement = (PreparedStatement) con.prepareStatement("select * from userdb where User_id='"+userID+"' ");
    			ResultSet result = statement.executeQuery();
    			if(!result.next())
    			break;
    			System.out.println("Sorry, this email is already registered enter different email");
                }
                System.out.println("Enter mobile number :");
                mob=bf.readLine();
                System.out.println("Enter address :");
                add=bf.readLine();
                System.out.println("Choose account type :\n 1 for normal\n2 for premium");
                if(Integer.parseInt(bf.readLine())==1)
                    type="normal";
                else
                    type="premium";
                String validity="normal";
				if(type.equals("normal"))
                	validity="forever";
                if(type.equals("premium"))
                	validity="1year";
                System.out.println("Enter password: ");
                pass=bf.readLine();
         
                Class.forName("com.mysql.jdbc.Driver");
    			Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/final","root","");
                String query = " insert into userdb (Name,User_name,User_id,Mobile_no,Address)"
    			        + " values (?,?,?,?,?)";
                
    			PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
    			statement.setString(1, name);
    			statement.setString(2, u_name);
    			statement.setString(3, userID);
    			//statement.setLong(4,accountNumber);
    			statement.setString(4,mob);
    			statement.setString(5, add);
    			statement.execute();
    			String query2 = " insert into accountdb (type,validity,password)"
    			        + " values (?,?,?)";
    			PreparedStatement statement2 = (PreparedStatement) con.prepareStatement(query2);
    			//statement2.setLong(1, accountNumber);
    			statement2.setString(1, type);
    			statement2.setString(2, validity);
    			statement2.setString(3,pass);
    			
    			statement2.execute();
    			System.out.println("Account successfully created !! Login");
            }
            else
                break;
        }

        bf.close();
    }

}
