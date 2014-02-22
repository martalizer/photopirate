import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public class IndexServlet extends HttpServlet {
	
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req,
			javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		String bilderString = "";

		bilderString = ImageDAO.getImages();

		req.setAttribute("bilder", bilderString);

		if (req.getSession().getAttribute("user") == null) {
			req.setAttribute("content",
					"<a href='/login.jsp'>Login</a> | <a href='/register.jsp'>Register User</a>");
		} else
			req.setAttribute(
					"content",
					"<a href='mypage'>Home</a> | <a href='/users'>List Users</a> | <a href='upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");

		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

}
