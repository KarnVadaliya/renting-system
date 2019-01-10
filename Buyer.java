package sen;

import java.io.IOException;
import java.sql.SQLException;

public class Buyer extends User{
    
    public Buyer(String name,String username,String userid,String accountno,String mob, String add) {
    	super(name, username, userid, accountno, mob, add);
    }
    
	public void searchproduct(String temp, int k) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		Product pro =new Product();
		pro.search(temp, k,this.name1);
		
	}
}
