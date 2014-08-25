import javax.servlet.http.HttpServlet;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

public class MyPage extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		String user = (String) req.getSession().getAttribute("user");
		String bilderString = ImageDAO.getImages(user);

<<<<<<< HEAD
		if (user == null) {
=======
		if (req.getSession().getAttribute("user") == null) {
>>>>>>> ea7d31d5ce7168634460e6de39ab21d7b0c5c6fd
			resp.sendRedirect("/");
			return;
		} else {
			String message = String.format("<h2><a href='http://martalizer.se/user/%s'>martalizer.se/user/%s</a></h2>", user, user);
					
			req.setAttribute("pageinfo2", menuManager.message(message));	
			req.setAttribute("bilder", bilderString);
<<<<<<< HEAD
			req.setAttribute("content", menuManager.getUserLoggedInMenu());
=======
			req.setAttribute(
					"content",
					"<a href='mypage'>Home</a> | <a href='upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");
>>>>>>> ea7d31d5ce7168634460e6de39ab21d7b0c5c6fd
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
}