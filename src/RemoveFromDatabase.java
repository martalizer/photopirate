import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public class RemoveFromDatabase extends HttpServlet {

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		
		if (req.getSession().getAttribute("user") == null) {
			resp.sendRedirect("/");
			return;
		}
		
		String messageString = "";
		Integer ImageId = Integer.parseInt(req.getParameter("id"));
		
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM photopirate WHERE id=" + ImageId;
			stmt.executeUpdate(sql);

			messageString = "Image with ID=" + ImageId + " was removed from database";

			stmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.setAttribute("bilder", messageString);
		req.setAttribute("content",
				"<a href='upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");
		req.getRequestDispatcher("delete.jsp").forward(req, resp);
	}
}
