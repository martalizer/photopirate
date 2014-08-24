import javax.servlet.http.HttpServlet;

import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		String bilderString = "";

		bilderString = ImageDAO.getImages();

		req.setAttribute("bilder", bilderString);

		if (req.getSession().getAttribute("user") == null) {
			req.setAttribute("content", "<a href='" + new Login().getLoginUrl() + "'><img src='login.png'></a>");
		} else
			req.setAttribute(
					"content",
					"<a href='mypage'>Home</a> | <a href='upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");

		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
