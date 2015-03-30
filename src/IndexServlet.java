import java.util.List;

import javax.servlet.http.HttpServlet;

public class IndexServlet extends HttpServlet {

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {
	
		List<Image> images = ImageDAO.getImagesRandom();
		req.setAttribute("bilder", images);		
			
		if (req.getSession().getAttribute("user") == null) {
			req.setAttribute("content", MenuManager.getNoUserMenu());
			req.setAttribute("content", "<a href='" + new Login().getLoginUrl() + "'><img src='login.png'></a>");
		} else
			req.setAttribute("content", MenuManager.getUserLoggedInMenu());

		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
