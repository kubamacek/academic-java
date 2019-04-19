import org.json.*;


public class JsonParser {
	private JSONObject json;
	
	public Parser(JSONObject object) {
		json = object;
	}
	
	public String getString(String key) {
		return key;
	}
}
