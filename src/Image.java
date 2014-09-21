
public class Image {

	private String username;
	private String filename;
	private int id;
	
	public Image(String username, String filename, int id) {
		this(username, filename);
		this.id = id;
	}	
	public Image(String username, String filename) {
		this.username = username;
		this.filename = filename;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
