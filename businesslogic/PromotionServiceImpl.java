package businesslogic;

import java.rmi.RemoteException;
import java.util.List;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businesslogicservice.PromotionService;
import dataservice.promotiondataservice;
import vo.PromotionVo;
import po.PromotionPo;
import rmi.RemoteHelper;

public class PromotionServiceImpl implements PromotionService {
	
	private List<PromotionPo> promotionList;
	
//	private  Date date;
	
	public PromotionServiceImpl(){
//		this.date = date;
//		promotionDao = promotiondataservice.getInstance();
//		promotionList = promotionDao.find(date);
		promotionList = new ArrayList<PromotionPo>();
//		try {
//			promotionList = RemoteHelper.getInstance().getPromotiondataservice().find();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	public boolean insert(PromotionPo promotionPo){
		try {
			int id = RemoteHelper.getInstance().getPromotiondataservice().promotioninsert(promotionPo);
			if(id>999){
				return true;
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		

	}
	

	
	public List<PromotionVo> find(){
		List<PromotionVo> list = new ArrayList<PromotionVo>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		for(PromotionPo promotionPo : promotionList){
			PromotionVo promotionVo = new PromotionVo(promotionPo);
			list.add(promotionVo);
//			try {
//				Date dateBegin = sdf.parse(promotionVo.getBeginTime());
//				if(date.getTime()<=dateBegin.getTime()){
//					list.add(promotionVo);
//				}
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
		}
		return list;
	}

	@Override
	public void logout(int id) {

	}

	@Override
	public String getname() {
		return "a";
	}


}
