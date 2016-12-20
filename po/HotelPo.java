package po;

public class HotelPo {
	int hotelID;
	String position;
	String hotelName;
	
	int avdachuangfang;
	
	int avshuangrenfang;
	int avsanrenjian;
	int dachaungfangprice;
	
	int shuangrenfangprice;
	int sanrenjianprice;
	int star;
	double score;
	String assess;
	String description;
	boolean reserved;
	
	public HotelPo(){
		super();
	}
	public HotelPo(int hotelID,String position,String hotelName){
		this.hotelID = hotelID;
		this.position = position;
		this.hotelName = hotelName;
		
	}
	
	public int getHotelID(){
		return this.hotelID;
	}
	
	public void setHotelID(int hotelID){
		this.hotelID = hotelID;
	}

	public String getPosition(){
		return this.position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	
	public String getHotelName(){
		return this.hotelName;
	}
	
	public void setHotelName(String hotelName){
		this.hotelName = hotelName;
	}
	public int getAvdachuangfang() {
		return avdachuangfang;
	}
	public void setAvdachuangfang(int avdachuangfang) {
		this.avdachuangfang = avdachuangfang;
	}
	public int getAvshuangrenfang() {
		return avshuangrenfang;
	}
	public void setAvshuangrenfang(int avshuangrenfang) {
		this.avshuangrenfang = avshuangrenfang;
	}
	public int getAvsanrenjian() {
		return avsanrenjian;
	}
	public void setAvsanrenjian(int avsanrenjian) {
		this.avsanrenjian = avsanrenjian;
	}
	
	public int getDachaungfangprice() {
		return dachaungfangprice;
	}
	public int getShuangrenfangprice() {
		return shuangrenfangprice;
	}
	public int getSanrenjianprice() {
		return sanrenjianprice;
	}
	public int getStar(){
		return this.star;
	}
	public void setStar(int i){
		if(i>=1&&i<=5){
			star = i;
		}
	}
	public double getScore(){
		return this.score;
	}
	public void setScore(double score){
		this.score = score;
	}
	public String getAssess(){
		return this.assess;
	}
	public void setAssess(String assess){
		this.assess = assess;
	}
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public boolean getReserved(){
		return this.reserved;
	}
	public void setReserved(boolean reserved){
		this.reserved = reserved;
	}

}
