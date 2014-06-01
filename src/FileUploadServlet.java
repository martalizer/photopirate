import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String randomString = UUID.randomUUID().toString() + ".jpg";
		File imageDirectory = new File("/opt/tomcat7/webapps/ROOT/bilder/");

		if (!imageDirectory.exists())
			imageDirectory.mkdir();
		File filename = new File(imageDirectory, randomString);
		String user = (String) req.getSession().getAttribute("user");

		InputStream in = req.getPart("file").getInputStream();
		FileOutputStream out = new FileOutputStream(filename);

		byte[] buffer = new byte[1024];

		int read;

		while ((read = in.read(buffer)) != -1)
			out.write(buffer, 0, read);

		out.close();

		Connection conn;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");
			
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO photopirate (username, filename) VALUES ('"+user+"', '" + randomString + "')");	

			stmt.close();
			conn.close();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("bilder", "Image Uploaded");	
		
		
		
		if (req.getSession().getAttribute("user") == null) {
			req.setAttribute("content",
					"<a href='/login.jsp'>Login</a> | <a href='/register.jsp'>Register User</a><p><a href='"+new Login().getLoginUrl()+"'><img src='login.png'></a>");
		} else
			req.setAttribute(
					"content",
					"<a href='mypage'>Home</a> | <a href='/users'>List Users</a> | <a href='upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");

		req.getRequestDispatcher("index.jsp").forward(req, resp);
	}

}
