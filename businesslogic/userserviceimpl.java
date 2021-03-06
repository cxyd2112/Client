package businesslogic;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import businesslogicservice.loginservice;
import dataservice.orderdataservice;
import dataservice.userdataservice;
import po.HotelPo;
import po.OrderPo;
import po.UserPo;
import presentation.userui.SearchHotelController;
import rmi.RemoteHelper;
import vo.HotelVo;
import vo.OrderVo;
import vo.UserVo;

public class userserviceimpl implements businesslogicservice.userservice {
	private userdataservice userdaser;
	private orderdataservice orderdaser;
	private UserPo upo;
	private HotelPo hpo;
	private List<OrderPo> opolist;
	private loginservice logs;
	private List<HotelPo> hpolist;
	public userserviceimpl(){

	}
	public userserviceimpl(int userId){
		try {
			upo=RemoteHelper.getInstance().getUserdataservice().userfind(userId);
			opolist=RemoteHelper.getInstance().getOrderdataservice().findorderbyuserid(userId);
			hpolist=RemoteHelper.getInstance().getHoteldataservice().getallhotellist();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		logs=new login();
	}

	@Override
	public int getcredit() {
		// TODO Auto-generated method stub
		return upo.getCredit();
	}
	@Override
	public List<OrderVo> getAllOrders(int userId) {
		// TODO Auto-generated method stub
		ArrayList<OrderVo> l=new ArrayList();
		if (opolist==null) return null;
		for (OrderPo op:opolist){
			l.add(new OrderVo(op));
		}
		return l;
	}

	@Override
	public List<OrderVo> getUnfinishedOrders(int userId) {
		// TODO Auto-generated method stub
		
		ArrayList<OrderVo> l=new ArrayList();
		if (opolist==null) return null;
		for (OrderPo op:opolist){
			if (op.getStatus()==0)
			l.add(new OrderVo(op));
		}
		return l;
		
	
	}

	@Override
	public List<OrderVo> getFinishedOrders(int userId) {
		// TODO Auto-generated method stub
		ArrayList<OrderVo> l=new ArrayList();
		if (opolist==null) return null;
		for (OrderPo op:opolist){
			if (op.getStatus()==1)
			l.add(new OrderVo(op));
		}
		return l;
		
	}

	@Override
	public List<OrderVo> getAbnormalOrders(int userId) {
		// TODO Auto-generated method stub
		ArrayList<OrderVo> l=new ArrayList();
		if (opolist==null) return null;
		for (OrderPo op:opolist){
			if (op.getStatus()==2)
			l.add(new OrderVo(op));
		}
		return l;
		
	}
	public List<OrderVo> getCancelOrders(int userId) {
		// TODO Auto-generated method stub
		ArrayList<OrderVo> l=new ArrayList();
		if (opolist==null) return null;
		for (OrderPo op:opolist){
			if (op.getStatus()==3)
			l.add(new OrderVo(op));
		}
		return l;
		
	}

	@Override
	public List<HotelVo> getAllHotels(int userId) {
		// TODO Auto-generated method stub
	//	HotelPo v = new HotelPo(hotelID,position,hotelName,dachuangfangprice,shuangrenfangprice,sanrenjianprice,star,score,description);
		ArrayList<HotelVo> list =new ArrayList<>();
		for (HotelPo hp:hpolist){
			list.add(new HotelVo(hp));
		}
		return list;
	}


	@Override
	public int createorder(OrderVo ovo) {
		// TODO Auto-generated method stub
		//计算价格
		
		HotelPo hpo = null;
		try {
			hpo = RemoteHelper.getInstance().getHoteldataservice().findhotelbyid(ovo.getHotelid());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int roomnum = ovo.getRoomnum();
		int roomstyle = ovo.getRoomstyle();
		
		int value = 0;
		if(ovo.getRoomstyle()==0){
			if(roomnum<=hpo.getAvdachuangfang()){
			value = (ovo.getRoomnum()+1)*hpo.getAvdachuangfang();
			hpo.setAvdachuangfang(hpo.getAvdachuangfang()-roomnum);
			}
			else
				return 0;
		}else if(ovo.getRoomstyle()==1){
			if(roomnum<=hpo.getAvshuangrenfang()){
			value = (ovo.getRoomnum()+1)*hpo.getAvshuangrenfang();
			hpo.setAvshuangrenfang(hpo.getAvshuangrenfang()-roomnum);
			}else
				return 0;
		}else if(ovo.getRoomstyle()==2){
			if(roomnum<=hpo.getAvsanrenjian()){
			value = (ovo.getRoomnum()+1)*hpo.getAvsanrenjian();
			hpo.setAvsanrenjian(hpo.getAvsanrenjian()-roomnum);
			}else
				return 0;
		}
		

//		插入数据库
		 try {
			RemoteHelper.getInstance().getOrderdataservice().orderinsert(new OrderPo(ovo.getOrderid(),ovo.getUserid(),ovo.getHotelid(),ovo.getCreatetime(),ovo.getExecutetime(),ovo.getDelaytime(),ovo.getEndtime(),value,ovo.getStatus(),ovo.getRoomstyle(),ovo.getRoomnum()));
			RemoteHelper.getInstance().getHoteldataservice().hotelupdate(hpo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return value;
	}

	@Override
	public int getuserid() {
		// TODO Auto-generated method stub
		return upo.getId();
	}

	@Override
	public String getname() {
		return  upo.getUserName();
	}

	@Override
	public void logout(int userid) {
		logs.logout(userid);
	}

	@Override
	public void updateuserinfo(String name,String phone) {
		// TODO Auto-generated method stub
		//插入数据库
		upo.setUserName(name);
		upo.setPhone(phone);
		try {
			RemoteHelper.getInstance().getUserdataservice().userupdate(upo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updatenormalvip (String bir) {
		// TODO Auto-generated method stub
		//插入数据库
		upo.setBirthday(bir);
		
		try {
			RemoteHelper.getInstance().getUserdataservice().userupdate(upo);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updatecompanyvip (String com) {
		// TODO Auto-generated method stub
		//插入数据库
		upo.setCompany(com);
		
		try {
			RemoteHelper.getInstance().getUserdataservice().userupdate(upo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public String getphone() {
		// TODO Auto-generated method stub
		return upo.getPhone();
	}

	@Override
	public int insert(String name, String number,char[] password) {
		UserPo x=new UserPo(0,name,null,number,0,null);
		int i=0;
		try {
			i=RemoteHelper.getInstance().getUserdataservice().userinsert(x,password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	@Override
	public void updateorderinfo(int orderid,String assess, int score) {
		// TODO Auto-generated method stub
		OrderPo opo = null;
		HotelPo hp=null;
		try {
			opo = RemoteHelper.getInstance().getOrderdataservice().orderfind(orderid);
			 hp=RemoteHelper.getInstance().getHoteldataservice().findhotelbyid(opo.getHotelid());
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    opo.setAssess(assess);
	    
	    
	    opo.setScore(score);
	    hp.setAssess(assess);
	    hp.setScore((hp.getScore()*hp.getscorenum()+score)/(hp.getscorenum()+1));
	    hp.setscorenum(hp.getscorenum()+1);
	    
	    
		try {
			RemoteHelper.getInstance().getOrderdataservice().orderupdate(opo);
			RemoteHelper.getInstance().getHoteldataservice().hotelupdate(hp);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void cancelorder(int orderid) {
		// TODO Auto-generated method stub
		OrderPo opo = null;
		try {
			opo = RemoteHelper.getInstance().getOrderdataservice().orderfind(orderid);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		opo.setStatus(3);
		try {
			RemoteHelper.getInstance().getOrderdataservice().orderupdate(opo);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public UserPo getUser(int userId) {
		UserPo upo = null;
		try{
			upo = RemoteHelper.getInstance().getUserdataservice().userfind(userId);
		}catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return upo;
	}
	@Override
	public String getAddress() {
		// TODO Auto-generated method stub
		
		return hpo.getAddress();
	}
	@Override
	public void creathotelpo(int selectedhotelid) {
		// TODO Auto-generated method stub
		try {
			hpo = RemoteHelper.getInstance().getHoteldataservice().findhotelbyid(selectedhotelid);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String getPosition() {
		// TODO Auto-generated method stub
		return hpo.getPosition();
	}
	@Override
	public int getDachuangfangprice() {
		// TODO Auto-generated method stub
		return hpo.getDachaungfangprice();
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return hpo.getDescription();
	}
	@Override
	public int getShuangrenfangprice() {
		// TODO Auto-generated method stub
		return hpo.getShuangrenfangprice();
	}
	@Override
	public int getSanrenjianprice() {
		// TODO Auto-generated method stub
		return hpo.getSanrenjianprice();
	}
	@Override
	public int getStar() {
		// TODO Auto-generated method stub
		return hpo.getStar();
	}
	@Override
	//判断是否是预定过得酒店
	public boolean isreserved(int selectedhotelid,int userId) {
		// TODO Auto-generated method stub
		
		try {
			return RemoteHelper.getInstance().getUserdataservice().hotelreserved(selectedhotelid, userId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	}


//}
