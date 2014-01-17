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
				
		Connection conn;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			String sql = "SELECT id, username, filename FROM photopirate";

			ResultSet rs = stmt.executeQuery(sql);
					
			while (rs.next()){};
			
			while (rs.previous())
			{
				String username = rs.getString("username");

				String filnamn = rs.getString("filename");
				
				String image = "<a title='"+ username +"' href='/bilder/" + filnamn + "' rel='bild'>" + "\r\n\t\t\t<img alt='" + username + " 'class='preview' src='/image?file=" + filnamn + "&type=thumb'></a>";
											
				String imageelement = "\n\r\t\t\n\r\t\t\t" + image + " \n\r\t\t";
							
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
		req.setAttribute("content", "<a href='upload.html'>Upload image</a> | <a href='/deleteimagelist'>Delete image</a>");		
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		
		
	}
}
