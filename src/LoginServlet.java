import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String user = req.getParameter("username");
		String password = req.getParameter("password");
		
		resp.getOutputStream().print(user + " " + password);
		
		int success = 0;
			
		if("martalizer".contains(user))
			success = 1;
		
/*		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO photopirate (username, filename) VALUES ('"+user+"', '" + filename.toString() + "')");

			resp.setContentType("TEXT/HTML");
			resp.getOutputStream().write("Image uploaded!".getBytes());		

			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */

		
	    if(success == 1)
	    {
	    	req.getSession().setAttribute("user", user);
	    	resp.sendRedirect("index.html");
	    }
		else
		{
			String svar ="fail, login incorrect, no user:" +user;
			resp.getOutputStream().print(svar);
		}

	}	
}
