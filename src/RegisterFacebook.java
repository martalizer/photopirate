import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.codec.binary.Base64;

public class RegisterFacebook extends HttpServlet {

	public static String base64UrlDecode(String input) {
	    String result = null;
	    Base64 decoder = new Base64(true);
	    byte[] decodedBytes = decoder.decode(input);
	    result = new String(decodedBytes);
	    return result;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { 	
		String code = req.getParameter("signed_request");
				
		String[] delar = code.split("\\.");
				
		String json = base64UrlDecode(delar[1]);
				
	    ObjectMapper mapper = new ObjectMapper();
	    	
	    JsonNode node = mapper.readTree(json);		
		
		String user = node.get("user_id").asText();
		String password = "";
				
		
		boolean success = false;
		String svar = "wtf, user exists or random error";
		
		
		if (user.isEmpty())
		{
			success = false;
		}
		else
			success = true;
					
		if(!success) {		
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
		
		req.setAttribute("bilder", svar);	

		if (req.getSession().getAttribute("user") == null) {
			req.setAttribute("content",
					"<a href='/login.jsp'>Login</a> | <a href='/register.jsp'>Register User</a><p><a href='"+new Login().getLoginUrl()+"'><img src='login.png'></a>");
		} else
			req.setAttribute(
					"content",
					"<a href='mypage'>Home</a> | <a href='/users'>List Users</a> | <a href='upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");

		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
