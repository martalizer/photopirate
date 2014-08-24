import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class DeleteImage extends HttpServlet {

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		String bilderString = "";		
		String user = (String) req.getSession().getAttribute("user");

		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();

			String sql = "SELECT id, username, filename FROM photopirate where username='" + user
					+ "' Order by id DESC"; 

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String filnamn = rs.getString("filename");
				String id = rs.getString("id");				
				
				String image = "\r\t\t\t<a href=/image?file="+ filnamn +"&type=medium rel='bild'><img src='/image?file=" + filnamn
						+ "&type=thumb'></a><a class= 'deletebutton' href='deletethisimage?id=" + id + "'>Delete Image</a>";
				String imageelement = "\n\r\t\t\n\r\t\t\t" + image + " \n\r\t\t<p>";
				bilderString += imageelement;
			}

			rs.close(); stmt.close(); conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.setAttribute("bilder", bilderString);		
		if (req.getSession().getAttribute("user") == null) {
			resp.sendRedirect("/");
		} 
		else {
			req.setAttribute("content",	menuManager.getUserLoggedInMenu());
			req.getRequestDispatcher("delete.jsp").forward(req, resp);
		}
	}	
}
