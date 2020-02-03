package singleton;


import java.util.ArrayList;
import java.util.List;

import controller.MemberController;
import controller.OrderController;
import dto.MenuDto;
import dto.OrderDto;
import view.MenuView;

public class Singleton {
	private static Singleton s = null;
	
	public MemberController memCtrl = null;
	public OrderController orderCtrl = null;
	public MenuView mnView;
	private String loginId = null;
	private int seqNum;
	
	public List<OrderDto> list;

	
	private Singleton() {
		memCtrl = new MemberController();
		orderCtrl = new OrderController();
	}
	
	public void visible(List<MenuDto> list) {
		mnView = new MenuView(list);
	}
	
	public static Singleton getInstance() {
		if(s == null ) {
			s = new Singleton();
		}
		
		return s;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public int getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(int seqNum) {
		this.seqNum = seqNum;
	}
	
	
	

}
