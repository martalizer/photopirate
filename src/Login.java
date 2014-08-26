import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.FacebookApi;
import org.scribe.oauth.OAuthService;

public class Login {
	public String getLoginUrl() {
		OAuthService service = new ServiceBuilder().provider(FacebookApi.class).apiKey("748812261795853")
				.apiSecret("8ffd2e5855ff88f4581a1d6a7933df3f").callback("http://martalizer.se/login").build();

		String authUrl = service.getAuthorizationUrl(null);
		return authUrl;
	}
}
