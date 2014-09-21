import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ImageDAO {

	public static void addImage(String user, String randomString) {
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(String.format("INSERT INTO photopirate (username, filename) VALUES ('%s', '%s')", user,
					randomString));
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static List<Image> getImagesFromUser(String user) {
		return getImages("SELECT id, username, filename FROM photopirate where username='" + user
				+ "' Order by id DESC LIMIT 1000");
	}
	
	public static List<Image> getImagesRandomFromUser(String user) {
		return getImages("SELECT id, username, filename FROM photopirate where username='" + user
			+ "' ORDER BY RAND() LIMIT 1000");
	}
		
	public static List<Image> getImagesRandom(){
		return getImages("SELECT id, username, filename FROM photopirate ORDER BY RAND() LIMIT 1000");
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
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return images;
	}
	
	
	/*public static String getImages() {
		return performGetImagesQuery("SELECT id, username, filename FROM photopirate ORDER BY RAND() LIMIT 1000");
	}*/

//	public static String getImagesRandom(String user) {
//		return performGetImagesQuery("SELECT id, username, filename FROM photopirate where username='" + user
//				+ "' ORDER BY RAND() LIMIT 1000");
//	}

/*	public static String getImages(String user) {
		return performGetImagesQuery("SELECT id, username, filename FROM photopirate where username='" + user
				+ "' Order by id DESC LIMIT 1000");
	} */
	
	public static List<Image> getImagesForDelete(String user) {
		return getImages("SELECT id, username, filename FROM photopirate where username='" + user
				+ "' Order by id DESC");
	}

	
	
	
	private static String performGetImagesQueryDorDelete(String sql) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/jdbcexample", "mart", "mart");

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String bilderString = "";

			while (rs.next()) {
				
				
				
				
				String filnamn = rs.getString("filename");
				String id = rs.getString("id");

				String image = String.format("<p><a href=/image?file=%s&type=medium rel='bild'>"
						+ "<img src='/image?file=%s&type=thumb'></a>" + 
						"<a class= 'deletebutton' href='deletethisimage?id=%s'>Delete Image</a></p>", filnamn, filnamn, id);
				
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
	
	private static String performGetImagesQuery(String sql) {
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
