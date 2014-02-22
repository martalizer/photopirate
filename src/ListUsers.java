import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public class ListUsers extends HttpServlet {
	@Override
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, java.io.IOException
	{
		String bilderString = "";

		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");
			Statement stmt = conn.createStatement();			
			String sql = "SELECT * FROM users";
			ResultSet rs = stmt.executeQuery(sql);

			int nbrOfUsers = 0;			
			while (rs.next())
			{
				nbrOfUsers++;
				String username = rs.getString("username");
				String temp = "<a href='/user/" + username +"'>"+ username + "</a><br>";
				bilderString += temp; 						
			}

			bilderString += "<p class='title'>There are currently " + nbrOfUsers + " registered users <br>";

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		req.setAttribute("bilder", bilderString);	

		if(req.getSession().getAttribute("user") == null) {
			req.setAttribute("content", "<a href='/login.jsp'>Log in</a> | <a href='/register.jsp'>Register User</a>");		
		} 
		else req.setAttribute("content", "<a href='mypage'>Home</a> | <a href='/users'>List Users</a> | <a href='upload.html'>Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>");		

		req.getRequestDispatcher("/index.jsp").forward(req, resp);	

	}
}