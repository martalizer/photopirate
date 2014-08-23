import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImageDAO {

	public static String getImages() {
		return performQuery("SELECT id, username, filename FROM photopirate ORDER BY RAND() LIMIT 1000");
	}

	public static String getImages(String user) {
		return performQuery("SELECT id, username, filename FROM photopirate where username='" + user
				+ "' Order by id DESC LIMIT 1000");
	}

	private static String performQuery(String sql) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String bilderString = "";

			while (rs.next()) {
				String username = rs.getString("username");
				String filnamn = rs.getString("filename");
				String image = String.format("<a title='%s' href=/image?file=%s&type=medium rel='bild'>"
						+ "<img alt='%s' class='preview' src='/image?file=%s&type=thumb'>" + "</a>", username, filnamn,
						username, filnamn);
				bilderString += image;
			}
			rs.close();
			stmt.close();
			conn.close();
			return bilderString;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

}
