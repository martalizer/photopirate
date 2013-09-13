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

				// String image = "<a href='/photopirate/bilder/" + filnamn + "'>" + "<img width=10% src='/photopirate/bilder/" + filnamn + "'/></a>";
				
				String image = "<a href='/photopirate/image?file=" + filnamn + "&type=full'>" + "<img src='/photopirate/image?file=" + filnamn + "&type=thumb'></a>";
				
				// http://martalizer.se/photopirate/image?file=a62c8047-4612-4a9e-8edb-200cd58b6bb1.jpg&type=thumb
					
				bilderString += image += username; 
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
