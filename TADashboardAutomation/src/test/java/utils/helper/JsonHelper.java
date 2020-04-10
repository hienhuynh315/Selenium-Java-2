package utils.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class JsonHelper {

	private static Logger log = Logger.getLogger(JsonHelper.class);

	public static Map<String, String> convertJsonToMap(String json) {
		try {
			log.debug("JsonHelper: convertJsonToMap");
			Type mapType = new TypeToken<Map<String, String>>() {
			}.getType();
			Gson gson = new Gson();
			return gson.fromJson(json, mapType);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public static <T> List<T> getListData(String jsonPath, Type type) {
		try {
			log.debug("JsonHelper: getListData");
			JsonReader reader = getJsonReader(jsonPath);
			List<T> lst = new ArrayList<T>();
			Gson gson = new Gson();
			lst = gson.fromJson(reader, type);
			return lst;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public static <T> List<T> getListData(String jsonPath, Class<?> clazz) {
		try {
			log.debug("JsonHelper: getListData");
			JsonReader reader = getJsonReader(jsonPath);
			List<T> lst = new ArrayList<T>();
			Gson gson = new Gson();
			lst = gson.fromJson(reader, clazz);
			return lst;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public static <T> T getData(String jsonPath, Type type) {
		try {
			log.debug("JsonHelper: getData");
			Gson gson = new Gson();
			JsonReader reader = getJsonReader(jsonPath);
			return gson.fromJson(reader, type);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}

	}

	public static <T> T getData(String jsonPath, Class<?> clazz) {
		try {
			log.debug("JsonHelper: getData");
			Gson gson = new Gson();
			JsonReader reader = getJsonReader(jsonPath);
			return gson.fromJson(reader, clazz);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public static JsonObject getJsonObject(String jsonPath) {
		try {
			log.debug("JsonHelper: getJsonObject");
			JsonObject obj = new JsonObject();
			Gson gson = new Gson();
			JsonReader reader = getJsonReader(jsonPath);
			obj = gson.fromJson(reader, JsonObject.class);
			return obj;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public static DesiredCapabilities convertJsonToCapabilities(String json) {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		Map<String, String> caps = JsonHelper.convertJsonToMap(json);
		if (caps != null) {
			Set<String> keys = caps.keySet();
			for (String key : keys) {
				capabilities.setCapability(key, caps.get(key));
			}
		}
		return capabilities;
	}

	public static List<String> convertJsonToArguments(String json) {
		List<String> args = new ArrayList<String>();

		Map<String, String> maps = JsonHelper.convertJsonToMap(json);
		if (maps != null) {
			Set<String> keys = maps.keySet();
			for (String key : keys) {
				args.add(maps.get(key));
			}
		}
		return args;
	}

	private static JsonReader getJsonReader(String jsonPath) {
		try {
			JsonReader reader;
			reader = new JsonReader(new FileReader(jsonPath));
			return reader;
		} catch (FileNotFoundException e) {
			return null;
		}
	}
}
