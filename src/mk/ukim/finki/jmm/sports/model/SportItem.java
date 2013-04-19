package mk.ukim.finki.jmm.sports.model;


public class SportItem {

	private Long latitude;
	private Long longitude;
	private String name;
	private String category;
	private Long distance;
	private String link;
	
	public SportItem() {
	}

	public SportItem(String name, Long longi, Long lati, String c, Long d, String link) {
		super();
		this.name = name;
		this.longitude=longi;
		this.latitude=lati;
		this.category=c;
		this.distance=d;
		this.link=link;
	}

	public String getCategory() {
		return category;
	}
	
	public Long getDistance() {
		return distance;
	}
	
	public Long getLongitude() {
		return longitude;
	}
	
	public Long getLatitude() {
		return latitude;
	}
	
	public String getLink() {
		return link;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}
	
	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	 
}
