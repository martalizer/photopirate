import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

public class ImageServer extends HttpServlet
{
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		String imagename = "";
		String type = "";
				
		imagename += req.getParameter("file");	
		type += req.getParameter("type");
		
	       ServletContext cntx= getServletContext();
	       // Get the absolute path of the image
	       
	       if (type.contains("thumb"))
	       {
	    	   String filename = cntx.getRealPath("/bilder/thumb/" + imagename);
	    	   
	    	   File file = new File(filename);
	    	       
	    	   resp.setContentType("image/jpeg");
	    	   
		       if(!file.isFile())
		       {
			    	   String arguments[] = new String[2];
			    	   arguments[0] = cntx.getRealPath("/bilder/" + imagename);
			    	   arguments[1] = cntx.getRealPath("/bilder/thumb/" + imagename);
			    	   try
					{
						ThumbnailCreator.main(arguments);	//create the thumbnail as needed
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		       }    
		       
		       if(!file.isFile())
		       {
		    	   file = new File(cntx.getRealPath("/bilder/fail.jpg"));
		    	   
			       resp.setContentLength((int)file.length());
			   	
			       FileInputStream in = new FileInputStream(file);
			       OutputStream out = resp.getOutputStream();
	
			       // Copy the contents of the file to the output stream
			        byte[] buf = new byte[1024];
			        int count = 0;
			        while ((count = in.read(buf)) >= 0) {
			          out.write(buf, 0, count);
			       }
			       out.close();
			       in.close();	    	   
		       } 
		       else
		       {
			       resp.setContentLength((int)file.length());
	
			       FileInputStream in = new FileInputStream(file);
			       OutputStream out = resp.getOutputStream();
	
			       // Copy the contents of the file to the output stream
			        byte[] buf = new byte[1024];
			        int count = 0;
			        while ((count = in.read(buf)) >= 0) {
			          out.write(buf, 0, count);
			       }
			       out.close();
			       in.close();
		       }
	       }
	       else	if (type.contains("full"))

	       {
	    	   String filename = cntx.getRealPath("/bilder/" + imagename);
		       String mime = cntx.getMimeType(filename);
		       if (mime == null) {
		         resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		         return;
		       }
		       resp.setContentType(mime);
		       
		       File file = new File(filename);
		       
		       if(!file.isFile())
		       {
		    	   file = new File(cntx.getRealPath("/bilder/fail.jpg"));
		    	   
			       resp.setContentLength((int)file.length());
			   	
			       FileInputStream in = new FileInputStream(file);
			       OutputStream out = resp.getOutputStream();
	
			       // Copy the contents of the file to the output stream
			        byte[] buf = new byte[1024];
			        int count = 0;
			        while ((count = in.read(buf)) >= 0) {
			          out.write(buf, 0, count);
			       }
			       out.close();
			       in.close();	    	   
		       } 
		       else
		       {
			       resp.setContentLength((int)file.length());
	
			       FileInputStream in = new FileInputStream(file);
			       OutputStream out = resp.getOutputStream();
	
			       // Copy the contents of the file to the output stream
			        byte[] buf = new byte[1024];
			        int count = 0;
			        while ((count = in.read(buf)) >= 0) {
			          out.write(buf, 0, count);
			       }
			       out.close();
			       in.close();
		       }
	       }		
	       else
	       {       
	    	   resp.setContentType("image/jpeg");
		    	   
	    	   //resp.sendRedirect("fail.png");
	    	   File file = new File(cntx.getRealPath("/bilder/fail.jpg"));
	    	   
		       resp.setContentLength((int)file.length());
		   	
		       FileInputStream in = new FileInputStream(file);
		       OutputStream out = resp.getOutputStream();

		       // Copy the contents of the file to the output stream
		        byte[] buf = new byte[1024];
		        int count = 0;
		        while ((count = in.read(buf)) >= 0) {
		          out.write(buf, 0, count);
		       }
		       out.close();
		       in.close();    	   
	       }
	}
}
