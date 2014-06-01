import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;



public class LoginServlet extends HttpServlet {

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
	    	//resp.sendRedirect("index.html");
	    	resp.sendRedirect("mypage");

	    }
	    else {
			String svar ="<div>fail, login incorrect, wrong password or username</div>";
			resp.getOutputStream().print(svar);
		}
	}	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{ 
		String code = req.getParameter("code");
		
		OAuthService service = new ServiceBuilder()
				.provider(FacebookApi.class)
				.apiKey("748812261795853")
				.apiSecret("8ffd2e5855ff88f4581a1d6a7933df3f")
				.callback("http://martalizer.se/login")
				.build();	    		
						
	    //OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/");
	    OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me?fields=id,first_name,last_name");
	    
	    //resp.getOutputStream().print(request.toString());
	    		
	    		
		Token accessToken = service.getAccessToken(null, new Verifier(code));
	    service.signRequest(accessToken, request);

	    Response response = request.send();

	    ObjectMapper map = new ObjectMapper();
		JsonNode node = map.readTree(response.getBody());
		String id = node.get("id").asText();
		String firstName = node.get("first_name").asText();
		String lastName = node.get("last_name").asText();
	    
	    
	    //resp.getOutputStream().print(id + firstName + lastName);
	    //resp.getOutputStream().print("\n"+response.getBody());
	    
		
		String user = id;
		String password = "";
				
		boolean success = false;
		String svar = "";
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
	    	//resp.sendRedirect("index.html");
	    	resp.sendRedirect("mypage");

	    }
	    else {
			svar ="<div>fail, login incorrect, wrong password or username</div>";
		}
	}	
}
