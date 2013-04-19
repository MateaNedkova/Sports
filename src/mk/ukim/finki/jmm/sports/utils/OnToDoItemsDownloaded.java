package mk.ukim.finki.jmm.sports.utils;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.jmm.sports.model.SportItem;

import org.json.JSONArray;
import org.json.JSONObject;

public class OnToDoItemsDownloaded implements
		OnContentDownloaded<List<SportItem>> {

	//kreiranje na lista od SportItemi od  podatocite prevzemeni od servisot
	private List<SportItem> items = new ArrayList<SportItem>();

	@Override
	public void onContentDownloaded(String content, int httpStatus)
			throws Exception {
		 JSONObject json = new JSONObject(content);
		 JSONArray jsonItems = json.getJSONArray("pois");

		for (int i = 0; i < jsonItems.length(); i++) {
			JSONObject jObj = (JSONObject) jsonItems.get(i);
			SportItem item = new SportItem();
			item.setCategory(jObj.getString("category"));
			item.setDistance(jObj.getLong("distance"));
			item.setLatitude(jObj.getLong("latitude"));
			item.setLink(jObj.getString("link"));
			item.setLongitude(jObj.getLong("longitude"));
			item.setName(jObj.getString("name"));
			items.add(item);
		}
	}

	@Override
	public List<SportItem> getResult() {
		return items;
	}
}
