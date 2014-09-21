import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

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
			String sql = "UPDATE photopirate SET inactive = 1 WHERE id=" + ImageId;
			stmt.executeUpdate(sql);

			messageString = "Image with ID=" + ImageId + " was removed from database";
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		List<Image> images = ImageDAO.getImagesFromUser(req.getSession().getAttribute("user").toString());
		req.setAttribute("bilder", images);	
		req.setAttribute("message", messageString);	
		req.setAttribute("content", menuManager.getUserLoggedInMenu());
		req.getRequestDispatcher("delete.jsp").forward(req, resp);
	}
}
