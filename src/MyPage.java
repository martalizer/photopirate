import javax.servlet.http.HttpServlet;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

public class MyPage extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
		
		String user = (String) req.getSession().getAttribute("user");
		String bilderString = ImageDAO.getImages(user);
		
		if (req.getSession().getAttribute("user") == null) {
			resp.sendRedirect("/");
		} else {
			req.setAttribute("bilder", bilderString);
			req.setAttribute(
					"content",
					"<a href='mypage'>Home</a> | <a href='upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");
			req.getRequestDispatcher("/index2.jsp").forward(req, resp);
		}
	}
}