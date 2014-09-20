import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		
		
		/*
		String bilderString = "";
		bilderString = ImageDAO.getImages();
		req.setAttribute("bilder", bilderString);
		*/
		
		String sql = "SELECT id, username, filename FROM photopirate ORDER BY RAND() LIMIT 1000";
						
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			List<Image> images = new ArrayList<>(); 
	
			while (rs.next()) {
				String username = rs.getString("username");
				String filename = rs.getString("filename");
				images.add(new Image(username, filename));			
			}
			rs.close();
			stmt.close();
			conn.close();
			req.setAttribute("bilder", images);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		
		
		

		if (req.getSession().getAttribute("user") == null) {
			req.setAttribute("content", menuManager.getNoUserMenu());
			req.setAttribute("content", "<a href='" + new Login().getLoginUrl() + "'><img src='login.png'></a>");
		} else
			req.setAttribute("content", menuManager.getUserLoggedInMenu());

		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
