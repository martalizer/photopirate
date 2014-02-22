import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class ImageDAO {
	
	public static String getImages() {
		String bilderString = "";
		String sql = "SELECT id, username, filename FROM photopirate ORDER BY RAND() LIMIT 15";

		bilderString = performQuery(bilderString, sql);
		
		return bilderString;
	}
	
	public static String getImages(String user) {
		String bilderString = "";
		String sql = "SELECT id, username, filename FROM photopirate where username='"+user+"' Order by id DESC";
		
		bilderString = performQuery(bilderString, sql);

		return bilderString;
	}

	private static String performQuery(String bilderString, String sql) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String username = rs.getString("username");
				String filnamn = rs.getString("filename");
				String image = String.format("<a title='%s' href='/bilder/%s' rel='bild'>" +
											 "<img alt='%s' class='preview' src='/image?file=%s&type=thumb'>" +
											 "</a>",
										     username,
										     filnamn,
										     username,
										     filnamn);
				bilderString += image;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bilderString;
	}

}
