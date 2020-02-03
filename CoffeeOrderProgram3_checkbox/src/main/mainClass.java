package main;

import db.DBConnection;
import singleton.Singleton;
import view.MenuView;
import view.PaymentView;

public class mainClass {
	public static void main(String[] args) {
		DBConnection.initConnection();
		
		Singleton s = Singleton.getInstance();
		s.memCtrl.login();
		
		
	}
}
