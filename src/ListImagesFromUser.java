import javax.servlet.http.HttpServlet;

public class ListImagesFromUser extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
		String userFromURL = req.getPathInfo().substring(1);
		String bilderString = ImageDAO.getImagesRandom(userFromURL);
		String currentUserFromSession = (String) req.getSession().getAttribute("user");
		req.setAttribute("bilder", bilderString);

		if (userFromURL.equals(currentUserFromSession)) {
			req.setAttribute("pageinfo", " - " + userFromURL);
			req.setAttribute(
					"pageinfo2",
					menuManager.message("Share this page with others if you like!")
							+ menuManager.message("It will display all your uploaded images sorted randomly"));
		} else {
			req.setAttribute("pageinfo", " - " + userFromURL);
		}

		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
