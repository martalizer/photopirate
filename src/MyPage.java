import java.util.List;

import javax.servlet.http.HttpServlet;

public class MyPage extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		String user = (String) req.getSession().getAttribute("user");

		if (user == null) {
			resp.sendRedirect("/");
			return;
		} else {
			List<Image> images = ImageDAO.getImagesFromUser(user);
			req.setAttribute("bilder", images);				
			
			String message = String.format("<h2><a href='http://martalizer.se/user/%s'>martalizer.se/user/%s</a></h2>",
					user, user);
			req.setAttribute("pageinfo2", MenuManager.message(message));
			req.setAttribute("content", MenuManager.getUserLoggedInMenu());
			req.getRequestDispatcher("/index.jsp").forward(req, resp);
		}
	}
}