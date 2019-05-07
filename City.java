
public class City {
	
	private String cityName;
	private double xAxis;
	private double yAxis;
	private String javaTime;
	
	public City(String cityName, double xAxis, double yAxis, String javaTime) {
		this.cityName = cityName;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.javaTime = javaTime;
	}
	
	City toronto = new City("TORONTO", 231, 141, "America/Toronto");
	City newYork = new City("NEW YORK", 251.5, 153, "America/New_York");
	City vancouver = new City("VANCOUVER", 70.25, 115.75, "America/Vancouver");
	City saltLakeCity = new City("SALTLAKECITY", 122, 149, "US/Mountain");
	City saoPaulo = new City ("SÃO PAULO", 351.75, 403.5, "America/Sao_Paulo");
	City houston = new City ("HOUSTON", 172.5, 201.5, "US/Central");
	City losAngeles = new City ("LOS ANGELES", 87.5, 183.5, "America/Los_Angeles");
	City anchorage = new City ("ANCHORAGE", -28.5, 53.5, "America/Anchorage");
	City honolulu = new City ("HONOLULU", -58, 233.5, "Pacific/Honolulu");
	City beijing = new City ("BEIJING", 954.75, 160, "Asia/Shanghai");
	City hongKong = new City ("HONG KONG",	945.5, 230, "Hongkong");
	City perth = new City ("PERTH", 951.5, 435, "Australia/Perth");
	City adelaide = new City ("ADELAIDE", 1036, 447.75, "Australia/Adelaide");
	City sydney = new City ("SYDNEY", 1082.5, 443.5, "Australia/Sydney");
	City london = new City ("LONDON", 522.5, 105, "Europe/London");
	City mexicoCity = new City ("MEXICO CITY", 158.5, 243.5, "America/Mexico_City");
	City capeTown = new City ("CAPE TOWN", 592.25, 444.25, "Africa/Johannesburg");
	City addisAbaba = new City ("ADDIS ABABA", 667, 280.5, "Africa/Addis_Ababa");
	City rome = new City ("ROME", 570.25, 149.75, "Europe/Rome");
	City santiago = new City ("SANTIAGO", 263, 441, "America/Santiago");
	City tokyo = new City ("TOKYO", 1039.75, 176.5, "Asia/Tokyo");
	City jakarta = new City ("JAKARTA", 918, 337, "Asia/Jakarta");
	City tehran = new City ("TEHRAN", 713, 175, "Asia/Tehran");
	City lahore = new City ("LAHORE", 797.75, 194.5, "Asia/Karachi");
	City kathmandu = new City ("KATHMANDU", 838.5, 208.5, "Asia/Kathmandu");
	City mumbai = new City ("MUMBAI", 794, 244, "Asia/Kolkata");
	City caracas = new City ("CARACAS", 277, 275.25, "America/Caracas");
	City freetown = new City ("FREETOWN", 475.75, 282.5, "Africa/Freetown");
	City dubai = new City ("DUBAI", 729, 219, "Asia/Dubai");
	City auckland = new City ("AUCKLAND", 1168.75, 455.75, "Pacific/Auckland");
	City magadan = new City ("MAGADAN", 1080.5, 63, "Asia/Magadan");
	City dhaka = new City ("DHAKA", 857.75, 224.75, "Asia/Dhaka");
	City yangon = new City ("YANGON", 879, 251, "Asia/Rangoon");
	City nuuk = new City ("NUUK", 333, 36, "America/Godthab");
	City cairo = new City ("CAIRO", 639, 200, "Africa/Cairo");
	City kinshasa = new City ("KINSHASA", 580.5, 330.5, "Africa/Kinshasa");
	City lima = new City ("LIMA", 240.5, 359, "America/Lima");
	City reykjavik = new City ("REYKJAVÍK", 444, 36.25, "Atlantic/Reykjavik");

	public String getCityName() {
		return cityName;
	}
	public double getxAxis() {
		return xAxis;
	}
	public double getyAxis() {
		return yAxis;
	}
	public String getJavaTime() {
		return javaTime;
	}
	public City getToronto() {
		return toronto;
	}
	public City getNewYork() {
		return newYork;
	}
	public City getVancouver() {
		return vancouver;
	}
	public City getSaltLakeCity() {
		return saltLakeCity;
	}
	public City getSaoPaulo() {
		return saoPaulo;
	}
	public City getHouston() {
		return houston;
	}
	public City getLosAngeles() {
		return losAngeles;
	}
	public City getAnchorage() {
		return anchorage;
	}
	public City getHonolulu() {
		return honolulu;
	}
	public City getBeijing() {
		return beijing;
	}
	public City getHongKong() {
		return hongKong;
	}
	public City getPerth() {
		return perth;
	}
	public City getAdelaide() {
		return adelaide;
	}
	public City getSydney() {
		return sydney;
	}
	public City getLondon() {
		return london;
	}
	public City getMexicoCity() {
		return mexicoCity;
	}
	public City getCapeTown() {
		return capeTown;
	}
	public City getAddisAbaba() {
		return addisAbaba;
	}
	public City getRome() {
		return rome;
	}
	public City getSantiago() {
		return santiago;
	}
	public City getTokyo() {
		return tokyo;
	}
	public City getJakarta() {
		return jakarta;
	}
	public City getTehran() {
		return tehran;
	}
	public City getLahore() {
		return lahore;
	}
	public City getKathmandu() {
		return kathmandu;
	}
	public City getMumbai() {
		return mumbai;
	}
	public City getCaracas() {
		return caracas;
	}
	public City getFreetown() {
		return freetown;
	}
	public City getDubai() {
		return dubai;
	}
	public City getAuckland() {
		return auckland;
	}
	public City getMagadan() {
		return magadan;
	}
	public City getDhaka() {
		return dhaka;
	}
	public City getYangon() {
		return yangon;
	}
	public City getNuuk() {
		return nuuk;
	}
	public City getCairo() {
		return cairo;
	}
	public City getKinshasa() {
		return kinshasa;
	}
	public City getLima() {
		return lima;
	}
	public City getReykjavik() {
		return reykjavik;
	}
	
}
