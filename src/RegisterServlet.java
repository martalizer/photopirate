import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet
{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		boolean sucess = false;
	//	boolean nonzero = false;
		String svar = "wtf";
		
		String user = req.getParameter("username");
		String password = req.getParameter("password");
		
		if ((user+password).isEmpty())
			sucess = false;
		else
			sucess = true;
			
		
		
		if(!sucess) {		
			svar ="<div>fail, empty password or username</div>";

		}	
		else {	
			Connection conn;
			try
			{
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");
	
				Statement stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO users (username, password) VALUES ('"+user+"', '"+password+"')");
		
				svar ="<div>User: " + user + " registered successfully</div>";
				
				stmt.close();
				conn.close();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		resp.getOutputStream().print(svar);
	}	
}
