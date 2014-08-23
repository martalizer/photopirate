import javax.servlet.http.HttpServlet;


public class ListImagesFromUser extends HttpServlet {
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException {
		String user = req.getPathInfo().substring(1);
		String bilderString = "";				
		bilderString = ImageDAO.getImages(user);

		req.setAttribute("bilder", bilderString);	
		if (req.getSession().getAttribute("user") == null) {
			req.setAttribute("content",
					"<a href='/login.jsp'>Login</a> | <a href='/register.jsp'>Register User</a>");
		} else
			req.setAttribute(
					"content",
					"<a href='/upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");
	
		req.getRequestDispatcher("/index2.jsp").forward(req, resp);		
	}

}
