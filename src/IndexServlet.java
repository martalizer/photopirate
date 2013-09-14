import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public class IndexServlet extends HttpServlet
{
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		String bilderString = "";
		String imageDirectory = "/opt/tomcat7/webapps/photopirate/bilder/";
				
		Connection conn;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			String sql = "SELECT id, username, filename FROM photopirate";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
			{
				// int id = rs.getInt("id");
				String username = rs.getString("username");

				String filnamn = rs.getString("filename").substring(imageDirectory.toString().length());
				
				String image = "<a href='/photopirate/view?file=" + filnamn + "'>" + "\r\n\t\t\t<img class='preview' src='/photopirate/image?file=" + filnamn + "&type=thumb'></a>";
											
				String imageelement = "\n\r\t\t<div class='previewBox'>\n\r\t\t\t" + image + "<br/>" + username + " \n\r\t\t</div>";
				
				
				bilderString += imageelement; 
				
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.setAttribute("bilder", bilderString);		
		req.setAttribute("content", "<a href='upload.html'>Upload image</a>");
			
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
		
	}
}
