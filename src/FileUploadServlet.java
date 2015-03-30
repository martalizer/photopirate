import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		if (req.getSession().getAttribute("user") == null) {
			resp.sendRedirect("/");
			return;
		}
		
		String user = (String) req.getSession().getAttribute("user");
		InputStream in = req.getPart("file").getInputStream();
		
		//Save image to Disk
		String generatedFileName = FileIO.saveImage(in);
		
		//Store image information in database		
		ImageDAO.addImage(new Image(user, generatedFileName));
				
		List<Image> images = ImageDAO.getImagesFromUser(user);
		req.setAttribute("bilder", images);		
				
		req.setAttribute("pageinfo2", MenuManager.message("Image Uploaded!"));
		req.setAttribute("content", MenuManager.getUserLoggedInMenu());
		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}
}
