import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class DeleteImage extends HttpServlet {

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {

		if (req.getSession().getAttribute("user") == null) {
			resp.sendRedirect("/");
			return;
		}
		
		req.setAttribute("bilder", ImageDAO.getImagesForDelete((String)req.getSession().getAttribute("user")));		
		
		req.setAttribute("content", menuManager.getUserLoggedInMenu());
		req.getRequestDispatcher("delete.jsp").forward(req, resp);
	}
}
