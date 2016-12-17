package presentation.controller;

import presentation.hotelui.HotelStrategyView;
import presentation.hotelui.HotelStrategyViewController;
import presentation.hotelui.ManageHotelInfoView;
import presentation.hotelui.ManageHotelInfoViewController;
import presentation.hotelui.ManageOrderView;
import presentation.hotelui.ManageOrderViewController;
import presentation.hotelui.ManageRoomView;
import presentation.hotelui.ManageRoomViewController;
import presentation.hotelui.hotelmainframe;
import presentation.hotelui.mainframecontroller;
import presentation.login.login;
import presentation.login.logincontroller;
import presentation.manageui.ManageView;
import presentation.manageui.ManageViewControllerService;
import presentation.signup.signup;
import presentation.signup.signupcontroller;
import presentation.userui.user;
import presentation.userui.usercontroller;

import javax.swing.*;

/**
 * Created by huihantao on 2016/11/17.
 */
public class controller {
    private JPanel view;
    private logincontroller logcon;
    private signupcontroller signupcon;
    private usercontroller usercon;
    private JFrame frame;
    private ManageViewControllerService managecon;
    private mainframecontroller hotelcon;
    private ManageViewControllerService manageViewController;



    public controller(JFrame frame){
        this.frame=frame;
        this.login();

    }
    public void login(){
        frame.getContentPane().removeAll();
        frame.repaint();


        logcon=new logincontrollerimpl();
        this.view=new login(logcon);
        frame.getContentPane().add(this.view);
        frame.setVisible(true);
        logcon.setcon(this);
        logcon.setview((login) this.view);

    }


// decision
    public void loggedin(String id){
        if (id.substring(0).equals("0")){

            usercon=new usercontrollerimpl();
            frame.getContentPane().removeAll();
            frame.repaint();
            this.view=new user(usercon);
            usercon.setcon(this);
            frame.getContentPane().add(this.view);
            frame.setVisible(true);
            usercon.setView((user) this.view);
            return;
        }

        if (id.substring(0).equals("1")){

            hotelcon =new hotelcontroller("1");
            frame.getContentPane().removeAll();
            frame.repaint();
            this.view=new hotelmainframe(hotelcon);
            hotelcon.setcon(this);
            frame.getContentPane().add(this.view);
            frame.setVisible(true);
            return;
        }

        if (id.substring(0).equals("2")){

            manageViewController =new ManageViewControllerImpl();
            frame.getContentPane().removeAll();
            frame.repaint();
            this.view=new ManageView(manageViewController);
            manageViewController.setcon(this);
            frame.getContentPane().add(this.view);
            frame.setVisible(true);
            return;
        }







    }

    public void signup(){

        frame.getContentPane().removeAll();
        frame.repaint();


        signupcon=new signupcontrollerimpl();

        this.view=new signup(signupcon);
        frame.getContentPane().add(this.view);
        frame.setVisible(true);
        signupcon.setview((signup) this.view);

        signupcon.setcon(this);

    }
    
    public void ManageHotelInfo(){
    	frame.getContentPane().removeAll();
        frame.repaint();


        ManageHotelInfoViewController hotelController=new ManageHotelInfoViewControllerImpl(1);

        this.view=new ManageHotelInfoView(hotelController);
        frame.getContentPane().add(this.view);
        frame.setVisible(true);
        hotelController.setView((ManageHotelInfoView) this.view);

        hotelController.setcon(this);
    }
    public void ManageOrder(){
      	frame.getContentPane().removeAll();
        frame.repaint();


        ManageOrderViewController hotelController=new ManageOrderViewControllerImpl(1);

        this.view=new ManageOrderView(hotelController);
        frame.getContentPane().add(this.view);
        frame.setVisible(true);
        hotelController.setView((ManageOrderView) this.view);

        hotelController.setcon(this);
    }
    public void ManageRoom(){
    	frame.getContentPane().removeAll();
        frame.repaint();


        ManageRoomViewController hotelController=new ManageRoomViewControllerImpl(1);

        this.view=new ManageRoomView(hotelController);
        frame.getContentPane().add(this.view);
        frame.setVisible(true);
        hotelController.setView((ManageRoomView) this.view);

        hotelController.setcon(this);
    }
    public void HotelStrategy(){
    	frame.getContentPane().removeAll();
        frame.repaint();


        HotelStrategyViewController hotelController=new HotelStrategyViewControllerImpl(1);

        this.view=new HotelStrategyView(hotelController);
        frame.getContentPane().add(this.view);
        frame.setVisible(true);
        hotelController.setView((HotelStrategyView) this.view);

        hotelController.setcon(this);
    }


}