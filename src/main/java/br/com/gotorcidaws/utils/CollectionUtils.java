package br.com.gotorcidaws.utils;

import java.util.List;

import br.com.gotorcidaws.utils.json.JSONArray;
import br.com.gotorcidaws.utils.json.JSONObject;

public class CollectionUtils {

	public static JSONArray fromListToJSONArray(List<?> objectList){
		JSONArray array = new JSONArray();
		
		for (int i = 0; i < objectList.size(); i++) {
			array.put(new JSONObject(objectList.get(i)));
		}
		
		return array;
	}
}
