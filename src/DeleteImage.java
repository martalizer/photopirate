import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;


public class DeleteImage extends HttpServlet {

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		String bilderString = "";
		
		String user = (String) req.getSession().getAttribute("user");
		
		Connection conn;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			
			String sql = "SELECT id, username, filename FROM photopirate where username='"+user+"' Order by id DESC";
			
			ResultSet rs = stmt.executeQuery(sql);
								
			while (rs.next())
			{
				String filnamn = rs.getString("filename");
				
				String id = rs.getString("id");
				
				String image = "<a href='deletethisimage?id=" + id + "'>\r\t\t\t<img src='/image?file=" + filnamn + "&type=thumb'></a>";
											
				String imageelement = "\n\r\t\t\n\r\t\t\t" + image + " \n\r\t\t<p>";
							
				bilderString += imageelement ; 				
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
		req.setAttribute("content", "<a href='upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");
		req.getRequestDispatcher("delete.jsp").forward(req, resp);
		
		
	}
	
	
}
