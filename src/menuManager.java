
public class menuManager {
	public static String getUserLoggedInMenu() {
		String menu = "<a href='mypage'>My Page</a> | <a href=\"#\" onclick=\"openFileOption();return;\">Upload image</a> | <a href='/logout'>Logout</a> | <a href='/deleteimagelist'>Delete image</a>";
		return menu; 
	}
	
	public static String getNoUserMenu() {
		String menu = "<a href='" + new Login().getLoginUrl() + "'><img src='login.png'></a>";
		return menu;
	}

	public static String message(String string) {
		return String.format("<div>%s</div>", string);
	}
	

}
