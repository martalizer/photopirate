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
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JsonNode node = facebookMagic(req);
				
		String facebookId = node.get("id").asText();
		String firstName = node.get("first_name").asText();
		String lastName = node.get("last_name").asText();

		String userName = "";
		boolean success = false;
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");
			Statement stmt = conn.createStatement();
			String sql = String.format("SELECT * FROM facebookusers WHERE facebookid = '%s'", facebookId);
			ResultSet rs = stmt.executeQuery(sql);
	
			int userID = 0;
			if(rs.next()) {
				success = true;
				userID = rs.getInt(1);
				rs.close();
			}	
			else {
				stmt = conn.createStatement();
				sql = String.format("INSERT INTO facebookusers (facebookid) VALUES ('%s')", facebookId);
				stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
							
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				
				if(generatedKeys.next()) {
					stmt = conn.createStatement();
					userID = generatedKeys.getInt(1);
					sql = String.format("INSERT INTO users (id, username, firstname, lastname) VALUES ('%s', '%s', '%s', '%s')", userID, (firstName+lastName), firstName, lastName);
					stmt.executeUpdate(sql);
					success = true;
				}								
			}
						
			System.out.println("userID = " + userID);
			
			rs.close();
			
			sql = String.format("SELECT * FROM users WHERE ID = '%s'", userID);
			rs = stmt.executeQuery(sql);	
			
			if(rs.next()) {
				userName = rs.getString("username"); 
			}
			else {
				userName = "-1";
				success = false;
			}
			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (success) {
			req.getSession().setAttribute("user", userName);
			resp.sendRedirect("mypage");

		} else {
			resp.sendRedirect("/");
		}
	}

	private JsonNode facebookMagic(HttpServletRequest req) throws IOException, JsonProcessingException {
		String code = req.getParameter("code");

		OAuthService service = new ServiceBuilder().provider(FacebookApi.class).apiKey("748812261795853")
				.apiSecret("8ffd2e5855ff88f4581a1d6a7933df3f").callback("http://martalizer.se/login").build();

		OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me?fields=id,first_name,last_name");

		Token accessToken = service.getAccessToken(null, new Verifier(code));
		service.signRequest(accessToken, request);

		Response response = request.send();

		ObjectMapper map = new ObjectMapper();
		JsonNode node = map.readTree(response.getBody());
		return node;
	}
}
