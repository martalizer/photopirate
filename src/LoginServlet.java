import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
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
				
		boolean success = false;
		
		Connection conn;
		try		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM users WHERE password = '" + password + "' AND username = '" + user + "'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())	{
				success = true;
			}

			rs.close();			stmt.close();			conn.close();
		} catch (Exception e)		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    if(success) {
	    	req.getSession().setAttribute("user", user);
	    	resp.sendRedirect("index.html");
	    }
	    else {
			String svar ="<div>fail, login incorrect, wrong password or username</div>";
			resp.getOutputStream().print(svar);
		}
	}	
}
