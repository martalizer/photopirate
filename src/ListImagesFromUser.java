import java.util.List;

import javax.servlet.http.HttpServlet;

public class ListImagesFromUser extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		String userFromURL = req.getPathInfo().substring(1);
		
		List<Image> images = ImageDAO.getImagesRandomFromUser(userFromURL);
		req.setAttribute("bilder", images);			
		
		String currentUserFromSession = (String) req.getSession().getAttribute("user");

		if (userFromURL.equals(currentUserFromSession)) {
			req.setAttribute("pageinfo", " - " + userFromURL);
			req.setAttribute(
					"pageinfo2",
					MenuManager.message("Share this page with others if you like!")
							+ MenuManager.message("It will display all your uploaded images sorted randomly"));
		} else {
			req.setAttribute("pageinfo", " - " + userFromURL);
		}

		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
