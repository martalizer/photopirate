import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

	public static void addImage(Image image) {
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(String.format("INSERT INTO photopirate (username, filename, inactive) VALUES ('%s', '%s', '0')", image.getUsername(),
					image.getFilename()));
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static List<Image> getImagesFromUser(String user) {
		return getImages("SELECT id, username, filename FROM photopirate where inactive = 0 and username='" + user
				+ "' Order by id DESC LIMIT 1000");
	}
	
	public static List<Image> getImagesRandomFromUser(String user) {
		return getImages("SELECT id, username, filename FROM photopirate where inactive = 0 and username='" + user
			+ "' ORDER BY RAND() LIMIT 1000");
	}
		
	public static List<Image> getImagesRandom(){
		return getImages("SELECT id, username, filename FROM photopirate where inactive = 0 ORDER BY RAND() LIMIT 1000");
	}
	
	public static List<Image> getImages(String sql) {
		List<Image> images = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String username = rs.getString("username");
				String filename = rs.getString("filename");
				int id = rs.getInt("id");
				images.add(new Image(username, filename, id));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return images;
	}	
	
	public static List<Image> getImagesForDelete(String user) {
		return getImages("SELECT id, username, filename FROM photopirate where inactive = 0 and username='" + user
				+ "' Order by id DESC");
	}
}
