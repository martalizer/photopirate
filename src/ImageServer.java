import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class ImageServer extends HttpServlet {
	ThumbnailCreator thumb = new ThumbnailCreator();

	private void sendFile(HttpServletResponse resp, File file) throws IOException {
		resp.setContentLength((int) file.length());
		FileInputStream in = new FileInputStream(file);
		OutputStream out = resp.getOutputStream();
		byte[] buf = new byte[1024];
		int count = 0;
		while ((count = in.read(buf)) >= 0) {
			out.write(buf, 0, count);
		}
		out.close();
		in.close();
	}

	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws javax.servlet.ServletException, java.io.IOException {

		String imagename = req.getParameter("file");
		String type = req.getParameter("type");

		ServletContext cntx = getServletContext();
		// Get the absolute path of the image

		if (type.contains("thumb")) {
			String filename = cntx.getRealPath("/bilder/thumb_" + imagename);
			File file = new File(filename);

			resp.setContentType("image/jpeg");

			if (!file.exists()) {
				thumb.createThumbnail(cntx.getRealPath("/bilder/" + imagename),
						cntx.getRealPath("/bilder/thumb_" + imagename), 600);
			}

			if (!file.isFile()) {
				sendFile(resp, new File(cntx.getRealPath("/bilder/fail.jpg")));
			} else {
				sendFile(resp, file);
			}
		} else if (type.contains("medium")) {
			String filename = cntx.getRealPath("/bilder/medium_" + imagename);
			File file = new File(filename);

			resp.setContentType("image/jpeg");

			if (!file.exists()) {
				thumb.createThumbnail(cntx.getRealPath("/bilder/" + imagename),
						cntx.getRealPath("/bilder/medium_" + imagename), 2000);
			}

			if (!file.isFile()) {
				sendFile(resp, new File(cntx.getRealPath("/bilder/fail.jpg")));
			} else {
				sendFile(resp, file);
			}
		} else if (type.contains("full")) {
			String filename = cntx.getRealPath("/bilder/" + imagename);

			File file = new File(filename);

			if (!file.isFile()) {
				sendFile(resp, new File(cntx.getRealPath("/bilder/fail.jpg")));

			} else {
				sendFile(resp, file);
			}
		} else {
			sendFile(resp, new File(cntx.getRealPath("/bilder/fail.jpg")));

		}
	}
}
