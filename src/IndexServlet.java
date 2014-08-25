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
<<<<<<< HEAD
			req.setAttribute("content", menuManager.getNoUserMenu());
=======
			req.setAttribute("content", "<a href='" + new Login().getLoginUrl() + "'><img src='login.png'></a>");
>>>>>>> ea7d31d5ce7168634460e6de39ab21d7b0c5c6fd
		} else
			req.setAttribute("content", menuManager.getUserLoggedInMenu());

		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
