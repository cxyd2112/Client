package vo;

import po.HotelPo;
import java.util.Vector;
public class HotelVo extends Vector<String>{
	
public HotelVo(HotelPo hotelPo) {
	this.add(String.valueOf(hotelPo.getHotelID()));
	this.add(hotelPo.getHotelName());
	this.add(hotelPo.getPosition());
	this.add(hotelPo.getAddress());
	this.add(String.valueOf(hotelPo.getStar()));
	this.add(String.valueOf(hotelPo.getScore()));
	this.add(String.valueOf(hotelPo.getMinprice()));
	this.add(hotelPo.getAssess());
	this.add(hotelPo.getDescription());

}

	public HotelVo(int hotelid,String hotelname,String position,String address,int star,float mark,boolean reserved){
		this.add(String.valueOf(hotelid));
		this.add(hotelname);
		this.add(position);
		this.add(address);
		this.add(String.valueOf(star));
		this.add(String.valueOf(mark));
	}




public int getHotelID(){
	return Integer.parseInt(this.get(0));
}

public String getPosition(){
	return this.get(2);
}

public String getHotelName(){
	return this.get(1);
}
public String getRoomNums(){
	return this.get(3);
}
public String getAvailableRoomNums(){
	return this.get(4);
}
public String getAddress(){
	return this.get(3);
}
public String getPrices(){
	return this.get(5);
}
public String getStar(){
	return this.get(4);
}
public String getScore(){
	return this.get(5);
}
public String getAssess(){
	return this.get(6);
}
public String getDescription(){
	return this.get(7);
}
}
