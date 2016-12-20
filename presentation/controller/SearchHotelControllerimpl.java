package presentation.controller;

import java.util.ArrayList;
import java.util.List;

import businesslogic.userserviceimpl;
import businesslogicservice.userservice;
import presentation.userui.SearchHotel;
import presentation.userui.SearchHotelController;
import vo.HotelVo;
import vo.OrderVo;

public class SearchHotelControllerimpl implements SearchHotelController{

	private userservice userso;
	private SearchHotel View;
	private int userId;
	private controller con;

public SearchHotelControllerimpl(int userId){
	userso = new userserviceimpl(userId);
	this.userId = userId;
	
}
	public void cancel() {
		// TODO Auto-generated method stub
		
	}
	public void setview(SearchHotel view) {
		// TODO Auto-generated method stub
		this.View = view;
	}
	@Override
	public int getUserID() {
		// TODO Auto-generated method stub
		return 0;
	}
	public List<HotelVo> getAllHotels(int userId){
		// TODO Auto-generated method stub
		return userso.getAllHotels(userId);
	}
	@Override
	public void usersearchhotel(String s1,String s2,String s3,String s4) {
		// TODO Auto-generated method stub
//		View=new SearchHotel(this);
//		System.out.println(userso.usersearchhotel(s1,s2,s3,s4).elementAt(0).getHotelName());
		System.out.println("shit");
		View.createtable(userso.usersearchhotel(s1,s2,s3,s4));
	}

	@Override
	public void ExitButtonClicked() {
		// TODO Auto-generated method stub
		con.loggedin(userId+"");
	}
	@Override
	public void reservehotel() {
		// TODO Auto-generated method stub
		View.reservehotel();
	}
	public SearchHotelControllerimpl(){
		userso = new userserviceimpl(1);
	}
	@Override
	public int getcredit() {
		// TODO Auto-generated method stub
		return userso.getcredit();
	}
	@Override
	public void setcon(controller con) {
		// TODO Auto-generated method stub
		this.con=con;
	}
	@Override
	public void change() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int createorder(int userid,int hotelid,int intnowtime,long s5,long s7,long s6,int status,String s1,int s2) {
		// TODO Auto-generated method stub
		
		OrderVo ovo = new OrderVo(userid,hotelid,intnowtime,s5,s7,s6,status,s1,s2);
		
		return userso.createorder(ovo);
		
	}

}
