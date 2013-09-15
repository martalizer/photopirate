import javax.servlet.http.HttpServlet;


public class ViewFullSize extends HttpServlet
{
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		String imagename = "";
				
		imagename += req.getParameter("file");
		
		String response = "<div><a href = '/photopirate/image?file=" + imagename + "&type=full'><img height='100%' src='/photopirate/image?file=" + imagename + "&type=full'></a></div>";
					
		resp.getOutputStream().print(response);
	}
}
