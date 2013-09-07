import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexView extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String data = req.getAttribute("data").toString();

		ServletOutputStream out = resp.getOutputStream();
		out.write("<!DOCTYPE html>".getBytes());
		out.write("<html>".getBytes());
		out.write("<body>".getBytes());
		out.write("<form action='upload_file' method='post'".getBytes());
		out.write("         enctype='multipart/form-data'>".getBytes());
		out.write("  <label for='file'>Filename:</label>".getBytes());
		out.write("  <input type='file' name='file' id='file'><br>".getBytes());
		out.write("  <input type='submit' name='submit' value='Submit'>".getBytes());
		out.write("</form>".getBytes());
		out.write(String.format("<p>Sidan genererades: %s</p>", data).getBytes());
		out.write("</p>".getBytes());
		out.write("  </body>".getBytes());
		out.write("</html>".getBytes());		
	}
}
