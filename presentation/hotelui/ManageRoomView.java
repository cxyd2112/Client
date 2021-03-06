package presentation.hotelui;

import javax.swing.*;
import dataservice.hoteldataservice;

import javax.swing.table.DefaultTableModel;

import po.*;
import vo.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Vector;
import presentation.controller.controller;
import rmi.RemoteHelper;
public class ManageRoomView extends JPanel{
	
	private int HotelID;
	
	private ManageRoomViewController controller;
	
	private JButton inputRoomButton;
	private JButton deleteRoomButton;
	private JButton exitButton;
	
	private JPanel manageButtonJpanel;
	private JPanel serviceTypeJpanel;
	private JPanel inputButtonJpanel;
	
	private DefaultTableModel roomModel;
	private DefaultTableModel manageRoomModel;
	
	private Vector<String> vColumns;
	private Vector<String> manageColumns;
	
	private Vector<Vector<String>> vData;
	private Vector<Vector<String>> manageData;
	
	private JTable roomTable;
	private JTable manageRoomTable;
	
	private HotelPo po1;
	
	private hoteldataservice hoteldataservice;
	
	private JScrollPane scrollPane;
	
	public ManageRoomView(ManageRoomViewController controller){
		this.controller = controller;
		this.init();
		this.setLayout(null);
	}
	
	
	public void init(){
		scrollPane = new JScrollPane();

		inputRoomButton = new JButton("录入");
		deleteRoomButton = new JButton("删除");
		exitButton = new JButton("返回");
		
		serviceTypeJpanel = new JPanel();
		inputButtonJpanel = new JPanel();
		manageButtonJpanel = new JPanel();
		
		manageColumns = new Vector<String>();
		manageColumns.add("酒店id");
		manageColumns.add("酒店名称");
		manageColumns.add("房间类型");
		manageColumns.add("可用数量");
		manageColumns.add("价格");
		
		manageData = new Vector<Vector<String>>();
		Vector<String> vo1 = new Vector<String>();
		Vector<String> vo2 = new Vector<String>();
		Vector<String> vo3 = new Vector<String>();
		
		try {
			po1 = RemoteHelper.getInstance().getHoteldataservice().findhotelbyid(HotelID);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
      
		vo1.add(String.valueOf(controller.getHotelID()));
		vo1.add(controller.getHotelName());
		vo1.add("大床房");
		vo1.add(String.valueOf(controller.getAvdachuangfang()));
		vo1.add(String.valueOf(controller.getDachaungfangprice()));
		
		vo2.add(String.valueOf(controller.getHotelID()));
		vo2.add(controller.getHotelName());
		vo2.add("双人房");
		vo2.add(String.valueOf(controller.getAvshuangrenfang()));
		vo2.add(String.valueOf(controller.getShuangrenfangprice()));
		
		vo3.add(String.valueOf(controller.getHotelID()));
		vo3.add(controller.getHotelName());
		vo3.add("三人间");
		vo3.add(String.valueOf(controller.getAvsanrenjian()));
		vo3.add(String.valueOf(controller.getSanrenjianprice()));
		manageData.add(vo1);
		manageData.add(vo2);
		manageData.add(vo3);
		

		manageRoomModel = new DefaultTableModel(manageData, manageColumns);
		manageRoomTable = new JTable(manageRoomModel){

			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		manageRoomTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.getViewport().add(manageRoomTable);
		manageRoomTable.setFillsViewportHeight(true);
       
        
        manageButtonJpanel.add(inputRoomButton);
        manageButtonJpanel.add(deleteRoomButton);
        
        inputRoomButton.addActionListener(new ActionListener(){
	         public void actionPerformed(ActionEvent e) {
		         InputRoomButtonClicked();
	         }
        });
        deleteRoomButton.addActionListener(new ActionListener(){
	         public void actionPerformed(ActionEvent e) {
		          DeleteRoomButtonClicked();
	         }
       });
         
        serviceTypeJpanel.setLayout(null);
	    serviceTypeJpanel.add(exitButton);
	    exitButton.setBounds(600, 40, 70, 25);
	    exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				controller.ExitButtonClicked();
				
			}
	    	
	    });
	    serviceTypeJpanel.add(scrollPane);
	    scrollPane.setBounds(20,80,470,300);
	    serviceTypeJpanel.add(manageButtonJpanel);
	    manageButtonJpanel.setBounds(480,300,250,100);
	    
	    this.add(serviceTypeJpanel);
		serviceTypeJpanel.setBounds(0, 0, 800, 600);
	}
	

	public void InputRoomButtonClicked(){
		final JFrame inputFrame = new JFrame("录入可用客房");
		JPanel inputPanel = new JPanel();
		
		JLabel label2 = new JLabel("房间类型");
		Vector<String> categories = new Vector<String>();
		categories.add("大床房");
		categories.add("双人房");
		categories.add("三人间");
		final JComboBox box1 = new JComboBox(categories);
	    JLabel label = new JLabel("房间数量");
	    final JTextField field = new JTextField(10);
		
		JButton confirmButton = new JButton("确定");
		
		inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		inputPanel.add(label2);
		inputPanel.add(box1);
		inputPanel.add(label);
		inputPanel.add(field);
		
		inputPanel.add(confirmButton);
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(box1.getSelectedIndex()==0){
					po1.setAvdachuangfang(po1.getAvdachuangfang()+Integer.parseInt(field.getText()));
				}
				if(box1.getSelectedIndex()==1){
					po1.setAvshuangrenfang(po1.getAvshuangrenfang()+Integer.parseInt(field.getText()));
				}
				if(box1.getSelectedIndex()==2){
					po1.setAvsanrenjian(po1.getAvsanrenjian()+Integer.parseInt(field.getText()));
				}
				
				manageRoomModel = new DefaultTableModel(manageData, manageColumns);
				manageRoomTable = new JTable(manageRoomModel){

					public boolean isCellEditable(int row, int column){
						return false;
					}
				};
				
				serviceTypeJpanel.validate();
				inputFrame.dispose();
				if(controller.updateHotel(po1)){
					JOptionPane.showMessageDialog(null, "修改成功！","", JOptionPane.INFORMATION_MESSAGE);
				}
				controller.refresh();
			}
			
		});
		
		inputFrame.getContentPane().add(inputPanel);
		inputFrame.setBounds(400,400, 180, 200);
		inputFrame.setResizable(true);
		inputFrame.setVisible(true);
		
	}


	public void DeleteRoomButtonClicked(){
		final JFrame deleteFrame = new JFrame("删除可用客房");
		JPanel deletePanel = new JPanel();
		
		JLabel label2 = new JLabel("房间类型");
		Vector<String> categories = new Vector<String>();
		categories.add("大床房");
		categories.add("双人房");
		categories.add("三人间");
		final JComboBox box1 = new JComboBox(categories);
		JLabel label = new JLabel("房间数量");
		final JTextField field = new JTextField(10);
		
		JButton confirmButton = new JButton("确定");
		
		deletePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		deletePanel.add(label2);
		deletePanel.add(box1);
		deletePanel.add(label);
		deletePanel.add(field);
		
		deletePanel.add(confirmButton);
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(box1.getSelectedIndex()==0){
					po1.setAvdachuangfang(po1.getAvdachuangfang()-Integer.parseInt(field.getText()));
				}
				if(box1.getSelectedIndex()==1){
					po1.setAvshuangrenfang(po1.getAvshuangrenfang()-Integer.parseInt(field.getText()));
				}
				if(box1.getSelectedIndex()==2){
					po1.setAvsanrenjian(po1.getAvsanrenjian()-Integer.parseInt(field.getText()));
				}
				
				manageRoomModel = new DefaultTableModel(manageData, manageColumns);
				manageRoomTable = new JTable(manageRoomModel){

					public boolean isCellEditable(int row, int column){
						return false;
					}
				};
				
				serviceTypeJpanel.validate();
				
				if(controller.updateHotel(po1)){
					JOptionPane.showMessageDialog(null, "修改成功！","", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "修改失败！","", JOptionPane.INFORMATION_MESSAGE);
				}
				
				deleteFrame.dispose();
				controller.refresh();
			}
				
		});
		
		deleteFrame.getContentPane().add(deletePanel);
		deleteFrame.setBounds(400,400, 180, 200);
		deleteFrame.setResizable(false);
		deleteFrame.setVisible(true);
	}
	
}
	
