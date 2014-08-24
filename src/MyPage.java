import javax.servlet.http.HttpServlet;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

public class MyPage extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		String user = (String) req.getSession().getAttribute("user");
		String bilderString = ImageDAO.getImages(user);

		if (user == null) {
			resp.sendRedirect("/");
			return;
		} else {
			String message = String.format("<h2><a href='http://martalizer.se/user/%s'>martalizer.se/user/%s</a></h2>", user, user);
					
			req.setAttribute("pageinfo2", menuManager.message(message));	
			req.setAttribute("bilder", bilderString);
			req.setAttribute("content", menuManager.getUserLoggedInMenu());
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
}