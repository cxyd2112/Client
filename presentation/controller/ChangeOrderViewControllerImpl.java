package presentation.controller;


import presentation.promotionui.ChangeOrderView;
import presentation.promotionui.ChangeOrderViewControllerService;
import vo.OrderVo;

import java.util.List;

public class ChangeOrderViewControllerImpl implements ChangeOrderViewControllerService {
	
	private ChangeOrderView view;
	
	private int hotelId;

	private controller con;
	
	public ChangeOrderViewControllerImpl(int hotelId){
		this.hotelId = hotelId;
	}
	
	public void setView(ChangeOrderView view){
		this.view = view;
	}
	
	public int getHotelId(){
		return hotelId;
	}

	@Override
	public void setcon(controller controller) {
		this.con=con;
	}

	public List<OrderVo> getAbnormalOrder(){
		return null;
//		return changeOrderService.getAbnormalOrder();
	}
	
	public void changeButtonClicked(){
		view.changeButtonClicked();
	}

}
